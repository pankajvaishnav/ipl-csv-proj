package org.jspiders.iplApp;

import java.io.IOException;

public class Test 
{
	public static void main(String[] args) throws IOException
	{
		//------------------------------------------class-1
		Task1 t1 = new Task1();
		t1.getRecords();
		
		//------------------------------------------class-2
		Task2 t2 = new Task2();
		t2.getTeamRecords();
		
		//------------------------------------------class-3
		Task3 t3 = new Task3();
		t3.getBowlerRecords();
		
		//------------------------------------------class4
		Task4 t4 = new Task4();
		t4.getNetRunRate();
		
		//TeamsPerYear.readDeliveries("2016");
		//BowlersPerYear.readDeliveries("2017");
	}
}
