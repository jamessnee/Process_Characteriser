package uk.ac.cam.jas250.processcharacteriser.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import uk.ac.cam.jas250.processcharacteriser.input.FTrace_Input;
import uk.ac.cam.jas250.processcharacteriser.input.InputData_Interface;
import uk.ac.cam.jas250.processcharacteriser.models.TraceEntry;

public class Characteriser {
	public void characterise_process(Hashtable<String,String>config){
		System.out.println("Characterising process: "+config.get("PROCESS_FILTER"));
		
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
			trace = data_input.get_all_trace_entries(config); // Read in all of the trace entries
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		// Split up the entries into followers for the correct window size
		int window_size = Integer.parseInt(config.get("WINDOW_SIZE"));
		for(int i=0;i<trace.length;i++){
			TraceEntry current_entry = trace[i];
			ArrayList<TraceEntry> followers = new ArrayList<TraceEntry>();
			for(int j=0;j<window_size;j++)
				if(i+j+1<trace.length)
					followers.add(trace[i+j+1].copy());
			current_entry.setFollowingEntries(followers);
		}
		
//		System.out.println("List after splitting:");
//		for(int i=0;i<trace.length;i++){
//			System.out.println(trace[i]);
//		}
//		System.out.println("\n");
		
		ArrayList<TraceEntry> col_trace = collate_into_list(trace);
//		System.out.println("List after collating:");
//		for(TraceEntry ent : col_trace)
//			System.out.println(ent);
		
		//OUTPUT
		System.out.println("Writing out the trace");
		boolean debug = (config.get("DEBUG").equals("YES")) ? true : false;
		boolean output_enabled = (config.get("OUTPUT_ENABLED").equals("YES")) ? true : false;
		output_characterised_trace(config.get("OUTPUT_FILE_PATH"), col_trace,debug, output_enabled);
		
		System.out.println("Finished characterising the "+config.get("PROCESS_FILTER")+" process");
	}
	
	private ArrayList<TraceEntry> collate_into_list(TraceEntry[] trace){
		ArrayList<TraceEntry> trace_list = new ArrayList<TraceEntry>(Arrays.asList(trace));
		ArrayList<TraceEntry> collated_list = new ArrayList<TraceEntry>();		
		
		for(TraceEntry ent : trace_list){
			boolean in_list = false;
			for(TraceEntry n_ent : collated_list){
				if(compare_entries(ent, n_ent)){
					in_list = true;
					break;
				}
			}
			if(!in_list){
				collated_list.add(ent);
				in_list = false;
			}
		}
		return collated_list;
	}
	
	private boolean compare_entries(TraceEntry entry1, TraceEntry entry2){
		ArrayList<TraceEntry>followers1 = entry1.getFollowingEntries();
		ArrayList<TraceEntry>followers2 = entry2.getFollowingEntries();
		
		if(followers1.size()!=followers2.size())
			return false;
		
		for(int i=0;i<followers1.size();i++){
			if(!followers1.get(i).getFunctionName().equals(followers2.get(i).getFunctionName()))
				return false;
		}
		return true;
	}
	
	/*
	 * Trace output format:
	 * functionName:-follower1,follower2,...followerN,-FunctionName...
	 */
	public void output_characterised_trace(String file_path, ArrayList<TraceEntry> trace, boolean debug, boolean output_enabled){
		BufferedWriter bw = null;
		
		if(output_enabled){
			try {
				bw = new BufferedWriter(new FileWriter(new File(file_path),false));
			} catch (IOException e) {
				System.out.println("Couldn't open the output file");
				e.printStackTrace();
				return;
			}
		}
		
		for(TraceEntry entry : trace){
			String line = "";
			line+=entry.getFunctionName();
			line+=":";
			ArrayList<TraceEntry> follower_list = entry.getFollowingEntries();
			for(TraceEntry follower_entry : follower_list){
				line+=follower_entry.getFunctionName()+",";
			}
			line+="\n";
			System.out.println(line);
			
			if(output_enabled){
				try{
					bw.write(line);
				}catch(IOException e){
					System.out.println("Couldn't write to the output file");
					e.printStackTrace();
				}
			}
		}
		
		if(bw!=null){
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				System.out.println("Couldn't close the output file");
				e.printStackTrace();
			}
		}
		
	}
}
