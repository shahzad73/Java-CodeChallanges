/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeeval.easy;

import java.io.IOException;


public class primeadd {
    
    
    public static void main (String[] args) throws IOException {
        int no = 2;
        int count = 0;
        long sum = 0;
        while(count < 1000)
        {
            if(isPrimeNumber(no)){
                sum += no;
                count++;
            }
            no++;
        }
        System.out.println(sum);
    }
     
    private static boolean isPrimeNumber(int number){
         
        for(int i=2; i<=number/2; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
    
}
