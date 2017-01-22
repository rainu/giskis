package netxml

import (
	"time"
	"encoding/xml"
)

type KismetTime struct {
	time.Time
}

const KismetTimeFormat = "Mon Jan 2 15:04:05 2006" //"Mon Jan 2 15:04:05 -0700 MST 2006"

func (c *KismetTime) UnmarshalXML(d *xml.Decoder, start xml.StartElement) error {
	var v string
	d.DecodeElement(&v, &start)
	parse, _ := time.Parse(KismetTimeFormat, v)
	*c = KismetTime{parse}
	return nil
}

func (c *KismetTime) UnmarshalXMLAttr(attr xml.Attr) error {
	parse, _ := time.Parse(KismetTimeFormat, attr.Value)
	*c = KismetTime{parse}
	return nil
}

type DetectionRun struct {
	KismetVersion   string `xml:"kismet-version,attr"`
	StartTime       KismetTime `xml:"start-time,attr"`
	CardSource      CardSource `xml:"card-source"`
	WirelessNetwork []WirelessNetwork `xml:"wireless-network"`
}

type CardSource struct {
	UUID      string `xml:"uuid,attr"`
	Source    string `xml:"card-source"`
	Name      string `xml:"card-name"`
	Interface string`xml:"card-interface"`
	Type      string `xml:"card-type"`
	Packets   int `xml:"card-packets"`
	Hop       bool `xml:"card-hop"`
	Channels  string `xml:"card-channels"`
}

type WirelessNetwork struct {
	Number          int `xml:"number,attr"`
	Type            string `xml:"type,attr"`
	FirstTime       KismetTime `xml:"first-time,attr"`
	LastTime        KismetTime `xml:"last-time,attr"`
	SSID            SSID `xml:"SSID"`
	BSSID           string `xml:"BSSID"`
	Manuf           string `xml:"manuf"`
	Channel         int `xml:"channel"`
	FreqMHZ         string `xml:"freqmhz"`
	MaxSeenRate     int64 `xml:"maxseenrate"`
	Carrier         []string `xml:"carrier"`
	Encoding        []string `xml:"encoding"`
	Packets         Packets `xml:"packets"`
	Datasize        int64 `xml:"datasize"`
	SNRInfo         SNRInfo `xml:"snr-info"`
	GPSInfo         GPSInfo `xml:"gps-info"`
	IPAddress       IPAddress `xml:"ip-address"`
	CDPDevice       string `xml:"cdp-device"`
	CDPPortId       string `xml:"cdp-portid"`
	DHCPHostname    string `xml:"dhcp-hostname"`
	DHCPVendor      string `xml:"dhcp-vendor"`
	SeenCard        SeenCard   `xml:"seen-card"`
	Tag             []Tag `xml:"tag"`
	WirelessClients []WirelessClient `xml:"wireless-client"`
}

type SSID struct {
	FirstTime   KismetTime `xml:"first-time,attr"`
	LastTime    KismetTime `xml:"last-time,attr"`
	Type        string `xml:"type"`
	MaxRate     float32 `xml:"max-rate"`
	Packets     int `xml:"packets"`
	Beaconrate  int `xml:"beaconrate"`
	WPS         string `xml:"wps"`
	WPSManuf    string `xml:"wps-manuf"`
	DevName     string `xml:"dev-name"`
	ModelName   string `xml:"model-name"`
	ModelNumber string `xml:"model-num"`
	WPAVersion  string `xml:"wpa-version"`
	Encryption  []string `xml:"encryption"`
	Dot11d      Dot11d `xml:"dot11d"`
	SSID        string `xml:"ssid"`
	Info        string `xml:"info"`
	ESSID       struct {
						Value   string `xml:",innerxml"`
						Cloaked bool `xml:"cloaked,attr"`
					} `xml:"essid"`
}

type Dot11d struct {
	Country string `xml:"country,attr"`
	Ranges  []Dot11dRange `xml:"dot11d-range"`
}

type Dot11dRange struct {
	RangeStart int `xml:"start,attr"`
	RangeEnd   int `xml:"end,attr"`
	MaxPower   int `xml:"max-power,attr"`
}

type Packets struct {
	LLC       int `xml:"LLC"`
	Data      int `xml:"data"`
	Crypt     int `xml:"crypt"`
	Total     int `xml:"total"`
	Fragments int `xml:"fragments"`
	Retries   int `xml:"retries"`
}

type SNRInfo struct {
	LastSignalDbm  int `xml:"last_signal_dbm"`
	LastNoiseDbm   int `xml:"last_noise_dbm"`
	LastSignalRssi int `xml:"last_signal_rssi"`
	LastNoiseRssi  int `xml:"last_noise_rssi"`
	MinSignalDbm   int `xml:"min_signal_dbm"`
	MinNoiseDbm    int `xml:"min_noise_dbm"`
	MinSignalRssi  int `xml:"min_signal_rssi"`
	MinNoiseRssi   int `xml:"min_noise_rssi"`
	MaxSignalDbm   int `xml:"max_signal_dbm"`
	MaxNoiseDbm    int `xml:"max_noise_dbm"`
	MaxSignalRssi  int `xml:"max_signal_rssi"`
	MaxNoiseRssi   int `xml:"max_noise_rssi"`
}

type GPSInfo struct {
	MinLat     float64 `xml:"min-lat"`
	MinLon     float64 `xml:"min-lon"`
	MinAlt     float64 `xml:"min-alt"`
	MinSpd     float64 `xml:"min-spd"`
	MaxLat     float64 `xml:"max-lat"`
	MaxLon     float64 `xml:"max-lon"`
	MaxAlt     float64 `xml:"max-alt"`
	MaxSpd     float64 `xml:"max-spd"`
	PeakLat    float64 `xml:"peak-lat"`
	PeakLon    float64 `xml:"peak-lon"`
	PeakAlt    float64 `xml:"peak-alt"`
	AverageLat float64 `xml:"avg-lat"`
	AverageLon float64 `xml:"avg-lon"`
	AverageAlt float64 `xml:"avg-alt"`
}

type IPAddress struct {
	Type    string `xml:"type,attr"`
	Block   string `xml:"ip-block"`
	Netmask string `xml:"ip-netmask"`
	Gateway string `xml:"ip-gateway"`
}

type SeenCard struct {
	UUID    string `xml:"seen-uuid"`
	Time    KismetTime `xml:"seen-time"`
	Packets int `xml:"seen-packets"`
}

type Tag      struct {
	Value string `xml:",innerxml"`
	Name  string `xml:"name,attr"`
}

type WirelessClient struct {
	Number       int `xml:"number,attr"`
	Type         string `xml:"type,attr"`
	FirstTime    KismetTime `xml:"first-time,attr"`
	LastTime     KismetTime `xml:"last-time,attr"`
	MAC          string `xml:"client-mac"`
	Manuf        string `xml:"client-manuf"`
	SSID         SSID `xml:"SSID"`
	Channel      int `xml:"channel"`
	FreqMHZ      string `xml:"freqmhz"`
	MaxSeenRate  int64 `xml:"maxseenrate"`
	Carrier      []string `xml:"carrier"`
	Encoding     []string `xml:"encoding"`
	Packets      Packets `xml:"packets"`
	Datasize     int64 `xml:"datasize"`
	SNRInfo      SNRInfo `xml:"snr-info"`
	GPSInfo      GPSInfo `xml:"gps-info"`
	IPAddress    IPAddress `xml:"ip-address"`
	CDPDevice    string `xml:"cdp-device"`
	CDPPortId    string `xml:"cdp-portid"`
	DHCPHostname string `xml:"dhcp-hostname"`
	DHCPVendor   string `xml:"dhcp-vendor"`
	SeenCard     SeenCard   `xml:"seen-card"`
	Tag          []Tag `xml:"tag"`
}