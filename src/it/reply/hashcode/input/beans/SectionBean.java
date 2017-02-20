package it.reply.hashcode.input.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class SectionBean implements Serializable {
	
	List<List<Integer>> lstVal = null;

	public List<List<Integer>> getLstVal() {
		if(lstVal == null){
			lstVal = new ArrayList<List<Integer>>();			
		}
		return lstVal;
	}

	public void setLstVal(List<List<Integer>> lstVal) {
		this.lstVal = lstVal;
	}

	public void add(List<Integer> lst){
		getLstVal().add(lst);
	}

	@Override
	public String toString() {
		String str = "Section:\n";
		for(List<Integer> lst : lstVal){
			String line = "";
			for(Integer i: lst){
				line += i + " ";
			}
			str += line.trim() + "\n";
		}
		return str;
	}
	
	

}//SectionBean
