package sql

import (
	"giskis/netxml"
	"fmt"
	"strings"
	"reflect"
)

const DETECTION_RUN = "detection_run"
const DETECTION_RUN_ID = "id"
const DETECTION_RUN_VERSION = "version"
const DETECTION_RUN_START = "start"
const DETECTION_RUN_CARD_SOURCE = "card_source"

const CARD_SOURCE = "card_source"
const CARD_SOURCE_ID = "id"
const CARD_SOURCE_UUID = "uuid"
const CARD_SOURCE_SOURCE = "source"
const CARD_SOURCE_NAME = "name"
const CARD_SOURCE_INTERFACE = "interface"
const CARD_SOURCE_TYPE = "type"
const CARD_SOURCE_PACKETS = "packets"
const CARD_SOURCE_HOP = "hop"
const CARD_SOURCE_CHANNELS = "channels"

const WIRELESS_NETWORK = "wireless_network"
const WIRELESS_NETWORK_ID = "id"
const WIRELESS_NETWORK_NUMBER = "number"
const WIRELESS_NETWORK_TYPE = "type"
const WIRELESS_NETWORK_FIRST_TIME = "first_time"
const WIRELESS_NETWORK_LAST_TIME = "last_time"
const WIRELESS_NETWORK_SSID = "ssid"
const WIRELESS_NETWORK_BSSID = "bssid"
const WIRELESS_NETWORK_MANUF = "manuf"
const WIRELESS_NETWORK_CHANNEL = "channel"
const WIRELESS_NETWORK_FREQ_MHZ = "freq"
const WIRELESS_NETWORK_MAX_SEEN_RATE = "max_seen_rate"
const WIRELESS_NETWORK_PACKETS = "packets"
const WIRELESS_NETWORK_DATASIZE = "datasize"
const WIRELESS_NETWORK_SNR_INFO = "snr_info"
const WIRELESS_NETWORK_GPS_INFO = "gps_info"
const WIRELESS_NETWORK_IP_ADDRESS = "ip_address"
const WIRELESS_NETWORK_CDP_DEVICE = "cdp_device"
const WIRELESS_NETWORK_CDP_PORT_ID = "cdp_port_id"
const WIRELESS_NETWORK_DHCP_HOSTNAME = "dhcp_hostname"
const WIRELESS_NETWORK_DHCP_VENDOR = "dhcp_vendor"
const WIRELESS_NETWORK_SEEN_CARD = "seen_card"
const WIRELESS_NETWORK_DETECTION_RUN = "detection_run_ref"

const CARRIER = "carrier"
const CARRIER_ID = "id"
const CARRIER_VALUE = "value"
const CARRIER_NETWORK_REF = "wireless_network_ref"
const CARRIER_CLIENT_REF = "wireless_client_ref"

const ENCODING = "encoding"
const ENCODING_ID = "id"
const ENCODING_VALUE = "value"
const ENCODING_NETWORK_REF = "wireless_network_ref"
const ENCODING_CLIENT_REF = "wireless_client_ref"

const SSID = "ssid"
const SSID_ID = "id"
const SSID_FISRT_TIME = "first_time"
const SSID_LAST_TIME = "last_time"
const SSID_TYPE = "type"
const SSID_MAX_RATE = "max_rate"
const SSID_PACKETS = "packets"
const SSID_BEACONRATE = "beaconrate"
const SSID_WPS = "wps"
const SSID_WPS_MANUF = "wps_manuf"
const SSID_DEV_NAME = "dev_name"
const SSID_MODEL_NAME = "model_name"
const SSID_MODEL_NUMBER = "model_number"
const SSID_WPA_VERSION = "wpa_version"
const SSID_ENCRYPTION = "encryption"
const SSID_DOT11D = "dot11d"
const SSID_SSID = "ssid"
const SSID_INFO = "info"
const SSID_ESSID = "essid"
const SSID_ESSID_CLOAKED = "essid_cloaked"

const DOT11D = "dot11d"
const DOT11D_ID = "id"
const DOT11D_COUNTRY = "country"

const DOT11D_RANGE = "dot11d_range"
const DOT11D_RANGE_ID = "id"
const DOT11D_RANGE_START = "range_start"
const DOT11D_RANGE_END = "range_end"
const DOT11D_RANGE_MAX_POWER = "max_power"
const DOT11D_RANGE_DOT11D = "dot11d_ref"


const PACKETS = "packets"
const PACKETS_ID = "id"
const PACKETS_LLC = "llc"
const PACKETS_DATA = "data"
const PACKETS_CRYPT = "crypt"
const PACKETS_TOTAL = "total"
const PACKETS_FRAGMENTS = "fragments"
const PACKETS_RETRIES = "retries"

const SNR_INFO = "snr_info"
const SNR_INFO_ID = "id"
const SNR_INFO_LAST_SIGNAL_DBM = "last_signal_dbm"
const SNR_INFO_LAST_NOISE_DBM = "last_noise_dbm"
const SNR_INFO_LAST_SIGNAL_RSSI = "last_signal_rssi"
const SNR_INFO_LAST_NOISE_RSSI = "last_noise_rssi"
const SNR_INFO_MIN_SIGNAL_DBM = "min_signal_dbm"
const SNR_INFO_MIN_NOISE_DBM = "min_noise_dbm"
const SNR_INFO_MIN_SIGNAL_RSSI = "min_signal_rssi"
const SNR_INFO_MIN_NOISE_RSSI = "min_noise_rssi"
const SNR_INFO_MAX_SIGNAL_DBM = "max_signal_dbm"
const SNR_INFO_MAX_NOISE_DBM = "max_noise_dbm"
const SNR_INFO_MAX_SIGNAL_RSSI = "max_signal_rssi"
const SNR_INFO_MAX_NOISE_RSSI = "max_noise_rssi"

const GIS_POINT = "gis_point"
const GIS_POINT_ID = "id"
const GIS_POINT_LON = "longitude"
const GIS_POINT_LAT = "latitude"
const GIS_POINT_ALT = "altitude"

const GPS_INFO = "gps_info"
const GPS_INFO_ID = "id"
const GPS_INFO_MIN = "min_ref"
const GPS_INFO_MIN_SPD = "min_spd"
const GPS_INFO_MAX = "max_ref"
const GPS_INFO_MAX_SPD = "max_spd"
const GPS_INFO_PEAK = "peak_ref"
const GPS_INFO_AVERAGE = "average_ref"

const IP_ADDRESS = "ip_address"
const IP_ADDRESS_ID = "id"
const IP_ADDRESS_TYPE = "type"
const IP_ADDRESS_BLOCK = "block"
const IP_ADDRESS_NETMASK = "netmask"
const IP_ADDRESS_GATEWAY = "gateway"

const SEEN_CARD = "seen_card"
const SEEN_CARD_ID = "id"
const SEEN_CARD_UUID = "uuid"
const SEEN_CARD_TIME = "time"
const SEEN_CARD_PACKETS = "packets"

const TAG = "tags"
const TAG_ID = "id"
const TAG_VALUE = "value"
const TAG_NAME = "name"
const TAG_NETWORK_REF = "network_ref"
const TAG_CLIENT_REF = "client_ref"

const WIRELESS_CLIENT = "wireless_client"
const WIRELESS_CLIENT_ID = "id"
const WIRELESS_CLIENT_NUMBER = "number"
const WIRELESS_CLIENT_TYPE = "type"
const WIRELESS_CLIENT_FIRST_TIME = "first_time"
const WIRELESS_CLIENT_LAST_TIME = "last_time"
const WIRELESS_CLIENT_MAC = "mac"
const WIRELESS_CLIENT_MANUF = "manuf"
const WIRELESS_CLIENT_SSID = "ssid"
const WIRELESS_CLIENT_CHANNEL = "channel"
const WIRELESS_CLIENT_FREQ_MHZ = "freq"
const WIRELESS_CLIENT_MAX_SEEN_RATE = "max_seen_rate"
const WIRELESS_CLIENT_PACKETS = "packets"
const WIRELESS_CLIENT_DATASIZE = "datasize"
const WIRELESS_CLIENT_SNR_INFO = "snr_info"
const WIRELESS_CLIENT_GPS_INFO = "gps_info"
const WIRELESS_CLIENT_IP_ADDRESS = "ip_address"
const WIRELESS_CLIENT_CDP_DEVICE = "cdp_device"
const WIRELESS_CLIENT_CDP_PORT_ID = "cdp_port_id"
const WIRELESS_CLIENT_DHCP_HOSTNAME = "dhcp_hostname"
const WIRELESS_CLIENT_DHCP_VENDOR = "dhcp_vendor"
const WIRELESS_CLIENT_SEEN_CARD = "seen_card"
const WIRELESS_CLIENT_NETWORK_REF = "network_ref"

const initDatabaseScript = `
CREATE TABLE ` + CARD_SOURCE + `(
	` + CARD_SOURCE_ID + ` SERIAL PRIMARY KEY,
	` + CARD_SOURCE_UUID + ` CHARACTER(36),
	` + CARD_SOURCE_SOURCE + ` VARCHAR(127),
	` + CARD_SOURCE_NAME + ` VARCHAR(127),
	` + CARD_SOURCE_INTERFACE + ` VARCHAR(127),
	` + CARD_SOURCE_TYPE + ` VARCHAR(127),
	` + CARD_SOURCE_PACKETS + ` INT,
	` + CARD_SOURCE_HOP + ` BOOLEAN,
	` + CARD_SOURCE_CHANNELS + ` VARCHAR(127)
);

CREATE TABLE ` + DOT11D + `(
	` + DOT11D_ID + ` SERIAL PRIMARY KEY,
	` + DOT11D_COUNTRY + ` VARCHAR(127)
);

CREATE TABLE ` + DOT11D_RANGE + `(
	` + DOT11D_RANGE_ID + ` SERIAL PRIMARY KEY,
	` + DOT11D_RANGE_START + ` INT NOT NULL,
	` + DOT11D_RANGE_END + ` INT NOT NULL,
	` + DOT11D_RANGE_MAX_POWER + ` INT NOT NULL,
	` + DOT11D_RANGE_DOT11D + ` INT REFERENCES ` + DOT11D + `(` + DOT11D_ID + `) NOT NULL
);

CREATE TABLE ` + SSID + `(
	` + SSID_ID + ` SERIAL PRIMARY KEY,
	` + SSID_FISRT_TIME + ` TIMESTAMP NOT NULL,
	` + SSID_LAST_TIME + ` TIMESTAMP NOT NULL,
	` + SSID_TYPE + `  VARCHAR(127),
	` + SSID_MAX_RATE + ` REAL,
	` + SSID_PACKETS + ` INT,
	` + SSID_BEACONRATE + ` INT,
	` + SSID_WPS + ` VARCHAR(127),
	` + SSID_WPS_MANUF + ` VARCHAR(127),
	` + SSID_DEV_NAME + ` VARCHAR(127),
	` + SSID_MODEL_NAME + ` VARCHAR(127),
	` + SSID_MODEL_NUMBER + ` VARCHAR(127),
	` + SSID_WPA_VERSION + ` VARCHAR(127),
	` + SSID_ENCRYPTION + ` VARCHAR(127),
	` + SSID_DOT11D + ` INT REFERENCES ` + DOT11D + `(` + DOT11D_ID + `),
	` + SSID_SSID + ` VARCHAR(127),
	` + SSID_INFO + ` VARCHAR(127),
	` + SSID_ESSID + ` VARCHAR(127),
	` + SSID_ESSID_CLOAKED + ` BOOLEAN
);

CREATE TABLE ` + PACKETS + `(
	` + PACKETS_ID + ` SERIAL PRIMARY KEY,
	` + PACKETS_LLC + ` INT NOT NULL,
	` + PACKETS_DATA + ` INT NOT NULL,
	` + PACKETS_CRYPT + ` INT NOT NULL,
	` + PACKETS_TOTAL + ` INT NOT NULL,
	` + PACKETS_FRAGMENTS + ` INT NOT NULL,
	` + PACKETS_RETRIES + ` INT NOT NULL
);

CREATE TABLE ` + SNR_INFO + `(
	` + SNR_INFO_ID + ` SERIAL PRIMARY KEY,
	` + SNR_INFO_LAST_SIGNAL_DBM  + ` INT NOT NULL,
	` + SNR_INFO_LAST_NOISE_DBM + ` INT NOT NULL,
	` + SNR_INFO_LAST_SIGNAL_RSSI + ` INT NOT NULL,
	` + SNR_INFO_LAST_NOISE_RSSI + ` INT NOT NULL,
	` + SNR_INFO_MIN_SIGNAL_DBM  + ` INT NOT NULL,
	` + SNR_INFO_MIN_NOISE_DBM + ` INT NOT NULL,
	` + SNR_INFO_MIN_SIGNAL_RSSI + ` INT NOT NULL,
	` + SNR_INFO_MIN_NOISE_RSSI + ` INT NOT NULL,
	` + SNR_INFO_MAX_SIGNAL_DBM  + ` INT NOT NULL,
	` + SNR_INFO_MAX_NOISE_DBM + ` INT NOT NULL,
	` + SNR_INFO_MAX_SIGNAL_RSSI + ` INT NOT NULL,
	` + SNR_INFO_MAX_NOISE_RSSI + ` INT NOT NULL
);

CREATE TABLE ` + GIS_POINT + `(
	` + GIS_POINT_ID + ` SERIAL PRIMARY KEY,
	` + GIS_POINT_LON + ` DECIMAL(9,6) NOT NULL,
	` + GIS_POINT_LAT + ` DECIMAL(9,6) NOT NULL,
	` + GIS_POINT_ALT + ` DECIMAL(9,6)
);

CREATE TABLE ` + GPS_INFO + `(
	` + GPS_INFO_ID + ` SERIAL PRIMARY KEY,
	` + GPS_INFO_MIN + ` INT REFERENCES ` + GIS_POINT + `(` + GIS_POINT_ID + `),
	` + GPS_INFO_MIN_SPD + ` REAL NOT NULL,
	` + GPS_INFO_MAX + ` INT REFERENCES ` + GIS_POINT + `(` + GIS_POINT_ID + `),
	` + GPS_INFO_MAX_SPD + ` REAL NOT NULL,
	` + GPS_INFO_PEAK + ` INT REFERENCES ` + GIS_POINT + `(` + GIS_POINT_ID + `),
	` + GPS_INFO_AVERAGE + ` INT REFERENCES ` + GIS_POINT + `(` + GIS_POINT_ID + `)
);

CREATE TABLE ` + IP_ADDRESS + `(
	` + IP_ADDRESS_ID + ` SERIAL PRIMARY KEY,
	` + IP_ADDRESS_TYPE + ` VARCHAR(127) NOT NULL,
	` + IP_ADDRESS_BLOCK + ` VARCHAR(127) NOT NULL,
	` + IP_ADDRESS_NETMASK + ` VARCHAR(127) NOT NULL,
	` + IP_ADDRESS_GATEWAY + ` VARCHAR(127) NOT NULL
);

CREATE TABLE ` + SEEN_CARD + `(
	` + SEEN_CARD_ID + ` SERIAL PRIMARY KEY,
	` + SEEN_CARD_UUID + ` CHARACTER(36) NOT NULL,
	` + SEEN_CARD_TIME + ` TIMESTAMP NOT NULL,
	` + SEEN_CARD_PACKETS + ` INT NOT NULL
);

CREATE TABLE ` + DETECTION_RUN + `(
	` + DETECTION_RUN_ID + ` SERIAL PRIMARY KEY,
	` + DETECTION_RUN_VERSION + ` VARCHAR(127) NOT NULL,
	` + DETECTION_RUN_START + ` TIMESTAMP NOT NULL,
	` + DETECTION_RUN_CARD_SOURCE + ` INT REFERENCES ` + CARD_SOURCE + `(` + CARD_SOURCE_ID + `)
);

CREATE TABLE ` + WIRELESS_NETWORK + `(
	` + WIRELESS_NETWORK_ID + ` SERIAL PRIMARY KEY,
	` + WIRELESS_NETWORK_NUMBER + ` INT NOT NULL,
	` + WIRELESS_NETWORK_TYPE + ` VARCHAR(127),
	` + WIRELESS_NETWORK_FIRST_TIME + ` TIMESTAMP NOT NULL,
	` + WIRELESS_NETWORK_LAST_TIME + ` TIMESTAMP NOT NULL,
	` + WIRELESS_NETWORK_SSID + ` INT REFERENCES ` + SSID + `(` + SSID_ID + `),
	` + WIRELESS_NETWORK_BSSID + ` CHARACTER(17), 
	` + WIRELESS_NETWORK_MANUF + ` VARCHAR(127),
	` + WIRELESS_NETWORK_CHANNEL + ` SMALLINT NOT NULL,
	` + WIRELESS_NETWORK_FREQ_MHZ + ` VARCHAR(127),
	` + WIRELESS_NETWORK_MAX_SEEN_RATE + ` BIGINT,
	` + WIRELESS_NETWORK_PACKETS + ` INT REFERENCES ` + PACKETS + `(` + PACKETS_ID + `),
	` + WIRELESS_NETWORK_DATASIZE + ` BIGINT,
	` + WIRELESS_NETWORK_SNR_INFO + ` INT REFERENCES ` + SNR_INFO + `(` + SNR_INFO_ID + `),
	` + WIRELESS_NETWORK_GPS_INFO + ` INT REFERENCES ` + GPS_INFO + `(` + GPS_INFO_ID + `),
	` + WIRELESS_NETWORK_IP_ADDRESS + ` INT REFERENCES ` + IP_ADDRESS + `(` + IP_ADDRESS_ID + `),
	` + WIRELESS_NETWORK_CDP_DEVICE + ` VARCHAR(127),
	` + WIRELESS_NETWORK_CDP_PORT_ID + ` VARCHAR(127),
	` + WIRELESS_NETWORK_DHCP_HOSTNAME + ` VARCHAR(127),
	` + WIRELESS_NETWORK_DHCP_VENDOR + ` VARCHAR(127),
	` + WIRELESS_NETWORK_SEEN_CARD + ` INT REFERENCES ` + SEEN_CARD + `(` + SEEN_CARD_ID + `),
	` + WIRELESS_NETWORK_DETECTION_RUN + ` INT REFERENCES ` + DETECTION_RUN + `(` + DETECTION_RUN_ID + `)
);

CREATE TABLE ` + WIRELESS_CLIENT + `(
	` + WIRELESS_CLIENT_ID + ` SERIAL PRIMARY KEY,
	` + WIRELESS_CLIENT_NUMBER + ` INT NOT NULL,
	` + WIRELESS_CLIENT_TYPE + ` VARCHAR(127),
	` + WIRELESS_CLIENT_FIRST_TIME + ` TIMESTAMP NOT NULL,
	` + WIRELESS_CLIENT_LAST_TIME + ` TIMESTAMP NOT NULL,
	` + WIRELESS_CLIENT_SSID + ` INT REFERENCES ` + SSID + `(` + SSID_ID + `),
	` + WIRELESS_CLIENT_MAC + ` CHARACTER(17),
	` + WIRELESS_CLIENT_MANUF + ` VARCHAR(127),
	` + WIRELESS_CLIENT_CHANNEL + ` SMALLINT NOT NULL,
	` + WIRELESS_CLIENT_FREQ_MHZ + ` VARCHAR(127),
	` + WIRELESS_CLIENT_MAX_SEEN_RATE + ` BIGINT,
	` + WIRELESS_CLIENT_PACKETS + ` INT REFERENCES ` + PACKETS + `(` + PACKETS_ID + `),
	` + WIRELESS_CLIENT_DATASIZE + ` BIGINT,
	` + WIRELESS_CLIENT_SNR_INFO + ` INT REFERENCES ` + SNR_INFO + `(` + SNR_INFO_ID + `),
	` + WIRELESS_CLIENT_GPS_INFO + ` INT REFERENCES ` + GPS_INFO + `(` + GPS_INFO_ID + `),
	` + WIRELESS_CLIENT_IP_ADDRESS + ` INT REFERENCES ` + IP_ADDRESS + `(` + IP_ADDRESS_ID + `),
	` + WIRELESS_CLIENT_CDP_DEVICE + ` VARCHAR(127),
	` + WIRELESS_CLIENT_CDP_PORT_ID + ` VARCHAR(127),
	` + WIRELESS_CLIENT_DHCP_HOSTNAME + ` VARCHAR(127),
	` + WIRELESS_CLIENT_DHCP_VENDOR + ` VARCHAR(127),
	` + WIRELESS_CLIENT_SEEN_CARD + ` INT REFERENCES ` + SEEN_CARD + `(` + SEEN_CARD_ID + `),
	` + WIRELESS_CLIENT_NETWORK_REF + ` INT REFERENCES ` + WIRELESS_NETWORK + `(` + WIRELESS_NETWORK_ID + `)
);

CREATE TABLE ` + TAG + `(
	` + TAG_ID + ` SERIAL PRIMARY KEY,
	` + TAG_NAME + ` VARCHAR(127) NOT NULL,
	` + TAG_VALUE + ` VARCHAR(127) NOT NULL,
	` + TAG_NETWORK_REF + ` INT REFERENCES ` + WIRELESS_NETWORK + `(` + WIRELESS_NETWORK_ID + `),
	` + TAG_CLIENT_REF + ` INT REFERENCES ` + WIRELESS_CLIENT + `(` + WIRELESS_CLIENT_ID + `)
);

CREATE TABLE ` + CARRIER + `(
	` + CARRIER_ID + ` SERIAL PRIMARY KEY,
	` + CARRIER_VALUE + ` VARCHAR(127) NOT NULL,
	` + CARRIER_NETWORK_REF + ` INT REFERENCES ` + WIRELESS_NETWORK + `(` + WIRELESS_NETWORK_ID + `),
	` + CARRIER_CLIENT_REF + ` INT REFERENCES ` + WIRELESS_CLIENT + `(` + WIRELESS_CLIENT_ID + `)
);

CREATE TABLE ` + ENCODING + `(
	` + ENCODING_ID + ` SERIAL PRIMARY KEY,
	` + ENCODING_VALUE + ` VARCHAR(127) NOT NULL,
	` + ENCODING_NETWORK_REF + ` INT REFERENCES ` + WIRELESS_NETWORK + `(` + WIRELESS_NETWORK_ID + `),
	` + ENCODING_CLIENT_REF + ` INT REFERENCES ` + WIRELESS_CLIENT + `(` + WIRELESS_CLIENT_ID + `)
);
`

const PostgresTimeFormat = "2006-01-02 15:04:05" //"Mon Jan 2 15:04:05 -0700 MST 2006"

type PostgresImporter struct {}

func NewPostgresImporter() *PostgresImporter {
	return &PostgresImporter{}
}

func (pi *PostgresImporter) GenerateInitScript() string {
	return initDatabaseScript
}

func (pi *PostgresImporter) GenerateInserts(run *netxml.DetectionRun) []string {
	inserts := []string{
		pi.generateCardSourceInsert(&run.CardSource),
		pi.generateDetectionRunInsert(run),
	}

	for _, network := range run.WirelessNetwork {
		inserts = append(inserts, pi.generateDot11dInsert(&network.SSID.Dot11d))
		inserts = append(inserts, pi.generateSSIDInsert(&network.SSID))

		for _, dot11dRange := range network.SSID.Dot11d.Ranges {
			inserts = append(inserts, pi.generateDot11dRangeInsert(&network.SSID.Dot11d, &dot11dRange))
		}

		inserts = append(inserts, pi.generatePacketsInsert(&network.Packets))
		inserts = append(inserts, pi.generateSnrInfoInsert(&network.SNRInfo))

		inserts = append(inserts, pi.generateGisPointInsert(network.GPSInfo.MinLat, network.GPSInfo.MinLon, network.GPSInfo.MinAlt))
		inserts = append(inserts, pi.generateGisPointInsert(network.GPSInfo.MaxLat, network.GPSInfo.MaxLon, network.GPSInfo.MaxAlt))
		inserts = append(inserts, pi.generateGisPointInsert(network.GPSInfo.PeakLat, network.GPSInfo.PeakLon, network.GPSInfo.PeakAlt))
		inserts = append(inserts, pi.generateGisPointInsert(network.GPSInfo.AverageLat, network.GPSInfo.AverageLon, network.GPSInfo.AverageAlt))
		inserts = append(inserts, pi.generateGPSInfoInsert(&network.GPSInfo))

		inserts = append(inserts, pi.generateIPAddressInsert(&network.IPAddress))
		inserts = append(inserts, pi.generateSeenCardInsert(&network.SeenCard))
		inserts = append(inserts, pi.generateNetworkInsert(run, &network))

		for _, tag := range network.Tag {
			inserts = append(inserts, pi.generateNetworkTagInsert(&network, &tag))
		}
		for _, carrier := range network.Carrier {
			inserts = append(inserts, pi.generateNetworkCarrierInsert(&network, carrier))
		}
		for _, encoding := range network.Encoding {
			inserts = append(inserts, pi.generateNetworkEncodingInsert(&network, encoding))
		}

		for _, client := range network.WirelessClients {
			inserts = append(inserts, pi.generateDot11dInsert(&client.SSID.Dot11d))
			inserts = append(inserts, pi.generateSSIDInsert(&client.SSID))

			for _, dot11dRange := range client.SSID.Dot11d.Ranges {
				inserts = append(inserts, pi.generateDot11dRangeInsert(&client.SSID.Dot11d, &dot11dRange))
			}

			inserts = append(inserts, pi.generatePacketsInsert(&client.Packets))
			inserts = append(inserts, pi.generateSnrInfoInsert(&client.SNRInfo))

			inserts = append(inserts, pi.generateGisPointInsert(client.GPSInfo.MinLat, client.GPSInfo.MinLon, client.GPSInfo.MinAlt))
			inserts = append(inserts, pi.generateGisPointInsert(client.GPSInfo.MaxLat, client.GPSInfo.MaxLon, client.GPSInfo.MaxAlt))
			inserts = append(inserts, pi.generateGisPointInsert(client.GPSInfo.PeakLat, client.GPSInfo.PeakLon, client.GPSInfo.PeakAlt))
			inserts = append(inserts, pi.generateGisPointInsert(client.GPSInfo.AverageLat, client.GPSInfo.AverageLon, client.GPSInfo.AverageAlt))
			inserts = append(inserts, pi.generateGPSInfoInsert(&client.GPSInfo))

			inserts = append(inserts, pi.generateIPAddressInsert(&client.IPAddress))
			inserts = append(inserts, pi.generateSeenCardInsert(&client.SeenCard))
			inserts = append(inserts, pi.generateClientInsert(&network, &client))

			for _, tag := range client.Tag {
				inserts = append(inserts, pi.generateClientTagInsert(&client, &tag))
			}
			for _, carrier := range client.Carrier {
				inserts = append(inserts, pi.generateClientCarrierInsert(&client, carrier))
			}
			for _, encoding := range client.Encoding {
				inserts = append(inserts, pi.generateClientEncodingInsert(&client, encoding))
			}
		}
	}

	for i, _ := range inserts {
		inserts[i] = strings.Replace(inserts[i], "\n", "", -1)
		inserts[i] = strings.Replace(inserts[i], "\t", " ", -1)
		inserts[i] = strings.Replace(inserts[i], "''", "NULL", -1)
		inserts[i] = strings.Replace(inserts[i], "'NULL'", "NULL", -1)
	}

	return inserts
}

func (pi *PostgresImporter) boolean(b bool) string {
	if b {
		return "true"
	}
	return "false"
}

func (pi *PostgresImporter) time(t *netxml.KismetTime) string {
	return t.Format(PostgresTimeFormat)
}

func sprintf(format string, origArgs ...interface{}) string {
	var replacedArgs []interface{} = make([]interface{}, len(origArgs))
	for i, arg := range origArgs {
		if reflect.TypeOf(arg) == reflect.TypeOf("") {
			sArg := arg.(string)

			if !strings.Contains(sArg, "SELECT") {
				replacedArgs[i] = strings.Replace(sArg, "'", "''", -1)
				continue
			}
		}

		replacedArgs[i] = arg
	}

	return fmt.Sprintf(format, replacedArgs...)
}

func (pi *PostgresImporter) generateCardSourceInsert(s *netxml.CardSource) string {
	return sprintf(
		`INSERT INTO ` + CARD_SOURCE + `
		 (` + CARD_SOURCE_UUID + `,` + CARD_SOURCE_SOURCE + `,` + CARD_SOURCE_NAME + `,` + CARD_SOURCE_INTERFACE + `,` + CARD_SOURCE_TYPE + `,` + CARD_SOURCE_PACKETS + `,` + CARD_SOURCE_HOP + `,` + CARD_SOURCE_CHANNELS+ `)
		 VALUES('%s', '%s', '%s', '%s', '%s', %d, %s, '%s');`,
		s.UUID, s.Source, s.Name, s.Interface, s.Type, s.Packets, pi.boolean(s.Hop), s.Channels)
}

func (pi *PostgresImporter) generateCardSourceSelect(s *netxml.CardSource) string {
	return sprintf(
		`SELECT ` + CARD_SOURCE_ID + `
		FROM ` + CARD_SOURCE + `
		WHERE ` + CARD_SOURCE_UUID + ` = '%s'
			AND ` + CARD_SOURCE_SOURCE + ` = '%s'
			AND ` + CARD_SOURCE_NAME + ` = '%s'
			AND ` + CARD_SOURCE_INTERFACE + ` = '%s'
			AND ` + CARD_SOURCE_TYPE + ` = '%s'
			AND ` + CARD_SOURCE_PACKETS + ` = %d
			AND ` + CARD_SOURCE_HOP + ` = %s
			AND ` + CARD_SOURCE_CHANNELS + ` = '%s'
		ORDER BY ` + SEEN_CARD_ID + ` DESC LIMIT 1`,
		s.UUID, s.Source, s.Name, s.Interface, s.Type, s.Packets, pi.boolean(s.Hop), s.Channels)
}

func (pi *PostgresImporter) generateDetectionRunInsert(d *netxml.DetectionRun) string {
	return sprintf(
		`INSERT INTO ` + DETECTION_RUN + `(` +
			DETECTION_RUN_VERSION + `,` +
			DETECTION_RUN_START + `,` +
			DETECTION_RUN_CARD_SOURCE + `)
		 VALUES('%s', '%s', (%s));`,
		d.KismetVersion, pi.time(&d.StartTime), pi.generateCardSourceSelect(&d.CardSource))
}

func (pi *PostgresImporter) generateDetectionRunSelect(d *netxml.DetectionRun) string {
	return sprintf(
		`SELECT ` + DETECTION_RUN_ID + ` FROM ` + DETECTION_RUN + `
		WHERE ` + DETECTION_RUN_START + ` = '%s'
		ORDER BY ` + DETECTION_RUN_ID + ` DESC LIMIT 1`,
		pi.time(&d.StartTime))
}

func (pi *PostgresImporter) generateDot11dInsert(d *netxml.Dot11d) string {
	if (reflect.DeepEqual(netxml.Dot11d{}, *d)) {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + DOT11D + `(` + DOT11D_COUNTRY + `) VALUES('%s');`,
		d.Country)
}

func (pi *PostgresImporter) generateDot11dSelect(d *netxml.Dot11d) string {
	if (reflect.DeepEqual(netxml.Dot11d{}, *d)) {
		return "NULL"
	}

	return sprintf(
		`SELECT ` + DOT11D_ID + ` FROM ` + DOT11D + `
		WHERE ` + DOT11D_COUNTRY + ` = '%s'
		ORDER BY ` + DOT11D_ID + ` DESC LIMIT 1`,
		d.Country)
}

func (pi *PostgresImporter) generateDot11dRangeInsert(d *netxml.Dot11d, r *netxml.Dot11dRange) string {
	if (netxml.Dot11dRange{}) == *r {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + DOT11D_RANGE + `(` +
			DOT11D_RANGE_START + `,` +
			DOT11D_RANGE_END + `,` +
			DOT11D_RANGE_MAX_POWER + `,` +
			DOT11D_RANGE_DOT11D + `)
		 VALUES(%d, %d, %d, (%s));`,
		r.RangeStart, r.RangeEnd, r.MaxPower, pi.generateDot11dSelect(d))
}

func (pi *PostgresImporter) generateSSIDInsert(s *netxml.SSID) string {
	if (reflect.DeepEqual(netxml.SSID{}, *s)) {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + SSID + `(` +
			SSID_FISRT_TIME + `, ` +
			SSID_LAST_TIME + `, ` +
			SSID_TYPE + `, ` +
			SSID_MAX_RATE + `, ` +
			SSID_PACKETS + `, ` +
			SSID_BEACONRATE + `, ` +
			SSID_WPS + `, ` +
			SSID_WPS_MANUF + `, ` +
			SSID_DEV_NAME + `, ` +
			SSID_MODEL_NAME + `, ` +
			SSID_MODEL_NUMBER + `, ` +
			SSID_WPA_VERSION + `, ` +
			SSID_ENCRYPTION + `, ` +
			SSID_SSID + `, ` +
			SSID_INFO + `, ` +
			SSID_ESSID + `, ` +
			SSID_ESSID_CLOAKED + `, ` +
			SSID_DOT11D + ` )
		VALUES('%s', '%s', '%s', %e, %d, %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', (%s));`,
		pi.time(&s.FirstTime), pi.time(&s.LastTime), s.Type, s.MaxRate, s.Packets, s.Beaconrate, s.WPS, s.WPSManuf, s.DevName, s.ModelName, s.ModelNumber, s.WPAVersion, s.Encryption, s.SSID, s.Info, s.ESSID.Value, pi.boolean(s.ESSID.Cloaked), pi.generateDot11dSelect(&s.Dot11d))
}

func (pi *PostgresImporter) generateSSIDSelect(s *netxml.SSID) string {
	if (reflect.DeepEqual(netxml.SSID{}, *s)) {
		return "NULL"
	}

	return sprintf(
		`SELECT ` + SSID_ID + ` FROM ` + SSID + `
		WHERE ` + SSID_FISRT_TIME + ` = '%s'
			AND ` + SSID_LAST_TIME + ` = '%s'
			AND ` + SSID_TYPE + ` = '%s'
		ORDER BY ` + SSID_ID + ` DESC LIMIT 1`,
		pi.time(&s.FirstTime), pi.time(&s.LastTime), s.Type)
}

func (pi *PostgresImporter) generatePacketsInsert(p *netxml.Packets) string {
	if (netxml.Packets{}) == *p {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + PACKETS + `(` +
			PACKETS_LLC + `,` +
			PACKETS_DATA + `,` +
			PACKETS_CRYPT + `,` +
			PACKETS_TOTAL + `,` +
			PACKETS_FRAGMENTS + `,` +
			PACKETS_RETRIES + `)
		 VALUES(%d, %d, %d, %d, %d, %d);`,
		p.LLC, p.Data, p.Crypt, p.Total, p.Fragments, p.Retries)
}

func (pi *PostgresImporter) generatePacketsSelect(p *netxml.Packets) string {
	if (netxml.Packets{}) == *p {
		return "NULL"
	}

	return sprintf(
		`SELECT ` + PACKETS_ID + ` FROM ` + PACKETS + `
		WHERE ` + PACKETS_LLC + ` = %d
			AND ` + PACKETS_DATA + ` = %d
			AND ` + PACKETS_CRYPT + ` = %d
			AND ` + PACKETS_TOTAL + ` = %d
			AND ` + PACKETS_FRAGMENTS + ` = %d
			AND ` + PACKETS_RETRIES + ` = %d
		ORDER BY ` + PACKETS_ID + ` DESC LIMIT 1`,
		p.LLC, p.Data, p.Crypt, p.Total, p.Fragments, p.Retries)
}

func (pi *PostgresImporter) generateSnrInfoInsert(s *netxml.SNRInfo) string {
	if (netxml.SNRInfo{}) == *s {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + SNR_INFO + `(` +
			SNR_INFO_LAST_SIGNAL_DBM + `,` +
			SNR_INFO_LAST_NOISE_DBM + `,` +
			SNR_INFO_LAST_SIGNAL_RSSI + `,` +
			SNR_INFO_LAST_NOISE_RSSI + `,` +
			SNR_INFO_MIN_SIGNAL_DBM + `,` +
			SNR_INFO_MIN_NOISE_DBM + `,` +
			SNR_INFO_MIN_SIGNAL_RSSI + `,` +
			SNR_INFO_MIN_NOISE_RSSI + `,` +
			SNR_INFO_MAX_SIGNAL_DBM + `,` +
			SNR_INFO_MAX_NOISE_DBM + `,` +
			SNR_INFO_MAX_SIGNAL_RSSI + `,` +
			SNR_INFO_MAX_NOISE_RSSI + `)
		 VALUES(%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d);`,
		s.LastSignalDbm, s.LastNoiseDbm, s.LastSignalRssi, s.LastNoiseRssi,
		s.MinSignalDbm, s.MinNoiseDbm, s.MinSignalRssi, s.MinNoiseRssi,
		s.MaxSignalDbm, s.MaxNoiseDbm, s.MaxSignalRssi, s.MaxNoiseRssi)
}

func (pi *PostgresImporter) generateSnrInfoSelect(s *netxml.SNRInfo) string {
	if (netxml.SNRInfo{}) == *s {
		return "NULL"
	}

	return sprintf(
		`SELECT ` + SNR_INFO_ID + ` FROM ` + SNR_INFO + `
		WHERE ` + SNR_INFO_LAST_SIGNAL_DBM + ` = %d
			AND ` + SNR_INFO_LAST_NOISE_DBM + ` = %d
			AND ` + SNR_INFO_LAST_SIGNAL_RSSI + ` = %d
			AND ` + SNR_INFO_LAST_NOISE_RSSI + ` = %d
			AND ` + SNR_INFO_MIN_SIGNAL_DBM + ` = %d
			AND ` + SNR_INFO_MIN_NOISE_DBM + ` = %d
			AND ` + SNR_INFO_MIN_SIGNAL_RSSI + ` = %d
			AND ` + SNR_INFO_MIN_NOISE_RSSI + ` = %d
			AND ` + SNR_INFO_MAX_SIGNAL_DBM + ` = %d
			AND ` + SNR_INFO_MAX_NOISE_DBM + ` = %d
			AND ` + SNR_INFO_MAX_SIGNAL_RSSI + ` = %d
			AND ` + SNR_INFO_MAX_NOISE_RSSI + ` = %d
		ORDER BY ` + SNR_INFO_ID + ` DESC LIMIT 1`,
		s.LastSignalDbm, s.LastNoiseDbm, s.LastSignalRssi, s.LastNoiseRssi,
		s.MinSignalDbm, s.MinNoiseDbm, s.MinSignalRssi, s.MinNoiseRssi,
		s.MaxSignalDbm, s.MaxNoiseDbm, s.MaxSignalRssi, s.MaxNoiseRssi)
}

func (pi *PostgresImporter) generateGisPointInsert(lat float64, lon float64, alt float64) string {
	if lat == 0 && lon == 0 && alt == 0 {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + GIS_POINT + `(` +
			GIS_POINT_LAT + `,` +
			GIS_POINT_LON + `,` +
			GIS_POINT_ALT + `)
		 VALUES(%e, %e, %e);`,
		lat, lon, alt)
}

func (pi *PostgresImporter) generateGisPointSelect(lat float64, lon float64, alt float64) string {
	if lat == 0 && lon == 0 && alt == 0 {
		return "NULL"
	}

	return sprintf(
		`SELECT ` + GIS_POINT_ID + ` FROM ` + GIS_POINT + `
		WHERE ` + GIS_POINT_LAT + ` = %e
			AND ` + GIS_POINT_LON + ` = %e
			AND ` + GIS_POINT_ALT + ` = %e
		ORDER BY ` + GIS_POINT_ID + ` DESC LIMIT 1`,
		lat, lon, alt)
}

func (pi *PostgresImporter) generateGPSInfoInsert(g * netxml.GPSInfo) string {
	if (netxml.GPSInfo{}) == *g {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + GPS_INFO + `(` +
			GPS_INFO_MIN_SPD + `,` +
			GPS_INFO_MAX_SPD + `,` +
			GPS_INFO_MIN + `,` +
			GPS_INFO_MAX + `,` +
			GPS_INFO_PEAK + `,` +
			GPS_INFO_AVERAGE + `)
		 VALUES(%e, %e, (%s), (%s), (%s), (%s));`,
		g.MinSpd, g.MaxSpd,
		pi.generateGisPointSelect(g.MinLat, g.MinLon, g.MinAlt),
		pi.generateGisPointSelect(g.MaxLat, g.MaxLon, g.MaxAlt),
		pi.generateGisPointSelect(g.PeakLat, g.PeakLon, g.PeakAlt),
		pi.generateGisPointSelect(g.AverageLat, g.AverageLon, g.AverageAlt))
}

func (pi *PostgresImporter) generateGPSInfoSelect(g * netxml.GPSInfo) string {
	if (netxml.GPSInfo{}) == *g {
		return "NULL"
	}

	return sprintf(
		`SELECT ` + GPS_INFO_ID + ` FROM ` + GPS_INFO + `
		WHERE ` + GPS_INFO_MIN + ` = (%s)
			AND ` + GPS_INFO_MAX + ` = (%s)
			AND ` + GPS_INFO_PEAK + ` = (%s)
			AND ` + GPS_INFO_AVERAGE + ` = (%s)
		ORDER BY ` + GPS_INFO_ID + ` DESC LIMIT 1`,
		pi.generateGisPointSelect(g.MinLat, g.MinLon, g.MinAlt),
		pi.generateGisPointSelect(g.MaxLat, g.MaxLon, g.MaxAlt),
		pi.generateGisPointSelect(g.PeakLat, g.PeakLon, g.PeakAlt),
		pi.generateGisPointSelect(g.AverageLat, g.AverageLon, g.AverageAlt))
}

func (pi *PostgresImporter) generateIPAddressInsert(i *netxml.IPAddress) string {
	if (netxml.IPAddress{}) == *i {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + IP_ADDRESS + `(` +
			IP_ADDRESS_TYPE + `,` +
			IP_ADDRESS_BLOCK + `,` +
			IP_ADDRESS_NETMASK + `,` +
			IP_ADDRESS_GATEWAY + `)
		 VALUES('%s', '%s', '%s', '%s');`,
		i.Type, i.Block, i.Netmask, i.Gateway)
}

func (pi *PostgresImporter) generateIPAddressSelect(i *netxml.IPAddress) string {
	if (netxml.IPAddress{}) == *i {
		return "NULL"
	}

	return sprintf(
		`SELECT ` + IP_ADDRESS_ID + ` FROM ` + IP_ADDRESS + `
		WHERE ` + IP_ADDRESS_TYPE + ` = '%s'
			AND ` + IP_ADDRESS_BLOCK + ` = '%s'
			AND ` + IP_ADDRESS_NETMASK + ` = '%s'
			AND ` + IP_ADDRESS_GATEWAY + ` = '%s'
		ORDER BY ` + IP_ADDRESS_ID + ` DESC LIMIT 1`,
		i.Type, i.Block, i.Netmask, i.Gateway)
}

func (pi *PostgresImporter) generateSeenCardInsert(s *netxml.SeenCard) string {
	if (netxml.SeenCard{}) == *s {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + SEEN_CARD + `(` +
			SEEN_CARD_UUID + `,` +
			SEEN_CARD_TIME + `,` +
			SEEN_CARD_PACKETS + `)
		 VALUES('%s', '%s', %d);`,
		s.UUID, pi.time(&s.Time), s.Packets)
}

func (pi *PostgresImporter) generateSeenCardSelect(s *netxml.SeenCard) string {
	if (netxml.SeenCard{}) == *s {
		return "NULL"
	}

	return sprintf(
		`SELECT ` + SEEN_CARD_ID + ` FROM ` + SEEN_CARD + `
		WHERE ` + SEEN_CARD_UUID + ` = '%s'
			AND ` + SEEN_CARD_TIME + ` = '%s'
			AND ` + SEEN_CARD_PACKETS + ` = %d
		ORDER BY ` + SEEN_CARD_ID + ` DESC LIMIT 1`,
		s.UUID, pi.time(&s.Time), s.Packets)
}

func (pi *PostgresImporter) generateNetworkInsert(d *netxml.DetectionRun, n *netxml.WirelessNetwork) string {
	return sprintf(
		`INSERT INTO ` + WIRELESS_NETWORK + `(` +
			WIRELESS_NETWORK_NUMBER + `,` +
			WIRELESS_NETWORK_TYPE + `,` +
			WIRELESS_NETWORK_FIRST_TIME + `,` +
			WIRELESS_NETWORK_LAST_TIME + `,` +
			WIRELESS_NETWORK_BSSID + `,` +
			WIRELESS_NETWORK_MANUF + `,` +
			WIRELESS_NETWORK_CHANNEL + `,` +
			WIRELESS_NETWORK_FREQ_MHZ + `,` +
			WIRELESS_NETWORK_MAX_SEEN_RATE + `,` +
			WIRELESS_NETWORK_DATASIZE + `,` +
			WIRELESS_NETWORK_CDP_DEVICE + `,` +
			WIRELESS_NETWORK_CDP_PORT_ID + `,` +
			WIRELESS_NETWORK_DHCP_HOSTNAME + `,` +
			WIRELESS_NETWORK_DHCP_VENDOR + `,` +
			WIRELESS_NETWORK_SSID + `,` +
			WIRELESS_NETWORK_PACKETS + `,` +
			WIRELESS_NETWORK_SNR_INFO + `,` +
			WIRELESS_NETWORK_GPS_INFO + `,` +
			WIRELESS_NETWORK_IP_ADDRESS + `,` +
			WIRELESS_NETWORK_SEEN_CARD + `,` +
			WIRELESS_NETWORK_DETECTION_RUN + `)
		 VALUES(%d, '%s', '%s', '%s', '%s', '%s', %d, '%s', %d, %d, '%s', '%s', '%s', '%s', (%s), (%s), (%s), (%s), (%s), (%s), (%s));`,
		n.Number, n.Type, pi.time(&n.FirstTime), pi.time(&n.LastTime), n.BSSID, n.Manuf,
		n.Channel, n.FreqMHZ, n.MaxSeenRate, n.Datasize, n.CDPDevice, n.CDPPortId, n.DHCPHostname, n.DHCPVendor,
		pi.generateSSIDSelect(&n.SSID),
		pi.generatePacketsSelect(&n.Packets),
		pi.generateSnrInfoSelect(&n.SNRInfo),
		pi.generateGPSInfoSelect(&n.GPSInfo),
		pi.generateIPAddressSelect(&n.IPAddress),
		pi.generateSeenCardSelect(&n.SeenCard),
		pi.generateDetectionRunSelect(d))
}

func (pi *PostgresImporter) generateNetworkSelect(n *netxml.WirelessNetwork) string {
	return sprintf(
		`SELECT ` + WIRELESS_NETWORK_ID + ` FROM ` + WIRELESS_NETWORK + `
		WHERE ` + WIRELESS_NETWORK_FIRST_TIME + ` = '%s'
			AND ` + WIRELESS_NETWORK_LAST_TIME + ` = '%s'
			AND ` + WIRELESS_NETWORK_NUMBER + ` = %d
		ORDER BY ` + WIRELESS_NETWORK_ID + ` DESC LIMIT 1`,
		pi.time(&n.FirstTime), pi.time(&n.LastTime), n.Number)
}

func (pi *PostgresImporter) generateClientInsert(n *netxml.WirelessNetwork, c *netxml.WirelessClient) string {
	return sprintf(
		`INSERT INTO ` + WIRELESS_CLIENT + `(` +
			WIRELESS_CLIENT_NUMBER + `,` +
			WIRELESS_CLIENT_TYPE + `,` +
			WIRELESS_CLIENT_FIRST_TIME + `,` +
			WIRELESS_CLIENT_LAST_TIME + `,` +
			WIRELESS_CLIENT_MAC + `,` +
			WIRELESS_CLIENT_MANUF + `,` +
			WIRELESS_CLIENT_CHANNEL + `,` +
			WIRELESS_CLIENT_FREQ_MHZ + `,` +
			WIRELESS_CLIENT_MAX_SEEN_RATE + `,` +
			WIRELESS_CLIENT_DATASIZE + `,` +
			WIRELESS_CLIENT_CDP_DEVICE + `,` +
			WIRELESS_CLIENT_CDP_PORT_ID + `,` +
			WIRELESS_CLIENT_DHCP_HOSTNAME + `,` +
			WIRELESS_CLIENT_DHCP_VENDOR + `,` +
			WIRELESS_CLIENT_SSID + `,` +
			WIRELESS_CLIENT_PACKETS + `,` +
			WIRELESS_CLIENT_SNR_INFO + `,` +
			WIRELESS_CLIENT_GPS_INFO + `,` +
			WIRELESS_CLIENT_IP_ADDRESS + `,` +
			WIRELESS_CLIENT_SEEN_CARD + `,` +
			WIRELESS_CLIENT_NETWORK_REF + `)
		 VALUES(%d, '%s', '%s', '%s', '%s', '%s', %d, '%s', %d, %d, '%s', '%s', '%s', '%s', (%s), (%s), (%s), (%s), (%s), (%s), (%s));`,
		c.Number, c.Type, pi.time(&c.FirstTime), pi.time(&c.LastTime), c.MAC, c.Manuf,
		c.Channel, c.FreqMHZ, c.MaxSeenRate, c.Datasize, c.CDPDevice, c.CDPPortId, c.DHCPHostname, c.DHCPVendor,
		pi.generateSSIDSelect(&c.SSID),
		pi.generatePacketsSelect(&c.Packets),
		pi.generateSnrInfoSelect(&c.SNRInfo),
		pi.generateGPSInfoSelect(&c.GPSInfo),
		pi.generateIPAddressSelect(&c.IPAddress),
		pi.generateSeenCardSelect(&c.SeenCard),
		pi.generateNetworkSelect(n))
}

func (pi *PostgresImporter) generateClientSelect(c *netxml.WirelessClient) string {
	return sprintf(
		`SELECT ` + WIRELESS_CLIENT_ID + ` FROM ` + WIRELESS_CLIENT + `
		WHERE ` + WIRELESS_CLIENT_FIRST_TIME + ` = '%s'
			AND ` + WIRELESS_CLIENT_LAST_TIME + ` = '%s'
			AND ` + WIRELESS_CLIENT_NUMBER + ` = %d
		ORDER BY ` + WIRELESS_CLIENT_ID + ` DESC LIMIT 1`,
		pi.time(&c.FirstTime), pi.time(&c.LastTime), c.Number)
}

func (pi *PostgresImporter) generateNetworkTagInsert(n *netxml.WirelessNetwork, t *netxml.Tag) string {
	if (netxml.Tag{}) == *t {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + TAG + `(` +
			TAG_NAME + `, ` +
			TAG_VALUE + `, ` +
			TAG_NETWORK_REF + `)
		 VALUES('%s', '%s', (%s));`,
		t.Name, t.Value, pi.generateNetworkSelect(n))
}

func (pi *PostgresImporter) generateClientTagInsert(c *netxml.WirelessClient, t *netxml.Tag) string {
	if (netxml.Tag{}) == *t {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + TAG + `(` +
			TAG_NAME + `, ` +
			TAG_VALUE + `, ` +
			TAG_CLIENT_REF + `)
		 VALUES('%s', '%s', (%s));`,
		t.Name, t.Value, pi.generateClientSelect(c))
}

func (pi *PostgresImporter) generateNetworkCarrierInsert(n *netxml.WirelessNetwork, carrier string) string {
	if ("") == carrier {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + CARRIER + `(` +
			CARRIER_VALUE + `, ` +
			CARRIER_NETWORK_REF + `)
		 VALUES('%s', (%s));`,
		carrier, pi.generateNetworkSelect(n))
}

func (pi *PostgresImporter) generateClientCarrierInsert(c *netxml.WirelessClient, carrier string) string {
	if ("") == carrier {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + CARRIER + `(` +
			CARRIER_VALUE + `, ` +
			CARRIER_CLIENT_REF + `)
		 VALUES('%s', (%s));`,
		carrier, pi.generateClientSelect(c))
}

func (pi *PostgresImporter) generateNetworkEncodingInsert(n *netxml.WirelessNetwork, encoding string) string {
	if ("") == encoding {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + ENCODING + `(` +
			ENCODING_VALUE + `, ` +
			ENCODING_NETWORK_REF + `)
		 VALUES('%s', (%s));`,
		encoding, pi.generateNetworkSelect(n))
}

func (pi *PostgresImporter) generateClientEncodingInsert(c *netxml.WirelessClient, encoding string) string {
	if ("") == encoding {
		return ""
	}

	return sprintf(
		`INSERT INTO ` + ENCODING + `(` +
			ENCODING_VALUE + `, ` +
			ENCODING_CLIENT_REF + `)
		 VALUES('%s', (%s));`,
		encoding, pi.generateClientSelect(c))
}