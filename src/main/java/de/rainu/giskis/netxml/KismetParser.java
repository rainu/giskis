package de.rainu.giskis.netxml;

import de.rainu.giskis.model.DetectionRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.annotation.PostConstruct;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.nio.file.Path;
import java.util.function.Function;

/**
 * This class is responsible for parsing kismet files.
 */
@Component
public class KismetParser {
	private JAXBContext jaxbContext;
	private XMLReader xmlReader;

	@PostConstruct
	public void setup() throws Exception {
		jaxbContext = JAXBContext.newInstance(DetectionRun.class);

		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
		xmlReader = spf.newSAXParser().getXMLReader();
	}

	/**
	 * Parse Kismet file and return the {@link DetectionRun} model.
	 */
	public DetectionRun parse(File kismetFile) throws FileNotFoundException, JAXBException {
		InputSource inputSource = new InputSource(new FileReader(kismetFile));
		SAXSource source = new SAXSource(xmlReader, inputSource);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return (DetectionRun) unmarshaller.unmarshal(source);
	}
}
