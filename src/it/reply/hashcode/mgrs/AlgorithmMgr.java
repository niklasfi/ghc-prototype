package it.reply.hashcode.mgrs;

import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

import it.reply.hashcode.Server;
import it.reply.hashcode.Utils;
import it.reply.hashcode.input.beans.Problem;
import it.reply.hashcode.output.beans.Row;
import it.reply.hashcode.output.beans.Segment;
import it.reply.hashcode.output.beans.Solution;

/**
 * @author d.cavassa
 *
 */

class PoolRowCapacity {
	int pool;
	int row;
	int cap;

	public PoolRowCapacity(int pool, int row, int cap){
		this.pool = pool;
		this.row = row;
		this.cap = cap;
	}
}

public class AlgorithmMgr implements Runnable {

	private final Problem problem;
	private int optimalScore = 0;
	private ScoreMgr g_scoreMgr = null;
	private final Random randomGenerator = new Random(System.currentTimeMillis());
	public Solution best;

	public AlgorithmMgr(File inData) throws IOException {
		problem = new Problem(inData);
		g_scoreMgr = new ScoreMgr();
		best = problem.emptySolution();
	}

	public synchronized Random getRandom() {
		return new Random(randomGenerator.nextInt(100));
	}

	// Generates solutions
	public void run() {
		// TODO
		Random r = getRandom();
		Solution sln = destroy(r, best, 0.3f);
		
		int globalSegmentIndex = 0;
		
		// sort remaining servers
		Comparator<Server> compareServers = (s1, s2) -> -Integer.compare(s1.capacity / s1.size, s2.capacity / s2.size);
		sln.remainingServers.sort(compareServers);

		boolean stepSuccess = false;
		stepLoop: do {
			stepSuccess = false;
			
			/*
			ArrayList<PoolRowCapacity> poolrows= new ArrayList<PoolRowCapacity>(problem.poolNumber * problem.rows.size());
			Integer[] totalCaps = g_scoreMgr.getTotalCaps(sln);
			for(int pool = 0; pool < problem.poolNumber; ++pool){
				for(int row = 0; row < problem.rows.size(); ++row){
					poolrows.add(new PoolRowCapacity(pool, row, totalCaps[pool] - sln.rows.get(row).poolCapacity.get(pool))); 
				}
			}
			Comparator<PoolRowCapacity> comparePoolRowCapacity = (prc1, prc2) -> Integer.compare(prc1.cap, prc2.cap);
			poolrows.sort(comparePoolRowCapacity);
			*/
			//for(PoolRowCapacity prc : poolrows){
			//we're working on currentPool
			poolLoop: for(int currentPool = 0; currentPool < problem.poolNumber  && sln.remainingServers.size() > 0; ++currentPool){
				//int currentPool = prc.pool;
				//sort rows by relevance
				Integer[] rows = new Integer[problem.rows.size()];
				for (int i = 0; i < rows.length; ++i) {
					rows[i] = i;
				}

				final int currentPoolFinal = currentPool;
				Comparator<Integer> compareRows = (r1, r2) -> Integer.compare(
						sln.rows.get(r1).poolCapacity.get(currentPoolFinal),
						sln.rows.get(r2).poolCapacity.get(currentPoolFinal));
				Arrays.sort(rows, compareRows);
				for (int rowIndex = 0; rowIndex < rows.length; ++rowIndex) {
					// decide which segment to work on
					Row row = sln.rows.get(rows[rowIndex]);
				
					int segmentIndexStart = globalSegmentIndex % row.segments.size();
					for(int segmentIndex = segmentIndexStart; segmentIndex < row.segments.size(); ++segmentIndex){
						Segment segment = row.segments.get(segmentIndex);
						
						// try to find a fitting server
						for (int serverIndex = 0; serverIndex < sln.remainingServers.size(); ++serverIndex) {
							Server server = sln.remainingServers.get(serverIndex);
							if (server.size <= segment.sizeRemaining) {
								segment.addServer(server, currentPool);
								sln.remainingServers.remove(serverIndex);
								stepSuccess = true;
								globalSegmentIndex += segmentIndex - segmentIndexStart;
								continue poolLoop;
							}
						}
					}
					
					for(int segmentIndex = 0; segmentIndex < segmentIndexStart; ++segmentIndex){
						Segment segment = row.segments.get(segmentIndex);
						
						// try to find a fitting server
						for (int serverIndex = 0; serverIndex < sln.remainingServers.size(); ++serverIndex) {
							Server server = sln.remainingServers.get(serverIndex);
							if (server.size <= segment.sizeRemaining) {
								segment.addServer(server, currentPool);
								sln.remainingServers.remove(serverIndex);
								stepSuccess = true;
								globalSegmentIndex += segmentIndex + row.segments.size() - segmentIndexStart;
								continue poolLoop;
							}
						}
					}
				}
			}
		} while(stepSuccess);
		
//		best = sln;

		int score = g_scoreMgr.evaluate(sln);

		if (score > optimalScore) {
			System.out.println(score);
			synchronized (this) {
				optimalScore = score;
				best = sln;
			}
		}
	}// generateNextSolution

	private Solution destroy(Random r, Solution old, float percent) {
		Solution sol = new Solution(old);
		for (int n = (int) Math.ceil(percent * (sol.problem.servers.size() - sol.remainingServers.size())); n > 0; --n) {
			Row row;
			Segment segment;
			while(true){
				row = sol.rows.get(r.nextInt(sol.rows.size()));
				segment = row.segments.get(r.nextInt(row.segments.size()));
				
				if(segment.servers.size() != 0){
					break;
				}
			}
			int serverIndex = r.nextInt(segment.servers.size());
			Server server = segment.removeServer(serverIndex);
			sol.remainingServers.add(server);
		}
		return sol;
	}

}// Class AlgorithmMgr
