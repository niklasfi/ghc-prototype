package it.reply.hashcode.input.beans;

import java.io.Serializable;

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class ConfigurationBean implements Serializable {
	
	private Integer param0 = null;
	private Integer param1 = null;
	private Integer sec1len = null;
	private Integer sec2len = null;
	private Integer sec3len = null;
	private Integer numCycles = null;
	
	private Integer idxStartSec1 = null;
	private Integer idxStartSec2 = null;
	private Integer idxStartSec3 = null;
	
	private SectionBean sec1 = null;
	private SectionBean sec2 = null;
	private SectionBean sec3 = null;
	
	public Integer getParam0() {
		return param0;
	}
	public void setParam0(Integer param0) {
		this.param0 = param0;
	}
	public Integer getParam1() {
		return param1;
	}
	public void setParam1(Integer param1) {
		this.param1 = param1;
	}
	public SectionBean getSec1() {
		return sec1;
	}
	public void setSec1(SectionBean sec1) {
		this.sec1 = sec1;
	}
	public SectionBean getSec2() {
		return sec2;
	}
	public void setSec2(SectionBean sec2) {
		this.sec2 = sec2;
	}
	
	
	public Integer getSec1len() {
		return sec1len;
	}
	public void setSec1len(Integer sec1len) {
		this.sec1len = sec1len;
	}
	public Integer getSec2len() {
		return sec2len;
	}
	public void setSec2len(Integer sec2len) {
		this.sec2len = sec2len;
	}
	public Integer getSec3len() {
		return sec3len;
	}
	public void setSec3len(Integer sec3len) {
		this.sec3len = sec3len;
	}
	public Integer getIdxStartSec1() {
		return idxStartSec1;
	}
	public void setIdxStartSec1(Integer idxStartSec1) {
		this.idxStartSec1 = idxStartSec1;
	}
	public Integer getIdxStartSec2() {
		return idxStartSec2;
	}
	public void setIdxStartSec2(Integer idxStartSec2) {
		this.idxStartSec2 = idxStartSec2;
	}
	public Integer getIdxStartSec3() {
		return idxStartSec3;
	}
	public void setIdxStartSec3(Integer idxStartSec3) {
		this.idxStartSec3 = idxStartSec3;
	}
	public SectionBean getSec3() {
		return sec3;
	}
	public void setSec3(SectionBean sec3) {
		this.sec3 = sec3;
	}
	public Integer getNumCycles() {
		return numCycles;
	}
	public void setNumCycles(Integer numCycles) {
		this.numCycles = numCycles;
	}
	@Override
	public String toString() {
		String head = "\nConfiguration:\n" + param0 + " " + param1 + " " + sec1len + " " + sec2len + " " + sec3len +" " + sec3len + " numCycles" + numCycles + "\n";
		String data = sec1.toString() + sec2.toString() + sec3.toString();
		return head + data;
	}
	
}//ConfigurationBean
