package org.jspiders.iplApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFileReader 
{
	ArrayList<String> matchIdDeliveries = new ArrayList<>();
	ArrayList<String> battingTeam = new ArrayList<>();
	ArrayList<String> bowlingTeams = new ArrayList<>();
	ArrayList<String> batsNames = new ArrayList<>();
	ArrayList<String> bwNames = new ArrayList<>();
	ArrayList<String> wides = new ArrayList<>();
	ArrayList<String> legByes = new ArrayList<>();
	ArrayList<String> byes = new ArrayList<>();
	ArrayList<String> noBalls = new ArrayList<>();
	ArrayList<String> bRuns = new ArrayList<>();
	ArrayList<String> extras = new ArrayList<>();
	ArrayList<String> getmIdRuns = new ArrayList<>();
	
//==================================deliveries.csv===================================================================================//
	@SuppressWarnings("resource")
	public void fetchDeliveriesFile() throws IOException 
	{
		String csvFileMatches = "C:\\Users\\pankaj vaishnav\\Desktop\\database\\deliveries.csv";
		BufferedReader brMatches = null;
		String lineMatches = "";
		String matchesCSVSplitBy = ",";
		
		brMatches = new BufferedReader(new FileReader(csvFileMatches));
		
		int count=0;
		while ((lineMatches = brMatches.readLine()) != null)
		{
			String[] col = lineMatches.split(matchesCSVSplitBy);
			if(count>0)
			{
				matchIdDeliveries.add(col[0]);
	      		battingTeam.add(col[2]);
	      		bowlingTeams.add(col[3]);
	      		batsNames.add(col[6]);
	      		bwNames.add(col[7]);
	      		wides.add(col[8]);
	      		byes.add(col[9]);
	      		legByes.add(col[10]);
	      		noBalls.add(col[11]);
	      		bRuns.add(col[13]);
	      		extras.add(col[14]);
	      		getmIdRuns.add(col[15]);//getmIdRuns
			}
			count++;
		}
	}
	
//--------------------------------------------------------------------------------------------------------------------------------//
	ArrayList<String> matchId = new ArrayList<String>();
	ArrayList<String> season = new ArrayList<String>();
	ArrayList<String> city = new ArrayList<String>();
	ArrayList<String> team1 = new ArrayList<String>();
	ArrayList<String> team2 = new ArrayList<String>();
	ArrayList<String> tossWonBy = new ArrayList<String>();
	ArrayList<String> tossDecision = new ArrayList<String>();
	ArrayList<String> result = new ArrayList<String>();
	ArrayList<String> winner = new ArrayList<String>();
	
//==================================matches.csv===================================================================================//
	@SuppressWarnings("resource")
	public void fetchMatchesFile() throws IOException 
	{
		String csvFileMatches = "C:\\Users\\pankaj vaishnav\\Desktop\\database\\matches.csv";
		BufferedReader brMatches = null;
		String lineMatches = "";
		String matchesCSVSplitBy = ",";
	        
		brMatches = new BufferedReader(new FileReader(csvFileMatches));
		int count=0;
		while ((lineMatches = brMatches.readLine()) != null)
		{
			String[] col = lineMatches.split(matchesCSVSplitBy);
			if(count>0)
			{
				matchId.add(count+"");
				season.add(col[1]);
				city.add(col[2]);
				team1.add(col[4]);
				team2.add(col[5]);
				tossWonBy.add(col[6]);
				tossDecision.add(col[7]);
				result.add(col[8]);
				winner.add(col[9]);
			}
			count++;
		}
	}
}

