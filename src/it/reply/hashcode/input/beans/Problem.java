package it.reply.hashcode.input.beans;

import it.reply.hashcode.*;
import it.reply.hashcode.output.beans.Solution;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class Problem implements Serializable {
	public final ArrayList<RowSegment> rows;
	public final ArrayList<Server> servers;
	public final int poolNumber;
		
	public Problem(File input) {
		//TODO
	}
	
	public Solution emptySolution() {
		return null;//TODO
	}
}//ConfigurationBean
