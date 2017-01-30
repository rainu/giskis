package de.rainu.giskis.consumer;

import de.rainu.giskis.kml.KMLExporter;
import de.rainu.giskis.model.DetectionRun;
import de.rainu.giskis.sql.DetectionRunMerger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This {@link FileConsumer} is responsible for generating a KML file based on all data inside the database.
 */
@Component
public class KismetMerge implements FileConsumer {
	private final static Logger LOG = LoggerFactory.getLogger(KismetMerge.class);

	@Autowired
	private DetectionRunMerger merger;

	private final File outputDir;

	public KismetMerge(
			  @Value("${OUTPUT_DIR}") String outputDir) {

		this.outputDir = new File(outputDir);
	}

	@Override
	public void accept(Path path) {
		LOG.info("Read data from database.");
		DetectionRun run = merger.buildFullDetectionRun();

		final File targetFile = new File(outputDir, String.format("%s.kml", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)));
		LOG.info("Generate KML: " + targetFile);

		try {
			final KMLExporter kmlExporter = new KMLExporter(run);
			kmlExporter.generateKML().marshal(targetFile);
		} catch (FileNotFoundException e) {
			//this should not be happen
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean responsibleFor(Path path) {
		return path.getFileName().toString().equalsIgnoreCase("merge.kml");
	}
}
