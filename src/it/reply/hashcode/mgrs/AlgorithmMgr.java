package it.reply.hashcode.mgrs;

import it.reply.hashcode.exceptions.StopSolutionExcetption;
import it.reply.hashcode.input.beans.ConfigurationBean;
import it.reply.hashcode.output.beans.CommandBean;
import it.reply.hashcode.output.beans.SolutionBean;

/**
 * @author d.cavassa
 *
 */
public class AlgorithmMgr {
	
	private ConfigurationBean g_inData;
	private int optimalScore = 0;
	private ScoreMgr g_scoreMgr = null; 
	private final int NUM_MAX_SOLUTION = 3;
	
	public AlgorithmMgr(ConfigurationBean inData){
		g_scoreMgr = new ScoreMgr(); 
		g_inData = inData;
	}
	
	public SolutionBean generateNextSolution(int solNum) throws StopSolutionExcetption{
		if(solNum > NUM_MAX_SOLUTION){
			return null;
		}
		SolutionBean solution = getSolution();
		int currScore = g_scoreMgr.evaluate(solution);
		if(!g_scoreMgr.isBest(currScore, optimalScore)){
			throw new StopSolutionExcetption("Not optimal score");
		}
		solution.setScore(currScore);
		optimalScore = currScore;
		return solution;		
	}//generateNextSolution
	
	
	private SolutionBean getSolution() throws StopSolutionExcetption{
		int partialScore = 0;
		SolutionBean solution = new SolutionBean();
		
		for(int i = 0; i < g_inData.getNumCycles(); i++){
			CommandBean cmd = getCommand(i);
			partialScore += g_scoreMgr.evaluate(cmd);
			if(!g_scoreMgr.isBest(partialScore, optimalScore)){
				throw new StopSolutionExcetption("Not optimal partial score");
			}
			solution.getLstCmd().add(cmd);
		}

		return solution;		
	}//getSolution
	
	
	private CommandBean getCommand(int cycle){
		CommandBean command = new CommandBean();
		
		//Logic to build a command
		for(int i=0; i < 5; i++){
			command.getLstVal().add(new Integer(cycle + i));
		}
		
		return command;		
	}//getSolution
	
	
	
	
}//Class AlgorithmMgr

