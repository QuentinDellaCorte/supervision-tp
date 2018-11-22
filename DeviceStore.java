package supervision;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "supervision")
public class DeviceStore {

	@XmlElementWrapper(name = "deviceList")
	@XmlElement(name = "device")
	private ArrayList<Device> deviceList;
	private String name;
	
	public ArrayList<Device> getDevicelist() {
		return deviceList;
	}
	public void setDevicelist(ArrayList<Device> devicelist) {
		this.deviceList = devicelist;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
