package it.reply.hashcode;

import java.io.File;

import it.reply.hashcode.exceptions.StopSolutionExcetption;
import it.reply.hashcode.input.beans.ConfigurationBean;
import it.reply.hashcode.mgrs.AlgorithmMgr;
import it.reply.hashcode.mgrs.InputMgr;
import it.reply.hashcode.mgrs.OutputMgr;
import it.reply.hashcode.output.beans.SolutionBean;

/**
 * @author d.cavassa
 *
 */
public class SolutionMgr {

	public static void main(String[] args) {

		if(args == null || args.length == 0){
			System.out.println("No arguments specified");
			System.exit(-1);
		}

		if(args == null || args.length != 2){
			System.out.println("Usage: SolutionMgr <inputFile> <outputDir>");
			System.exit(-1);
		}

		String inFilePath = args[0];
		File inputFile = new File(inFilePath);
		if(!inputFile.exists() || !inputFile.isFile()){
			System.out.println("File does not exist or is not a file: " + inFilePath);
			System.exit(-2);
		}
		
		String outDirPath = args[1];
		File outputDir = new File(outDirPath);
		if(!outputDir.exists() || !outputDir.isDirectory()){
			System.out.println("Directory does not exist or is not a directory: " + outDirPath);
			System.exit(-3);
		}
		
		try{
			new SolutionMgr().elaborate(inputFile, outputDir);
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
			System.exit(-4);
		}
		
	}//main
	
	
	public void elaborate(File inputFile, File outputDir) throws Exception{
		
		int solNum = 0;
		
		InputMgr cfgMgr = new InputMgr();
		
		ConfigurationBean config = cfgMgr.getConfig(inputFile);
		
		AlgorithmMgr algMgr = new AlgorithmMgr(config);
		OutputMgr outMgr = new OutputMgr(outputDir);
		
		SolutionBean sol = null;		
		boolean suboptimal = false;
		do{
			try{
				sol = algMgr.generateNextSolution(solNum++);
				if(sol != null){
					outMgr.writeToDir(sol);
				}
				
			}catch(StopSolutionExcetption e){
				//IN case of suboptimal solution
				suboptimal = true;
				System.out.println("Discarded Solution: " + e.getMessage());
			}

		}while(sol != null || suboptimal);
		
	}//elaborate
	
}//Class SolutionMgr
