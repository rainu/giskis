package de.rainu.giskis.consumer;

import de.rainu.giskis.model.DetectionRun;
import de.rainu.giskis.model.WirelessNetwork;
import de.rainu.giskis.netxml.KismetParser;
import de.rainu.giskis.nosql.DetectionRunRepository;
import de.rainu.giskis.nosql.WirelessClientDao;
import de.rainu.giskis.nosql.WirelessNetworkDao;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This {@link FileConsumer} is responsible for saving the kismet files into the NoSQL-Database.
 */
@Component
public class DocumentSaver implements FileConsumer {
	private final static Logger LOG = LoggerFactory.getLogger(DocumentSaver.class);

	@Autowired
	private KismetParser kismetParser;

	@Autowired
	private DetectionRunRepository detectionRunRepository;

	@Autowired
	private WirelessNetworkDao wirelessNetworkDao;

	@Autowired
	private WirelessClientDao wirelessClientDao;

	@Override
	public void accept(Path path) {
		try {
			DetectionRun run = kismetParser.parse(path.toFile());

			if (detectionRunRepository.exists(run)) {
				LOG.warn("Kismet file already stored in database!");
			} else {
				LOG.info("Store data into database.");

				for(WirelessNetwork network : run.getWirelessNetworks()){
					network.setWirelessClients(wirelessClientDao.repo().save(network.getWirelessClients()));
				}

				run.setWirelessNetworks(wirelessNetworkDao.repo().save(run.getWirelessNetworks()));

				detectionRunRepository.save(run);
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
