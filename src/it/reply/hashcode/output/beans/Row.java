package it.reply.hashcode.output.beans;

import java.util.ArrayList;

public class Row {

	public ArrayList<Segment> segments;
	public ArrayList<Integer> poolCapacity; //pool Capacity for pool[i] per row
	
	public Row(int pool) {
		segments = new ArrayList<>();
		poolCapacity = new ArrayList<>();
		for(int i = 0; i < pool; i++)
			poolCapacity.add(0);
	}
	
	public Row(final Row old) {
		segments = new ArrayList<>();
		for(Segment s : old.segments) {
			segments.add(new Segment(s, this));
		}
		poolCapacity = new ArrayList<>(old.poolCapacity);
	}
}
