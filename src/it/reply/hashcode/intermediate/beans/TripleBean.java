package it.reply.hashcode.intermediate.beans;

/**
 * @author d.cavassa
 *
 */
public class TripleBean {
	Integer val1 = null;
	Integer val2 = null;
	Integer val3 = null;

	public TripleBean(Integer a, Integer b, Integer c){
		val1 = a;
		val2 = b;
		val3 = c;
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

	public Integer getVal3() {
		return val3;
	}

	public void setVal3(Integer val3) {
		this.val3 = val3;
	}
	
	@Override
	public String toString() {
		return "[" + val1 + "," + val2 + "," + val3 + "]";
	}

}
