package it.reply.hashcode.output.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.reply.hashcode.Server;
import it.reply.hashcode.input.beans.Problem;

class RunEncodeNode {
	public int start;
	public int length;
	public Server server; // null if server is unassigned
	public int pool; // -1 for blocked slots
}

class Row{
	public ArrayList<RunEncodeNode> slotsTotal;
	public ArrayList<RunEncodeNode> slotsFree;
	public ArrayList<Integer> poolCapacity; //pool Capacity for pool[i] per row
}

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class Solution {
	public Problem problem;
	public ArrayList<Row> rows;
	public ArrayList<Server> remainingServers;
	
	
}//SectionBean
