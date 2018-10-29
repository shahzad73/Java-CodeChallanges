
package com.testing;


//Palindrome means if a sting or integer is inverted then it statys the same like    1331    if you invert it it will staty same
public class isPalindrome {
	
public static void main(String[] args) {
    System.out.println( isPalindrome(1331)  );
    System.out.println( isPalindrome(43434)  );    
    System.out.println( isPalindrome(43424)  );    
}


public static boolean isPalindrome(int z)
{
    String str = String.valueOf(z);
    char[] charArray = str.toCharArray();
    
    System.out.println(str);
    int start;
    int end = str.length() - 1;
    
    boolean isPalindrome = true;
    
    for(start=0;start<end;start++) 
    {
       if(charArray[start] != charArray[end])
         isPalindrome = false;  
       
       end = end - 1;
    }
   
   return isPalindrome;
}

}