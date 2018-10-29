
/*

This is a demo task. You can read about this task and its solutions in this blog post.
http://blog.codility.com/2011/03/solutions-for-task-equi.html

A zero-indexed array A consisting of N integers is given. An equilibrium index of this array is any integer P 
such that 0 ≤ P < N and the sum of elements of lower indices is equal to the sum of elements of higher indices, 
i.e. 
A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N−1.

For example, consider the following array A consisting of N = 8 elements:

  A[0] = -1
  A[1] =  3
  A[2] = -4
  A[3] =  5
  A[4] =  1
  A[5] = -6
  A[6] =  2
  A[7] =  1

P = 1 is an equilibrium index of this array, because:
A[0] = −1   =   A[2] + A[3] + A[4] + A[5] + A[6] + A[7]

P = 3 is an equilibrium index of this array, because:
A[0] + A[1] + A[2] = −2 = A[4] + A[5] + A[6] + A[7]

P = 7 is also an equilibrium index, because:
A[0] + A[1] + A[2] + A[3] + A[4] + A[5] + A[6] = 0

and there are no elements with indices greater than 7.

P = 8 is not an equilibrium index, because it does not fulfill the condition 0 ≤ P < N.

Write a function:

int solution(int A[], int N);

that, given a zero-indexed array A consisting of N integers, returns any of its equilibrium indices. The function should return −1 if no equilibrium index exists.

For example, given array A shown above, the function may return 1, 3 or 7, as explained above.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.



another set is   

A[0]=-7 
A[1]=1 
A[2]=5 
A[3]=2 
A[4]=-4 
A[5]=3 
A[6]=0

{-7, 1, 5, 2, -4, 3, 0}

3 is an equilibrium index, because:
    A[0]+A[1]+A[2]  =   A[4]+A[5]+A[6]
6 is also an equilibrium index, because:
    A[0]+A[1]+A[2]+A[3]+A[4]+A[5]   =   0




https://codility.com/programmers/task/frog_river_one/


*/


package com.testing;

/**
 *
 * @author Administrator
 */
public class FindEqui {
    
     public static void main(String[] args)
     {
        int[] z = {-1, 3, -4, 5, 1, -6, 2, 1};
        //int[] z = {-7, 1, 5, 2, -4, 3, 0};
         
         
        for(int a=0; a<z.length; a++)
        { 
            System.out.println("-------------------Loop " + a + "--------------------------"); 
            int res = findEquiExcludingCurrentIndex(z, a);
            if( res != -1)
                System.out.println("Found a Equli - " + a);
        }

        System.out.println( "Single Loop" + FindEqulibriumWithSingleLoop_ON(z) );
         
         
        /*int[] z2 = {4, 4, 1, 2, 2, 1, 2 };  
        for(int a=0; a<z2.length; a++)
        { 
            System.out.println("-------------------Loop " + a + "--------------------------"); 
            int res = findEquiIncludingCurrentIndex(z2, a);
            if( res != -1)
                System.out.println("Found a Equli - " + a);
        }*/         
         
     }
     
     
    static int findEquiExcludingCurrentIndex(int[] z, int a)
    {   
            int sol1 = 0;
            int sol2 = 0;        
            int b;
            
            
            for(b=0; b<a; b++ )        
                sol1 += z[b];
            
            for(b=a+1; b<z.length; b++ ) 
                sol2 += z[b];
            
           if(sol1 == sol2)
               return a;
           else
               return -1;     
    }

    
    
    
    
    
    static int findEquiIncludingCurrentIndex(int[] z, int a)
    {
        int sol1 = 0;
        int sol2 = 0;
        int k;
        
        for(k=0; k<=a; k++)
            sol1 += z[k];
        
        for(k=a+1; k<z.length; k++)
            sol2 += z[k];
       
        
        
       if(sol1 == sol2)
          return a;
       else
          return -1;          
    }
    
    
    
    static int FindEqulibriumWithSingleLoop_ON(int[] arr)
    {
        int sum = 0;
        int SumLeft = 0;
        
        int tmp;
        
        for(tmp=0; tmp<arr.length; tmp++)
            sum += arr[tmp];
        
        for(tmp=0; tmp<arr.length; tmp++)
        {
            int SumRight = sum - SumLeft - arr[tmp];
            
            if(SumRight == SumLeft)
                return tmp;
            
            SumLeft += arr[tmp];
        }
        
        return -1;
    }
    
    
    
    
    
}


