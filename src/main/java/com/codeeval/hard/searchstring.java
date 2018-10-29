/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeeval.hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class searchstring {
    
    public static void main (String[] args) throws IOException {
        
        
        File file = new File("D:\\fil.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        boolean result;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();            
                        
            String[] words = line.split(",");
            char[] s1 = words[0].toCharArray();
            char[] s2 = words[1].toCharArray();
            result = isMatched2(s1, s2);
            if(result == true)
                System.out.println("true");
            else
                System.out.println(   isMatched2(ReverseString(s1), ReverseString(s2))   );
            
            
        }    
    }
        
        
        
        static boolean isMatched2(char[] s1, char[] s2)
        {
            boolean matchstarted = false;
            
            int s1i = 0;
            int s2i = 0;
            
            if(s2[0] == '*')
                matchstarted  =  true; 
                
            for(s1i=0; s1i<s1.length; s1i++)
            {
                if( (matchstarted == false) )
                {
                    if( s1[s1i] == s2[s2i] )
                        matchstarted = true;
                }
                else
                {        
                     if(((s2i + 1) > (s2.length-1)) )
                         break;      
                     else
                     {
                        if(s2[s2i] == '*')
                        {
                            if(s1[s1i] == s2[s2i + 1])
                                s2i++;
                        }
                        else
                        {
                            s2i++;
                            if(s2[s2i] == '*')
                            {
                                if(((s2i + 1) > (s2.length-1)) )
                                    break;
                                else
                                {
                                    if(s1[s1i] == s2[s2i + 1])      //this if handles   ababab,ba*ba  case   * means 0 or more chaacters
                                        s2i++;                                
                                }
                            }
                            else
                            {
                                if( s1[s1i] != s2[s2i] )
                                {
                                   matchstarted = false;
                                   s2i = 0;
                                }
                            }
                        }
                     }
                }                
            }
            
            if( matchstarted== true && (s2i == (s2.length-1)) )
                return true;
            else
                return false;
            
        }
        
        
        
        static char[] ReverseString(char[] a)
        {
            char z; 
            int tmp1 = 0;
            int tmp2 = a.length - 1;
            while (tmp1<tmp2)
            {
               z = a[tmp1];
               a[tmp1] = a[tmp2];
               a[tmp2] = z;
               
               tmp1++;
               tmp2--;
            }
            
            return a;
        }
        
        
    
}

        
/*

af,f
vjQxZYp1S9bcb2LKXO7NoPyvHRKHf,f
Hello,ell
This is good, is
Old,Young
what is it,it
neiwfnoiwefioz,noir
This is good,*z
Cgqs6q5lf,lf
eqSEcXCq2GY jftCK4DjTbyvdcqPUbiqr,cDKYPjb
KgcDwvcBw9xOy KLGJNwUAUq N,cDwvcB*O
3 eyEBf P2QTNf2kl,2QTN
YarOmqbM715G0FvzWYojYmDidxHZY7,Ym*H
KQ15tSQ1WS4Kemc kfnSEaZo,SQ1WS4Ke
Po28Wz9myFyCW,\*C
ecNTbxI7Z2DLGYJOZNkePl2Ue pPYP,ZNk*l2Ue pPYP
xz1ZFQuSFjzYoAPSLrYSwFjgIoZX,SL*wFjg
bGDxTeC QK4ZTKBKcY2pth2xXfK6 MD8,2xXf
kku7RfOTShlVd7c9r2Smtu5,Sh
vjQxZYp1S9bcb2LKXO7NoPyvHRKHf,y				 
aoooa,ooa
ababab,ba*ba



aoooa,ooa    this is the special case where i have search in the reverse way as well
ababab,ba*ba     this is also special case.    *   means there can be 0 or more character in between     a-baba-b = ba*ba

*/