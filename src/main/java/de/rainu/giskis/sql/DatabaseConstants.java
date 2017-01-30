package de.rainu.giskis.sql;

/**
 * This interface contains all table- and column names from the used database.
 */
public interface DatabaseConstants {
	String DETECTION_RUN = "detection_run";
	String DETECTION_RUN_ID = "id";
	String DETECTION_RUN_VERSION = "version";
	String DETECTION_RUN_START = "start";
	String DETECTION_RUN_CARD_SOURCE = "card_source";

	String CARD_SOURCE = "card_source";
	String CARD_SOURCE_ID = "id";
	String CARD_SOURCE_UUID = "uuid";
	String CARD_SOURCE_SOURCE = "source";
	String CARD_SOURCE_NAME = "name";
	String CARD_SOURCE_INTERFACE = "interface";
	String CARD_SOURCE_TYPE = "type";
	String CARD_SOURCE_PACKETS = "packets";
	String CARD_SOURCE_HOP = "hop";
	String CARD_SOURCE_CHANNELS = "channels";

	String WIRELESS_NETWORK = "wireless_network";
	String WIRELESS_NETWORK_ID = "id";
	String WIRELESS_NETWORK_NUMBER = "number";
	String WIRELESS_NETWORK_TYPE = "type";
	String WIRELESS_NETWORK_FIRST_TIME = "first_time";
	String WIRELESS_NETWORK_LAST_TIME = "last_time";
	String WIRELESS_NETWORK_SSID = "ssid";
	String WIRELESS_NETWORK_BSSID = "bssid";
	String WIRELESS_NETWORK_MANUF = "manuf";
	String WIRELESS_NETWORK_CHANNEL = "channel";
	String WIRELESS_NETWORK_MAX_SEEN_RATE = "max_seen_rate";
	String WIRELESS_NETWORK_PACKETS = "packets";
	String WIRELESS_NETWORK_DATASIZE = "datasize";
	String WIRELESS_NETWORK_SNR_INFO = "snr_info";
	String WIRELESS_NETWORK_GPS_INFO = "gps_info";
	String WIRELESS_NETWORK_IP_ADDRESS = "ip_address";
	String WIRELESS_NETWORK_CDP_DEVICE = "cdp_device";
	String WIRELESS_NETWORK_CDP_PORT_ID = "cdp_port_id";
	String WIRELESS_NETWORK_DHCP_HOSTNAME = "dhcp_hostname";
	String WIRELESS_NETWORK_DHCP_VENDOR = "dhcp_vendor";
	String WIRELESS_NETWORK_SEEN_CARD = "seen_card";
	String WIRELESS_NETWORK_DETECTION_RUN = "detection_run_ref";


	String SSID = "ssid";
	String SSID_ID = "id";
	String SSID_FISRT_TIME = "first_time";
	String SSID_LAST_TIME = "last_time";
	String SSID_TYPE = "type";
	String SSID_MAX_RATE = "max_rate";
	String SSID_PACKETS = "packets";
	String SSID_BEACONRATE = "beaconrate";
	String SSID_WPS = "wps";
	String SSID_WPS_MANUF = "wps_manuf";
	String SSID_DEV_NAME = "dev_name";
	String SSID_MODEL_NAME = "model_name";
	String SSID_MODEL_NUMBER = "model_number";
	String SSID_WPA_VERSION = "wpa_version";
	String SSID_DOT11D = "dot11d";
	String SSID_SSID = "ssid";
	String SSID_INFO = "info";
	String SSID_ESSID = "essid_ref";

	String ESSID = "essid";
	String ESSID_ID = "id";
	String ESSID_ESSID = "essid";
	String ESSID_ESSID_CLOAKED = "essid_cloaked";

	String DOT11D = "dot11d";
	String DOT11D_ID = "id";
	String DOT11D_COUNTRY = "country";

	String DOT11D_RANGE = "dot11d_range";
	String DOT11D_RANGE_ID = "id";
	String DOT11D_RANGE_START = "range_start";
	String DOT11D_RANGE_END = "range_end";
	String DOT11D_RANGE_MAX_POWER = "max_power";
	String DOT11D_RANGE_DOT11D = "dot11d_ref";


	String PACKETS = "packets";
	String PACKETS_ID = "id";
	String PACKETS_LLC = "llc";
	String PACKETS_DATA = "data";
	String PACKETS_CRYPT = "crypt";
	String PACKETS_TOTAL = "total";
	String PACKETS_FRAGMENTS = "fragments";
	String PACKETS_RETRIES = "retries";

	String SNR_INFO = "snr_info";
	String SNR_INFO_ID = "id";
	String SNR_INFO_LAST_SIGNAL_DBM = "last_signal_dbm";
	String SNR_INFO_LAST_NOISE_DBM = "last_noise_dbm";
	String SNR_INFO_LAST_SIGNAL_RSSI = "last_signal_rssi";
	String SNR_INFO_LAST_NOISE_RSSI = "last_noise_rssi";
	String SNR_INFO_MIN_SIGNAL_DBM = "min_signal_dbm";
	String SNR_INFO_MIN_NOISE_DBM = "min_noise_dbm";
	String SNR_INFO_MIN_SIGNAL_RSSI = "min_signal_rssi";
	String SNR_INFO_MIN_NOISE_RSSI = "min_noise_rssi";
	String SNR_INFO_MAX_SIGNAL_DBM = "max_signal_dbm";
	String SNR_INFO_MAX_NOISE_DBM = "max_noise_dbm";
	String SNR_INFO_MAX_SIGNAL_RSSI = "max_signal_rssi";
	String SNR_INFO_MAX_NOISE_RSSI = "max_noise_rssi";

	String GPS_INFO = "gps_info";
	String GPS_INFO_ID = "id";
	String GPS_INFO_MIN_LAT = "min_lat";
	String GPS_INFO_MIN_LON = "min_lon";
	String GPS_INFO_MIN_ALT = "min_alt";
	String GPS_INFO_MIN_SPD = "min_spd";
	String GPS_INFO_MAX_LAT = "max_lat";
	String GPS_INFO_MAX_LON = "max_lon";
	String GPS_INFO_MAX_ALT = "max_alt";
	String GPS_INFO_MAX_SPD = "max_spd";
	String GPS_INFO_PEAK_LAT = "peak_lat";
	String GPS_INFO_PEAK_LON = "peak_lon";
	String GPS_INFO_PEAK_ALT = "peak_alt";
	String GPS_INFO_AVERAGE_LAT = "average_lat";
	String GPS_INFO_AVERAGE_LON = "average_lon";
	String GPS_INFO_AVERAGE_ALT = "average_alt";

	String IP_ADDRESS = "ip_address";
	String IP_ADDRESS_ID = "id";
	String IP_ADDRESS_TYPE = "type";
	String IP_ADDRESS_BLOCK = "block";
	String IP_ADDRESS_NETMASK = "netmask";
	String IP_ADDRESS_GATEWAY = "gateway";

	String SEEN_CARD = "seen_card";
	String SEEN_CARD_ID = "id";
	String SEEN_CARD_UUID = "uuid";
	String SEEN_CARD_TIME = "time";
	String SEEN_CARD_PACKETS = "packets";

	String TAG = "tags";
	String TAG_ID = "id";
	String TAG_VALUE = "value";
	String TAG_NAME = "name";
	String TAG_NETWORK_REF = "network_ref";
	String TAG_CLIENT_REF = "client_ref";

	String WIRELESS_CLIENT = "wireless_client";
	String WIRELESS_CLIENT_ID = "id";
	String WIRELESS_CLIENT_NUMBER = "number";
	String WIRELESS_CLIENT_TYPE = "type";
	String WIRELESS_CLIENT_FIRST_TIME = "first_time";
	String WIRELESS_CLIENT_LAST_TIME = "last_time";
	String WIRELESS_CLIENT_MAC = "mac";
	String WIRELESS_CLIENT_MANUF = "manuf";
	String WIRELESS_CLIENT_SSID = "ssid";
	String WIRELESS_CLIENT_CHANNEL = "channel";
	String WIRELESS_CLIENT_MAX_SEEN_RATE = "max_seen_rate";
	String WIRELESS_CLIENT_PACKETS = "packets";
	String WIRELESS_CLIENT_DATASIZE = "datasize";
	String WIRELESS_CLIENT_SNR_INFO = "snr_info";
	String WIRELESS_CLIENT_GPS_INFO = "gps_info";
	String WIRELESS_CLIENT_IP_ADDRESS = "ip_address";
	String WIRELESS_CLIENT_CDP_DEVICE = "cdp_device";
	String WIRELESS_CLIENT_CDP_PORT_ID = "cdp_port_id";
	String WIRELESS_CLIENT_DHCP_HOSTNAME = "dhcp_hostname";
	String WIRELESS_CLIENT_DHCP_VENDOR = "dhcp_vendor";
	String WIRELESS_CLIENT_SEEN_CARD = "seen_card";
	String WIRELESS_CLIENT_NETWORK_REF = "network_ref";
}
