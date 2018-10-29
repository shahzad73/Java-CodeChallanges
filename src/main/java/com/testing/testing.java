/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing;


import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.regex.Pattern;


public class testing {
    
     public static void main(String[] args)
     {
          int temp1, temp2, temp3, temp4;
          Random random = new Random();
          
          
         //--------------------------------------------------------------------------------
         //----------------------------------HashSet---------------------------------------
         //--------------------------------------------------------------------------------
         HashSet<String> s = new HashSet<String>();
         
         s.add("string 1");
         s.add("string 2");
         
         for (String s2 : s) {
            System.out.println(s2);
         }
         
         if(s.contains("string 1"))
             System.out.println("yes");

         
         
         
         
         
         
         //--------------------------------------------------------------------------------
         //----------------------------------Hash Map---------------------------------------
         //--------------------------------------------------------------------------------         
         HashMap<String, Integer> hm;
         hm = new HashMap<String, Integer>();
         
         hm.put("a", 1);
         hm.put("b", 2);
         
         for (Map.Entry<String, Integer> entry : hm.entrySet()) {
             System.out.println(entry.getKey() + " " + entry.getValue());
         }
         
         Integer i = hm.get("a");
         i = i + 100;
         
         hm.put("a", i);
         for (Map.Entry<String, Integer> entry : hm.entrySet()) {
             System.out.println(entry.getKey() + " " + entry.getValue());
         }
         
        
         
         
         
         
         
        //------------------------------------------------------------------------------------- 
        //------------------------------Loops in Java------------------------------------------
        //------------------------------------------------------------------------------------- 
        int [] numbers = {10, 20, 30, 40, 50};
        for(int x : numbers ){
           System.out.print( x );
           System.out.print(",");
        }
        System.out.print("\n");
        String [] names ={"James", "Larry", "Tom", "Lacy"};
        for( String name : names ) {
           System.out.print( name );
           System.out.print(",");
        }         
         
        
        int x = 10;
        while( x < 20 ) {
           System.out.print("value of x : " + x );
           x++;
           System.out.print("\n");
        }        
        
        do{
           System.out.print("value of x : " + x );
           x++;
           System.out.print("\n");
        }while( x < 20 );        
        
        
        
        
        
        
        
        
        
        
        
        
         //--------------------------------------------------------------------------------
         //----------------------------------Others ---------------------------------------
         //--------------------------------------------------------------------------------        
        String s2 = "ssss";
        if(s2 == "ssss")
            System.out.println("yes");
        
        
        
        
         //--------------------------------------------------------------------------------
         //----------------------------------Reading input from console -------------------------
         //--------------------------------------------------------------------------------           
        Scanner scan = new Scanner(System.in); 
        
        String ss = " bbbbb";
       
        
        
        /*
        int a; 
        double d;
        
        System.out.println ("Enter String :");
        ss = scan.nextLine();        
        ss = s + ss; 
        System.out.println(ss);
        
        
        System.out.println ("Enter Int :");
        a = scan.nextInt();
        System.out.println("Number entered is :" + a);
        
        
        System.out.println ("Enter Double :");
        d = scan.nextDouble();
        System.out.println("Number entered is :" + d);
        */
        
        
        
        //following will generate random number between 0-9       
        //for(temp1=0;temp1<=20;temp1++)
        //    System.out.println(temp1 + " - IP " + IPs[ random.nextInt( IPs.length ) ]);


        
        
        
        
        PrintWriter fileOutPut = null;
        try{   fileOutPut = new PrintWriter("d:/useractivitylog.txt");  }    catch(Exception e){}
        
        temp3 = 0;
        String[] IPs = new String[200 * 200];        
        for(temp1=1;temp1<=200;temp1++)
           for(temp2=1;temp2<=200;temp2++)
           {
               IPs[ temp3 ] = "192.68." + temp1 + "." + temp2 ;
               temp3++;
           }
        
        int UsersToPlayWith = 200;
        HashSet<String> loginUserHash = new HashSet<String>();

        long prevTime = System.currentTimeMillis();
        long currentSeconds = 0;
        while(currentSeconds < 86400)
        {
            long tt = System.currentTimeMillis();
            currentSeconds = currentSeconds + (tt - prevTime);
            prevTime = tt;
            String strTime = ConvertSecondsToMMHHSS(currentSeconds);
            
            for(temp1=0;  temp1<=(random.nextInt( 30 ) + 1);  temp1++ )    
            {    
                   String event = "";
                   
                   int PercentageToLoginUser = 0;

                   if( loginUserHash.isEmpty() )
                        PercentageToLoginUser = 100;
                   
                   if( loginUserHash.size() < 10 )
                       PercentageToLoginUser = 80;
                   
                   if( loginUserHash.size() >= 10 && loginUserHash.size() < 20 )
                       PercentageToLoginUser = 60;
                   
                   if( loginUserHash.size() >= 20 && loginUserHash.size() < 30 )
                       PercentageToLoginUser = 50;
                   
                   if( loginUserHash.size() >= 30 && loginUserHash.size() < 50 )
                       PercentageToLoginUser = 30;
                   
                   if( loginUserHash.size() >= 50 )
                       PercentageToLoginUser = 5;
                   
                   
                   
                   if( random.nextInt(100) < PercentageToLoginUser )
                   {
                       int tmpUser = random.nextInt( UsersToPlayWith );
                       int tempIP = random.nextInt(IPs.length);
                       //Generate login event of this user
                       if(! loginUserHash.contains( IPs[tempIP] + "-" + "User" + tmpUser ) )
                       {
                           //user is not login this UP address so log him in into this IP address
                           event = strTime + ", LOGIN, " + IPs[tempIP] + ", User" + tmpUser;
                           loginUserHash.add( IPs[tempIP] + "-" + "User" + tmpUser );
                       }
                   }
                   else
                   {
                       //Following code will select a random item from the set
                        String tmpSelectedItemFromSet = "";
                        int tmpItemNo = new Random().nextInt(loginUserHash.size()); 
                        temp4 = 0;
                        for(Object obj : loginUserHash)
                        {
                            if (temp4 == tmpItemNo)
                            {
                                tmpSelectedItemFromSet = (String)obj;
                                break;
                            }
                            temp4++;
                        }                       
                       
                       event = strTime + ", LOGOUT, " + tmpSelectedItemFromSet.split("-")[0] + ", " + tmpSelectedItemFromSet.split("-")[1];
                       loginUserHash.remove( tmpSelectedItemFromSet );                           
                   }
                   
                   
                   if(event != "")
                   {
                        System.out.println(event);
                        fileOutPut.println(event);
                   }
            }       
            fileOutPut.flush();
        }
        fileOutPut.close();
        
     }
     
     
     
        public static String ConvertSecondsToMMHHSS(long longVal)
        {
            int hours = (int) longVal / 3600;
            int remainder = (int) longVal - hours * 3600;
            int mins = remainder / 60;
            remainder = remainder - mins * 60;
            int secs = remainder;

            String timeis = "";
            if(hours > 9)
                timeis = String.valueOf(hours) + ":";
            else
                timeis = "0" + String.valueOf(hours) + ":";
            
            if(mins > 9)
                timeis = timeis + String.valueOf(mins) + ":";
            else
                timeis = timeis + "0" + String.valueOf(mins) + ":";
            
            if(secs > 9)
                timeis = timeis + String.valueOf(secs);
            else
                timeis = timeis + "0" + String.valueOf(secs);

            return timeis;
        }     
     
     
}
