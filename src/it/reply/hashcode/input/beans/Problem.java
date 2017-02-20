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

		Map<Integer, List<Integer>> brokenSeg = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < unavailableSlots; i++) {
			params = br.readLine().trim().split(" ");
			int row = new Integer(params[0]);
			int seg = new Integer(params[1]);
			if (!brokenSeg.containsKey(row)) brokenSeg.put(row, new ArrayList<Integer>());
			brokenSeg.get(row).add(seg);
		}

		Comparator<Integer> comp = (Integer a, Integer b) -> {
		    return a.compareTo(b);
		};

		for (Integer b : brokenSeg.keySet()) {
			Collections.sort(brokenSeg.get(b), comp);
			brokenSeg.get(b).add(rowCapacity+1);
		}
		
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
