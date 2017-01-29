package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.netxml.KismetTimeAdapter;
import de.rainu.giskis.sql.DatabaseConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)

@Entity(name = DatabaseConstants.WIRELESS_NETWORK)
@Access(AccessType.FIELD)
public class WirelessNetwork implements DatabaseConstants {
	static final WirelessNetwork EMPTY = new WirelessNetwork();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = WIRELESS_NETWORK_ID)
	private Long id;

	@XmlAttribute(name = "number")
	@Column(name = WIRELESS_NETWORK_NUMBER)
	private Integer number;

	@XmlAttribute(name = "type")
	@Column(name = WIRELESS_NETWORK_TYPE)
	private String type;

	@XmlAttribute(name = "first-time")
	@XmlJavaTypeAdapter(KismetTimeAdapter.class)
	@Column(name = WIRELESS_NETWORK_FIRST_TIME)
	private LocalDateTime firstTime;

	@XmlAttribute(name = "last-time")
	@XmlJavaTypeAdapter(KismetTimeAdapter.class)
	@Column(name = WIRELESS_NETWORK_LAST_TIME)
	private LocalDateTime lastTime;

	@XmlElement(name = "SSID")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = WIRELESS_NETWORK_SSID)
	private SSID SSID;

	@XmlElement(name = "BSSID")
	@Column(name = WIRELESS_NETWORK_BSSID)
	private String BSSID;

	@XmlElement(name = "manuf")
	@Column(name = WIRELESS_NETWORK_MANUF)
	private String manuf;

	@XmlElement(name = "channel")
	@Column(name = WIRELESS_NETWORK_CHANNEL)
	private Integer channel;

	@XmlElement(name = "freqmhz")
	@ElementCollection
	private List<String> freqMHZ = new ArrayList<>();

	@XmlElement(name = "maxseenrate")
	@Column(name = WIRELESS_NETWORK_MAX_SEEN_RATE)
	private Long maxSeenRate;

	@XmlElement(name = "carrier")
	@ElementCollection
	private List<String> carrier = new ArrayList<>();

	@XmlElement(name = "encoding")
	@ElementCollection
	private List<String> encoding = new ArrayList<>();

	@XmlElement(name = "packets")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = WIRELESS_NETWORK_PACKETS)
	private Packets packets;

	@XmlElement(name = "datasize")
	@Column(name = WIRELESS_NETWORK_DATASIZE)
	private Long datasize;

	@XmlElement(name = "snr-info")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = WIRELESS_NETWORK_SNR_INFO)
	private SNRInfo SNRInfo;

	@XmlElement(name = "gps-info")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = WIRELESS_NETWORK_GPS_INFO)
	private GPSInfo GPSInfo;

	@XmlElement(name = "ip-address")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = WIRELESS_NETWORK_IP_ADDRESS)
	private IPAddress IPAddress;

	@XmlElement(name = "cdp-device")
	@Column(name = WIRELESS_NETWORK_CDP_DEVICE)
	private String CDPDevice;

	@XmlElement(name = "cdp-portid")
	@Column(name = WIRELESS_NETWORK_CDP_PORT_ID)
	private String CDPPortId;

	@XmlElement(name = "dhcp-hostname")
	@Column(name = WIRELESS_NETWORK_DHCP_HOSTNAME)
	private String DHCPHostname;

	@XmlElement(name = "dhcp-vendor")
	@Column(name = WIRELESS_NETWORK_DHCP_VENDOR)
	private String DHCPVendor;

	@XmlElement(name = "seen-card")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = WIRELESS_NETWORK_SEEN_CARD)
	private SeenCard seenCard;

	@XmlElement(name = "tag")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = TAG_NETWORK_REF)
	private List<Tag> tag = new ArrayList<>();

	@XmlElement(name = "wireless-client")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = WIRELESS_CLIENT_NETWORK_REF)
	private List<WirelessClient> wirelessClients = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(LocalDateTime firstTime) {
		this.firstTime = firstTime;
	}

	public LocalDateTime getLastTime() {
		return lastTime;
	}

	public void setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
	}

	public de.rainu.giskis.model.SSID getSSID() {
		return Optional.ofNullable(SSID).orElse(de.rainu.giskis.model.SSID.EMPTY);
	}

	public void setSSID(de.rainu.giskis.model.SSID SSID) {
		this.SSID = SSID;
	}

	public String getBSSID() {
		return BSSID;
	}

	public void setBSSID(String BSSID) {
		this.BSSID = BSSID;
	}

	public String getManuf() {
		return manuf;
	}

	public void setManuf(String manuf) {
		this.manuf = manuf;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public List<String> getFreqMHZ() {
		return freqMHZ;
	}

	public void setFreqMHZ(List<String> freqMHZ) {
		this.freqMHZ = freqMHZ;
	}

	public Long getMaxSeenRate() {
		return maxSeenRate;
	}

	public void setMaxSeenRate(Long maxSeenRate) {
		this.maxSeenRate = maxSeenRate;
	}

	public List<String> getCarrier() {
		return carrier;
	}

	public void setCarrier(List<String> carrier) {
		this.carrier = carrier;
	}

	public List<String> getEncoding() {
		return encoding;
	}

	public void setEncoding(List<String> encoding) {
		this.encoding = encoding;
	}

	public Packets getPackets() {
		return Optional.ofNullable(packets).orElse(Packets.EMPTY);
	}

	public void setPackets(Packets packets) {
		this.packets = packets;
	}

	public Long getDatasize() {
		return datasize;
	}

	public void setDatasize(Long datasize) {
		this.datasize = datasize;
	}

	public de.rainu.giskis.model.SNRInfo getSNRInfo() {
		return Optional.ofNullable(SNRInfo).orElse(de.rainu.giskis.model.SNRInfo.EMPTY);
	}

	public void setSNRInfo(de.rainu.giskis.model.SNRInfo SNRInfo) {
		this.SNRInfo = SNRInfo;
	}

	public de.rainu.giskis.model.GPSInfo getGPSInfo() {
		return Optional.ofNullable(GPSInfo).orElse(de.rainu.giskis.model.GPSInfo.EMPTY);
	}

	public void setGPSInfo(de.rainu.giskis.model.GPSInfo GPSInfo) {
		this.GPSInfo = GPSInfo;
	}

	public de.rainu.giskis.model.IPAddress getIPAddress() {
		return Optional.ofNullable(IPAddress).orElse(de.rainu.giskis.model.IPAddress.EMPTY);
	}

	public void setIPAddress(de.rainu.giskis.model.IPAddress IPAddress) {
		this.IPAddress = IPAddress;
	}

	public String getCDPDevice() {
		return CDPDevice;
	}

	public void setCDPDevice(String CDPDevice) {
		this.CDPDevice = CDPDevice;
	}

	public String getCDPPortId() {
		return CDPPortId;
	}

	public void setCDPPortId(String CDPPortId) {
		this.CDPPortId = CDPPortId;
	}

	public String getDHCPHostname() {
		return DHCPHostname;
	}

	public void setDHCPHostname(String DHCPHostname) {
		this.DHCPHostname = DHCPHostname;
	}

	public String getDHCPVendor() {
		return DHCPVendor;
	}

	public void setDHCPVendor(String DHCPVendor) {
		this.DHCPVendor = DHCPVendor;
	}

	public SeenCard getSeenCard() {
		return Optional.ofNullable(seenCard).orElse(SeenCard.EMPTY);
	}

	public void setSeenCard(SeenCard seenCard) {
		this.seenCard = seenCard;
	}

	public List<Tag> getTag() {
		return tag;
	}

	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}

	public List<WirelessClient> getWirelessClients() {
		return wirelessClients;
	}

	public void setWirelessClients(List<WirelessClient> wirelessClients) {
		this.wirelessClients = wirelessClients;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WirelessNetwork)) return false;

		WirelessNetwork that = (WirelessNetwork) o;

		if (number != null ? !number.equals(that.number) : that.number != null) return false;
		if (type != null ? !type.equals(that.type) : that.type != null) return false;
		if (firstTime != null ? !firstTime.equals(that.firstTime) : that.firstTime != null) return false;
		if (lastTime != null ? !lastTime.equals(that.lastTime) : that.lastTime != null) return false;
		if (SSID != null ? !SSID.equals(that.SSID) : that.SSID != null) return false;
		if (BSSID != null ? !BSSID.equals(that.BSSID) : that.BSSID != null) return false;
		if (manuf != null ? !manuf.equals(that.manuf) : that.manuf != null) return false;
		if (channel != null ? !channel.equals(that.channel) : that.channel != null) return false;
		if (freqMHZ != null ? !freqMHZ.equals(that.freqMHZ) : that.freqMHZ != null) return false;
		if (maxSeenRate != null ? !maxSeenRate.equals(that.maxSeenRate) : that.maxSeenRate != null) return false;
		if (carrier != null ? !carrier.equals(that.carrier) : that.carrier != null) return false;
		if (encoding != null ? !encoding.equals(that.encoding) : that.encoding != null) return false;
		if (packets != null ? !packets.equals(that.packets) : that.packets != null) return false;
		if (datasize != null ? !datasize.equals(that.datasize) : that.datasize != null) return false;
		if (SNRInfo != null ? !SNRInfo.equals(that.SNRInfo) : that.SNRInfo != null) return false;
		if (GPSInfo != null ? !GPSInfo.equals(that.GPSInfo) : that.GPSInfo != null) return false;
		if (IPAddress != null ? !IPAddress.equals(that.IPAddress) : that.IPAddress != null) return false;
		if (CDPDevice != null ? !CDPDevice.equals(that.CDPDevice) : that.CDPDevice != null) return false;
		if (CDPPortId != null ? !CDPPortId.equals(that.CDPPortId) : that.CDPPortId != null) return false;
		if (DHCPHostname != null ? !DHCPHostname.equals(that.DHCPHostname) : that.DHCPHostname != null) return false;
		if (DHCPVendor != null ? !DHCPVendor.equals(that.DHCPVendor) : that.DHCPVendor != null) return false;
		if (seenCard != null ? !seenCard.equals(that.seenCard) : that.seenCard != null) return false;
		if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;
		return wirelessClients != null ? wirelessClients.equals(that.wirelessClients) : that.wirelessClients == null;
	}

	@Override
	public int hashCode() {
		int result = number != null ? number.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (firstTime != null ? firstTime.hashCode() : 0);
		result = 31 * result + (lastTime != null ? lastTime.hashCode() : 0);
		result = 31 * result + (SSID != null ? SSID.hashCode() : 0);
		result = 31 * result + (BSSID != null ? BSSID.hashCode() : 0);
		result = 31 * result + (manuf != null ? manuf.hashCode() : 0);
		result = 31 * result + (channel != null ? channel.hashCode() : 0);
		result = 31 * result + (freqMHZ != null ? freqMHZ.hashCode() : 0);
		result = 31 * result + (maxSeenRate != null ? maxSeenRate.hashCode() : 0);
		result = 31 * result + (carrier != null ? carrier.hashCode() : 0);
		result = 31 * result + (encoding != null ? encoding.hashCode() : 0);
		result = 31 * result + (packets != null ? packets.hashCode() : 0);
		result = 31 * result + (datasize != null ? datasize.hashCode() : 0);
		result = 31 * result + (SNRInfo != null ? SNRInfo.hashCode() : 0);
		result = 31 * result + (GPSInfo != null ? GPSInfo.hashCode() : 0);
		result = 31 * result + (IPAddress != null ? IPAddress.hashCode() : 0);
		result = 31 * result + (CDPDevice != null ? CDPDevice.hashCode() : 0);
		result = 31 * result + (CDPPortId != null ? CDPPortId.hashCode() : 0);
		result = 31 * result + (DHCPHostname != null ? DHCPHostname.hashCode() : 0);
		result = 31 * result + (DHCPVendor != null ? DHCPVendor.hashCode() : 0);
		result = 31 * result + (seenCard != null ? seenCard.hashCode() : 0);
		result = 31 * result + (tag != null ? tag.hashCode() : 0);
		result = 31 * result + (wirelessClients != null ? wirelessClients.hashCode() : 0);
		return result;
	}
}
