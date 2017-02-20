package it.reply.hashcode.mgrs;

import java.util.Arrays;
import java.util.Collections;

import it.reply.hashcode.output.beans.Solution;

/**
 * @author d.cavassa
 *
 */
public class ScoreMgr {

	public int evaluate(Solution sln){
		Integer[] totalCaps = getTotalCaps(sln);
		return 1*bestOf1(sln, totalCaps) + 3*bestOf2(sln, totalCaps) + 10*bestOf3(sln, totalCaps);
	}//evaluate
	
	private Integer[] getTotalCaps(Solution sln){
		Integer[] result = new Integer[sln.problem.poolNumber];
		for(int i = 0; i < sln.problem.poolNumber; ++i){
			for(int j = 0; j < sln.rows.size(); ++j){
				result[i] += sln.rows.get(j).poolCapacity.get(i);
			}
		}
		return result;
	}
	
	private int bestOf1(Solution sln, Integer[] totalCaps){
		int minScore = 0;
		for(int r = 0; r < sln.rows.size(); ++r){
			Integer[] caps = totalCaps.clone();
			
			for(int p = 0; p < sln.problem.poolNumber; ++p){
				caps[p] -= sln.rows.get(r).poolCapacity.get(p);
			}
			minScore = Math.max(minScore, Collections.min(Arrays.asList(caps)));
		}
		return minScore;
	}
	
	private int bestOf2(Solution sln, Integer[] totalCaps){
		int minScore = 0;
		for(int r1 = 0; r1 < sln.rows.size(); ++r1){
			Integer[] capsr1 = totalCaps.clone();
			
			for(int p = 0; p < sln.problem.poolNumber; ++p){
				capsr1[p] -= sln.rows.get(r1).poolCapacity.get(p);
			}
			
			for(int r2 = 0; r2 < r1; ++r2){
				Integer[] capsr2 = capsr1.clone();
				for(int p = 0; p < sln.problem.poolNumber; ++p){
					capsr1[p] -= sln.rows.get(r2).poolCapacity.get(p);
				}				
				minScore = Math.max(minScore, Collections.min(Arrays.asList(capsr2)));
			}
		}
		return minScore;
	}
	
	private int bestOf3(Solution sln, Integer[] totalCaps){
		return 0;
	}

}// ScoreMgr
