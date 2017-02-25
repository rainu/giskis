package de.rainu.giskis.nosql;

import de.rainu.giskis.model.WirelessClient;
import de.rainu.giskis.model.WirelessNetwork;
import java.util.List;

public interface WirelessClientDao {

  WirelessClientRepository repo();

  /**
   * Gets a list of all {@link WirelessClient} for the given bssid, which are not the network itself.
   * ({@link WirelessClient#getMac()} not equals {@link WirelessNetwork#getBSSID()}
   *
   * @param bssid Ths bssid of the {@link WirelessNetwork}
   * @return A list of {@link WirelessClient}
   */
  List<WirelessClient> findRealClientsForNetworkBssid(String bssid);
}
