package it.reply.hashcode.output.beans;

import it.reply.hashcode.Server;

import java.util.ArrayList;

public class Segment {
	public int startingPoint;
	public int sizeTotal;
	public int sizeRemaining;
	public ArrayList<Server> server;
	public ArrayList<Integer> pools;
}
