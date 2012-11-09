package uk.ac.cam.jas250.processcharacteriser.models;

import java.util.ArrayList;

public class TraceEntry {
	private String processName;
	private String functionName;
	private ArrayList<ArrayList<TraceEntry>> followingEntries;
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
	
	public ArrayList<ArrayList<TraceEntry>> getFollowingEntries(){
		return followingEntries;
	}
	public void add_following_entries(ArrayList<TraceEntry> entries){
		if(followingEntries == null)
			followingEntries = new ArrayList<ArrayList<TraceEntry>>();
		followingEntries.add(entries);
	}
	
}
