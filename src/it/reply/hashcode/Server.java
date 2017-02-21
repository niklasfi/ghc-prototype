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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Server other = (Server) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
