package it.reply.hashcode.mgrs;

import it.reply.hashcode.Server;
import it.reply.hashcode.output.beans.OutputObj;
import it.reply.hashcode.output.beans.Segment;
import it.reply.hashcode.output.beans.Solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

	public void writeToDir(Solution sol) throws Exception{
		DecimalFormat fmt = new DecimalFormat(PATTERN);
		Date currDate = new Date();
		
		String fileName = BASE_FILE_NAME + "_"+ currDate.getTime() + ".out";
		
		String outFile = g_outDir.getAbsolutePath() + File.separator + fileName;
		
		Map<Integer, OutputObj> result = new HashMap<Integer, OutputObj>();
		
		for (int iRow = 0; iRow < sol.rows.size(); iRow++) {
			for (Segment p : sol.rows.get(iRow).segments) {
				int startingPoint = 0;
				for (int iServer = 0; iServer < p.server.size(); iServer++) {
					Server s = p.server.get(iServer);
					if (!result.containsKey(s.id)) {
						startingPoint += p.startingPoint;
						result.put(s.id, new OutputObj(iRow, startingPoint, p.pools.get(iServer)));		
					}
					else continue;
				}
			}
		}
		for (Server s : sol.remainingServers) {
			result.put(s.id, new OutputObj());
		}
		
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter(outFile));
			for (Server s : sol.problem.servers) {
				bw.write(result.get(s.id).toString());
			}
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}finally{
			bw.flush();
			bw.close();
		}
	}//writeToDir
	
	
	
}//Class OutputMgr
