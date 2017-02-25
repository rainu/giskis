package de.rainu.giskis.nosql;

import de.rainu.giskis.model.WirelessClient;
import de.rainu.giskis.model.WirelessNetwork;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class WirelessClientDaoImpl implements WirelessClientDao, DatabaseConstants {

  @Autowired
  private  WirelessClientRepository repository;
  @Autowired
  private MongoTemplate mongo;

  @Override
  public WirelessClientRepository repo() {
    return repository;
  }

  @Override
  public List<WirelessClient> findRealClientsForNetworkBssid(String bssid) {
    final Query query = new Query();
    query.addCriteria(Criteria.where(WIRELESS_NETWORK_BSSID).is(bssid));
    query.fields().include(WIRELESS_CLIENT);

    final List<WirelessNetwork> networks = mongo.find(query, WirelessNetwork.class);
    final List<WirelessClient> clients = networks.stream()
        .flatMap(network -> network.getWirelessClients().stream())
        .filter(client -> !client.getMac().equals(bssid))
        .collect(Collectors.toList());

    return clients;
  }
}
