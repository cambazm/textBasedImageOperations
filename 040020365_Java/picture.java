/**
 * Mehmet CAMBAZ
 * 040020365
 * 
 * Advanced Programming Term Project
 * Java : picture.java
 */

 /**
  * picture class
  * represents a picture
  * contains number of rows (height)
  * 		 number of columns (width)
  * 		 picture content (pixels)
  * */ 

public class picture 
{
	public char pixels[][];		//picture content
	private int rows;			//number of rows (height)
	private int cols;			//number of columns (width)
	
	//constructor
	picture()
	{
		//means nothing assigned
		cols = 0;
		rows = 0;
		pixels = new char[100][100];	//default picture size is 100*100
	}

	
	//given height and width constructor
	picture(int row, int col)
	{
		//means nothing assigned
		cols = col;
		rows = row;
		pixels = new char[row][col];
	}
	
	
	//copy constructor
	picture(picture right)
	{
		cols = right.cols;
		rows = right.rows;
		
		for(int i=0; i<right.getRows(); i++)
			pixels[i] = right.pixels[i];
	}
	
		
	public int getCols()
	{
		return cols;
	}

		
	public int getRows()
	{
		return rows;
	}
	
		
	public void setCols(int col)
	{
		cols = col;
	}
	
		
	public void setRows(int row)
	{
		rows = row;
	}
	
	
	/**
	 * prints the picture content to user
	 */
	public void print()
	{
	     for(int i=0; i<getRows(); i++)
	     {
		     for(int j=0; j<getCols(); j++)
		     	 System.out.print(pixels[i][j]);
		    
		     System.out.print("\n");
	     }
	}
	
}
