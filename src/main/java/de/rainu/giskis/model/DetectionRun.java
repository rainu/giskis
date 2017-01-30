package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.netxml.KismetTimeAdapter;
import de.rainu.giskis.sql.DatabaseConstants;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@XmlRootElement(name = "detection-run")
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)

@Entity(name = DatabaseConstants.DETECTION_RUN)
@Access(AccessType.FIELD)
public class DetectionRun implements DatabaseConstants {
	static final DetectionRun EMPTY = new DetectionRun();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = DETECTION_RUN_ID)
	private BigInteger id;

	@XmlAttribute(name = "kismet-version")
	@Column(name = DETECTION_RUN_VERSION)
	private String kismetVersion;

	@XmlAttribute(name = "start-time")
	@XmlJavaTypeAdapter(KismetTimeAdapter.class)
	@Column(name = DETECTION_RUN_START)
	private LocalDateTime time;

	@XmlElement(name = "card-source")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = DETECTION_RUN_CARD_SOURCE)
	@Fetch(FetchMode.SELECT)
	private CardSource cardSource;

	@XmlElement(name = "wireless-network")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = WIRELESS_NETWORK_DETECTION_RUN)
	@Fetch(FetchMode.SELECT)
	private List<WirelessNetwork> wirelessNetworks = new ArrayList<>();

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getKismetVersion() {
		return kismetVersion;
	}

	public void setKismetVersion(String kismetVersion) {
		this.kismetVersion = kismetVersion;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public CardSource getCardSource() {
		return Optional.ofNullable(cardSource).orElse(CardSource.EMPTY);
	}

	public void setCardSource(CardSource cardSource) {
		this.cardSource = cardSource;
	}

	public List<WirelessNetwork> getWirelessNetworks() {
		return wirelessNetworks;
	}

	public void setWirelessNetworks(List<WirelessNetwork> wirelessNetworks) {
		this.wirelessNetworks = wirelessNetworks;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DetectionRun)) return false;

		DetectionRun that = (DetectionRun) o;

		if (kismetVersion != null ? !kismetVersion.equals(that.kismetVersion) : that.kismetVersion != null) return false;
		if (time != null ? !time.equals(that.time) : that.time != null) return false;
		if (cardSource != null ? !cardSource.equals(that.cardSource) : that.cardSource != null) return false;
		return wirelessNetworks != null ? wirelessNetworks.equals(that.wirelessNetworks) : that.wirelessNetworks == null;
	}

	@Override
	public int hashCode() {
		int result = kismetVersion != null ? kismetVersion.hashCode() : 0;
		result = 31 * result + (time != null ? time.hashCode() : 0);
		result = 31 * result + (cardSource != null ? cardSource.hashCode() : 0);
		result = 31 * result + (wirelessNetworks != null ? wirelessNetworks.hashCode() : 0);
		return result;
	}
}
