package supervision;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class EcriteurBDD {

	private ArrayList<Device> deviceList;

	public EcriteurBDD(ArrayList<Device> deviceList) {
		super();
		this.deviceList = deviceList;
	}

	public void insertInBDD(String requet) {
		try { // create a java mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/snmp";
			Statement statement;
			Class.forName(myDriver);
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
}
