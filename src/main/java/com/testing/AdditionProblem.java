
//This is one of the questions asked in Amazon interview     and i fuckup with the solution
// Given a list of numbers and another number k, find IF there are any 2 numbers whose sum is k

package com.testing;
import java.util.HashSet;

public class AdditionProblem {
    
    	
	public static void main(String[] args) {
            int[] a = {2,2,4,5,6};
            FindSumCombinations(a, 4);
        }
    
        
        public static void FindSumCombinations(int[] arr, int k)
        {
            HashSet<String> hs = new HashSet<>();
            
            for(int a=0;a<arr.length;a++)
            {
                if(hs.contains( String.valueOf( k - arr[a] ) ) )
                {
                    System.out.print( arr[a] );
                    System.out.print( (arr[a] - k) );
                    System.out.print("------------");
                }
                
                hs.add(String.valueOf(arr[a]));
            }
   
        }
        
}
