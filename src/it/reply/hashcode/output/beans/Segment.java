package it.reply.hashcode.output.beans;

import java.util.ArrayList;

import it.reply.hashcode.Server;

public class Segment {
	public final int sizeTotal;
	public final Row row;
	public int sizeRemaining;
	public ArrayList<Server> server;
	public ArrayList<Integer> pools;
	
	public Segment(int sizeTotal, Row row) {
		this.row = row;
		this.sizeRemaining = this.sizeTotal = sizeTotal;
		server = new ArrayList<>();
		pools = new ArrayList<>();
	}
	
	public Segment(final Segment old, Row row) {
		this.row = row;
		sizeTotal = old.sizeTotal;
		sizeRemaining = old.sizeRemaining;
		server = new ArrayList<>(server);
		pools = new ArrayList<>(pools);
	}

	public boolean addServer(Server s, int pool) {
		if(sizeRemaining >= s.size){
			server.add(s);
			pools.add(pool);
			sizeRemaining -= s.size;
			row.poolCapacity.set(pool, row.poolCapacity.get(pool) + s.capacity);
			return true;
		}
		return false;
	}
	
	public void removeServer(int pos) {
		Server r = server.remove(pos);
		sizeRemaining = r.size;
		row.poolCapacity.set(pos, row.poolCapacity.get(pos) - r.capacity);
	}
}
