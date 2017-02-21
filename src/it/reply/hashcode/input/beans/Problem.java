package it.reply.hashcode.input.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.reply.hashcode.RowSegment;
import it.reply.hashcode.Server;
import it.reply.hashcode.output.beans.Solution;

/**
 * @author d.cavassa
 *
 */
@SuppressWarnings("serial")
public class Problem implements Serializable {
	public final ArrayList<List<RowSegment>> rows = new ArrayList<List<RowSegment>>();
	public final ArrayList<Server> servers = new ArrayList<Server>();
	public final int poolNumber;

	public Problem(File inputFile) throws IOException {

		if (!inputFile.exists() || !inputFile.isFile()) {
			System.out.println("File does not exist or is not a file");
			System.exit(-2);
		}

		BufferedReader br = null;
		br = new BufferedReader(new FileReader(inputFile));
		String line = null;
		int lineNum = 0;

		String params[] = br.readLine().trim().split(" ");
		int rowsNumber = new Integer(params[0]);
		int rowCapacity = new Integer(params[1]);
		int unavailableSlots = new Integer(params[2]);
		poolNumber = new Integer(params[3]);
		int serversNumber = new Integer(params[4]);

		ArrayList<ArrayList<Integer>> unavailable = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < rowsNumber; ++i){
			unavailable.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < unavailableSlots; i++) {
			params = br.readLine().trim().split(" ");
			int row = new Integer(params[0]);
			int seg = new Integer(params[1]);
			unavailable.get(row).add(seg);
		}

		for(int row = 0; row < rowsNumber; ++row){
			ArrayList<Integer> unavailableRow = unavailable.get(row);
			unavailableRow.sort((i,j) -> Integer.compare(i, j));
			unavailableRow.add(rowCapacity + 1);
			ArrayList<RowSegment> segments = new ArrayList<RowSegment>();
			int segmentBegin = 0;
			
			for(int unavailableIndex: unavailableRow){
				if(unavailableIndex != segmentBegin){
					segments.add(new RowSegment(unavailableIndex - segmentBegin - 1, segmentBegin));
				}
				segmentBegin = unavailableIndex + 1;
			}
			rows.add(segments);
		}
		/*
		for (int c = 0, j = 0; c < rowsNumber; c++) {
			ArrayList<RowSegment> segments = new ArrayList<RowSegment>();
			int next;
			int i = 0;
			do {
				next = rowCapacity-i;
				if (brokenSeg.containsKey(c) && brokenSeg.get(c).size() > j) {
					next = brokenSeg.get(c).get(j);
					j++;
				}
				if (next - i > 0)
					segments.add(new RowSegment(next - i -1, i));
				i = next + 1;
			} while (next < rowCapacity);
			rows.add(segments);
		}
		*/

		for (int i = 0; i < serversNumber; i++) {
			params = br.readLine().trim().split(" ");
			servers.add(new Server(i, new Integer(params[0]), new Integer(params[1])));
		}
		br.close();
	}

	public Solution emptySolution() {
		return new Solution(this);// TODO
	}
}// ConfigurationBean
