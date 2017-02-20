package it.reply.hashcode.output.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Row implements Serializable {

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
		for(Segment s : segments) {
			segments.add(new Segment(s, this));
		}
		poolCapacity = new ArrayList<>(poolCapacity);
	}
}
