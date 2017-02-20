package it.reply.hashcode.output.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.reply.hashcode.Server;
import it.reply.hashcode.input.beans.Problem;

class RunEncodeNode {
	public int start;
	public int length;
	public Server server;
	public int pool;
}

class Row{
	public ArrayList<RunEncodeNode> slots;
	public ArrayList<Integer> poolCapacity;
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
