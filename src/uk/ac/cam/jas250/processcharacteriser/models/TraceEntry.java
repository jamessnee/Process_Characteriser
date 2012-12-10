package uk.ac.cam.jas250.processcharacteriser.models;

import java.util.ArrayList;

public class TraceEntry {
	private String processName;
	private String functionName;
	private ArrayList<TraceEntry> followingEntries; // Two arraylists to allow for multiple signitures per sys-call
	private float timestamp;
	
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public float getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(float timestamp) {
		this.timestamp = timestamp;
	}
	
	public ArrayList<TraceEntry> getFollowingEntries(){
		return followingEntries;
	}
	
	public void setFollowingEntries(ArrayList<TraceEntry> entries){
		followingEntries = entries;
	}
	
	public String toString(){
		String line = functionName;
		if(followingEntries!=null){
			line += ":";
			for(TraceEntry ent : followingEntries)
				line += ent.toString()+",";
		}
		return line;
	}
	
	public TraceEntry copy(){
		TraceEntry ent = new TraceEntry();
		ent.setFunctionName(functionName);
		ent.setProcessName(processName);
		ent.setTimestamp(timestamp);
		return ent;
	}
}
