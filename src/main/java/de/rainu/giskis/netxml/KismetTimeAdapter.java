package de.rainu.giskis.netxml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * This {@link XmlAdapter} is responsible for un/marshalling {@link LocalDateTime} based on the kismet used date format.
 */
public class KismetTimeAdapter extends XmlAdapter<String, LocalDateTime> {
	private static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss yyyy").withLocale(Locale.US);

	@Override
	public LocalDateTime unmarshal(String s) throws Exception {
		return LocalDateTime.parse(s, FORMAT);
	}

	@Override
	public String marshal(LocalDateTime localDateTime) throws Exception {
		if(localDateTime == null) {
			return "";
		}

		return localDateTime.format(FORMAT);
	}
}
