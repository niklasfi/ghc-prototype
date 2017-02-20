package it.reply.hashcode.intermediate.beans;

/**
 * @author d.cavassa
 *
 */
public class CoupleBean {
	Integer val1 = null;
	Integer val2 = null;

	public CoupleBean(Integer a, Integer b){
		val1 = a;
		val2 = b;
	}

	public Integer getVal1() {
		return val1;
	}

	public void setVal1(Integer val1) {
		this.val1 = val1;
	}

	public Integer getVal2() {
		return val2;
	}

	public void setVal2(Integer val2) {
		this.val2 = val2;
	}

	@Override
	public String toString() {
		return "[" + val1 + "," + val2 + "]";
	}
	
	
}
