package it.reply.hashcode;

import java.io.Serializable;

public class Server implements Serializable {
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
