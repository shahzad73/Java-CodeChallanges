package com.codeeval.hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

//Solved
public class Prefix {
    
    public static void main (String[] args) throws IOException {
        
        
        Stack<String> st = new Stack<String>();
        double tmp = 0;
        String operator = "";
        double CurrentNumber = 0;
        
        //File file = new File("D:\\fil.txt");
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();            
            String[] items = line.split(" ");
            
            for(int a=0;a<items.length; a++)
            {
                
                if( isInteger(items[a]) == true && isInteger(st.get(st.size()-1)) == true)
                {
                    CurrentNumber = Double.parseDouble(items[a] );
                    while(true)
                    {
                        //pop value and the operator and apply it. 
                        tmp = Double.parseDouble( st.pop() );
                        operator = st.pop();
                        
                        //System.out.println(tmp + " " + operator + " " + CurrentNumber  );
                        
                        if(operator.equalsIgnoreCase("+"))
                            CurrentNumber = tmp + CurrentNumber;
                        else if(operator.equalsIgnoreCase("/"))
                        {
                                CurrentNumber = tmp / CurrentNumber;
                        }
                        else if(operator.equalsIgnoreCase("*"))
                            CurrentNumber = tmp * CurrentNumber;                        
                        
                        
                        if(st.size() == 0)
                        {
                            st.push( String.valueOf(CurrentNumber) );
                            break;
                        }
                        else if(isInteger(st.get(st.size()-1)) == true)
                            continue;
                        else
                        {
                            st.push( String.valueOf(CurrentNumber) );
                            break;
                        }
                    }
                }
                else
                    st.push(items[a]);                
            }
            
            int iii = (int) Double.parseDouble(st.pop());
            System.out.println(iii);
        }
    
    }
    
    
        public static boolean isInteger(String s) {
            try { 
                Double.parseDouble(s);
            } catch(NumberFormatException e) { 
                return false; 
            } catch(NullPointerException e) {
                return false;
            }
            // only got here if we didn't return false
            return true;
        }    

        
/*        
        
        initially i used only int for all calculations. 
        but as you know there was / that can generate fractions. 
        so i convert all variables to doubles 
        and then in last display the result in int format by converting the double to int
        
        
                * + 2 3 4
                + / * + / / * + + + * * / + * * + + + / / + / / / + + * 5 8 2 7 2 6 6 9 5 2 1 4 0 4 8 3 7 6 1 3 1 4 3 5 7 8 0 9 0
                * * + * / + * + / * 7 6 1 9 9 1 4 5 6 3 5
                + + * + * * / * / + / / * + * / / / 5 1 1 5 4 7 5 4 9 7 6 0 3 8 6 7 0 3 2
                + + 5 3 8
                * + * / / * 3 3 9 2 8 2 2
                + / * / / * / * / * / / / * * / 6 5 2 0 6 5 9 0 3 9 7 4 8 8 7 1 4
                / * * 6 1 7 3
                + 4 5
                * * 3 9 3
                / + + * 6 2 2 1 1
                + 6 7
                * + * + / * + / * * 1 6 6 2 4 7 3 7 5 5 0
                * + / / / * * * + / + / / * + + 1 7 2 6 3 5 0 4 4 6 7 9 3 7 2 9 5
                * 3 3
                + + + / * + * / / / / + + + + * * * / * 1 3 5 8 6 2 1 1 3 4 5 4 8 6 3 7 0 7 7 6 5
                / * + + + 1 1 7 0 8 4
                * + / * * * 4 6 6 8 4 2 5
                / * / * * / * + * * * + + * + / * / * * 0 1 2 1 7 4 6 5 3 1 9 6 9 2 2 7 6 3 4 7 3
                / + 1 8 9
                + + * * / * 2 7 4 5 0 5 7
                / + + * * * + + * 2 5 8 0 3 1 0 9 6 1
                * * * 4 8 9 8
                / * * / + * * + + / / * * * + * * + 4 0 1 5 7 5 9 4 7 2 4 3 1 0 5 8 9 8 3
                * / / * / * / / + * * * / / * * / * + * + * + 8 4 3 9 5 1 1 7 1 6 2 3 8 3 0 9 7 4 0 5 2 9 5 2
                + + + / * + + + / + * * / * / 6 8 3 5 9 9 4 5 3 8 0 0 3 5 9 1
                * * + * * + * * * * * / / * / * + + * 8 3 6 9 6 1 2 7 2 6 4 1 9 0 3 8 5 5 8 0
                * 0 0
                * + * + * 0 7 7 3 1 3
                * + / / + * * * / + + / / + * + + * / + / + / * / / * * 3 5 4 1 7 1 8 6 5 9 3 0 1 8 4 5 9 6 2 7 9 6 2 7 3 2 8 0 0
                * + * / * * + / + * * + / + + * + / + + * / 5 3 1 7 7 7 5 1 6 0 4 1 9 2 3 6 6 3 0 9 2 5 8
                * + * + / + / / * 0 3 1 9 2 9 3 9 5 4
                + * 0 8 9
                + * * / 0 9 3 0 9
                * / * * * / * * + + / * / + + / + * + + 1 7 3 1 8 6 7 3 9 5 5 4 2 4 0 7 2 8 3 2 9
                * / * * / + 2 4 2 4 0 6 9
                * + / * * * / * / / + * * / / / / / + / 4 8 7 7 8 6 5 1 1 9 6 1 7 3 2 0 9 4 6 2 5
                * / * + 3 4 5 5 4
                * + 2 3 4
                / 8 4
                * * * + / + * + + + / * + * + + * 0 9 4 3 4 3 5 5 5 7 6 4 0 2 1 1 9 6
                + + / * 2 0 9 3 1
        
        
        
        
        
* * + * / + * + / * 7 6 1 9 9 1 4 5 6 3 5
8715.0
+ + * + * * / * / + / / * + * / / / 5 1 1 5 4 7 5 4 9 7 6 0 3 8 6 7 0 3 2
5.0
+ + 5 3 8
16.0
* + * / / * 3 3 9 2 8 2 2
12.0
+ / * / / * / * / * / / / * * / 6 5 2 0 6 5 9 0 3 9 7 4 8 8 7 1 4
4.0
/ * * 6 1 7 3
14.0
+ 4 5
9.0
* * 3 9 3
81.0
/ + + * 6 2 2 1 1
15.0
+ 6 7
13.0
* + * + / * + / * * 1 6 6 2 4 7 3 7 5 5 0
0.0
* + / / / * * * + / + / / * + + 1 7 2 6 3 5 0 4 4 6 7 9 3 7 2 9 5
270.0
* 3 3
9.0
+ + + / * + * / / / / + + + + * * * / * 1 3 5 8 6 2 1 1 3 4 5 4 8 6 3 7 0 7 7 6 5
18.0
/ * + + + 1 1 7 0 8 4
18.0
* + / * * * 4 6 6 8 4 2 5
1450.0
/ * / * * / * + * * * + + * + / * / * * 0 1 2 1 7 4 6 5 3 1 9 6 9 2 2 7 6 3 4 7 3
49577.99999999999
/ + 1 8 9
1.0
+ + * * / * 2 7 4 5 0 5 7
12.0
/ + + * * * + + * 2 5 8 0 3 1 0 9 6 1
15.0
* * * 4 8 9 8
2304.0
/ * * / + * * + + / / * * * + * * + 4 0 1 5 7 5 9 4 7 2 4 3 1 0 5 8 9 8 3
15.0
* / / * / * / / + * * * / / * * / * + * + * + 8 4 3 9 5 1 1 7 1 6 2 3 8 3 0 9 7 4 0 5 2 9 5 2
0.0
+ + + / * + + + / + * * / * / 6 8 3 5 9 9 4 5 3 8 0 0 3 5 9 1
15.0
* * + * * + * * * * * / / * / * + + * 8 3 6 9 6 1 2 7 2 6 4 1 9 0 3 8 5 5 8 0
0.0
* 0 0
0.0
* + * + * 0 7 7 3 1 3
66.0
* + / / + * * * / + + / / + * + + * / + / + / * / / * * 3 5 4 1 7 1 8 6 5 9 3 0 1 8 4 5 9 6 2 7 9 6 2 7 3 2 8 0 0
0.0
* + * / * * + / + * * + / + + * + / + + * / 5 3 1 7 7 7 5 1 6 0 4 1 9 2 3 6 6 3 0 9 2 5 8
40.0
* + * + / + / / * 0 3 1 9 2 9 3 9 5 4
136.0
+ * 0 8 9
9.0
+ * * / 0 9 3 0 9
9.0
* / * * * / * * + + / * / + + / + * + + 1 7 3 1 8 6 7 3 9 5 5 4 2 4 0 7 2 8 3 2 9
0.0
* / * * / + 2 4 2 4 0 6 9
0.0
* + / * * * / * / / + * * / / / / / + / 4 8 7 7 8 6 5 1 1 9 6 1 7 3 2 0 9 4 6 2 5
10.0
* / * + 3 4 5 5 4
28.0
* + 2 3 4
20.0
/ 8 4
2.0
* * * + / + * + + + / * + * + + * 0 9 4 3 4 3 5 5 5 7 6 4 0 2 1 1 9 6
5346.0
+ + / * 2 0 9 3 1
4.0        
        
        
*/
    
}


