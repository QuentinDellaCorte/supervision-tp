package supervision;

import java.util.logging.Handler;
import java.util.logging.FileHandler;
import java.util.logging.*;
import org.snmp4j.PDU;

import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import java.io.IOException;
import java.io.InputStream;

import java.util.Vector;
import java.util.logging.LogManager;

import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.smi.*;
import org.snmp4j.mp.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.event.ResponseEvent;
import java.util.logging.FileHandler;


public class SnmpManager implements Runnable {

	private Device device;

	public SnmpManager(Device device) {
		super();
		this.device = device;
	}



	private static Logger LOGGER = null;
	static {

		System.setProperty("java.util.logging.SimpleFormatter.format","[%1$tF %1$tT] [%4$-7s] %5$s %n");

		LOGGER = Logger.getLogger(SnmpManager.class.getName());
		LOGGER.setLevel(Level.ALL);

		Handler fh;
		try
		{
			fh = new FileHandler("/root/Téléchargements/log.txt", 0, 1, true);
			fh.setFormatter(new SimpleFormatter());
			fh.setLevel(Level.INFO);
			LOGGER.addHandler(fh);
		}
		catch (IOException e) {
			LOGGER.severe("Impossible d'associer le FileHandler");
		}
	}





	public void snmpRequest() {
		String result;
		try {
			Snmp snmp4j = new Snmp(new DefaultUdpTransportMapping());
			snmp4j.listen();
			Address add = new UdpAddress(this.device.getIpadresse()+ "/" + "161");
			CommunityTarget target = new CommunityTarget();
			target.setAddress(add);
			target.setTimeout(500);
			target.setRetries(3);
			target.setCommunity(new OctetString(this.device.getCommunity()));
			target.setVersion(SnmpConstants.version2c);
			PDU request = new PDU();
			request.setType(PDU.GET);
			//	OID oid = new OID(this.device.getOid());
			OID oid = new OID(this.device.getOid());
			request.add(new VariableBinding(oid));
			PDU responsePDU = null;
			ResponseEvent responseEvent;

			responseEvent = snmp4j.send(request, target);
			if (responseEvent != null) {
				responsePDU = responseEvent.getResponse();
				if (responsePDU != null) {
					Vector<VariableBinding> tmpv = responsePDU.getVariableBindings();
					if (tmpv != null) {
						for (int k = 0; k < tmpv.size(); k++) {
							VariableBinding vb = (VariableBinding) tmpv.get(k);
							String output = null;
							if (vb.isException()) {
								 String sOid = vb.getOid().toString();
								String errorstring = vb.getVariable().getSyntaxString();
								System.out.println("Error:" + errorstring);
								 LOGGER.warning("Error:"+errorstring);  
	   							 LOGGER.info("OID: "+sOid);
	   							 LOGGER.log(Level.SEVERE,errorstring, new Exception());// les messages + la pile d'exécution
							} else {

								Variable var = vb.getVariable();
								int oct = var.toInt();
								String sOid = vb.getOid().toString();
								//	 OctetString oct = new OctetString((OctetString)var);
								// String sVar = oct.toString();
								////System.out.println("Trafic entrant sur une interface:  " + oct);
								// System.out.println("success:"+oct);
	   							 ///LOGGER.info("OID: "+ sOid);
	   							 LOGGER.info("SNMPReponse from "+this.device.getDevice_name() +"  "+ oct); //successeful
	   							 
							}
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			snmpRequest();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
