package it.reply.hashcode.output.beans;

import java.util.ArrayList;

public class Row {

	public ArrayList<Segment> segments;
	public ArrayList<Integer> poolCapacity; //pool Capacity for pool[i] per row
	
	public Row(int pool) {
		segments = new ArrayList<>();
		poolCapacity = new ArrayList<>(pool);
		for(int i = 0; i < pool; i++)
			poolCapacity.set(i, 0);
	}
	
	public Row(final Row old) {
		segments = new ArrayList<>();
		for(Segment s : segments) {
			segments.add(new Segment(s, this));
		}
		poolCapacity = new ArrayList<>(poolCapacity);
	}
}
