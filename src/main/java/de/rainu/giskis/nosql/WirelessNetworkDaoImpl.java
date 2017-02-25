package de.rainu.giskis.nosql;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import de.rainu.giskis.model.WirelessNetwork;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class WirelessNetworkDaoImpl implements WirelessNetworkDao, DatabaseConstants {

  private static final String FIRST_KEY = "first";
  @Autowired
  private WirelessNetworkRepository repository;
  @Autowired
  private MongoTemplate mongo;

  @Override
  public WirelessNetworkRepository repo() {
    return repository;
  }

  @Override
  public List<String> findAllEssid() {
    return mongo.getCollection(WIRELESS_NETWORK).distinct(SSID + "." + ESSID + "." + ESSID_ESSID);
  }

  @Override
  public List<String> findAllBssid() {
    return mongo.getCollection(WIRELESS_NETWORK).distinct(WIRELESS_NETWORK_BSSID);
  }

  @Override
  public List<WirelessNetwork> findBestWirelessNetworks() {
    List<String> allBssid = findAllBssid();

    return allBssid.parallelStream()
        .map(this::findBestWirelessNetworkByBSSID)
        .collect(Collectors.toList());
  }

  @Override
  public WirelessNetwork findBestWirelessNetworkByBSSID(String bssid) {
      final MatchOperation matchOperation = match(Criteria.where(WIRELESS_NETWORK_BSSID).is(bssid));
      return findBestWirelessNetwork(matchOperation);
  }

  @Override
  public WirelessNetwork findBestWirelessNetworkByESSID(final String essid) {
    final MatchOperation matchOperation = match(
        Criteria.where(WIRELESS_NETWORK_SSID + "." + ESSID + "." + ESSID_ESSID).is(essid));

    return findBestWirelessNetwork(matchOperation);
  }

  private WirelessNetwork findBestWirelessNetwork(final MatchOperation matchOperation) {
    Aggregation agg = newAggregation(
        matchOperation,
        sort(DESC,
            SNR_INFO + "." + SNR_INFO_MAX_SIGNAL_DBM,
            GPS_INFO + "." + GPS_INFO_PEAK_LON,
            GPS_INFO + "." + GPS_INFO_AVERAGE_LON,
            GPS_INFO + "." + GPS_INFO_MAX_LON,
            GPS_INFO + "." + GPS_INFO_MIN_LON),
        group().first("$$ROOT").as(FIRST_KEY)
    );

    AggregationResults<Map> groupResults = mongo.aggregate(agg, WIRELESS_NETWORK, Map.class);
    List<Map> result = groupResults.getMappedResults();

    if (result.isEmpty()) {
      return null;
    }

    return (WirelessNetwork) result.get(0).get(FIRST_KEY);
  }
}
