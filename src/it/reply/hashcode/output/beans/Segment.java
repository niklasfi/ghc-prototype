package it.reply.hashcode.output.beans;

import java.io.Serializable;
import java.util.ArrayList;

import it.reply.hashcode.Server;

public class Segment implements Serializable {
	public final int sizeTotal;
	public final Row row;
	public final int startingPoint;
	public int sizeRemaining;
	public ArrayList<Server> servers;
	public ArrayList<Integer> pools;
	
	public Segment(int sizeTotal, Row row, int startingPoint) {
		this.row = row;
		this.startingPoint = startingPoint;
		this.sizeRemaining = this.sizeTotal = sizeTotal;
		servers = new ArrayList<>();
		pools = new ArrayList<>();
	}
	
	public Segment(final Segment old, Row row) {
		this.row = row;
		sizeTotal = old.sizeTotal;
		sizeRemaining = old.sizeRemaining;
		this.startingPoint = old.startingPoint;
		servers = new ArrayList<>(old.servers);
		pools = new ArrayList<>(old.pools);
	}

	public boolean addServer(Server s, int pool) {
		if(sizeRemaining >= s.size){
			servers.add(s);
			pools.add(pool);
			sizeRemaining -= s.size;
			row.poolCapacity.set(pool, row.poolCapacity.get(pool) + s.capacity);
			return true;
		}
		return false;
	}
	
	public Server removeServer(int pos) {
		Server r = servers.get(pos);
		sizeRemaining += r.size;
		row.poolCapacity.set(pools.get(pos), row.poolCapacity.get(pools.get(pos)) - r.capacity);
		servers.remove(pos);
		pools.remove(pos);
		return r;
	}
}
