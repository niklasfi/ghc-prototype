package it.reply.hashcode.output.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class SolutionBean implements Serializable {
	
	private int score = 0;
	
	List<CommandBean> lstCmd = null;

	public List<CommandBean> getLstCmd() {
		if(lstCmd == null){
			lstCmd = new ArrayList<CommandBean>();			
		}
		return lstCmd;
	}

	public void setLstCmd(List<CommandBean> lstCmd) {
		this.lstCmd = lstCmd;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void incScore(int delta) {
		this.score += delta;
	}
	
	
	@Override
	public String toString() {
		String str = "Solution:\n";
		for(CommandBean cmd : lstCmd){
			str += cmd.toString() + "\n";
		}
		return str;
	}
	
	

}//SectionBean
