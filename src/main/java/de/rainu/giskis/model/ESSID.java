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

@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)

@Entity(name = DatabaseConstants.ESSID)
@Access(AccessType.FIELD)
@Table(indexes = {
		  @Index(columnList = DatabaseConstants.ESSID_ESSID)
})
public class ESSID implements DatabaseConstants {
	static final ESSID EMPTY = new ESSID();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = ESSID_ID)
	private BigInteger id;

	@XmlValue
	@Column(name = ESSID_ESSID)
	private String essid;

	@XmlAttribute(name = "cloaked")
	@Column(name = ESSID_ESSID_CLOAKED)
	private Boolean cloaked;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getEssid() {
		return essid;
	}

	public void setEssid(String essid) {
		this.essid = essid;
	}

	public Boolean getCloaked() {
		return cloaked;
	}

	public void setCloaked(Boolean cloaked) {
		this.cloaked = cloaked;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ESSID)) return false;

		ESSID essid1 = (ESSID) o;

		if (essid != null ? !essid.equals(essid1.essid) : essid1.essid != null) return false;
		return cloaked != null ? cloaked.equals(essid1.cloaked) : essid1.cloaked == null;
	}

	@Override
	public int hashCode() {
		int result = essid != null ? essid.hashCode() : 0;
		result = 31 * result + (cloaked != null ? cloaked.hashCode() : 0);
		return result;
	}
}
