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
		//Iterate through the objects in time-stamp order
		for(int i=0;i<trace.length;i++){
			TraceEntry current_entry = trace[i];
			//TraceEntry[] followers = new TraceEntry[window_size];
			ArrayList<TraceEntry> followers = new ArrayList<TraceEntry>();
			//'Walk' forwards and fill the followers array
			for(int j=0;j<window_size;j++)
				if(i+j+1<trace.length-1)
					//followers[j] = trace[i+j+1];
					followers.add(trace[i+j+1]);
			//current_entry.setFollowingEntries(followers);
			current_entry.add_following_entries(followers);
		}
		
		System.out.println("Collating the trace...");
		ArrayList<TraceEntry> collated_list = collate_trace_entries(trace);
		
		System.out.println("Collating the trace signitures...");
		collated_list = collate_trace_signiture(collated_list);
		
		System.out.println("Writing out the trace");
		output_characterised_trace(config.get("OUTPUT_FILE_PATH"), collated_list);
		
		System.out.println("Finished characterising the "+config.get("PROCESS_FILTER")+" process");
	}
	
	private ArrayList<TraceEntry> collate_trace_signiture(ArrayList<TraceEntry> trace){
		ArrayList<TraceEntry> collated_trace = new ArrayList<TraceEntry>();
		for(TraceEntry curr_entry : trace){
			ArrayList<ArrayList<TraceEntry>> followers = curr_entry.getFollowingEntries();
			for(int i=0; i<followers.size();i++){
				
			}
		}
		
		return collated_trace;
	}
	
	private ArrayList<TraceEntry> collate_trace_entries(TraceEntry[] trace){
		ArrayList<TraceEntry>collated_list = new ArrayList<TraceEntry>();
		ArrayList<TraceEntry>uncol_trace = new ArrayList<TraceEntry>(Arrays.asList(trace));
		
		for(int i=0;i<uncol_trace.size();i++){
			TraceEntry entry = uncol_trace.get(i);
			for(int j=i+1;j<uncol_trace.size();j++){//<- look at the rest of the list
				TraceEntry lookahead_entry = uncol_trace.get(j);
				if(entry.getFunctionName().equals(lookahead_entry.getFunctionName())){//<- The functions match
					ArrayList<ArrayList<TraceEntry>> followers_to_add = lookahead_entry.getFollowingEntries();
					for(ArrayList<TraceEntry>followers : followers_to_add)
						entry.add_following_entries(followers);
					uncol_trace.remove(lookahead_entry);
				}
			}
			collated_list.add(entry);
		}
		return collated_list;
	}
	
	/*
	 * Trace output format:
	 * functionName:-follower1,follower2,...followerN,-FunctionName...
	 */
	public void output_characterised_trace(String file_path, ArrayList<TraceEntry> trace){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(file_path),false));
		} catch (IOException e) {
			System.out.println("Couldn't open the output file");
			e.printStackTrace();
			return;
		}
		
		for(TraceEntry entry : trace){
			String line = "";
			line+=entry.getFunctionName();
			line+=":";
			ArrayList<ArrayList<TraceEntry>> followers = entry.getFollowingEntries();
			for(ArrayList<TraceEntry>follower_list : followers){
				line+="-";
				for(TraceEntry follower_entry : follower_list){
					line+=follower_entry.getFunctionName()+",";
				}
			}
			line+="\n";
			try{
				bw.write(line);
			}catch(IOException e){
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
