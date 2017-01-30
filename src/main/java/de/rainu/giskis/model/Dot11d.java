package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.sql.DatabaseConstants;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)

@Entity(name = DatabaseConstants.DOT11D)
@Access(AccessType.FIELD)
public class Dot11d implements DatabaseConstants {
	static final Dot11d EMPTY = new Dot11d();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = DOT11D_ID)
	private BigInteger id;

	@XmlAttribute(name = "country")
	@Column(name = DOT11D_COUNTRY)
	private String country;

	@XmlElement(name = "dot11d-range")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = DOT11D_RANGE_DOT11D)
	@Fetch(FetchMode.SELECT)
	private List<Dot11dRange> ranges = new ArrayList<>();

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Dot11dRange> getRanges() {
		return ranges;
	}

	public void setRanges(List<Dot11dRange> ranges) {
		this.ranges = ranges;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Dot11d)) return false;

		Dot11d dot11d = (Dot11d) o;

		if (country != null ? !country.equals(dot11d.country) : dot11d.country != null) return false;
		return ranges != null ? ranges.equals(dot11d.ranges) : dot11d.ranges == null;
	}

	@Override
	public int hashCode() {
		int result = country != null ? country.hashCode() : 0;
		result = 31 * result + (ranges != null ? ranges.hashCode() : 0);
		return result;
	}
}
