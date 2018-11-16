package supervision;

import java.util.HashMap;

import supervision.LecteurConf;


public class Moteur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LecteurConf lecteur = new LecteurConf("/root/supervision/supervision.conf");
		HashMap<String, String> mesdevices = lecteur.lireConf();
		mesdevices.toString();
		EcriteurBDD ecriteur = new EcriteurBDD(mesdevices);
		ecriteur.EcrireBDD(mesdevices);
	}
}
