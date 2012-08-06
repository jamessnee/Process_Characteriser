package uk.ac.cam.jas250.processcharacteriser.input;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import uk.ac.cam.jas250.processcharacteriser.exceptions.LineNotFoundException;
import uk.ac.cam.jas250.processcharacteriser.models.TraceEntry;;

public class FTrace_Input implements InputData_Interface{
	
	private int current_line;
	private int number_of_lines;
	private InputStream file_in;
	
	public FTrace_Input(String file_path) throws FileNotFoundException{
		super();
		file_in = new BufferedInputStream(new FileInputStream(file_path));
		
		//Set the number of lines in the file
		number_of_lines = get_number_of_file_lines();
		current_line = 0;
	}
	
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
	public TraceEntry get_trace_entry_for_line(int line_number) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TraceEntry[] get_all_trace_entries(String process_name) {
		//Get the trace number for each line in the file
		
		
		return null;
	}

	@Override
	public int get_number_of_file_lines() {
		byte[] c = new byte[1024];
		int count = 0;
		int read_chars = 0;
		try {
			while((read_chars=file_in.read(c))!=-1){
				for(int i=0;i<read_chars;i++){
					if(c[i]=='\n')
						++count;
				}
			}
			file_in.reset();
			return count;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
