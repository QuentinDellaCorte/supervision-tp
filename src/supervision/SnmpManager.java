package supervision;

import java.io.IOException;
import java.util.Vector;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.smi.*;
import org.snmp4j.mp.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.event.ResponseEvent;

public class SnmpManager implements Runnable {

	private Device device;

	public SnmpManager(Device device) {
		super();
		this.device = device;
	}

	public void snmpRequest() {
		try {
			Snmp snmp4j = new Snmp(new DefaultUdpTransportMapping());
			snmp4j.listen();
			Address add = new UdpAddress(this.device.getIpadresse() + "/" + "161");
			CommunityTarget target = new CommunityTarget();
			target.setAddress(add);
			target.setTimeout(500);
			target.setRetries(3);
			target.setCommunity(new OctetString(this.device.getCommunity()));
			target.setVersion(SnmpConstants.version2c);
			PDU request = new PDU();
			request.setType(PDU.GET);
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
								String errorstring = vb.getVariable().getSyntaxString();
								System.out.println("Error:" + errorstring);
							} else {

								Variable var = vb.getVariable();
								int oct = var.toInt();
								// OctetString oct = new OctetString((OctetString)var);
								// String sVar = oct.toString();
								System.out.println("Trafic entrant sur une interface:  " + oct);
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
