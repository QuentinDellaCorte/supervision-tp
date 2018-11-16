package supervision;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LecteurConf {

	private String confFile;

	public LecteurConf(String confFile) {
		super();
		this.confFile = confFile;
	}

	public HashMap<String, String> lireConf() {
		
		HashMap<String, String> mesdevices = new HashMap<>();
		
		try {
			InputStream flux=new FileInputStream(this.confFile); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			while ((ligne=buff.readLine())!=null) {
				System.out.println(ligne);
				String[] keyvalue = ligne.split("=");
				mesdevices.put(keyvalue[0], keyvalue[1]);
			}
			buff.close(); 
		} catch (Exception e){
			System.out.println(e.toString());
		}
		return mesdevices;
	}
	
	public ArrayList<HashMap<String, String>> lireConf2() {
		
		ArrayList<HashMap<String, String>> mesdevices = new ArrayList<>();
		
		try {
			InputStream flux=new FileInputStream(this.confFile); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			while ((ligne=buff.readLine())!=null) {
				System.out.println(ligne);
				String[] keyvalue = ligne.split("=");
				mesdevices.put(keyvalue[0], keyvalue[1]);
			}
			buff.close();
		} catch (Exception e){
			System.out.println(e.toString());
		}
		return mesdevices;
	}
}
