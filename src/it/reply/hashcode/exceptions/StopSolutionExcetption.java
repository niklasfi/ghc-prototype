package it.reply.hashcode.exceptions;

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class StopSolutionExcetption extends Exception {
	String g_msg = null;
	public StopSolutionExcetption(){
		super();
	}
	public StopSolutionExcetption(Exception e){
		super(e);
	}
	public StopSolutionExcetption(String msg){
		super(msg);
		g_msg = msg;
	}

}//Class StopSolutionExcetption
