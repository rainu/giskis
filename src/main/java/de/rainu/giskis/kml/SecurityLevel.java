package de.rainu.giskis.kml;

enum SecurityLevel {
	SECURE("http://maps.google.com/mapfiles/kml/pushpin/grn-pushpin.png"),
	INSECURE("http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png"),
	OPEN("http://maps.google.com/mapfiles/kml/pushpin/red-pushpin.png"),
	WPS("http://maps.google.com/mapfiles/kml/pushpin/ltblu-pushpin.png");

	private String iconHref;

	SecurityLevel(String iconHref){
		this.iconHref = iconHref;
	}

	public String getIconHref() {
		return iconHref;
	}
}
