package uk.ac.cam.jas250.processcharacteriser.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import uk.ac.cam.jas250.processcharacteriser.input.FTrace_Input;
import uk.ac.cam.jas250.processcharacteriser.input.InputData_Interface;
import uk.ac.cam.jas250.processcharacteriser.models.TraceEntry;

public class Characteriser {
	public void characterise_process(Hashtable<String,String>config){
		
		//Parse the file into trace objects
		InputData_Interface data_input = null;
		try{
			data_input = new FTrace_Input(config);
		}catch(FileNotFoundException e){
			System.out.println("Couldn't initialise the data_input");
			e.printStackTrace();
			return;
		}
		
		TraceEntry[] trace = null;
		try{
			trace = data_input.get_all_trace_entries(config);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		int window_size = Integer.parseInt(config.get("WINDOW_SIZE"));
		//Iterate through the objects in timestamp order
		for(int i=0;i<trace.length;i++){
			TraceEntry current_entry = trace[i];
			TraceEntry[] followers = new TraceEntry[window_size];
			//'Walk' forwards and fill the followers array
			for(int j=0;j<window_size;j++)
				if(i+j+1<trace.length-1)
					followers[j] = trace[i+j+1];
			current_entry.setFollowingEntries(followers);
		}
		output_characterised_trace(config.get("OUTPUT_FILE_PATH"), trace);
		System.out.println("Finished characterising the "+config.get("PROCESS_FILTER")+" process");
	}
	
	/*
	 * Trace output format:
	 * functionName:follower1,follower2,...followerN-FunctionName...
	 */
	public void output_characterised_trace(String file_path, TraceEntry[] trace){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(file_path),false));
		} catch (IOException e) {
			System.out.println("Couldn't open the output file");
			e.printStackTrace();
			return;
		}
		
		for(int i=0;i<trace.length;i++){
			TraceEntry current_entry = trace[i];
			String line = "";
			line+=current_entry.getFunctionName()+":";
			TraceEntry[] followers = current_entry.getFollowingEntries();
			for(int j=0;j<followers.length;j++){
				if(followers[j]!=null){
					line+=followers[j].getFunctionName();
					if(j!=followers.length-1)
						line+=",";
				}
			}
			if(i!=trace.length-1)
				line+="\n";
			try {
				bw.write(line);
			} catch (IOException e) {
				System.out.println("Couldn't write to the output file");
				e.printStackTrace();
			}
		}
		
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.println("Couldn't close the output file");
			e.printStackTrace();
		}
		
	}
}
