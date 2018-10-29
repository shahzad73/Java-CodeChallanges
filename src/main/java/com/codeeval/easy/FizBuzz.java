/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeeval.easy;

import java.io.IOException;
import java.io.*;


public class FizBuzz {
    
    public static void main (String[] args) throws IOException {
        
        File file = new File("D:\\fil.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            // Process line of input Here
            String[] arr = line.split(" ");
            int n1 = Integer.parseInt(arr[0]);
            int n2 = Integer.parseInt(arr[1]);
            int end = Integer.parseInt(arr[2]);
            for(int i=1; i<=end; i++)
            {
                if(  ((i % n1) == 0)   &&   ((i % n2) == 0)  )
                    System.out.print("FB ");                
                else if((i % n1) == 0)
                    System.out.print("F ");
                else if((i % n2) == 0)
                    System.out.print("B ");
                else
                    System.out.print(i + " ");
            }
            System.out.println("");
        }
    }
}



/*

sample text.   

first number is the first numer    second is also a number
third number is the loop that goes from 1 to N

if number is divisible by first number then print F
if number is divisible by second number then print B
if number is divisible by both first and second number then FB 


17 9 58
10 2 40
15 7 60
10 19 95
17 15 51
13 14 67
13 9 69
18 4 51
18 5 60
19 13 40
8 17 50
18 17 36
20 8 89
7 11 92
11 18 76
4 18 100
18 5 92
5 20 37
9 3 33
18 10 25



*/