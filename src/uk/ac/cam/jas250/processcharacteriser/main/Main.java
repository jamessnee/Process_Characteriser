package uk.ac.cam.jas250.processcharacteriser.main;

import java.io.FileNotFoundException;
import java.util.Hashtable;

import uk.ac.cam.jas250.processcharacteriser.input.Config_Reader;
import uk.ac.cam.jas250.processcharacteriser.main.Characteriser;

public class Main {

	public static void main(String[] args) {
		Hashtable<String,String> config;
		try {
			config = Config_Reader.read_config("/home/jas250/Documents/PhD/Experiments/Process_Characteriser_FirstTests/experiment.conf");
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the config file");
			e.printStackTrace();
			return;
		}
		Characteriser characteriser = new Characteriser();
		characteriser.characterise_process(config);
	}

}
