package uk.ac.cam.jas250.processcharacteriser.input;

import uk.ac.cam.jas250.processcharacteriser.exceptions.LineNotFoundException;
import uk.ac.cam.jas250.processcharacteriser.models.TraceEntry;

public class SystemTap_Input implements InputData_Interface{

	@Override
	public String get_next_line() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get_line(int line_number) throws LineNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] get_all_data() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] get_all_data(String process_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TraceEntry[] get_all_trace_entries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TraceEntry get_trace_entry_for_line(int line_number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int get_number_of_file_lines() {
		// TODO Auto-generated method stub
		return 0;
	}

}
