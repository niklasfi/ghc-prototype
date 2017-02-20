package it.reply.hashcode.mgrs;

import java.util.ArrayList;
import java.util.List;

import it.reply.hashcode.intermediate.beans.CoupleBean;
import it.reply.hashcode.intermediate.beans.TripleBean;
import it.reply.hashcode.output.beans.Solution;

/**
 * @author d.cavassa
 *
 */
public class ScoreMgr {
	
	public int evaluate(Solution sol){
		//Calculate single row failure
		int row1FailScore = getSingleRowFailure(sol);
		int row2FailScore = getDoubleRowFailure(sol);
		int row3FailScore = getTripleRowFailure(sol);
		
		int finalScore = row1FailScore + (3 * row2FailScore) + (10 * row3FailScore);
		return finalScore;
	}//evaluate

	
	private int getSingleRowFailure(Solution sol){
		int numPools = sol.problem.poolNumber;
		int globalScore = -1;
		
		for(int i=0; i < sol.rows.size(); i++){
			int[] arPoolCapacity = new int[numPools];
			for(int j=0; j < sol.rows.size(); j++){
				//i is the excluded row
				if(i != j){
					ArrayList<Integer> poolCapacity = sol.rows.get(j).poolCapacity;
					for(int x = 0; x < poolCapacity.size(); x++){
						arPoolCapacity[x] += poolCapacity.get(x);
					}
				}
			}
			
			//arPoolCapacity contains all the pools capacity
			//I get the minimum from the pools capacities
			int partialScore = -1;
			for(int capacity : arPoolCapacity){
				if(partialScore == -1 || capacity < partialScore){
					partialScore = capacity;
				}
			}
			
			if(globalScore == -1 || partialScore < globalScore){
				globalScore = partialScore;
			}
		}
		return globalScore;
	}
	
	
	private int getDoubleRowFailure(Solution sol){
		int numPools = sol.problem.poolNumber;
		int globalScore = -1;
		
		
		for(CoupleBean couple : getCouples(sol.rows.size())){
			int[] arPoolCapacity = new int[numPools];
			for(int j=0; j < sol.rows.size(); j++){
				//i is the excluded row
				if(couple.getVal1() != j && couple.getVal2() != j){
					ArrayList<Integer> poolCapacity = sol.rows.get(j).poolCapacity;
					for(int x = 0; x < poolCapacity.size(); x++){
						arPoolCapacity[x] += poolCapacity.get(x);
					}
				}
			}
			//arPoolCapacity contains all the pools capacity
			//I get the minimum from the pools capacities
			int partialScore = -1;
			for(int capacity : arPoolCapacity){
				if(partialScore == -1 || capacity < partialScore){
					partialScore = capacity;
				}
			}
			
			if(globalScore == -1 || partialScore < globalScore){
				globalScore = partialScore;
			}
		}
		return globalScore;
	}
	
	private int getTripleRowFailure(Solution sol){
		int numPools = sol.problem.poolNumber;
		int globalScore = -1;
		
		
		for(TripleBean triple : getTriples(sol.rows.size())){
			int[] arPoolCapacity = new int[numPools];
			for(int j=0; j < sol.rows.size(); j++){
				//i is the excluded row
				if(triple.getVal1() != j && triple.getVal2() != j && triple.getVal3() != j){
					ArrayList<Integer> poolCapacity = sol.rows.get(j).poolCapacity;
					for(int x = 0; x < poolCapacity.size(); x++){
						arPoolCapacity[x] += poolCapacity.get(x);
					}
				}
			}
			//arPoolCapacity contains all the pools capacity
			//I get the minimum from the pools capacities
			int partialScore = -1;
			for(int capacity : arPoolCapacity){
				if(partialScore == -1 || capacity < partialScore){
					partialScore = capacity;
				}
			}
			
			if(globalScore == -1 || partialScore < globalScore){
				globalScore = partialScore;
			}
		}
		return globalScore;
	}
	
	
	private List<CoupleBean> getCouples(int numRows){
		List<CoupleBean> lst = new ArrayList<CoupleBean>();
		for(int i = 0; i < numRows - 1; i++){
			for(int j = i+1; j < numRows; j++){
				lst.add(new CoupleBean(i, j));
			}
		}		
		return lst;
	}
	
	private List<TripleBean> getTriples(int numRows){
		List<TripleBean> lst = new ArrayList<TripleBean>();
		for(int i = 0; i < numRows - 2; i++){
			for(int j = i+1; j < numRows -1; j++){
				for(int x = j+1; x < numRows; x++){
					lst.add(new TripleBean(i, j, x));
				}
			}
		}		
		return lst;
	}

}// ScoreMgr
