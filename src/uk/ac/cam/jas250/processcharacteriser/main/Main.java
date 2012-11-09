package uk.ac.cam.jas250.processcharacteriser.main;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import uk.ac.cam.jas250.processcharacteriser.input.Config_Reader;
import uk.ac.cam.jas250.processcharacteriser.main.Characteriser;

public class Main {

	public static void main(String[] args) {
		Hashtable<String,String> config = null;
		try {
			config = Config_Reader.read_config("/home/jas250/Documents/PhD/Experiments/Process_Characteriser_FirstTests/experiment.conf");
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the config file");
			e.printStackTrace();
		}
		
		if(config == null)
			config = get_config_from_user();
		
		Characteriser characteriser = new Characteriser();
		characteriser.characterise_process(config);
		
		return;
	}
	
	private static Hashtable<String,String> get_config_from_user(){
		
		
		return new Hashtable<String, String>();
	}

}
