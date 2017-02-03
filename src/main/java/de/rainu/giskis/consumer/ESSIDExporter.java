package de.rainu.giskis.consumer;

import de.rainu.giskis.kml.KMLExporter;
import de.rainu.giskis.model.DetectionRun;
import de.rainu.giskis.sql.DetectionRunMerger;
import de.rainu.giskis.sql.WirelessNetworkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This {@link FileConsumer} is responsible for generating a merged kml file only with named wifis.
 */
@Component
public class ESSIDExporter implements FileConsumer {
	private final static Logger LOG = LoggerFactory.getLogger(ESSIDExporter.class);


	@Autowired
	private WirelessNetworkRepository networkRepository;

	@Autowired
	private DetectionRunMerger merger;

	private final File outputDir;

	public ESSIDExporter(
			  @Value("${OUTPUT_DIR}") String outputDir) {

		this.outputDir = new File(outputDir);
	}

	@Override
	public void accept(Path path) {
		try {
			final List<String> listEssid = Files.readAllLines(path);
			if(listEssid == null || listEssid.isEmpty()) {
				LOG.error("No essid given!");
				return;
			}

			for(String essid : listEssid) {
				final DetectionRun run = merger.buildDetectionRun(() -> networkRepository.findBestWirelessNetworkByESSID(essid));
				run.setKismetVersion(essid);

				final File targetFile = new File(outputDir, String.format("%s-%s.kml", essid, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)));
				LOG.info("Generate KML: " + targetFile);

				final KMLExporter kmlExporter = new KMLExporter(run);
				kmlExporter.generateKML().marshal(targetFile);
			}
		} catch (IOException e) {
			LOG.error("IO-Error: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean responsibleFor(Path path) {
		return path.getFileName().toString().equalsIgnoreCase("essid.kml");
	}
}
