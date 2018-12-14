package org.jspiders.iplApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Task2 
{
	public void getTeamRecords() throws IOException 
	{
		CSVFileReader csv = new CSVFileReader();
		csv.fetchDeliveriesFile();
		csv.fetchMatchesFile();
		
		//-------------------------------------------------------------------MATCH-ID-&-SEASON-matches
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
		//System.out.println("\n\n\n");
		System.out.println("\n\n\n\n>>>  List total number of fours, sixes, total score with respect to team and year.  ");
		for(String year : years)
		{
			System.out.println();
			System.out.println("List total number of fours, sixes, total score of Year: "+year);
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			//TeamsPerYear tm = new TeamsPerYear();
			teamsInYear = TeamsPerYear.readDeliveries(year);
			for(String teams : teamsInYear)
			{
				//System.out.println(teams);
				int total=0,fours=0,sixes=0;
				for(int i=0;i<csv.matchIdDeliveries.size();i++)
				{
					if(midYear.get(csv.matchIdDeliveries.get(i)).equals(year)&&teams.equals(csv.battingTeam.get(i)))
					{
						total=total+(csv.getmIdRuns.get(i).charAt(0)-48);
					}
					
					if(midYear.get(csv.matchIdDeliveries.get(i)).equals(year)&&teams.equals(csv.battingTeam.get(i))&&csv.bRuns.get(i).equals("4"))
					{
						fours++;
					}
					
					if(midYear.get(csv.matchIdDeliveries.get(i)).equals(year)&&teams.equals(csv.battingTeam.get(i))&&csv.bRuns.get(i).equals("6"))
					{
						sixes++;
					}
				}
				System.out.println(teams+"                    Fours: "+fours+"                     Sixes: "+sixes+"             Total Runs: "+total);
			}
		}
	}
}
