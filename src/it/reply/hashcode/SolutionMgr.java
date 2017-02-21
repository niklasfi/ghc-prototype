package it.reply.hashcode;

import it.reply.hashcode.mgrs.AlgorithmMgr;
import it.reply.hashcode.mgrs.OutputMgr;

import java.io.File;

/**
 * @author d.cavassa
 *
 */
public class SolutionMgr {

	public static void main(String[] args) throws Exception {
		
		String prefix = "C:/Users/M.Queirolo/Desktop/hashcode/";

		String input = "input/";
		String output = "output/";

		String file = "ebcbe09d-a9e8-4c8c-ae23-761e44824c7a.in";
//		String file = "3e68ab86-b216-40f1-b7ad-41166b2b0ffe.in";
//		String file = "5c765651-ed73-4180-90d5-66a43373be2f.in";
//		String file = "70ab7a98-48c8-4bb5-a1bc-628974d27002.in";
//		String file = "92c5431d-4bfd-4dc7-8a7a-1e2ad77e9b6c.in";
//		String file = "098caacb-e39e-469b-8f91-6521567740ad.in";
//		String file = "dcexample.in";


		args = new String[] { prefix + input + file, prefix + output };
		
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
		
//		try{
			new SolutionMgr().elaborate(inputFile, outputDir);
/*		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			System.exit(-4);
		}
*/
	}//main

	public void elaborate(File inputFile, File outputDir) throws Exception{
		int solNum = 0;

		AlgorithmMgr algMgr = new AlgorithmMgr(inputFile);
		OutputMgr outMgr = new OutputMgr(outputDir, inputFile);
		
		for(int i = 0; i < 1; ++i){
			algMgr.run();
			//System.out.println("i: " + i);
		}


		outMgr.writeToDir(algMgr.best);
	}//elaborate
	
}//Class SolutionMgr
