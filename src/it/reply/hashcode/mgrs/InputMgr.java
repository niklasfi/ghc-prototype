package it.reply.hashcode.mgrs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import it.reply.hashcode.input.beans.ConfigurationBean;
import it.reply.hashcode.input.beans.SectionBean;

/**
 * @author d.cavassa
 *
 */
public class InputMgr {
	
	final private String SEPARATOR = " ";
	
	public static void main(String[] args) {

		if(args == null || args.length == 0){
			System.out.println("No arguments specified");
			System.exit(-1);
		}

		String inFilePath = args[0];
		File inputFile = new File(inFilePath);
		if(!inputFile.exists() || !inputFile.isFile()){
			System.out.println("File does not exist or is not a file: " + inFilePath);
			System.exit(-2);
		}
		
		try{
			ConfigurationBean config = new InputMgr().getConfig(inputFile);
			System.out.println(config);
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
			System.exit(-4);
		}
		
	}//main	

	public ConfigurationBean getConfig(File inputFile) throws Exception{
		ConfigurationBean config = new ConfigurationBean();
		
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(inputFile));
			String line = null;
			int lineNum = 0;
			
			while((line = br.readLine()) != null){
				if(line.trim().length() > 0){
					System.out.println(line);
					
					if(lineNum == 0){
						//First line
						String parms[] = line.trim().split(SEPARATOR);
						if(parms != null){
							config.setParam0(new Integer(parms[0]));
							config.setParam1(new Integer(parms[1]));
							config.setSec1len(new Integer(parms[2]));
							config.setSec2len(new Integer(parms[3]));
							config.setSec3len(new Integer(parms[4]));
							config.setNumCycles(new Integer(parms[5]));

							int idxStartSec1 = 1;
							int idxStartSec2 = idxStartSec1 + config.getSec1len();
							int idxStartSec3 = idxStartSec2 + config.getSec2len();

							config.setIdxStartSec1(idxStartSec1);
							config.setIdxStartSec2(idxStartSec2);
							config.setIdxStartSec3(idxStartSec3);
							
							config.setSec1(new SectionBean());
							config.setSec2(new SectionBean());
							config.setSec3(new SectionBean());
						}
												
					}else{
						//Manage the line base on the logic
						//Es .. N rows section 1, M rows section 2
						if(lineNum < config.getIdxStartSec2()){ //Section 1
							config.getSec1().add(getValues(line));
						}else if(lineNum >= config.getIdxStartSec2() && lineNum < config.getIdxStartSec3()){ //section 2
							config.getSec2().add(getValues(line));
						}else{ //section 3
							config.getSec3().add(getValues(line));
						}
					}
				}
				lineNum++;
			}

			return config;

		}catch(Exception e){
			throw new Exception(e.getMessage());
		}finally{
			br.close();
		}
		
	}//getConfig

	private List<Integer> getValues(String line){
		List<Integer> lstVal = new ArrayList<Integer>();
		String values[] = line.trim().split(SEPARATOR);
		for(String val : values){
			lstVal.add(new Integer(val));
		}
		return lstVal;
	}//getValues

	
	
	
}//Class SolutionMgr
