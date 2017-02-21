package it.reply.hashcode;

import java.io.Serializable;

public class RowSegment implements Serializable {
	public int capacity;
	public int x;
	public RowSegment(int capacity, int x) {
		super();
		this.capacity = capacity;
		this.x = x;
	}
	
	@Override
	public  String toString() {
		return "RowSegment: "+x+" - "+capacity;
	}
}
