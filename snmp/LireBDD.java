package supervision;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LireBDD implements LireBDDLocal {


	public String deviceList() {
		
		String  device_name=null; 
		String device_type=null; 
		String IpAdresse =null;
		String community =null;
		
		String select = "SELECT * FROM Device ";
		
		try { 
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/snmp";
			Statement statement;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "Pa$$w0rd");
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(select);
		while (rs.next()) {
			
			device_name = rs.getString("device_name");
		    device_type = rs.getString("device_type");
		    IpAdresse = rs.getString("IpAdresse");
		    community = rs.getString("community");
		 //  System.out.format("%s, %s, %s, %s\n", device_name, device_type, IpAdresse, community);
		}	
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	
		return device_name;
		
	
	}
}
