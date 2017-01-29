package de.rainu.giskis.model;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.sql.DatabaseConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)

@Entity(name = DatabaseConstants.GPS_INFO)
@Access(AccessType.FIELD)
public class GPSInfo implements DatabaseConstants {
	static final GPSInfo EMPTY = new GPSInfo();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = GPS_INFO_ID)
	private Long id;

	@XmlElement(name = "min-lat")
	@Column(name = GPS_INFO_MIN_LAT)
	private Double minLat;

	@XmlElement(name = "min-lon")
	@Column(name = GPS_INFO_MIN_LON)
	private Double minLon;

	@XmlElement(name = "min-alt")
	@Column(name = GPS_INFO_MIN_ALT)
	private Double minAlt;

	@XmlElement(name = "min-spd")
	@Column(name = GPS_INFO_MIN_SPD)
	private Double minSpd;

	@XmlElement(name = "max-lat")
	@Column(name = GPS_INFO_MAX_LAT)
	private Double maxLat;

	@XmlElement(name = "max-lon")
	@Column(name = GPS_INFO_MAX_LON)
	private Double maxLon;

	@XmlElement(name = "max-alt")
	@Column(name = GPS_INFO_MAX_ALT)
	private Double maxAlt;

	@XmlElement(name = "max-spd")
	@Column(name = GPS_INFO_MAX_SPD)
	private Double maxSpd;

	@XmlElement(name = "peak-lat")
	@Column(name = GPS_INFO_PEAK_LAT)
	private Double peakLat;

	@XmlElement(name = "peak-lon")
	@Column(name = GPS_INFO_PEAK_LON)
	private Double peakLon;

	@XmlElement(name = "peak-alt")
	@Column(name = GPS_INFO_PEAK_ALT)
	private Double peakAlt;

	@XmlElement(name = "avg-lat")
	@Column(name = GPS_INFO_AVERAGE_LAT)
	private Double averageLat;

	@XmlElement(name = "avg-lon")
	@Column(name = GPS_INFO_AVERAGE_LON)
	private Double averageLon;

	@XmlElement(name = "avg-alt")
	@Column(name = GPS_INFO_AVERAGE_ALT)
	private Double averageAlt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GeoPoint getMin(){
		return new GeoPoint(minLat, minLon, minAlt);
	}

	public GeoPoint getMax(){
		return new GeoPoint(maxLat, maxLon, maxAlt);
	}

	public GeoPoint getPeak(){
		return new GeoPoint(peakLat, peakLon, peakAlt);
	}

	public GeoPoint getAverage(){
		return new GeoPoint(averageLat, averageLon, averageAlt);
	}

	public Double getMinLat() {
		return minLat;
	}

	public void setMinLat(Double minLat) {
		this.minLat = minLat;
	}

	public Double getMinLon() {
		return minLon;
	}

	public void setMinLon(Double minLon) {
		this.minLon = minLon;
	}

	public Double getMinAlt() {
		return minAlt;
	}

	public void setMinAlt(Double minAlt) {
		this.minAlt = minAlt;
	}

	public Double getMinSpd() {
		return minSpd;
	}

	public void setMinSpd(Double minSpd) {
		this.minSpd = minSpd;
	}

	public Double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(Double maxLat) {
		this.maxLat = maxLat;
	}

	public Double getMaxLon() {
		return maxLon;
	}

	public void setMaxLon(Double maxLon) {
		this.maxLon = maxLon;
	}

	public Double getMaxAlt() {
		return maxAlt;
	}

	public void setMaxAlt(Double maxAlt) {
		this.maxAlt = maxAlt;
	}

	public Double getMaxSpd() {
		return maxSpd;
	}

	public void setMaxSpd(Double maxSpd) {
		this.maxSpd = maxSpd;
	}

	public Double getPeakLat() {
		return peakLat;
	}

	public void setPeakLat(Double peakLat) {
		this.peakLat = peakLat;
	}

	public Double getPeakLon() {
		return peakLon;
	}

	public void setPeakLon(Double peakLon) {
		this.peakLon = peakLon;
	}

	public Double getPeakAlt() {
		return peakAlt;
	}

	public void setPeakAlt(Double peakAlt) {
		this.peakAlt = peakAlt;
	}

	public Double getAverageLat() {
		return averageLat;
	}

	public void setAverageLat(Double averageLat) {
		this.averageLat = averageLat;
	}

	public Double getAverageLon() {
		return averageLon;
	}

	public void setAverageLon(Double averageLon) {
		this.averageLon = averageLon;
	}

	public Double getAverageAlt() {
		return averageAlt;
	}

	public void setAverageAlt(Double averageAlt) {
		this.averageAlt = averageAlt;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof GPSInfo)) return false;

		GPSInfo gpsInfo = (GPSInfo) o;

		if (minLat != null ? !minLat.equals(gpsInfo.minLat) : gpsInfo.minLat != null) return false;
		if (minLon != null ? !minLon.equals(gpsInfo.minLon) : gpsInfo.minLon != null) return false;
		if (minAlt != null ? !minAlt.equals(gpsInfo.minAlt) : gpsInfo.minAlt != null) return false;
		if (minSpd != null ? !minSpd.equals(gpsInfo.minSpd) : gpsInfo.minSpd != null) return false;
		if (maxLat != null ? !maxLat.equals(gpsInfo.maxLat) : gpsInfo.maxLat != null) return false;
		if (maxLon != null ? !maxLon.equals(gpsInfo.maxLon) : gpsInfo.maxLon != null) return false;
		if (maxAlt != null ? !maxAlt.equals(gpsInfo.maxAlt) : gpsInfo.maxAlt != null) return false;
		if (maxSpd != null ? !maxSpd.equals(gpsInfo.maxSpd) : gpsInfo.maxSpd != null) return false;
		if (peakLat != null ? !peakLat.equals(gpsInfo.peakLat) : gpsInfo.peakLat != null) return false;
		if (peakLon != null ? !peakLon.equals(gpsInfo.peakLon) : gpsInfo.peakLon != null) return false;
		if (peakAlt != null ? !peakAlt.equals(gpsInfo.peakAlt) : gpsInfo.peakAlt != null) return false;
		if (averageLat != null ? !averageLat.equals(gpsInfo.averageLat) : gpsInfo.averageLat != null) return false;
		if (averageLon != null ? !averageLon.equals(gpsInfo.averageLon) : gpsInfo.averageLon != null) return false;
		return averageAlt != null ? averageAlt.equals(gpsInfo.averageAlt) : gpsInfo.averageAlt == null;
	}

	@Override
	public int hashCode() {
		int result = minLat != null ? minLat.hashCode() : 0;
		result = 31 * result + (minLon != null ? minLon.hashCode() : 0);
		result = 31 * result + (minAlt != null ? minAlt.hashCode() : 0);
		result = 31 * result + (minSpd != null ? minSpd.hashCode() : 0);
		result = 31 * result + (maxLat != null ? maxLat.hashCode() : 0);
		result = 31 * result + (maxLon != null ? maxLon.hashCode() : 0);
		result = 31 * result + (maxAlt != null ? maxAlt.hashCode() : 0);
		result = 31 * result + (maxSpd != null ? maxSpd.hashCode() : 0);
		result = 31 * result + (peakLat != null ? peakLat.hashCode() : 0);
		result = 31 * result + (peakLon != null ? peakLon.hashCode() : 0);
		result = 31 * result + (peakAlt != null ? peakAlt.hashCode() : 0);
		result = 31 * result + (averageLat != null ? averageLat.hashCode() : 0);
		result = 31 * result + (averageLon != null ? averageLon.hashCode() : 0);
		result = 31 * result + (averageAlt != null ? averageAlt.hashCode() : 0);
		return result;
	}
}
