package de.rainu.giskis.model;

public class GeoPoint {
	public static final GeoPoint EMPTY = new GeoPoint(null, null, null);

	private Double lat;
	private Double lon;
	private Double alt;

	public GeoPoint(Double lat, Double lon, Double alt) {
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
	}

	public Double getLat() {
		return lat;
	}

	public Double getLon() {
		return lon;
	}

	public Double getAlt() {
		return alt;
	}

	public boolean isEmpty() {
		return this.equals(EMPTY);
	}

	public boolean isInvalid() {
		return (lat == null || lat == 0) &&
				 (lon == null || lon == 0) &&
				 (alt == null || alt == 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof GeoPoint)) return false;

		GeoPoint geoPoint = (GeoPoint) o;

		if (lat != null ? !lat.equals(geoPoint.lat) : geoPoint.lat != null) return false;
		if (lon != null ? !lon.equals(geoPoint.lon) : geoPoint.lon != null) return false;
		return alt != null ? alt.equals(geoPoint.alt) : geoPoint.alt == null;
	}

	@Override
	public int hashCode() {
		int result = lat != null ? lat.hashCode() : 0;
		result = 31 * result + (lon != null ? lon.hashCode() : 0);
		result = 31 * result + (alt != null ? alt.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "GeoPoint{" +
				  "lat=" + lat +
				  ", lon=" + lon +
				  ", alt=" + alt +
				  '}';
	}
}
