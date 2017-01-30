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

@Entity(name = DatabaseConstants.PACKETS)
@Access(AccessType.FIELD)
public class Packets implements DatabaseConstants {
	static final Packets EMPTY = new Packets();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = PACKETS_ID)
	private BigInteger id;

	@XmlElement(name = "LLC")
	@Column(name = PACKETS_LLC)
	private Integer LLC;

	@XmlElement(name = "data")
	@Column(name = PACKETS_DATA)
	private Integer data;

	@XmlElement(name = "crypt")
	@Column(name = PACKETS_CRYPT)
	private Integer crypt;

	@XmlElement(name = "total")
	@Column(name = PACKETS_TOTAL)
	private Integer total;

	@XmlElement(name = "fragments")
	@Column(name = PACKETS_FRAGMENTS)
	private Integer fragments;

	@XmlElement(name = "retries")
	@Column(name = PACKETS_RETRIES)
	private Integer retries;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getLLC() {
		return LLC;
	}

	public void setLLC(Integer LLC) {
		this.LLC = LLC;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public Integer getCrypt() {
		return crypt;
	}

	public void setCrypt(Integer crypt) {
		this.crypt = crypt;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getFragments() {
		return fragments;
	}

	public void setFragments(Integer fragments) {
		this.fragments = fragments;
	}

	public Integer getRetries() {
		return retries;
	}

	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Packets)) return false;

		Packets packets = (Packets) o;

		if (LLC != null ? !LLC.equals(packets.LLC) : packets.LLC != null) return false;
		if (data != null ? !data.equals(packets.data) : packets.data != null) return false;
		if (crypt != null ? !crypt.equals(packets.crypt) : packets.crypt != null) return false;
		if (total != null ? !total.equals(packets.total) : packets.total != null) return false;
		if (fragments != null ? !fragments.equals(packets.fragments) : packets.fragments != null) return false;
		return retries != null ? retries.equals(packets.retries) : packets.retries == null;
	}

	@Override
	public int hashCode() {
		int result = LLC != null ? LLC.hashCode() : 0;
		result = 31 * result + (data != null ? data.hashCode() : 0);
		result = 31 * result + (crypt != null ? crypt.hashCode() : 0);
		result = 31 * result + (total != null ? total.hashCode() : 0);
		result = 31 * result + (fragments != null ? fragments.hashCode() : 0);
		result = 31 * result + (retries != null ? retries.hashCode() : 0);
		return result;
	}
}
