package de.rainu.giskis;

import de.rainu.giskis.executer.KismetFileReceiver;
import de.rainu.giskis.kml.KMLExporter;
import de.rainu.giskis.model.DetectionRun;
import de.rainu.giskis.netxml.KismetParser;
import de.rainu.giskis.sql.DetectionRunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Path;

@SpringBootApplication
public class Main implements CommandLineRunner {
	private final static Logger LOG = LoggerFactory.getLogger(CommandLineRunner.class);

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Autowired
	private KismetParser kismetParser;

	@Autowired
	private KismetFileReceiver fileReceiver;

	@Autowired
	private DetectionRunRepository repository;

	@Value("${OUTPUT_DIR}")
	private String outputDir;

	@Override
	public void run(String... strings) throws Exception {
		fileReceiver.watch(path -> {
			try {
				LOG.info("Parse kismet file.");
				DetectionRun run = kismetParser.parseNetxml(path.toFile());

				if(repository.exists(run)){
					LOG.warn("Kismet file already stored in database!");
				} else {
					LOG.info("Store data into database.");
					repository.save(run);
				}

				final KMLExporter kmlExporter = new KMLExporter(run);
				final File targetKml = generateTargetKMLFile(path);
				LOG.info("Export KML file: " + targetKml);
				kmlExporter.generateKML().marshal(targetKml);
			} catch (Exception e) {
				LOG.error("Error on processing kismet file: " + path, e);
			}
		});
	}

	private File generateTargetKMLFile(Path path) {
		String fileName = path.getFileName().toString();
		fileName = fileName.substring(0, fileName.length() - ".netxml".length());
		fileName += ".kml";

		return new File(outputDir, fileName);
	}
}
