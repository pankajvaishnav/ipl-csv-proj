package org.jspiders.iplApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Task1 {
	
	public void getRecords() throws IOException {
		CSVFileReader csvr = new CSVFileReader();
		csvr.fetchMatchesFile();
		ArrayList<String> teams2016 = TeamsPerYear.readDeliveries("2016");
		ArrayList<String> teams2017 = TeamsPerYear.readDeliveries("2017");
		HashSet<String> teams2016_2017 = new HashSet<>();
		for(String team : teams2016) {
			teams2016_2017.add(team);
		}
		for(String team : teams2017) {
			teams2016_2017.add(team);
		}
		//System.out.println(teams2016_2017);
		HashMap<String, Integer> map = new HashMap<>();
		
		for(int i = 0 ; i < csvr.matchId.size(); i++) {
			for(String team : teams2016_2017) {
				if(csvr.tossWonBy.get(i).equals(team) && csvr.tossDecision.get(i).equals("field") && (csvr.season.get(i).equals("2016") || (csvr.season.get(i).equals("2017")))) {
					if(map.containsKey(team)) {
						map.put(team, (map.get(team)+1));
					}else {
						map.put(team, 1);
					}
				}
			}
		}
		//System.out.println(map);
		Map<Object, Integer> topFour =map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .limit(4).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		//System.out.println(topFour);
		Collection<Object> keys = topFour.keySet();
		int serielNo = 0;
		System.out.println(">>>  List of Top Four Teams Which Won the Toss and Elected to Field First in 2016 and 2017.\n");
		for(Object key : keys) {
			System.out.println(++serielNo+".  "+key+"    "+topFour.get(key));
		}
	}
}
