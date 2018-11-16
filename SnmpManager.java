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


public class SnmpManager {
			String device_name;
		    String community;
		    String adresse;
		    String OID;
		
	
	     
	      public void SnmpRequest() {
	    	  
	    	      	  
	    	  try {
		           Snmp snmp4j =  new Snmp(new DefaultUdpTransportMapping());
		           snmp4j.listen();
		           Address add = new UdpAddress("192.168.140.143  "+"/"+"161");
		           CommunityTarget target = new CommunityTarget();
		           target.setAddress(add);
		           target.setTimeout(500);                
		           target.setRetries(3);

		           target.setCommunity(new OctetString("univ-1"));
		           target.setVersion(SnmpConstants.version2c);
		           
		           PDU request = new PDU();
		           request.setType(PDU.GET);
		           OID oid= new OID(".1.3.6.1.2.1.1.5.0");
		           request.add(new VariableBinding(oid));

		           PDU responsePDU=null;
		           ResponseEvent responseEvent;
		           responseEvent = snmp4j.send(request, target);
		           if (responseEvent != null)
		           {
		               responsePDU = responseEvent.getResponse();
		               if ( responsePDU != null)
		               {    
		                   Vector <VariableBinding> tmpv = responsePDU.getVariableBindings();
		                   if(tmpv != null)
		                   {
		                       for(int k=0; k <tmpv.size();k++)
		                       {
		                           VariableBinding vb = (VariableBinding) tmpv.get(k);
		                           String output = null;
		                           if ( vb.isException())
		                           {

		                               String errorstring = vb.getVariable().getSyntaxString();
		                               System.out.println("Error:"+errorstring);
		                           }
		                           else
		                           {
		                               String sOid = vb.getOid().toString();
		                               Variable var = vb.getVariable();
		                               OctetString oct = new OctetString((OctetString)var);
		                               String sVar =  oct.toString();

		                               System.out.println("success:"+sVar);
		                           }                      
		                   }
		              }

		           }
		           }

		       } catch (IOException e) {
		            
		           e.printStackTrace();
		       }

	    	  
	     	  
	    	  
	      }

		public String getDevice_name() {
			return device_name;
		}

		public void setDevice_name(String device_name) {
			this.device_name = device_name;
		}

		public String getCommunity() {
			return community;
		}

		public void setCommunity(String community) {
			this.community = community;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public String getOID() {
			return OID;
		}

		public void setOID(String oID) {
			OID = oID;
		}
	
	       
	       
	       
}
