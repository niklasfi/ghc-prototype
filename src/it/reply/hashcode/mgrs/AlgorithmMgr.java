package it.reply.hashcode.mgrs;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import it.reply.hashcode.Server;
import it.reply.hashcode.input.beans.Problem;
import it.reply.hashcode.output.beans.Row;
import it.reply.hashcode.output.beans.Solution;

/**
 * @author d.cavassa
 *
 */

public class AlgorithmMgr implements Runnable {
	
	private final Problem problem;
	private int optimalScore = 0;
	private ScoreMgr g_scoreMgr = null; 
	private final Random randomGenerator = new Random(System.currentTimeMillis());
	private Solution best;
	
	public AlgorithmMgr(File inData) throws IOException{
		problem = new Problem(inData);
		g_scoreMgr = new ScoreMgr(); 
		best = problem.emptySolution();
	}
	
	public synchronized Random getRandom() {
		return new Random(randomGenerator.nextInt());
	}
	
	// Generates solutions
	public void run() {
		//TODO
		
		Solution sln = new Solution(problem);
		//destroy --> Marco
		
		//sort remaining servers
		Comparator<Server> compareServers = (s1, s2) -> -Integer.compare(s1.capacity / s1.size, s2.capacity / s2.size);
		sln.remainingServers.sort(compareServers);
		
		boolean stepSuccess = true;
		while(stepSuccess){
			stepSuccess = false;

			//we're working on currentPool
			for(int currentPool = 0; currentPool < problem.poolNumber; ++currentPool){
				//sort rows by relevance
				Integer[] rows = new Integer[problem.rows.size()];
				for(int i = 0; i < rows.length; ++i){
					rows[i] = i;
				}
				Comparator<Integer> compareRows = (r1, r2) -> Integer.compare(
						sln.rows.get(r1).poolCapacity.get(currentPool), sln.rows.get(r2).poolCapacity.get(currentPool));
				Arrays.sort(rows, compareRows);
				for(int rowIndex = 0; rowIndex < rows.length; ++rowIndex){
					//decide which segment to work on
					Row row = sln.rows.get(rows[rowIndex]);
					
					//sort seggments by relevance
					Integer[] segments = new Integer[row.segments.size()];
					for(int i = 0; i < segments.length; ++i){
						segments[i] = i;
					}
					Comparator<Integer> compareSegments = (s1, s2) -> -Integer.compare(
							row.segments.get(s1).sizeRemaining, row.segments.get(s2).sizeRemaining);
					
				}
				

				
				
			}
		}
	}//generateNextSolution
	

	public synchronized void setBestSolution(Solution solution) {
		best = solution;
	}
		
	
	private void destroy(Solution s) {
		
	}
	
	public Solution getBestSolution() {
		return new Solution(best);
	}//getSolution
	
}//Class AlgorithmMgr

