package de.rainu.giskis.sql;

import de.rainu.giskis.model.WirelessNetwork;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import static de.rainu.giskis.sql.DatabaseConstants.*;

/**
 * This {@link CrudRepository} is responsible for accessing {@link WirelessNetwork}
 */
public interface WirelessNetworkRepository extends CrudRepository<WirelessNetwork, BigInteger> {

	/**
	 * Get a list of {@link WirelessNetwork}-Ids group by the bssid. The {@link WirelessNetwork} of the
	 * strongest detected signal will be returned.
	 *
	 * @return A list of ids. For each id you can use {@link WirelessNetworkRepository#findOne(Serializable)}
	 */
	@Query(value = "SELECT wn." + WIRELESS_NETWORK_ID + " FROM " + WIRELESS_NETWORK + " AS wn " +
			  "WHERE wn." + WIRELESS_NETWORK_ID + " = (" +
			  "  SELECT wn_sub." + WIRELESS_NETWORK_ID +
			  "  FROM " + WIRELESS_NETWORK + " AS wn_sub" +
			  "  LEFT JOIN " + SNR_INFO + " snr ON wn_sub." + WIRELESS_NETWORK_SNR_INFO + " = snr." + SNR_INFO_ID +
			  "  JOIN " + GPS_INFO + " gps ON wn_sub." + WIRELESS_NETWORK_GPS_INFO + " = gps." + GPS_INFO_ID +
			  "  WHERE wn." + WIRELESS_NETWORK_BSSID + " = wn_sub." + WIRELESS_NETWORK_BSSID +
			  "  ORDER BY snr." + SNR_INFO_MAX_SIGNAL_DBM + " DESC, " +
			  "	gps." + GPS_INFO_PEAK_LON + " DESC, " +
			  "	gps." + GPS_INFO_AVERAGE_LON + " DESC, " +
			  "	gps." + GPS_INFO_MAX_LON + " DESC, " +
			  "	gps." + GPS_INFO_MIN_LON + " DESC " +
			  "  LIMIT 1" +
			  ")",
		nativeQuery = true)
	List<BigInteger> findBestWirelessNetworkIds();
}
