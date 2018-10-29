package com.testing;


public class Code1 {
    
     public static void main(String[] args)
     {
        int[] z = {5, 5, 1, 7, 2, 3, 5};
        System.out.println( solution(5, z)  );
    
     }
     
     
    

    public static int solution(int X, int[] A) 
    {
        int FromStart = -1;
        int FromEnd;
        int CountFoundEquals = 0;
        int CountFoundNotEqual = 0;
        int CurrentIndexToMove = 0;
        FromEnd = A.length;
        
        while(true)
        {
            
            if(CurrentIndexToMove == 0)
            {
                if(FromStart + 1 == FromEnd)
                    break;
                else
                    FromStart++;                
                
                if( A[FromStart] == X)
                {
                    CountFoundEquals = CountFoundEquals + 1;
                    CurrentIndexToMove = 1;
                }
            }
         
            
            
            if(CurrentIndexToMove == 1)
            {
                if(FromEnd - 1 == FromStart)
                    break;
                else
                    FromEnd--;                 
                
                if( A[FromEnd] != X)
                {
                    CountFoundNotEqual = CountFoundNotEqual + 1;
                    CurrentIndexToMove = 0;
                }
            }

        }
        
        if(CountFoundNotEqual == CountFoundEquals)
            return FromStart + 1;    //because this is 0 based index so i added 1 to specify at what index the solution was found if this is 1 based array indexing 
        else                
            return -1;
        
    }
    
    
}     
 

