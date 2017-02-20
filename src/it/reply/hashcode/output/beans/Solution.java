package it.reply.hashcode.output.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.reply.hashcode.Server;
import it.reply.hashcode.input.beans.Problem;

class Segment{
	int sizeTotal;
	int sizeRemaining;
	ArrayList<Server> server;
	ArrayList<Integer> pools;
}

class Row{
	public ArrayList<Segment> segments;
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