package uk.ac.cam.jas250.processcharacteriser.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class Config_Reader {
	
	public static Hashtable<String, String> get_configuration(){
		return get_config_from_user();
	}
	
	public static Hashtable<String, String> get_configuration(String config_filepath){
		Hashtable <String, String> config = null;
		try{
			config = read_config(config_filepath);
		}catch(FileNotFoundException e){
			System.out.println("Couldn't find the configuration file. Falling back to user provided config");
		}
		
		if(config==null)
			config = get_config_from_user();
		
		return config;
	}
	
	private static Hashtable<String,String> read_config(String config_path) throws FileNotFoundException{
		Hashtable<String,String> config_contents = new Hashtable<String,String>();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(config_path)));
		
		try {
			String line = br.readLine();
			while(line!=null){
				line = line.replaceAll("\\s", ""); //Remove spaces
				if(!line.isEmpty()){
					if(line.charAt(0)!='/' && line.charAt(0)!='*'){
						String[] contents = line.split("=");
						config_contents.put(contents[0], contents[1]);
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return config_contents;
	}
	
	/*
	 * Ask the user to manually input the configuration for the characteriser
	 */
	private static Hashtable<String,String> get_config_from_user(){
		Scanner scanner = new Scanner(System.in);
		
		//Input file path
		System.out.println("Input file path:");
		String in_fp = scanner.nextLine();
		System.out.println(in_fp);
		
		//Output file path
		System.out.println("Output file path:");
		String out_fp = scanner.nextLine();
		System.out.println(out_fp);
		
		//Process filter
		System.out.println("Process filter:");
		String process_filter = scanner.nextLine();
		System.out.println(process_filter);
		
		//Windows size
		System.out.println("Window size");
		String window_size = scanner.nextLine();
		System.out.println(window_size);
		
		//Trace type
		System.out.println("Trace type");
		String trace_type = scanner.nextLine();
		System.out.println(trace_type);
		
		//Output enabled
		System.out.println("Output enabled (YES/NO)");
		String output_enabled = scanner.nextLine();
		System.out.println(output_enabled);
		
		//Debug mode
		System.out.println("Debug mode enabled (YES/NO)");
		String debug_mode = scanner.nextLine();
		System.out.println(debug_mode);
		
		Hashtable<String, String> config = new Hashtable<String, String>();
		config.put("INPUT_FILE_PATH", in_fp);
		config.put("OUTPUT_FILE_PATH", out_fp);
		config.put("PROCESS_FILTER", process_filter);
		config.put("WINDOW_SIZE", window_size);
		config.put("TRACE_TYPE", trace_type);
		config.put("OUTPUT_ENABLED", output_enabled);
		config.put("DEBUG", debug_mode);
		
		System.out.println("Configuration saved:");
		
		return config;
	}

}
