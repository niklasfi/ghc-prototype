package it.reply.hashcode.input.beans;

import it.reply.hashcode.RowSegment;
import it.reply.hashcode.Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class Problem implements Serializable {
	public final ArrayList<RowSegment> rows = new ArrayList<RowSegment>();
	public final ArrayList<Server> servers = new ArrayList<Server>();
	public final int poolNumber;
	public final int rowCapacity;
	public final int rowsNumber;
	public final int unavailableSlots;
	public final int serversNumber;
		
	public Problem(File inputFile) throws IOException {

		if(!inputFile.exists() || !inputFile.isFile()){
			System.out.println("File does not exist or is not a file");
			System.exit(-2);
		}
		
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(inputFile));
			String line = null;
			int lineNum = 0;
	
			String params[] = br.readLine().trim().split(" ");
			rowsNumber = new Integer(params[0]);
			rowCapacity = new Integer(params[1]);
			unavailableSlots = new Integer(params[2]);
			poolNumber = new Integer(params[3]);
			serversNumber = new Integer(params[4]);
			
			Map<Integer, List<Integer>> brokenSeg = new HashMap<Integer, List<Integer>>();
			for (int i = 0; i < unavailableSlots; i++) {
				params = br.readLine().trim().split(" ");
				int row = new Integer(params[0]);
				int seg = new Integer(params[1]);
				if (!brokenSeg.containsKey(row)) {
					brokenSeg.put(row, new ArrayList<Integer>());
				}
				else brokenSeg.get(row).add(seg);
			}
			
			for (int i = 0, j = 0; i < rowsNumber; i++) {
				int x = i; 
				if (brokenSeg.containsKey(i)) {
					i = brokenSeg.get(i).get(j)+1;
				}
				rows.add(new RowSegment(rowCapacity,x));
			}
			
			for (int i = 0; i < serversNumber; i++) {
				params = br.readLine().trim().split(" ");
				servers.add(new Server(i,new Integer(params[0]), new Integer(params[1])));
			}
		
		} catch(Exception e){
			System.out.println("Error "+e.getMessage());
		}finally{
			br.close();
		}
	}
}
