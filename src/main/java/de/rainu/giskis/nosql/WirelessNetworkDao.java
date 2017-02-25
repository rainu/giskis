package de.rainu.giskis.nosql;

import de.rainu.giskis.model.WirelessNetwork;

import java.util.List;

public interface WirelessNetworkDao {

	WirelessNetworkRepository repo();

	List<String> findAllEssid();

	List<String> findAllBssid();

	List<WirelessNetwork> findBestWirelessNetworks();

	WirelessNetwork findBestWirelessNetworkByESSID(String essid);

	WirelessNetwork findBestWirelessNetworkByBSSID(String bssid);
}
