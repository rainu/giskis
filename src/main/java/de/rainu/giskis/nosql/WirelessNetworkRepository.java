package de.rainu.giskis.nosql;

import de.rainu.giskis.model.WirelessNetwork;
import java.math.BigInteger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WirelessNetworkRepository extends MongoRepository<WirelessNetwork, BigInteger> {

}
