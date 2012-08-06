package uk.ac.cam.jas250.processcharacteriser.main;

import java.io.FileNotFoundException;

import uk.ac.cam.jas250.processcharacteriser.constants.Constants;
import uk.ac.cam.jas250.processcharacteriser.input.FTrace_Input;
import uk.ac.cam.jas250.processcharacteriser.input.InputData_Interface;
import uk.ac.cam.jas250.processcharacteriser.input.SystemTap_Input;

public class Main {

	public static void main(String[] args) {
		//Parse the file into trace objects
		InputData_Interface data_input = null;
		try{
			if (Constants.TRACE_TYPE == "FTRACE"){
				data_input = new FTrace_Input(Constants.FILE_PATH);
			}else if(Constants.TRACE_TYPE == "SYSTEMTAP"){// <-- Not implemented yet TODO!
				data_input = new SystemTap_Input();
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		//Iterate through the objects in timestamp order
		
		//Get the next 'n' objects and add them to the following array
	}

}
