package it.reply.hashcode.output.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.reply.hashcode.RowSegment;
import it.reply.hashcode.Server;
import it.reply.hashcode.input.beans.Problem;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Solution {
	public final Problem problem;
	public ArrayList<Row> rows;
	public ArrayList<Server> remainingServers;
	
	public Solution(Problem problem) {
		this.problem = problem;

		this.rows = new ArrayList<>();
		this.remainingServers = new ArrayList<>(problem.servers);

		for(List<RowSegment> s : problem.rows){
			Row r = new Row();
			for(RowSegment rs : s){
				r.segments.add(new Segment(rs.capacity, r, problem.poolNumber));
			}
		}
		
	}
	
	public Solution(final Solution solution) {
		this.problem = solution.problem;
		this.rows = new ArrayList<>();
		for(Row r : solution.rows)
			rows.add(r);
		remainingServers = new ArrayList<>(solution.remainingServers);
	}
	
}//SectionBean