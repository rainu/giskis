package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.netxml.KismetTimeAdapter;
import de.rainu.giskis.nosql.DatabaseConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

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
public class SSID implements DatabaseConstants {
	static final SSID EMPTY = new SSID();

	@XmlTransient
	@JsonIgnore
	@Id
	private BigInteger id;

	@XmlAttribute(name = "first-time")
	@XmlJavaTypeAdapter(KismetTimeAdapter.class)
	@Field(SSID_FISRT_TIME)
	private LocalDateTime firstTime;

	@XmlAttribute(name = "last-time")
	@XmlJavaTypeAdapter(KismetTimeAdapter.class)
	@Field(SSID_LAST_TIME)
	private LocalDateTime lastTime;

	@XmlElement(name = "type")
	@Field(SSID_TYPE)
	private String type;

	@XmlElement(name = "max-rate")
	@Field(SSID_MAX_RATE)
	private Double maxRate;

	@XmlElement(name = "packets")
	@Field(SSID_PACKETS)
	private Integer packet;

	@XmlElement(name = "beaconrate")
	@Field(SSID_BEACONRATE)
	private Integer beaconrate;

	@XmlElement(name = "wps")
	@Field(SSID_WPS)
	private String wps;

	@XmlElement(name = "wps-manuf")
	@Field(SSID_WPS_MANUF)
	private String WPSManu;

	@XmlElement(name = "dev-name")
	@Field(SSID_DEV_NAME)
	private String devName;

	@XmlElement(name = "model-name")
	@Field(SSID_MODEL_NAME)
	private String modelName;

	@XmlElement(name = "model-num")
	@Field(SSID_MODEL_NUMBER)
	private String modelNumber;

	@XmlElement(name = "wpa-version")
	@Field(SSID_WPA_VERSION)
	private String wpaVersion;

	@XmlElement(name = "encryption")
	@Field(SSID_ENCRYPTIONS)
	private List<String> encryption = new ArrayList<>();

	@XmlElement(name = "dot11d")
	@Field(DOT11D)
	private Dot11d Dot11d;

	@XmlElement(name = "ssid")
	@Field(SSID_SSID)
	private String SSID;

	@XmlElement(name = "info")
	@Field(SSID_INFO)
	private String info;

	@XmlElement(name = "essid")
	@Field(DatabaseConstants.ESSID)
	private ESSID ESSID;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setFirstTime(LocalDateTime firstTime) {
		this.firstTime = firstTime;
	}

	public void setLastTime(LocalDateTime lastTime) {
		this.lastTime = lastTime;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setMaxRate(Double maxRate) {
		this.maxRate = maxRate;
	}

	public void setPacket(Integer packet) {
		this.packet = packet;
	}

	public void setBeaconrate(Integer beaconrate) {
		this.beaconrate = beaconrate;
	}

	public void setWps(String wps) {
		this.wps = wps;
	}

	public void setWPSManu(String WPSManu) {
		this.WPSManu = WPSManu;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public void setWpaVersion(String wpaVersion) {
		this.wpaVersion = wpaVersion;
	}

	public void setEncryption(List<String> encryption) {
		this.encryption = encryption;
	}

	public void setDot11d(de.rainu.giskis.model.Dot11d dot11d) {
		Dot11d = dot11d;
	}

	public void setSSID(String SSID) {
		this.SSID = SSID;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setESSID(de.rainu.giskis.model.ESSID ESSID) {
		this.ESSID = ESSID;
	}

	public LocalDateTime getFirstTime() {
		return firstTime;
	}

	public LocalDateTime getLastTime() {
		return lastTime;
	}

	public String getType() {
		return type;
	}

	public Double getMaxRate() {
		return maxRate;
	}

	public Integer getPacket() {
		return packet;
	}

	public Integer getBeaconrate() {
		return beaconrate;
	}

	public String getWps() {
		return wps;
	}

	public String getWPSManu() {
		return WPSManu;
	}

	public String getDevName() {
		return devName;
	}

	public String getModelName() {
		return modelName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public String getWpaVersion() {
		return wpaVersion;
	}

	public List<String> getEncryption() {
		return encryption;
	}

	public Dot11d getDot11d() {
		return Optional.ofNullable(Dot11d).orElse(de.rainu.giskis.model.Dot11d.EMPTY);
	}

	public String getSSID() {
		return SSID;
	}

	public String getInfo() {
		return info;
	}

	public ESSID getESSID() {
		return Optional.ofNullable(ESSID).orElse(de.rainu.giskis.model.ESSID.EMPTY);
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SSID)) return false;

		SSID ssid = (SSID) o;

		if (firstTime != null ? !firstTime.equals(ssid.firstTime) : ssid.firstTime != null) return false;
		if (lastTime != null ? !lastTime.equals(ssid.lastTime) : ssid.lastTime != null) return false;
		if (type != null ? !type.equals(ssid.type) : ssid.type != null) return false;
		if (maxRate != null ? !maxRate.equals(ssid.maxRate) : ssid.maxRate != null) return false;
		if (packet != null ? !packet.equals(ssid.packet) : ssid.packet != null) return false;
		if (beaconrate != null ? !beaconrate.equals(ssid.beaconrate) : ssid.beaconrate != null) return false;
		if (wps != null ? !wps.equals(ssid.wps) : ssid.wps != null) return false;
		if (WPSManu != null ? !WPSManu.equals(ssid.WPSManu) : ssid.WPSManu != null) return false;
		if (devName != null ? !devName.equals(ssid.devName) : ssid.devName != null) return false;
		if (modelName != null ? !modelName.equals(ssid.modelName) : ssid.modelName != null) return false;
		if (modelNumber != null ? !modelNumber.equals(ssid.modelNumber) : ssid.modelNumber != null) return false;
		if (wpaVersion != null ? !wpaVersion.equals(ssid.wpaVersion) : ssid.wpaVersion != null) return false;
		if (encryption != null ? !encryption.equals(ssid.encryption) : ssid.encryption != null) return false;
		if (Dot11d != null ? !Dot11d.equals(ssid.Dot11d) : ssid.Dot11d != null) return false;
		if (SSID != null ? !SSID.equals(ssid.SSID) : ssid.SSID != null) return false;
		if (info != null ? !info.equals(ssid.info) : ssid.info != null) return false;
		return ESSID != null ? ESSID.equals(ssid.ESSID) : ssid.ESSID == null;
	}

	@Override
	public int hashCode() {
		int result = firstTime != null ? firstTime.hashCode() : 0;
		result = 31 * result + (lastTime != null ? lastTime.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (maxRate != null ? maxRate.hashCode() : 0);
		result = 31 * result + (packet != null ? packet.hashCode() : 0);
		result = 31 * result + (beaconrate != null ? beaconrate.hashCode() : 0);
		result = 31 * result + (wps != null ? wps.hashCode() : 0);
		result = 31 * result + (WPSManu != null ? WPSManu.hashCode() : 0);
		result = 31 * result + (devName != null ? devName.hashCode() : 0);
		result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
		result = 31 * result + (modelNumber != null ? modelNumber.hashCode() : 0);
		result = 31 * result + (wpaVersion != null ? wpaVersion.hashCode() : 0);
		result = 31 * result + (encryption != null ? encryption.hashCode() : 0);
		result = 31 * result + (Dot11d != null ? Dot11d.hashCode() : 0);
		result = 31 * result + (SSID != null ? SSID.hashCode() : 0);
		result = 31 * result + (info != null ? info.hashCode() : 0);
		result = 31 * result + (ESSID != null ? ESSID.hashCode() : 0);
		return result;
	}
}
