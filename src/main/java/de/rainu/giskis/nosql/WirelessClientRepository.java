package de.rainu.giskis.nosql;

import de.rainu.giskis.model.WirelessClient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface WirelessClientRepository extends MongoRepository<WirelessClient, BigInteger> {
}
