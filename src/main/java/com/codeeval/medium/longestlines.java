/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeeval.medium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import structures.linkedlist;
import structures.linkedlist;
/**
 *
 * @author sehzad
 */
public class longestlines extends linkedlist {
    
    public static void main (String[] args) throws IOException {
            
        /*File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();            
            String[] items = line.split(" ");
            
        }
        */
        
        longestlines ll = new longestlines();
        
        ll.append("aaa");
        ll.append("bbb");
        ll.append("ccc");
        ll.replace("zzz", 1);
        ll.print();
        ll.removeNode(3);
        ll.removeNode(1);
        ll.print();
        ll.removeNode(1);
        ll.print();
        
        
    }
}
