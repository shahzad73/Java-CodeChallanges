package com.testing;

import javax.swing.JTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;


public class RankingTutorial {

	
	static HashMap<Integer, HashMap<String, Long>> destinationsTracking = new HashMap<Integer, HashMap<String, Long>>();
	static HashMap<String, Long> topItems = new HashMap<String, Long>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		int headSlot = 5;
		int tailSlot = 4;
		
		
	    
		for(int a=0; a<10; a++)
		{
			HashMap<String, Long> innerMap = new HashMap<String, Long>();
			destinationsTracking.put(a, innerMap);
		}
	    
		
		Random _rand;
		_rand = new Random();
		
		
		for(int a=0; a<destinationsTracking.size(); a++)
		{
			int numberOfItems = _rand.nextInt(20) + 3;
			HashMap<String, Long> temphs = destinationsTracking.get(a);
			
			System.out.println("Random -" + numberOfItems);
			for(int b=0;b<numberOfItems;b++)
			{
				temphs.put("Item-" + String.valueOf(_rand.nextInt(50000)), (long) _rand.nextInt(5000));
			}
		}
		
		
		for(int a=0; a<destinationsTracking.size(); a++)
		{
			HashMap<String, Long> currentHashSet = destinationsTracking.get(a);
			for (Entry<String, Long> currentEntry : currentHashSet.entrySet())
				System.out.println(currentEntry.getKey() + "  " + currentEntry.getValue());
			
			System.out.println("-----------");
		}
		
		

		
		
		
		
		
		topItems.clear();
		int TotalNumberOfRanksInRankedMap = 0;
		
		for(int a=0; a<destinationsTracking.size(); a++)
		{
			HashMap<String, Long> currentHashSet = destinationsTracking.get(a);
			
			for (Entry<String, Long> currentEntry : currentHashSet.entrySet())
			{
				if(!topItems.containsKey(currentEntry.getKey()))     //if item already ranked then no need for ranking
				{
					long lowestrankvalue = findLowestRankedValueFromRankedMap();
					if(currentEntry.getValue() > lowestrankvalue)
					{
						if(TotalNumberOfRanksInRankedMap < 3)
							TotalNumberOfRanksInRankedMap++;    //one more ranked item is going to be added
						else
							removeLowestRankedItems(lowestrankvalue);	 
						
						addSelectedRankInRankedMap(currentEntry.getValue());
					}
				}
			}			
		}
		
		
		System.out.println("------");
		System.out.println("sorted");
		for (Entry<String, Long> entry : topItems.entrySet())
		{
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}
		
		
		
		
		
		
	}
	
	
	
	
	private static long findLowestRankedValueFromRankedMap()
	{
		long LowestValue = -1;
		
		for (Entry<String, Long> tempEntry : topItems.entrySet())
		{
			if(LowestValue == -1)
				LowestValue = tempEntry.getValue();
			else
			{
				if(LowestValue > tempEntry.getValue() )
					LowestValue = tempEntry.getValue();
			}
		}
		
		return LowestValue;
	}
	
		
	
	private static void removeLowestRankedItems(long lowestrankvalue)
	{
		HashSet<String> tempHset = new HashSet<String>();
		for (Entry<String, Long> tempEntry : topItems.entrySet())
		{
			if(tempEntry.getValue() == lowestrankvalue)
				tempHset.add(tempEntry.getKey());
		}
                
		Iterator<String> tempIterator = tempHset.iterator();
                while(tempIterator.hasNext())
                    topItems.remove( tempIterator.next() );
	}
	
	
	private static void addSelectedRankInRankedMap(long rankitem)
	{
		for(int a=0; a<destinationsTracking.size(); a++)
			           
		{
			HashMap<String, Long> currentHashSet = destinationsTracking.get(a);
			
			for (Entry<String, Long> currentEntry : currentHashSet.entrySet())
			{
				if(currentEntry.getValue() == rankitem)
					topItems.put(currentEntry.getKey(), currentEntry.getValue());
			}
		}
	}	
	
}
