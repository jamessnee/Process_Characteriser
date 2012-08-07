package uk.ac.cam.jas250.processcharacteriser.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Config_Reader {
	public static Hashtable<String,String> read_config(String config_path) throws FileNotFoundException{
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

}
