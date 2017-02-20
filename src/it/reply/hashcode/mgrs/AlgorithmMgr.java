package it.reply.hashcode.mgrs;

import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
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

public class AlgorithmMgr implements Runnable {

	private final Problem problem;
	private int optimalScore = 0;
	private ScoreMgr g_scoreMgr = null;
	private final Random randomGenerator = new Random(System.currentTimeMillis());
	private Solution best;

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
		Solution sln = destroy(r, getBestSolution(), 0.3f);
		
		// sort remaining servers
		Comparator<Server> compareServers = (s1, s2) -> -Integer.compare(s1.capacity / s1.size, s2.capacity / s2.size);
		sln.remainingServers.sort(compareServers);

		boolean stepSuccess = true;
		mainLoop: while (stepSuccess && sln.remainingServers.size() > 0) {
			stepSuccess = false;

			//we're working on currentPool
			poolLoop: for(int currentPool = 0; currentPool < problem.poolNumber  && sln.remainingServers.size() > 0; ++currentPool){
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

					// sort seggments by relevance
					Integer[] segments = new Integer[row.segments.size()];
					for (int i = 0; i < segments.length; ++i) {
						segments[i] = i;
					}
					Comparator<Integer> compareSegments = (s1, s2) -> -Integer
							.compare(row.segments.get(s1).sizeRemaining, row.segments.get(s2).sizeRemaining);
					Arrays.sort(segments, compareSegments);

					for (int segmentIndex = 0; segmentIndex < segments.length; ++segmentIndex) {
						Segment segment = row.segments.get(segments[segmentIndex]);

						// try to find a fitting server
						for (int serverIndex = 0; serverIndex < sln.remainingServers.size(); ++serverIndex) {
							Server server = sln.remainingServers.get(serverIndex);
							if (server.size <= segment.sizeRemaining) {
								segment.addServer(server, currentPool);
								sln.remainingServers.remove(serverIndex);
								stepSuccess = true;
								continue poolLoop;
							}
						}
					}
				}
			}
		}
		
//		best = sln;

		int score = g_scoreMgr.evaluate(sln);
		if (score >= optimalScore) {
			optimalScore = score;
			best = sln;
		}
	}// generateNextSolution

	public synchronized void setBestSolution(Solution solution) {
		best = solution;
	}

	private Solution destroy(Random r, Solution old, float percent) {
		Solution sol = Utils.clone(old);
		for (int n = (int) Math.ceil(percent * (sol.problem.servers.size() - sol.remainingServers.size())); n > 0; --n) {
			int x = r.nextInt(sol.rows.size());
			Row row = sol.rows.get(x);
			int y = r.nextInt(row.segments.size());
			Segment s = row.segments.get(y);

			if(s.server.size() != 0){				
				sol.remainingServers.add(s.removeServer(r.nextInt(s.server.size())));
				break;
			}
		}
		return sol;
	}

	public Solution getBestSolution() {
		return new Solution(best);
	}// getSolution

}// Class AlgorithmMgr
