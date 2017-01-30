package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.sql.DatabaseConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.math.BigInteger;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)

@Entity(name = DatabaseConstants.SNR_INFO)
@Access(AccessType.FIELD)
public class SNRInfo implements DatabaseConstants {
	static final SNRInfo EMPTY = new SNRInfo();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = SNR_INFO_ID)
	private BigInteger id;

	@XmlElement(name = "last_signal_dbm")
	@Column(name = SNR_INFO_LAST_SIGNAL_DBM)
	private Integer lastSignalDbm;

	@XmlElement(name = "last_noise_dbm")
	@Column(name = SNR_INFO_LAST_NOISE_DBM)
	private Integer lastNoiseDbm;

	@XmlElement(name = "last_signal_rssi")
	@Column(name = SNR_INFO_LAST_SIGNAL_RSSI)
	private Integer lastSignalRssi;

	@XmlElement(name = "last_noise_rssi")
	@Column(name = SNR_INFO_LAST_NOISE_RSSI)
	private Integer lastNoiseRssi;

	@XmlElement(name = "min_signal_dbm")
	@Column(name = SNR_INFO_MIN_SIGNAL_DBM)
	private Integer minSignalDbm;

	@XmlElement(name = "min_noise_dbm")
	@Column(name = SNR_INFO_MIN_NOISE_DBM)
	private Integer minNoiseDbm;

	@XmlElement(name = "min_signal_rssi")
	@Column(name = SNR_INFO_MIN_SIGNAL_RSSI)
	private Integer minSignalRssi;

	@XmlElement(name = "min_noise_rssi")
	@Column(name = SNR_INFO_MIN_NOISE_RSSI)
	private Integer minNoiseRssi;

	@XmlElement(name = "max_signal_dbm")
	@Column(name = SNR_INFO_MAX_SIGNAL_DBM)
	private Integer maxSignalDbm;

	@XmlElement(name = "max_noise_dbm")
	@Column(name = SNR_INFO_MAX_NOISE_DBM)
	private Integer maxNoiseDbm;

	@XmlElement(name = "max_signal_rssi")
	@Column(name = SNR_INFO_MAX_SIGNAL_RSSI)
	private Integer maxSignalRssi;

	@XmlElement(name = "max_noise_rssi")
	@Column(name = SNR_INFO_MAX_NOISE_RSSI)
	private Integer maxNoiseRssi;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getLastSignalDbm() {
		return lastSignalDbm;
	}

	public void setLastSignalDbm(Integer lastSignalDbm) {
		this.lastSignalDbm = lastSignalDbm;
	}

	public Integer getLastNoiseDbm() {
		return lastNoiseDbm;
	}

	public void setLastNoiseDbm(Integer lastNoiseDbm) {
		this.lastNoiseDbm = lastNoiseDbm;
	}

	public Integer getLastSignalRssi() {
		return lastSignalRssi;
	}

	public void setLastSignalRssi(Integer lastSignalRssi) {
		this.lastSignalRssi = lastSignalRssi;
	}

	public Integer getLastNoiseRssi() {
		return lastNoiseRssi;
	}

	public void setLastNoiseRssi(Integer lastNoiseRssi) {
		this.lastNoiseRssi = lastNoiseRssi;
	}

	public Integer getMinSignalDbm() {
		return minSignalDbm;
	}

	public void setMinSignalDbm(Integer minSignalDbm) {
		this.minSignalDbm = minSignalDbm;
	}

	public Integer getMinNoiseDbm() {
		return minNoiseDbm;
	}

	public void setMinNoiseDbm(Integer minNoiseDbm) {
		this.minNoiseDbm = minNoiseDbm;
	}

	public Integer getMinSignalRssi() {
		return minSignalRssi;
	}

	public void setMinSignalRssi(Integer minSignalRssi) {
		this.minSignalRssi = minSignalRssi;
	}

	public Integer getMinNoiseRssi() {
		return minNoiseRssi;
	}

	public void setMinNoiseRssi(Integer minNoiseRssi) {
		this.minNoiseRssi = minNoiseRssi;
	}

	public Integer getMaxSignalDbm() {
		return maxSignalDbm;
	}

	public void setMaxSignalDbm(Integer maxSignalDbm) {
		this.maxSignalDbm = maxSignalDbm;
	}

	public Integer getMaxNoiseDbm() {
		return maxNoiseDbm;
	}

	public void setMaxNoiseDbm(Integer maxNoiseDbm) {
		this.maxNoiseDbm = maxNoiseDbm;
	}

	public Integer getMaxSignalRssi() {
		return maxSignalRssi;
	}

	public void setMaxSignalRssi(Integer maxSignalRssi) {
		this.maxSignalRssi = maxSignalRssi;
	}

	public Integer getMaxNoiseRssi() {
		return maxNoiseRssi;
	}

	public void setMaxNoiseRssi(Integer maxNoiseRssi) {
		this.maxNoiseRssi = maxNoiseRssi;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (!(o instanceof SNRInfo)) return false;

		SNRInfo snrInfo = (SNRInfo) o;

		if (lastSignalDbm != null ? !lastSignalDbm.equals(snrInfo.lastSignalDbm) : snrInfo.lastSignalDbm != null)
			return false;
		if (lastNoiseDbm != null ? !lastNoiseDbm.equals(snrInfo.lastNoiseDbm) : snrInfo.lastNoiseDbm != null)
			return false;
		if (lastSignalRssi != null ? !lastSignalRssi.equals(snrInfo.lastSignalRssi) : snrInfo.lastSignalRssi != null)
			return false;
		if (lastNoiseRssi != null ? !lastNoiseRssi.equals(snrInfo.lastNoiseRssi) : snrInfo.lastNoiseRssi != null)
			return false;
		if (minSignalDbm != null ? !minSignalDbm.equals(snrInfo.minSignalDbm) : snrInfo.minSignalDbm != null)
			return false;
		if (minNoiseDbm != null ? !minNoiseDbm.equals(snrInfo.minNoiseDbm) : snrInfo.minNoiseDbm != null) return false;
		if (minSignalRssi != null ? !minSignalRssi.equals(snrInfo.minSignalRssi) : snrInfo.minSignalRssi != null)
			return false;
		if (minNoiseRssi != null ? !minNoiseRssi.equals(snrInfo.minNoiseRssi) : snrInfo.minNoiseRssi != null)
			return false;
		if (maxSignalDbm != null ? !maxSignalDbm.equals(snrInfo.maxSignalDbm) : snrInfo.maxSignalDbm != null)
			return false;
		if (maxNoiseDbm != null ? !maxNoiseDbm.equals(snrInfo.maxNoiseDbm) : snrInfo.maxNoiseDbm != null) return false;
		if (maxSignalRssi != null ? !maxSignalRssi.equals(snrInfo.maxSignalRssi) : snrInfo.maxSignalRssi != null)
			return false;
		return maxNoiseRssi != null ? maxNoiseRssi.equals(snrInfo.maxNoiseRssi) : snrInfo.maxNoiseRssi == null;
	}

	@Override
	public int hashCode() {
		int result = lastSignalDbm != null ? lastSignalDbm.hashCode() : 0;
		result = 31 * result + (lastNoiseDbm != null ? lastNoiseDbm.hashCode() : 0);
		result = 31 * result + (lastSignalRssi != null ? lastSignalRssi.hashCode() : 0);
		result = 31 * result + (lastNoiseRssi != null ? lastNoiseRssi.hashCode() : 0);
		result = 31 * result + (minSignalDbm != null ? minSignalDbm.hashCode() : 0);
		result = 31 * result + (minNoiseDbm != null ? minNoiseDbm.hashCode() : 0);
		result = 31 * result + (minSignalRssi != null ? minSignalRssi.hashCode() : 0);
		result = 31 * result + (minNoiseRssi != null ? minNoiseRssi.hashCode() : 0);
		result = 31 * result + (maxSignalDbm != null ? maxSignalDbm.hashCode() : 0);
		result = 31 * result + (maxNoiseDbm != null ? maxNoiseDbm.hashCode() : 0);
		result = 31 * result + (maxSignalRssi != null ? maxSignalRssi.hashCode() : 0);
		result = 31 * result + (maxNoiseRssi != null ? maxNoiseRssi.hashCode() : 0);
		return result;
	}
}
