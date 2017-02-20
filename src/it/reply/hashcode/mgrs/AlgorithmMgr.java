package it.reply.hashcode.mgrs;

import it.reply.hashcode.exceptions.StopSolutionExcetption;
import it.reply.hashcode.input.beans.Problem;
import it.reply.hashcode.output.beans.CommandBean;
import it.reply.hashcode.output.beans.SolutionBean;

/**
 * @author d.cavassa
 *
 */
public class AlgorithmMgr {
	
	private Problem g_inData;
	private int optimalScore = 0;
	private ScoreMgr g_scoreMgr = null; 
	private final int NUM_MAX_SOLUTION = 3;
	
	public AlgorithmMgr(Problem inData){
		g_scoreMgr = new ScoreMgr(); 
		g_inData = inData;
	}
	
	public SolutionBean generateNextSolution(int solNum) throws StopSolutionExcetption{
		//TODO
	}//generateNextSolution
	
	
	private SolutionBean getSolution() throws StopSolutionExcetption{
		//TODO
	}//getSolution
	

	
	
	
}//Class AlgorithmMgr

