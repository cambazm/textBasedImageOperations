/**
 * Mehmet CAMBAZ
 * 040020365
 * 
 * Advanced Programming Term Project
 * Java : input.java
 */

/**
 * this source code is derived from the code at
 * http://kickjava.com/933.htm
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * input class
 * reads integer and String from the user
 */

 public class input 
 { 
     /**
      * reads String input from the user and returns it in a String object
      * */ 
     public String readString() 
     { 
	     BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
	     String data = null; 
	     
	     try 
	     { 
	    	 data = buf.readLine(); 
	     } 
	     
	     catch(Exception e) 
	     { 
	    	 System.out.print("An error occured, cannot read!"); 
	     } 
	     
	     return data; 
     } 
   
    
     /**
      * reads integer input from the user and returns it as an integer
      * */ 
     public int readInt() 
     { 
    	 int data = 0; 
    	 
    	 try 
    	 { 
    		 data = Integer.parseInt(readString()); 
    	 } 
    	 
    	 catch(NumberFormatException nfe) 
    	 { 
    		 System.out.print("You inputted suspicious data!\nTry again: "); 
    		 data = readInt(); 
    	 } 
    	 
    	 return data; 
     } 
 }
