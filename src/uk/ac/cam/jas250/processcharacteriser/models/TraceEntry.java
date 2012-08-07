package uk.ac.cam.jas250.processcharacteriser.models;

public class TraceEntry {
	private String processName;
	private String functionName;
	private TraceEntry[] followingEntries;
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
	public TraceEntry[] getFollowingEntries() {
		return followingEntries;
	}
	public void setFollowingEntries(TraceEntry[] followingEntries) {
		this.followingEntries = followingEntries;
	}
	public float getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(float timestamp) {
		this.timestamp = timestamp;
	}
}
