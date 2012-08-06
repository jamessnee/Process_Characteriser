package uk.ac.cam.jas250.processcharacteriser.models;

public class TraceEntry {
	private int id;
	private String processName;
	private String functionName;
	private TraceEntry[] followingEntries;
	private long timestamp;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
