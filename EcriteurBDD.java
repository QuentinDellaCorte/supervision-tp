package supervision;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;

public class EcriteurBDD {

	private HashMap<String, String> mesdevices;

	public EcriteurBDD(HashMap<String, String> mesdevices) {
		super();
		this.mesdevices = mesdevices;
	}
	
	public void EcrireBDD(HashMap<String, String> mesdevices) {
		String insert = "INSERT INTO Device"
				+ " values ('"+mesdevices.get("id_device")
				+"', '"+mesdevices.get("device_name")
				+"', '"+mesdevices.get("device_type")
				+ "','"+mesdevices.get("Ipadresse")
				+ "','"+mesdevices.get("community")
				+ "','"+mesdevices.get("oid")
				+ "')";
		
		try { // create a java mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/snmp";
			Statement statement;
			Class.forName(myDriver);
		/*	String query = "SELECT * FROM Device";
		     ResultSet rs = requete.executeQuery(query);*/
			Connection conn = DriverManager.getConnection(myUrl, "root", "Pa$$w0rd");
			statement = conn.createStatement();
			statement.executeUpdate(insert);
			conn.close();
		} catch (Exception e) { System.err.println("Got an exception! ");
		System.err.println(e.getMessage());
		}
	}
}
