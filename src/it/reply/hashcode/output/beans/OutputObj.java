package it.reply.hashcode.output.beans;


public class OutputObj {
	public int pool;
	public int row;
	public int point;
	public boolean disposed;
	
	public OutputObj(int row, int point, int pool) {
		this.row = row;
		this.point = point;
		this.pool = pool;
		this.disposed = true;
	}
	
	public OutputObj() {
		this.disposed = false;
	}
	@Override 
	public String toString() {
		if (disposed) return "X";
		return row + " " + point + " " + pool;
	}
}
