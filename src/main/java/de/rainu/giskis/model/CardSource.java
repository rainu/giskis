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
public class CardSource implements DatabaseConstants {
	static final CardSource EMPTY = new CardSource();

	@XmlTransient
	@JsonIgnore
	@Id
	private BigInteger id;

	@XmlAttribute(name = "uuid")
	@Field(CARD_SOURCE_UUID)
	private String uuid;

	@XmlElement(name = "card-source")
	@Field(CARD_SOURCE_SOURCE)
	private String source;

	@XmlElement(name = "card-name")
	@Field(CARD_SOURCE_NAME)
	private String name;

	@XmlElement(name = "card-interface")
	@Field(CARD_SOURCE_INTERFACE)
	private String interf;

	@XmlElement(name = "card-type")
	@Field(CARD_SOURCE_TYPE)
	private String type;

	@XmlElement(name = "card-packets")
	@Field(CARD_SOURCE_PACKETS)
	private Integer packets;

	@XmlElement(name = "card-hop")
	@Field(CARD_SOURCE_HOP)
	private Boolean hop;

	@XmlElement(name = "card-channels")
	@Field(CARD_SOURCE_CHANNELS)
	private String channels;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterf() {
		return interf;
	}

	public void setInterf(String interf) {
		this.interf = interf;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPackets() {
		return packets;
	}

	public void setPackets(Integer packets) {
		this.packets = packets;
	}

	public Boolean getHop() {
		return hop;
	}

	public void setHop(Boolean hop) {
		this.hop = hop;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CardSource)) return false;

		CardSource that = (CardSource) o;

		if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
		if (source != null ? !source.equals(that.source) : that.source != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (interf != null ? !interf.equals(that.interf) : that.interf != null) return false;
		if (type != null ? !type.equals(that.type) : that.type != null) return false;
		if (packets != null ? !packets.equals(that.packets) : that.packets != null) return false;
		if (hop != null ? !hop.equals(that.hop) : that.hop != null) return false;
		return channels != null ? channels.equals(that.channels) : that.channels == null;
	}

	@Override
	public int hashCode() {
		int result = uuid != null ? uuid.hashCode() : 0;
		result = 31 * result + (source != null ? source.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (interf != null ? interf.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (packets != null ? packets.hashCode() : 0);
		result = 31 * result + (hop != null ? hop.hashCode() : 0);
		result = 31 * result + (channels != null ? channels.hashCode() : 0);
		return result;
	}
}
