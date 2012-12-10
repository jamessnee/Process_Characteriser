package uk.ac.cam.jas250.processcharacteriser.main;

import java.util.Hashtable;
import uk.ac.cam.jas250.processcharacteriser.input.Config_Reader;
import uk.ac.cam.jas250.processcharacteriser.main.Characteriser;

public class Main {

	public static void main(String[] args) {
	
		Hashtable<String,String> config = null;
		
		if(args.length>0)
			config = Config_Reader.get_configuration(args[0]);
		else
			config = Config_Reader.get_configuration("/homes/jas250/Documents/PhD/Experiments/Process_Characteriser_FirstTests/experiment.conf");
		
		//CONFIG DEBUG
		/*
		for(String key : config.keySet()){
			System.out.println(key);
			System.out.println(config.get(key));
		}
		*/	
		
		Characteriser characteriser = new Characteriser();
		characteriser.characterise_process(config);
		
		return;
	}

}
