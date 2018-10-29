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

/**
 *
 * @author sehzad
 */
public class adddigits {
    
    public static void main (String[] args) throws IOException {
        
        File file = new File("D:\\fil.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        int result = 0;
        char c;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            result = 0;
            for (int i = 0; i < line.length(); i++){
                result = result + Character.getNumericValue(line.charAt(i));
            }            
            System.out.println(result);
        }
        
    }
    
}
