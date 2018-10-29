/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing;

public class reverseString {
    
        public static void main(String[] args){
            String str = "This is the word to reverse 123";
            reverseString obj = new reverseString();
            //str = obj.reverseOrderUsingAnohterBuffer(str);
            str = obj.reverseOrderUsingSameBuffer(str);
            System.err.println(str);
        }
        
        
        public String reverseOrderUsingSameBuffer(String str)
        {
            char[] chars = str.toCharArray();
            chars = reverseString(chars, 0, chars.length-1);
            
            int readingPos = 0;
            while(readingPos < chars.length)
            {
                if(chars[readingPos] != ' ')
                {
                    int WordStartPos = readingPos;
                    
                    while( readingPos < chars.length && chars[readingPos] != ' ')
                        readingPos++;
                    
                    chars = reverseString(chars, WordStartPos, readingPos-1);
                }
                else
                    readingPos++;
            }
            
            return String.valueOf(chars);
        }
        
        
        public char[] reverseString(char[]chars, int start, int end)
        {
            while(end > start)
            {
                char c = chars[end];
                chars[end] = chars[start];
                chars[start] = c;
                
                end--;
                start++;
            }
            
            return chars;
        }
        
        
        
        public String reverseOrderUsingAnohterBuffer(String str)
        {
            char[] chs = str.toCharArray();
            char[] chs2 = new char[chs.length];
            
            int currentReadPos = chs.length - 1;
            int currentWritePos = 0;
            int WordEndPosition = 0;
            
            
            while(currentReadPos >= 0)
            {
                if(chs[currentReadPos] == ' ')
                {
                    chs2[currentWritePos] = ' ';
                    currentReadPos--;
                    currentWritePos++;
                }
                else
                {
                    int WordEnd = currentReadPos;
                    while(currentReadPos >= 0)
                    {
                         if(chs[currentReadPos] == ' ')
                             break;
                         else
                             currentReadPos--;
                    }
                    for(int b=currentReadPos+1; b<=WordEnd; b++)
                    {
                        System.out.println(chs[b]);
                        chs2[currentWritePos] = chs[b];
                        currentWritePos++;
                    }
                }
            }
            return String.valueOf(chs2);
        }
        
}
