package de.rainu.giskis.consumer;

import de.rainu.giskis.kml.KMLExporter;
import de.rainu.giskis.model.DetectionRun;
import de.rainu.giskis.netxml.KismetParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

@Component
public class KmlGenerator implements FileConsumer {
	private final static Logger LOG = LoggerFactory.getLogger(KmlGenerator.class);

	@Autowired
	private KismetParser kismetParser;

	private final File outputDir;

	public KmlGenerator(
			  @Value("${OUTPUT_DIR}") String outputDir) {

		this.outputDir = new File(outputDir);
	}

	@Override
	public void accept(Path path) {
		try {
			final DetectionRun run = kismetParser.parse(path.toFile());
			final KMLExporter kmlExporter = new KMLExporter(run);
			final File targetKml = generateTargetKMLFile(path);

			LOG.info("Export KML file: " + targetKml);
			kmlExporter.generateKML().marshal(targetKml);
		} catch (FileNotFoundException | JAXBException e) {
			LOG.error("Could not parse kismet file!", e);
		}
	}

	@Override
	public boolean responsibleFor(Path path) {
		return PathUtils.isKismetFile(path);
	}

	private File generateTargetKMLFile(Path path) {
		String fileName = path.getFileName().toString();
		fileName = fileName.substring(0, fileName.length() - ".netxml".length());
		fileName += ".kml";

		return new File(outputDir, fileName);
	}
}
