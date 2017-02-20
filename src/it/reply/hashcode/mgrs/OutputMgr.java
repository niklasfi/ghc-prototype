package it.reply.hashcode.mgrs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.reply.hashcode.Server;
import it.reply.hashcode.output.beans.OutputObj;
import it.reply.hashcode.output.beans.Segment;
import it.reply.hashcode.output.beans.Solution;
/**
 * @author d.cavassa
 *
 */
public class OutputMgr {
	
	final private String PATTERN = "0000000000";
	final private String BASE_FILE_NAME = "out";
	final private File filename;
	
	private File g_outDir;
	
	public OutputMgr(File outDir, File filename){
		g_outDir = outDir;
		this.filename = filename;
	}//OutputMgr

	public void writeToDir(Solution sol) throws Exception{
		DecimalFormat fmt = new DecimalFormat(PATTERN);
		Date currDate = new Date();
		
		String fileName = this.filename.getName().substring(0,this.filename.getName().indexOf('.'))+".out";
		
		String outFile = g_outDir.getAbsolutePath() + File.separator + fileName;
		
		Map<Integer, OutputObj> result = new HashMap<Integer, OutputObj>();
		int tot = 0;
		for (int iRow = 0; iRow < sol.rows.size(); iRow++) {
			for (Segment p : sol.rows.get(iRow).segments) {
				int startingPoint = p.startingPoint;
				for (int iServer = 0; iServer < p.server.size(); iServer++) {
					Server s = p.server.get(iServer);
					if (!result.containsKey(s.id)) {
//						System.out.println("RESULT "+s);
						result.put(s.id, new OutputObj(iRow, startingPoint, p.pools.get(iServer)));		
						startingPoint += s.size;
						tot++;
					}
					else continue;
				}
			}
		}
		
		for (Server s : sol.remainingServers) {
			result.put(s.id, new OutputObj());
		}
		
		Comparator<Integer> comp = (Integer a, Integer b) -> {
		    return a.compareTo(b);
		};

		List<Integer> serversID = new ArrayList(result.keySet());
		Collections.sort(serversID, comp);
		System.out.println(serversID);
		
		BufferedWriter bw = null;
		int i = 0;
		try{
			bw = new BufferedWriter(new FileWriter(outFile));
			for (i = 0; i < sol.problem.servers.size(); i++) {
				bw.write(result.get(sol.problem.servers.get(i).id).toString());
				if (i+1 < sol.problem.servers.size()) bw.write("\r\n");
			}
		}catch(Exception e){
			System.out.println("INDEX ERROR "+i);
			throw new Exception(e.getMessage());
		}finally{
			bw.flush();
			bw.close();
		}
	}//writeToDir
	
	
	
}//Class OutputMgr
