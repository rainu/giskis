package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.netxml.KismetTimeAdapter;
import de.rainu.giskis.sql.DatabaseConstants;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
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

@Entity(name = DatabaseConstants.WIRELESS_CLIENT)
@Access(AccessType.FIELD)
@Table(indexes = {
		  @Index(columnList = DatabaseConstants.WIRELESS_CLIENT_MAC),
		  @Index(columnList = DatabaseConstants.WIRELESS_CLIENT_NETWORK_REF),
})
public class WirelessClient implements DatabaseConstants {
	static final WirelessClient EMPTY = new WirelessClient();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = WIRELESS_CLIENT_ID)
	private BigInteger id;

	@XmlAttribute(name = "number")
	@Column(name = WIRELESS_CLIENT_NUMBER)
	private Integer number;

	@XmlAttribute(name = "type")
	@Column(name = WIRELESS_CLIENT_TYPE)
	private String type;

	@XmlAttribute(name = "first-time")
	@XmlJavaTypeAdapter(KismetTimeAdapter.class)
	@Column(name = WIRELESS_CLIENT_FIRST_TIME)
	private LocalDateTime firstTime;

	@XmlAttribute(name = "last-time")
	@XmlJavaTypeAdapter(KismetTimeAdapter.class)
	@Column(name = WIRELESS_CLIENT_LAST_TIME)
	private LocalDateTime lastTime;

	@XmlElement(name = "client-mac")
	@Column(name = WIRELESS_CLIENT_MAC)
	private String mac;

	@XmlElement(name = "client-manuf")
	@Column(name = WIRELESS_CLIENT_MANUF)
	private String manuf;

	@XmlElement(name = "SSID")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = WIRELESS_CLIENT_SSID)
	@Fetch(FetchMode.SELECT)
	private SSID SSID;

	@XmlElement(name = "channel")
	@Column(name = WIRELESS_CLIENT_CHANNEL)
	private Integer channel;

	@XmlElement(name = "freqmhz")
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<String> freqMHZ = new ArrayList<>();

	@XmlElement(name = "maxseenrate")
	@Column(name = WIRELESS_CLIENT_MAX_SEEN_RATE)
	private Long maxSeenRate;

	@XmlElement(name = "carrier")
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<String> carrier = new ArrayList<>();

	@XmlElement(name = "encoding")
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<String> encoding = new ArrayList<>();

	@XmlElement(name = "packets")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = WIRELESS_CLIENT_PACKETS)
	@Fetch(FetchMode.SELECT)
	private Packets packets;

	@XmlElement(name = "datasize")
	@Column(name = WIRELESS_CLIENT_DATASIZE)
	private Long datasize;

	@XmlElement(name = "snr-info")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = WIRELESS_CLIENT_SNR_INFO)
	@Fetch(FetchMode.SELECT)
	private SNRInfo SNRInfo;

	@XmlElement(name = "gps-info")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = WIRELESS_CLIENT_GPS_INFO)
	@Fetch(FetchMode.SELECT)
	private GPSInfo GPSInfo;

	@XmlElement(name = "ip-address")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = WIRELESS_CLIENT_IP_ADDRESS)
	@Fetch(FetchMode.SELECT)
	private IPAddress IPAddress;

	@XmlElement(name = "cdp-device")
	@Column(name = WIRELESS_CLIENT_CDP_DEVICE)
	private String CDPDevice;

	@XmlElement(name = "cdp-portid")
	@Column(name = WIRELESS_CLIENT_CDP_PORT_ID)
	private String CDPPortId;

	@XmlElement(name = "dhcp-hostname")
	@Column(name = WIRELESS_CLIENT_DHCP_HOSTNAME)
	private String DHCPHostname;

	@XmlElement(name = "dhcp-vendor")
	@Column(name = WIRELESS_CLIENT_DHCP_VENDOR)
	private String DHCPVendor;

	@XmlElement(name = "seen-card")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = WIRELESS_CLIENT_SEEN_CARD)
	@Fetch(FetchMode.SELECT)
	private SeenCard seenCard;

	@XmlElement(name = "tag")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = TAG_CLIENT_REF)
	@Fetch(FetchMode.SELECT)
	private List<Tag> tag = new ArrayList<>();

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
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

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getManuf() {
		return manuf;
	}

	public void setManuf(String manuf) {
		this.manuf = manuf;
	}

	public de.rainu.giskis.model.SSID getSSID() {
		return Optional.ofNullable(SSID).orElse(de.rainu.giskis.model.SSID.EMPTY);
	}

	public void setSSID(de.rainu.giskis.model.SSID SSID) {
		this.SSID = SSID;
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

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WirelessClient)) return false;

		WirelessClient that = (WirelessClient) o;

		if (number != null ? !number.equals(that.number) : that.number != null) return false;
		if (type != null ? !type.equals(that.type) : that.type != null) return false;
		if (firstTime != null ? !firstTime.equals(that.firstTime) : that.firstTime != null) return false;
		if (lastTime != null ? !lastTime.equals(that.lastTime) : that.lastTime != null) return false;
		if (mac != null ? !mac.equals(that.mac) : that.mac != null) return false;
		if (manuf != null ? !manuf.equals(that.manuf) : that.manuf != null) return false;
		if (SSID != null ? !SSID.equals(that.SSID) : that.SSID != null) return false;
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
		return tag != null ? tag.equals(that.tag) : that.tag == null;
	}

	@Override
	public int hashCode() {
		int result = number != null ? number.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (firstTime != null ? firstTime.hashCode() : 0);
		result = 31 * result + (lastTime != null ? lastTime.hashCode() : 0);
		result = 31 * result + (mac != null ? mac.hashCode() : 0);
		result = 31 * result + (manuf != null ? manuf.hashCode() : 0);
		result = 31 * result + (SSID != null ? SSID.hashCode() : 0);
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
		return result;
	}
}
