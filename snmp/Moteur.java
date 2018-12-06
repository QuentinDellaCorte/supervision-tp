package supervision;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class Moteur {

	private static final String DEVICESTORE_XML = "/root/supervision/supervision.conf";
	
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		
		//CREATE JAXB CONTEXTAND INSTANTIATE MARSHALLER
		JAXBContext context = JAXBContext.newInstance(DeviceStore.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
        // get variables from our xml file, created before
        Unmarshaller um = context.createUnmarshaller();
        DeviceStore devicestore = (DeviceStore) um.unmarshal(new FileReader(DEVICESTORE_XML));
        ArrayList<Device> deviceList = devicestore.getDevicelist();
        EcriteurBDD ecriteur = new EcriteurBDD(deviceList);
    // ecriteur.deviceList();
        
//        Boolean bool1 = ecriteur.Authenticate("user", "user");
//        System.out.println(bool1.toString());
//        if (bool1==true) {
//       	System.out.println("Authentification user réussie");
//        }
        
      ecriteur.EcrireBDD();
      for (int i = 0; i < deviceList.size(); i++) {
			SnmpManager manager = new SnmpManager(deviceList.get(i));
			manager.snmpRequest();
           
			Thread t = new Thread(manager);
			t.start();
        }
	}
}