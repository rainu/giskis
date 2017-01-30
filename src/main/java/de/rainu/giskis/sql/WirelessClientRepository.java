package de.rainu.giskis.sql;

import de.rainu.giskis.model.WirelessClient;
import de.rainu.giskis.model.WirelessNetwork;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import static de.rainu.giskis.sql.DatabaseConstants.*;

/**
 * This {@link CrudRepository} is responsible for accessing {@link WirelessClient}
 */
public interface WirelessClientRepository extends CrudRepository<WirelessClient, BigInteger> {

	/**
	 * Gets a list of all {@link WirelessClient}-Ids for the given bssid, which are not the network itself.
	 * ({@link WirelessClient#getMac()} not equals {@link WirelessNetwork#getBSSID()}
	 *
	 * @param bssid Ths bssid of the {@link WirelessNetwork}
	 * @return A list of ids. For each id you can use {@link WirelessClientRepository#findOne(Serializable)}
	 */
	@Query(value = "SELECT c." + WIRELESS_CLIENT_ID +
			  " FROM " + WIRELESS_CLIENT + " AS c " +
			  " JOIN " + WIRELESS_NETWORK + " n ON c." + WIRELESS_CLIENT_NETWORK_REF + " = n." + WIRELESS_NETWORK_ID +
			  " WHERE c." + WIRELESS_CLIENT_MAC + " <> ?1 AND n." + WIRELESS_NETWORK_BSSID + " = ?1",
			  nativeQuery = true)
	List<BigInteger> findRealClientIdsForNetworkBssid(String bssid);
}
