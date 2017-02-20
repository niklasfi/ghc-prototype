package it.reply.hashcode;

public class Server {
	public final int id, size, capacity;

	public Server(int id, int size, int capacity) {
		super();
		this.id = id;
		this.size = size;
		this.capacity = capacity;
	}
	
	@Override
	public  String toString() {
		return "Server: "+id+" - "+size + "("+capacity+")";
	}
}
