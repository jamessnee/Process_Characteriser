package uk.ac.cam.jas250.processcharacteriser.input;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import uk.ac.cam.jas250.processcharacteriser.models.TraceEntry;

public interface InputData_Interface {
	
	public TraceEntry get_trace_entry_for_line(int line_number);
	
	/*
	 * Return the trace as an array of trace entry objects
	 */
	public TraceEntry[] get_all_trace_entries(Hashtable<String,String> config) throws FileNotFoundException;
}
