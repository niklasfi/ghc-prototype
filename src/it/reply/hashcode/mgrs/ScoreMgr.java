package it.reply.hashcode.mgrs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import it.reply.hashcode.output.beans.Solution;

/**
 * @author d.cavassa
 *
 */
public class ScoreMgr {

	public int evaluate(Solution sln){
		int[] totalScore = {0,0,0};
		for(int poolIndex = 0; poolIndex < sln.problem.poolNumber; ++poolIndex){
			ArrayList<Integer> rowIndexArray = new ArrayList<Integer>(sln.rows.size());
			
			for(int i = 0; i < sln.rows.size(); ++i){
				rowIndexArray.add(i);
			}
			
			final int poolIndexFinal = poolIndex;			
			Comparator<Integer> compareRows = (r1, r2) -> -Integer.compare(sln.rows.get(r1).poolCapacity.get(poolIndexFinal), sln.rows.get(r2).poolCapacity.get(poolIndexFinal));
			rowIndexArray.sort(compareRows);
			
			int poolTotalCap = 0;
			for(int i = 0; i < sln.rows.size(); ++i){
				poolTotalCap += sln.rows.get(i).poolCapacity.get(poolIndex);
			}
			
			for(int i = 0; i < 3; ++i){
				if(sln.problem.rows.size() > i + 1){
					totalScore[i] = Math.min(totalScore[i], poolTotalCap - maximumNSubtract(sln, i + 1, poolIndex, rowIndexArray));
				}
			}
		}
		
		return totalScore[0] + totalScore[1] * 3 + totalScore[2] * 10; 
	}//evaluate
	
	public Integer[] getTotalCaps(Solution sln){
		Integer[] result = new Integer[sln.problem.poolNumber];
		for(int i = 0; i < result.length; ++i){
			result[i] = 0;
		}
		
		for(int i = 0; i < sln.problem.poolNumber; ++i){
			for(int j = 0; j < sln.rows.size(); ++j){
				result[i] += sln.rows.get(j).poolCapacity.get(i);
			}
		}
		return result;
	}
	
	private int bestOf1(Solution sln, Integer[] totalCaps){
		int minScore = Collections.max(Arrays.asList(totalCaps));
		for(int r = 0; r < sln.rows.size(); ++r){
			Integer[] caps = totalCaps.clone();
			
			for(int p = 0; p < sln.problem.poolNumber; ++p){
				caps[p] -= sln.rows.get(r).poolCapacity.get(p);
			}
			minScore = Math.min(minScore, Collections.min(Arrays.asList(caps)));
		}
		return minScore;
	}
	
	private int bestOf2(Solution sln, Integer[] totalCaps){
		if(sln.rows.size() < 2) return 0;
		int minScore = Collections.max(Arrays.asList(totalCaps));
		for(int r1 = 0; r1 < sln.rows.size(); ++r1){
			Integer[] capsr1 = totalCaps.clone();
			
			for(int p = 0; p < sln.problem.poolNumber; ++p){
				capsr1[p] -= sln.rows.get(r1).poolCapacity.get(p);
			}
			
			for(int r2 = 0; r2 < r1; ++r2){
				Integer[] capsr2 = capsr1.clone();
				for(int p = 0; p < sln.problem.poolNumber; ++p){
					capsr2[p] -= sln.rows.get(r2).poolCapacity.get(p);
				}	
				minScore = Math.min(minScore, Collections.min(Arrays.asList(capsr2)));
			}
		}
		return minScore;
	}
	
	private int bestOfn(Solution sln, int n, Integer[] totalCaps){
		if(sln.rows.size() < n) return 0;
		int totalScore = Collections.max(Arrays.asList(totalCaps));

		for(int poolIndex = 0; poolIndex < sln.problem.poolNumber; ++poolIndex){
			ArrayList<Integer> rowIndexArray = new ArrayList<Integer>(sln.rows.size());
			
			for(int i = 0; i < sln.rows.size(); ++i){
				rowIndexArray.add(i);
			}
			
			final int poolIndexFinal = poolIndex;
			
			Comparator<Integer> compareRows = (r1, r2) -> -Integer.compare(sln.rows.get(r1).poolCapacity.get(poolIndexFinal), sln.rows.get(r2).poolCapacity.get(poolIndexFinal));
			rowIndexArray.sort(compareRows);
			
			
		}
		return totalScore;
	}
	private int maximumNSubtract(Solution sln, int n, int poolIndex, ArrayList<Integer> sortedRowIndexArray){
		if(sln.rows.size() < n) return 0;
		
		int subtract = 0;		
		for(int i = 0; i < n; ++i){
			subtract += sln.rows.get(sortedRowIndexArray.get(i)).poolCapacity.get(poolIndex);
		}
		return subtract;
		
	}

}// ScoreMgr
