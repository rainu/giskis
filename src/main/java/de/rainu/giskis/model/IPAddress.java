package de.rainu.giskis.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.rainu.giskis.sql.DatabaseConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE)

@Entity(name = DatabaseConstants.IP_ADDRESS)
@Access(AccessType.FIELD)
public class IPAddress implements DatabaseConstants {
	static final IPAddress EMPTY = new IPAddress();

	@XmlTransient
	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = IP_ADDRESS_ID)
	private Long id;

	@XmlAttribute(name = "type")
	@Column(name = IP_ADDRESS_TYPE)
	private String type;

	@XmlElement(name = "ip-block")
	@Column(name = IP_ADDRESS_BLOCK)
	private String block;

	@XmlElement(name = "ip-netmask")
	@Column(name = IP_ADDRESS_NETMASK)
	private String netmask;

	@XmlElement(name = "ip-gateway")
	@Column(name = IP_ADDRESS_GATEWAY)
	private String gateway;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getType() {
		return type;
	}

	public String getBlock() {
		return block;
	}

	public String getNetmask() {
		return netmask;
	}

	public String getGateway() {
		return gateway;
	}

	public boolean isEmpty(){
		return this.equals(EMPTY);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof IPAddress)) return false;

		IPAddress ipAddress = (IPAddress) o;

		if (type != null ? !type.equals(ipAddress.type) : ipAddress.type != null) return false;
		if (block != null ? !block.equals(ipAddress.block) : ipAddress.block != null) return false;
		if (netmask != null ? !netmask.equals(ipAddress.netmask) : ipAddress.netmask != null) return false;
		return gateway != null ? gateway.equals(ipAddress.gateway) : ipAddress.gateway == null;
	}

	@Override
	public int hashCode() {
		int result = type != null ? type.hashCode() : 0;
		result = 31 * result + (block != null ? block.hashCode() : 0);
		result = 31 * result + (netmask != null ? netmask.hashCode() : 0);
		result = 31 * result + (gateway != null ? gateway.hashCode() : 0);
		return result;
	}
}
