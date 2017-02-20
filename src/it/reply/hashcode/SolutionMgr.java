package it.reply.hashcode;

import it.reply.hashcode.mgrs.AlgorithmMgr;
import it.reply.hashcode.mgrs.OutputMgr;

import java.io.File;

/**
 * @author d.cavassa
 *
 */
public class SolutionMgr {

	public static void main(String[] args) {

		args = new String[] { "C:/Users/M.Queirolo/Desktop/hashcode/input/3e68ab86-b216-40f1-b7ad-41166b2b0ffe.in", "C:/Users/M.Queirolo/Desktop/hashcode/output/" };
		
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

		AlgorithmMgr algMgr = new AlgorithmMgr(inputFile);
		OutputMgr outMgr = new OutputMgr(outputDir);
		
		//do{
			algMgr.run();	
		//} while(System.in.read() != 'x');

		outMgr.writeToDir(algMgr.getBestSolution());
	}//elaborate
	
}//Class SolutionMgr
