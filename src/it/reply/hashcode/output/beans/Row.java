package it.reply.hashcode.output.beans;

import java.util.ArrayList;

public class Row {

	public ArrayList<Segment> segments;
	public ArrayList<Integer> poolCapacity; //pool Capacity for pool[i] per row
	
	public Row() {
		segments = new ArrayList<>();
		poolCapacity = new ArrayList<>();
	}
	
	public Row(final Row old) {
		segments = new ArrayList<>();
		for(Segment s : segments) {
			segments.add(new Segment(s, this));
		}
		poolCapacity = new ArrayList<>(poolCapacity);
	}
}
