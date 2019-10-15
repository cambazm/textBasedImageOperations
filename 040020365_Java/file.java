/**
 * Mehmet CAMBAZ
 * 040020365
 * 
 * Advanced Programming Term Project
 * Java : file.java
 */

import java.io.*;

/**
 * file class
 * contains file's name
 * contains a method that reads a picture from file and returns it as a picture object 
 */
public class file 
{
	String name;		//file's name

	//constructor
	file(String filename)
	{	
		name = filename;
	}
	
	
	/**
	 * reads a picture from file and returns it as a picture object
	 */
	public picture readFile()
	{
		picture picInMemory = new picture();		
        String row = null;
        int i=0, j=0;

        try 
        { 
        	FileReader fr = new FileReader(name);
        	BufferedReader br = new BufferedReader(fr);

        	row = new String();
        	while ((row = br.readLine()) != null) 
        	{
        		//set column number only once
        		if(i==0)
        			picInMemory.setCols(row.length());
        		
        		
        		for(j=0; j<row.length(); j++)
        			picInMemory.pixels[i][j] = row.charAt(j);
        		
        		i++;
        	}
        	
        	picInMemory.setRows(i);
        } 
        
        catch (IOException e) 
        { 
            // catch possible io errors from readLine()
            System.out.println("Cannot read file: "+name);
            System.exit(1);
        }
		
		return picInMemory;
	}

}
