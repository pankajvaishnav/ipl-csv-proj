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

public class Task4 
{
	public void getNetRunRate() throws IOException
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
						
		//-------------------------------------------------------------------for>>TEAMS-PER-YEAR-matches
		ArrayList<String> teamsInYear = new ArrayList<String>();
				
		//System.out.println(midFours);
		System.out.println("\n\n\n");
		System.out.println(">>>  Team name which has Highest Net Run Rate with respect to year.");
		for(String year : years)
		{
			System.out.println();
			//System.out.println("Team name which has Highest Net Run Rate of Year: "+year);
			System.out.println("---------------------------------------------------------------------------");
			//TeamsPerYear tm = new TeamsPerYear();
			teamsInYear = TeamsPerYear.readDeliveries(year);
			HashMap<String, Double> map = new HashMap<>();
			for(String teams : teamsInYear)
			{
				//System.out.println(teams);
				double total1=0,total2=0,ball1=0,ball2=0,overs1=0,overs2=0,nrr=0;
				for(int i=0;i<csv.matchIdDeliveries.size();i++)
				{
					String teamA = csv.battingTeam.get(i);
					if(teamA.equalsIgnoreCase("Rising Pune Supergiant") && year.equals("2017"))
						teamA = "Rising Pune Supergiants";
					
					if(midYear.get(csv.matchIdDeliveries.get(i)).equals(year)&&teams.equals(teamA))
					{
						ball1++;
						total1=total1+(csv.getmIdRuns.get(i).charAt(0)-48);
					}
					
					String teamB = csv.bowlingTeams.get(i);
					if(teamB.equalsIgnoreCase("Rising Pune Supergiant") && year.equals("2017"))
						teamB = "Rising Pune Supergiants";
					
					if(midYear.get(csv.matchIdDeliveries.get(i)).equals(year)&&teams.equals(teamB))
					{
						ball2++;
						total2=total2+(csv.getmIdRuns.get(i).charAt(0)-48);
					}
					
				}
				overs1=ball1/6;
				overs2=ball2/6;
				nrr=(total1/overs1)-(total2/overs2);
				map.put(teams, nrr);
				//System.out.println(year+".  "+teams+"------------------------------Net_Run_Rate:             "+nrr);
			}
			Map<String, Double> top =map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.limit(1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			Collection<String> c1 = top.keySet();
			Collection<Double> c2 = top.values();
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
			for(int i=0;i<keys.size();i++)
			{
				System.out.println(year+"        "+keys.get(i)+":       "+values.get(i));
			}

		}
	}
}
