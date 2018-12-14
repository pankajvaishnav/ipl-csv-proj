package org.jspiders.iplApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class TeamsPerYear
{
	static public ArrayList<String> readDeliveries(String year) throws IOException 
    {
		CSVFileReader csv = new CSVFileReader();
		csv.fetchDeliveriesFile();
		csv.fetchMatchesFile();
		
		//-------------------------------------------------------------------MATCH-ID-&-SEASON-matches
		ArrayList<String> list = new ArrayList<>();
		HashSet<String>set = new HashSet<>();
		for(int i=0;i<csv.season.size();i++)
		{
			if(csv.season.get(i).equals(year))
			{
				set.add(csv.team1.get(i));
			}
		}
		for(String tm : set)
		{
			list.add(tm);
		}
		Collections.sort(list);
        return list;
    }

}


