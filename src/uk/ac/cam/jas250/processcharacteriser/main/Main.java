package uk.ac.cam.jas250.processcharacteriser.main;

import uk.ac.cam.jas250.processcharacteriser.constants.Constants;
import uk.ac.cam.jas250.processcharacteriser.main.Characteriser;

public class Main {

	public static void main(String[] args) {
		Characteriser characteriser = new Characteriser();
		characteriser.characterise_process(Constants.PROCESS_FILTER, Constants.INPUT_FILE_PATH, Constants.OUTPUT_FILE_PATH);
	}

}
