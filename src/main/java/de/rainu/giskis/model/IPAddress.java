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
public class IPAddress implements DatabaseConstants {
	static final IPAddress EMPTY = new IPAddress();

	@XmlTransient
	@JsonIgnore
	@Id
	private BigInteger id;

	@XmlAttribute(name = "type")
	@Field(IP_ADDRESS_TYPE)
	private String type;

	@XmlElement(name = "ip-block")
	@Field(IP_ADDRESS_BLOCK)
	private String block;

	@XmlElement(name = "ip-netmask")
	@Field(IP_ADDRESS_NETMASK)
	private String netmask;

	@XmlElement(name = "ip-gateway")
	@Field(IP_ADDRESS_GATEWAY)
	private String gateway;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
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
