package it.reply.hashcode.output.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class CommandBean implements Serializable {
	
	List<Integer> lstVal = null;

	public List<Integer> getLstVal() {
		if(lstVal == null){
			lstVal = new ArrayList<Integer>();			
		}
		return lstVal;
	}

	public void setLstVal(List<Integer> lstVal) {
		this.lstVal = lstVal;
	}

	public void add(Integer lst){
		getLstVal().add(lst);
	}

	@Override
	public String toString() {
		String str = "";
		for(Integer i : lstVal){
			str += i  + " ";
		}
		return str.trim()+"\n";
	}
	
	

}//SectionBean
