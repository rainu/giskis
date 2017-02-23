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

@Entity(name = DatabaseConstants.TAG)
@Access(AccessType.FIELD)
@Table(indexes = {
		  @Index(columnList = DatabaseConstants.TAG_CLIENT_REF),
		  @Index(columnList = DatabaseConstants.TAG_NETWORK_REF),
})
public class Tag implements DatabaseConstants {
	static final Tag EMPTY = new Tag();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = TAG_ID)
	private BigInteger id;

	@XmlValue
	@Column(name = TAG_VALUE)
	private String value;

	@XmlAttribute(name = "name")
	@Column(name = TAG_NAME)
	private String name;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tag)) return false;

		Tag tag = (Tag) o;

		if (value != null ? !value.equals(tag.value) : tag.value != null) return false;
		return name != null ? name.equals(tag.name) : tag.name == null;
	}

	@Override
	public int hashCode() {
		int result = value != null ? value.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
