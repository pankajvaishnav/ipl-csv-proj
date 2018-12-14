package org.jspiders.iplApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Task3 
{
	public void getBowlerRecords() throws IOException 
	{
		CSVFileReader csv = new CSVFileReader();
		csv.fetchDeliveriesFile();
		csv.fetchMatchesFile();
		
		HashMap<String, String> midYear = new HashMap<String, String>();
		for(int i=0;i<csv.matchId.size();i++)
		{
			midYear.put(csv.matchId.get(i), csv.season.get(i));
		}
		
		//-------------------------------------------------------------------YEARS>>HASHSET-matches
		HashSet<String> yrs = new HashSet<>();
		for(String year : csv.season)
		{
			yrs.add(year);
		}		
		//-------------------------------------------------------------------YEARS>>ARRAYLIST-matches
		ArrayList<String> years = new ArrayList<String>();
		for(String year : yrs)
		{
			years.add(year);
		}
		Collections.sort(years);//-------------------------------------------sorted-unique-YEARS-matches			
		//System.out.println(midFours);
		//-------------------------------------------------------------------for>>BOWLERS-IN-YEAR-matches
		ArrayList<String> bowlersInYear = new ArrayList<String>();
		
		System.out.println("\n\n\n");
		System.out.println(">>>  Top 10 best economy rate bowler with respect to year who bowled at least 10 overs");
		System.out.println("-------------------------------------------------------------------------------------\n");
		
		//TeamsPerYear dc = new TeamsPerYear();
		for(String year : years)
		{
			/*System.out.println();
			System.out.println("Top 10 best economy rate bowler of Year: "+year);
			System.out.println("------------------------------------------------------------");*/
			bowlersInYear = BowlersPerYear.readDeliveries(year);
			HashMap<String, Double> map = new HashMap<>();
			
			for(String bwlr : bowlersInYear)
			{
				//System.out.println(bwlr);
				double total=0,ball=0,eco=0,overs=0;
				for(int i=0;i<csv.matchIdDeliveries.size();i++)
				{
					//System.out.println(bwlr);
					if(midYear.get(csv.matchIdDeliveries.get(i)).equals(year)&&bwlr.equals(csv.bwNames.get(i)))
					{
						ball++;
						total=total+(csv.getmIdRuns.get(i).charAt(0)-48)-(csv.byes.get(i).charAt(0)-48)-(csv.legByes.get(i).charAt(0)-48);
					}
				}
				overs=ball/6;
				eco=total/overs;
				if(overs>=10)
					map.put(bwlr, eco);
				
				//System.out.println(bwlr+"---"+eco);
				//Set<Map.Entry<String, Double>> set = map.entrySet();
			}
			Map<String, Double> topTen =map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
				       .limit(10).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			Collection<String> c1 = topTen.keySet();
			Collection<Double> c2 = topTen.values();
			ArrayList<String> keys = new ArrayList<>();
			for(String key : c1)
			{
				keys.add(key);
			}
			ArrayList<Double> values = new ArrayList<>();
			for(Double value : c2)
			{
				values.add(value);
			}
			for(int i=0;i<keys.size();)
			{
				System.out.println(year+"  "+keys.get(i)+"               "+values.get(i));
				break;
			}
			//System.out.println(values);
		} 
	}
}
