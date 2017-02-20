package it.reply.hashcode;

public class RowSegment {
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
