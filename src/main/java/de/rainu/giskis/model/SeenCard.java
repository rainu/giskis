package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.netxml.KismetTimeAdapter;
import de.rainu.giskis.nosql.DatabaseConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)
public class SeenCard implements DatabaseConstants {
	static final SeenCard EMPTY = new SeenCard();

	@XmlTransient
	@JsonIgnore
	@Id
	private BigInteger id;

	@XmlElement(name = "seen-uuid")
	@Field(SEEN_CARD_UUID)
	private String UUID;

	@XmlElement(name = "seen-time")
	@XmlJavaTypeAdapter(KismetTimeAdapter.class)
	@Field(SEEN_CARD_TIME)
	private LocalDateTime time;

	@XmlElement(name = "seen-packets")
	@Field(SEEN_CARD_PACKETS)
	private Integer packets;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String UUID) {
		this.UUID = UUID;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Integer getPackets() {
		return packets;
	}

	public void setPackets(Integer packets) {
		this.packets = packets;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SeenCard)) return false;

		SeenCard seenCard = (SeenCard) o;

		if (UUID != null ? !UUID.equals(seenCard.UUID) : seenCard.UUID != null) return false;
		if (time != null ? !time.equals(seenCard.time) : seenCard.time != null) return false;
		return packets != null ? packets.equals(seenCard.packets) : seenCard.packets == null;
	}

	@Override
	public int hashCode() {
		int result = UUID != null ? UUID.hashCode() : 0;
		result = 31 * result + (time != null ? time.hashCode() : 0);
		result = 31 * result + (packets != null ? packets.hashCode() : 0);
		return result;
	}
}
