/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeeval.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 *
 * @author sehzad
 */
public class bitposition {
    
    public static void main (String[] args) throws IOException {
        
        File file = new File("D:\\fil.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            
            String[] s = line.split(",");
            
            
            if( getBit(  Integer.parseInt(s[0]),  (Integer.parseInt(s[1]) - 1) ) == getBit(  Integer.parseInt(s[0]),  (Integer.parseInt(s[2]) - 1) )  )
                System.out.println("true");
            else
                System.out.println("false");
            
        }
        
    }
    
    
    static int getBit(int n, int k) {
        return (n >> k) & 1;
        
        /*
            n
            100010101011101010 (example)
            n >> 5
            000001000101010111 (all bits are moved over 5 spots, therefore
            &                   the bit you want is at the end)
            000000000000000001 (0 means it will always be 0,
            =                   1 means that it will keep the old value)
            1
        */        
    }  
    
}


