package org.jspiders.iplApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class BowlersPerYear 
{
	static public ArrayList<String> readDeliveries(String year) throws IOException 
    {
		CSVFileReader csv = new CSVFileReader();
		csv.fetchDeliveriesFile();
		csv.fetchMatchesFile();
	
		HashMap<String, String> midYear = new HashMap<String, String>();
		for(int i=0;i<csv.matchId.size();i++)
		{
			midYear.put(csv.matchId.get(i), csv.season.get(i));
		}

		
		//-------------------------------------------------------------------MATCH-ID-&-SEASON-matches
		ArrayList<String> list = new ArrayList<>();
		HashSet<String>set = new HashSet<>();
		for(int i=0;i<csv.matchIdDeliveries.size();i++)
		{
			if(midYear.get(csv.matchIdDeliveries.get(i)).equals(year))
			{
				set.add(csv.bwNames.get(i));
			}
		}
		for(String tm : set)
		{
			list.add(tm);
		}
		Collections.sort(list);
		//System.out.println(list);
        return list;
    }
}
