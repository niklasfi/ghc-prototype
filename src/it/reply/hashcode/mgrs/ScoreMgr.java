package it.reply.hashcode.mgrs;

import it.reply.hashcode.output.beans.CommandBean;
import it.reply.hashcode.output.beans.SolutionBean;

/**
 * @author d.cavassa
 *
 */
public class ScoreMgr {
	
	
	public int evaluate(SolutionBean sol){
		return 0;
	}//evaluate
	
	public int evaluate(CommandBean cmd){
		return 0;
	}//evaluate
	
	public boolean isBest(int a, int b){
		if(a == 0 && b == 0){
			return true; //No score management yet
		}
		return a > b;
	}//isBest

}// ScoreMgr
