package supervision;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EcriteurBDD {

	private ArrayList<Device> deviceList;

	public EcriteurBDD(ArrayList<Device> deviceList) {
		super();
		this.deviceList = deviceList;
	}

	public void insertInBDD(String result, int id_device) {
		try { // create a java mysql database connection
		
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/snmp";
			Statement statement;
			Class.forName(myDriver);
			String  requet = "INSERT INTO Logs "+"(id_device,log)"
					+ " values ('" + id_device
					+ "', '" + result
					+ "')";
			Connection conn = DriverManager.getConnection(myUrl, "root", "Pa$$w0rd");
			statement = conn.createStatement();
			statement.executeUpdate(requet);
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public void EcrireBDD() {
		for (int i = 0; i < this.deviceList.size(); i++) {
			String insert = "INSERT INTO Device"
					+ " values ('" + this.deviceList.get(i).getId_device()
					+ "', '" + this.deviceList.get(i).getDevice_name()
					+ "', '" + this.deviceList.get(i).getDevice_type()
					+ "', '" + this.deviceList.get(i).getIpadresse()
					+ "', '" + this.deviceList.get(i).getCommunity()
					+ "', '" + this.deviceList.get(i).getOid()
					+ "')";

			try { // create a java mysql database connection 
				String myDriver = "org.gjt.mm.mysql.Driver";
				String myUrl = "jdbc:mysql://localhost/snmp";
				Statement statement;
				Class.forName(myDriver);
				Connection conn = DriverManager.getConnection(myUrl, "root", "Pa$$w0rd");
				statement = conn.createStatement();
				statement.executeUpdate(insert);
				conn.close();
			} catch (Exception e) {
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
			}
		}
	}
	
	public Boolean Authenticate(String login, String password) {
		Boolean retour=null;
		String select = "SELECT * FROM Users WHERE login = '"+login+"';";
		String loginresult=null;
		String passwordresult=null;
		try { // create a java mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/snmp";
			Statement statement;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "Pa$$w0rd");
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(select);
			while(rs.next()) {
				loginresult = rs.getString("login");
				passwordresult = rs.getString("password");
			}
			if (password.equals(passwordresult)) {
				retour = true;
			} else {
				retour = false;
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return retour;
	}
	
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
			conn.close();
		while (rs.next()) {
			
			device_name = rs.getString("device_name");
		    device_type = rs.getString("device_type");
		    IpAdresse = rs.getString("IpAdresse");
		    community = rs.getString("community");
		    System.out.format("%s, %s, %s, %s\n", device_name, device_type, IpAdresse, community);
		}	
			
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return device_name;
		
	
	}
	
	
}
