package uk.ac.cam.jas250.processcharacteriser.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import uk.ac.cam.jas250.processcharacteriser.models.TraceEntry;;

public class FTrace_Input implements InputData_Interface{
	
	public FTrace_Input(Hashtable<String,String>config)throws FileNotFoundException{
		super();
		File infile = new File(config.get("INPUT_FILE_PATH"));
		if(!infile.exists())
			throw new FileNotFoundException();
	}
	
	@Override
	public TraceEntry get_trace_entry_for_line(int line_number) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TraceEntry[] get_all_trace_entries(Hashtable<String,String> config) throws FileNotFoundException {
		ArrayList<TraceEntry> entries = new ArrayList<TraceEntry>();
		BufferedReader br = new BufferedReader(new FileReader(new File(config.get("INPUT_FILE_PATH"))));
		
		try {
			String line = br.readLine();
			while(line!=null){
				line = line.trim();
				//process---cpu---time:-parent-<-function
				String[] parts = line.split("   ");
				if(parts[0].contains(config.get("PROCESS_FILTER"))){
					//Set the function name
					TraceEntry current_entry = new TraceEntry();
					current_entry.setProcessName(parts[0]);
					
					//Sometimes the CPU num and parent will be split by variable spaces (usually 2)
					String tail = null;
					if(parts.length==2)
						tail = parts[1].split("  ")[1];
					else
						tail = parts[2];
					
					//Timestamp
					String[] timearr = tail.split(":");
					current_entry.setTimestamp(Float.parseFloat(timearr[0]));
					
					//System call
					String parentsyscall = timearr[1];
					parentsyscall = parentsyscall.trim();
					String syscall = parentsyscall.split("<-")[1];
					current_entry.setFunctionName(syscall);
					
					entries.add(current_entry);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//blerg can't be bothered to look at the javadocs for a better way right now...
		TraceEntry[] entryarr = new TraceEntry[entries.size()];
		for(int i=0;i<entries.size();i++)
			entryarr[i] = entries.get(i);
		return entryarr;
	}
}
