package de.rainu.giskis.consumer;

import de.rainu.giskis.model.DetectionRun;
import de.rainu.giskis.netxml.KismetParser;
import de.rainu.giskis.sql.DetectionRunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.nio.file.Path;

/**
 * This {@link FileConsumer} is responsible for saving the kismet files into the database.
 */
@Component
public class DatabaseSaver implements FileConsumer {
	private final static Logger LOG = LoggerFactory.getLogger(DatabaseSaver.class);

	@Autowired
	private KismetParser kismetParser;

	@Autowired
	private DetectionRunRepository repository;

	@Override
	public void accept(Path path) {
		try {
			DetectionRun run = kismetParser.parse(path.toFile());

			if (repository.exists(run)) {
				LOG.warn("Kismet file already stored in database!");
			} else {
				LOG.info("Store data into database.");
				repository.save(run);
			}
		} catch (FileNotFoundException | JAXBException e) {
			LOG.error("Could not parse kismet file!", e);
		}
	}

	@Override
	public boolean responsibleFor(Path path) {
		return PathUtils.isKismetFile(path);
	}
}
