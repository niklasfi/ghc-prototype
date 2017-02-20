package it.reply.hashcode.mgrs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Date;

import it.reply.hashcode.output.beans.CommandBean;
import it.reply.hashcode.output.beans.SolutionBean;

/**
 * @author d.cavassa
 *
 */
public class OutputMgr {
	
	final private String PATTERN = "0000000000";
	final private String BASE_FILE_NAME = "out";
	
	private File g_outDir;
	
	public OutputMgr(File outDir){
		g_outDir = outDir;
	}//OutputMgr

	public void writeToDir(SolutionBean sol) throws Exception{
		DecimalFormat fmt = new DecimalFormat(PATTERN);
		Date currDate = new Date();
		
		String fileName = fmt.format(sol.getScore()) + "_" + BASE_FILE_NAME + "_"+ currDate.getTime() + ".txt";
		
		String outFile = g_outDir.getAbsolutePath() + File.separator + fileName;
		
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter(outFile));
			
			for(CommandBean cmd : sol.getLstCmd()){
				bw.write(cmd.toString());
			}
			
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}finally{
			bw.flush();
			bw.close();
		}

		
		
		
	}//writeToDir
	
	
	
}//Class OutputMgr
