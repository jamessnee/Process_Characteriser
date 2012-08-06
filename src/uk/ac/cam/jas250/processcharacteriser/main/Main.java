package uk.ac.cam.jas250.processcharacteriser.main;

import java.io.FileNotFoundException;

import uk.ac.cam.jas250.processcharacteriser.constants.Constants;
import uk.ac.cam.jas250.processcharacteriser.input.FTrace_Input;
import uk.ac.cam.jas250.processcharacteriser.input.InputData_Interface;
import uk.ac.cam.jas250.processcharacteriser.input.SystemTap_Input;
import uk.ac.cam.jas250.processcharacteriser.models.TraceEntry;

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
		
		TraceEntry[] trace = data_input.get_all_trace_entries(Constants.PROCESS_FILTER);
		
		//Iterate through the objects in timestamp order
		for(int i=0;i<trace.length;i++){
			TraceEntry current_entry = trace[i];
			TraceEntry[] followers = new TraceEntry[Constants.WINDOW_SIZE];
			//'Walk' forwards and fill the followers array
			for(int j=0;j<Constants.WINDOW_SIZE;j++)
				if(i+j<trace.length-1)
					followers[j] = trace[i+j];
			current_entry.setFollowingEntries(followers);
		}
	}

}
