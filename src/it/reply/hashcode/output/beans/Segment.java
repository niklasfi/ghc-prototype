package it.reply.hashcode.output.beans;

import java.util.ArrayList;

import it.reply.hashcode.Server;

public class Segment {
	public final int sizeTotal;
	public int sizeRemaining;
	public ArrayList<Server> server;
	public ArrayList<Integer> pools;
	
	public Segment(int sizeTotal) {
		this.sizeRemaining = this.sizeTotal = sizeTotal;
		server = new ArrayList<>();
		pools = new ArrayList<>();
	}
	
	public Segment(final Segment old) {
		sizeTotal = old.sizeTotal;
		sizeRemaining = old.sizeRemaining;
		server = new ArrayList<>(server);
		pools = new ArrayList<>(pools);
	}
	
}
