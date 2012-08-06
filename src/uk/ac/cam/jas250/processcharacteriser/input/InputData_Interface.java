package uk.ac.cam.jas250.processcharacteriser.input;

import uk.ac.cam.jas250.processcharacteriser.exceptions.LineNotFoundException;
import uk.ac.cam.jas250.processcharacteriser.models.TraceEntry;

public interface InputData_Interface {
	
	public int get_number_of_file_lines();
	
	/*Read the next line from input data*/
	public String get_next_line();
	
	/*Read a specific line from input data*/
	public String get_line(int line_number) throws LineNotFoundException;
	
	/*Get all data as an array of format:
	 *  [process_name],[time_since_boot],[caller],[function_called]\n
	 */
	public String[] get_all_data();
	
	/* Get the all the data for a specific process in the format:
	 * [process_name],[time_since_boot],[caller],[function_called]\n
	 */
	public String[] get_all_data(String process_name);
	
	public TraceEntry get_trace_entry_for_line(int line_number);
	
	/*
	 * Return the trace as an array of trace entry objects
	 */
	public TraceEntry[] get_all_trace_entries();
}
