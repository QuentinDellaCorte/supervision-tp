package supervision;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "device")
@XmlType(propOrder = { "id_device", "device_name", "device_type", "ipadresse", "community", "oid" })

public class Device {

	private String id_device;
	private String device_name;
	private String device_type;
	private String ipadresse;
	private String community;
	private String oid;
	
	public Device() {
		super();
	}
	public String getId_device() {
		return id_device;
	}
	public void setId_device(String id_device) {
		this.id_device = id_device;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getIpadresse() {
		return ipadresse;
	}
	public void setIpadresse(String ipadresse) {
		this.ipadresse = ipadresse;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
}
