package it.reply.hashcode.mgrs;

import java.util.Random;

import it.reply.hashcode.input.beans.Problem;
import it.reply.hashcode.output.beans.Solution;

/**
 * @author d.cavassa
 *
 */
public class AlgorithmMgr implements Runnable {
	
	private Problem g_inData;
	private int optimalScore = 0;
	private ScoreMgr g_scoreMgr = null; 
	private final Random randomGenerator = new Random(System.currentTimeMillis());
	
	private Solution best = null;
	
	public AlgorithmMgr(Problem inData){
		g_scoreMgr = new ScoreMgr(); 
		g_inData = inData;
	}
	
	public synchronized Random getRandom() {
		return new Random(randomGenerator.nextInt());
	}
	
	// Generates solutions
	public void run() {
		//TODO
	}//generateNextSolution
	

	public synchronized void setBestSolution(Solution solution) {
		best = solution;
	}
		
	public Solution getBestSolution() {
		return best;
	}//getSolution
	
}//Class AlgorithmMgr

