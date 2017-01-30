package de.rainu.giskis.kml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import de.micromata.opengis.kml.v_2_2_0.*;
import de.rainu.giskis.model.*;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static de.rainu.giskis.kml.SecurityLevel.*;

/**
 * This class is responsible for KML-Exporting based on a {@link DetectionRun}.
 */
public class KMLExporter {
	private final DetectionRun run;
	private final ObjectMapper mapper;

	private Kml kml;
	private Document document;
	private Map<String, Style> styleMap = new HashMap<>();
	private Style styleClient;
	private Folder openNetworkFolder;
	private Folder insecureNetworkFolder;
	private Folder wpsNetworkFolder;
	private Folder secureNetworkFolder;

	public KMLExporter(DetectionRun run) {
		this.run = run;

		mapper = new ObjectMapper();
		mapper.registerModule(new JSR310Module());
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	public Kml generateKML(){
		kml = new Kml();

		buildDocument();
		buildStyles();
		buildFolders();
		fillFolders();

		return kml;
	}

	private void buildDocument() {
		document = kml.createAndSetDocument();
		document.setName(getDocumentName());
	}

	private void buildStyles() {
		styleClient = document.createAndAddStyle()
				  .withId("client");
		styleClient.createAndSetIconStyle()
				  .withColor("00000000")
				  .withScale(1.0d)
				  .withColorMode(ColorMode.NORMAL)
				  .withIcon(
							 KmlFactory.createIcon().withHref("http://maps.google.com/mapfiles/kml/pushpin/wht-pushpin.png"));
	}

	private void buildFolders() {
		openNetworkFolder = document.createAndAddFolder()
				  .withName("Open");
		insecureNetworkFolder = document.createAndAddFolder()
				  .withName("Insecure");
		wpsNetworkFolder = document.createAndAddFolder()
				  .withName("WPS");
		secureNetworkFolder = document.createAndAddFolder()
				  .withName("Secure");
	}

	private void fillFolders() {
		for(WirelessNetwork network : run.getWirelessNetworks()) {
			if(network.getSSID().isEmpty()) {
				continue;
			}

			Folder parentFolder = secureNetworkFolder;
			Style style = getStyle(SECURE);

			if(network.getSSID().getEncryption().stream().filter(e -> e.equals("None")).count() > 0){
				parentFolder = openNetworkFolder;
				style = getStyle(OPEN);
			}else if(network.getSSID().getEncryption().stream().filter(e -> e.contains("WEP")).count() > 0){
				parentFolder = insecureNetworkFolder;
				style = getStyle(INSECURE);
			}else if("configured".equalsIgnoreCase(network.getSSID().getWps())) {
				parentFolder = wpsNetworkFolder;
				style = getStyle(WPS);
			}

			Folder childFolder = parentFolder.createAndAddFolder().withName(getNetworkName(network));
			buildPlacemark(childFolder, style, network);

			for(WirelessClient client : network.getWirelessClients()) {
				if(network.getBSSID().equals(client.getMac())) {
					//no real client :( - let's skip this
					continue;
				}

				buildPlacemark(childFolder, styleClient, client);
			}

			//remove empty folders
			if(childFolder.getFeature() == null || childFolder.getFeature().isEmpty()){
				parentFolder.getFeature().remove(childFolder);
			} else {
				if(childFolder.getFeature().size() > 1) {
					childFolder.setName("[" + (childFolder.getFeature().size() - 1) + "] " + childFolder.getName());
				}
			}
		}
	}

	private void buildPlacemark(Folder folder, Style style, WirelessNetwork network) {
		final GeoPoint point = getBestPoint(network.getGPSInfo());
		if(point == null || point.isEmpty() || point.isInvalid()) {
			return;
		}

		Placemark placemark = folder.createAndAddPlacemark()
				  .withName(getNetworkName(network))
				  .withStyleSelector(Arrays.asList(style))
				  .withDescription(getDescription(network));

		placemark.createAndSetPoint()
				  .addToCoordinates(
							 point.getLon(),
							 point.getLat(),
							 point.getAlt());
	}

	private String getDescription(WirelessNetwork network) {
		try {
			return String.format("BSSID: %s\nJSON:\n\n%s",
					  network.getBSSID(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(network)
			);
		} catch (Exception e) {
		}
		return null;
	}

	private String getNetworkName(WirelessNetwork network) {
		if(Boolean.TRUE.equals(network.getSSID().getESSID().getCloaked())) {
			return "cloaked - " + network.getBSSID();
		}

		return network.getSSID().getESSID().getEssid();
	}

	private void buildPlacemark(Folder folder, Style style, WirelessClient client) {
		final GeoPoint cp = getBestPoint(client.getGPSInfo());
		if (cp == null || cp.isEmpty() || cp.isInvalid()) {
			return;
		}
		final Coordinate clientCoord = KmlFactory.createCoordinate(cp.getLon(), cp.getLat(), cp.getAlt());

		Placemark placemark = folder.createAndAddPlacemark()
				  .withStyleSelector(Arrays.asList(style))
				  .withName("" + client.getNumber())
				  .withDescription(getDescription(client))
				  .withVisibility(false);

		placemark.createAndSetPoint().setCoordinates(Arrays.asList(clientCoord));
	}

	private String getDescription(WirelessClient client) {
		try {
			return String.format("MAC: %s\nJSON:\n\n%s",
				client.getMac(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(client)
			);
		} catch (Exception e) {
		}
		return null;
	}

	private GeoPoint getBestPoint(GPSInfo gpsInfo) {
		if(!gpsInfo.getPeak().isEmpty() && !gpsInfo.getPeak().isInvalid()) {
			return gpsInfo.getPeak();
		}

		if(!gpsInfo.getAverage().isEmpty() && !gpsInfo.getAverage().isInvalid()) {
			return gpsInfo.getAverage();
		}

		if(!gpsInfo.getMax().isEmpty() && !gpsInfo.getMax().isInvalid()) {
			return gpsInfo.getMax();
		}

		if(!gpsInfo.getMin().isEmpty() && !gpsInfo.getMin().isInvalid()) {
			return gpsInfo.getMin();
		}

		return null;
	}

	private Style getStyle(SecurityLevel level) {
		if(!styleMap.containsKey(level.name())){
			Style style = document.createAndAddStyle()
					  .withId(level.name());
			style.createAndSetIconStyle()
					  .withColor("ffffffff")
					  .withScale(1.0d)
					  .withColorMode(ColorMode.NORMAL)
					  .withIcon(KmlFactory.createIcon().withHref(level.getIconHref()));

			styleMap.put(level.name(), style);
		}

		return styleMap.get(level.name());
	}

	private String getDocumentName() {
		return String.format("%s(%s)",
				  run.getTime().format(DateTimeFormatter.ISO_DATE_TIME),
				  run.getKismetVersion());
	}
}
