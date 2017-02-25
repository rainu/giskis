package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.nosql.DatabaseConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.xml.bind.annotation.*;

import java.math.BigInteger;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)
public class Dot11dRange implements DatabaseConstants {
	static final Dot11dRange EMPTY = new Dot11dRange();

	@XmlTransient
	@JsonIgnore
	@Id
	private BigInteger id;

	@XmlAttribute(name = "start")
	@Field(DOT11D_RANGE_START)
	private Integer rangeStart;

	@XmlAttribute(name = "end")
	@Field(DOT11D_RANGE_END)
	private Integer rangeEnd;

	@XmlAttribute(name = "max-power")
	@Field(DOT11D_RANGE_MAX_POWER)
	private Integer maxPower;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getRangeStart() {
		return rangeStart;
	}

	public void setRangeStart(Integer rangeStart) {
		this.rangeStart = rangeStart;
	}

	public Integer getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(Integer rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	public Integer getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(Integer maxPower) {
		this.maxPower = maxPower;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Dot11dRange)) return false;

		Dot11dRange that = (Dot11dRange) o;

		if (rangeStart != null ? !rangeStart.equals(that.rangeStart) : that.rangeStart != null) return false;
		if (rangeEnd != null ? !rangeEnd.equals(that.rangeEnd) : that.rangeEnd != null) return false;
		return maxPower != null ? maxPower.equals(that.maxPower) : that.maxPower == null;
	}

	@Override
	public int hashCode() {
		int result = rangeStart != null ? rangeStart.hashCode() : 0;
		result = 31 * result + (rangeEnd != null ? rangeEnd.hashCode() : 0);
		result = 31 * result + (maxPower != null ? maxPower.hashCode() : 0);
		return result;
	}
}
