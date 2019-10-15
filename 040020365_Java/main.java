/**
 * Mehmet CAMBAZ
 * 040020365
 * 
 * Advanced Programming Term Project
 * Java : main.java
 */


/**
 * main class
 * contains main method to be executed at start
 * contains the static methods for the picture process operations 
 */
public class main 
{

	/**
	 * main
	 */
	public static void main(String[] args) 
	{
		try 
		{
			int choice=0, exit=1;
			int i, j, factor=1, x, y, w, h;
			String filename, filename2;
			
			
			menu m = new menu();		//menu object responsible for menu display and getting choice
			input in = new input();		//input object responsible for user I/O interaction
			
			picture picInMemory = new picture();	//picture object to represent the picture which operations will be made on
			picture picInMemory2 = new picture();	//second picture object for additional operations to be made on 

			System.out.println("Enter the picture file: ");
			filename = in.readString();
			
			file f = new file(filename);	//file object which takes care of file I/O operations
			
			picInMemory = f.readFile();		//fill the picture from the file
			
			picInMemory.print();			//show picture to user

			//process loop
			while(exit != 0)
			{
				//show user menu and get choice
				choice = m.getMenu();
			    
			    switch(choice)
			    {
			        case 1:
			             System.out.print("<flipping horizontally>\n");
			             flipHorizontal(picInMemory);
			             break;
			             
			        case 2:
			             System.out.print("<flipping vertically>\n");
			             flipVertical(picInMemory);
			             break;
			             
			        case 3:
			             System.out.print("<rotating>\n");
			             rotate(picInMemory);
			             break;

			        case 4:
			             System.out.print("<rotating 90 degrees anti-clockwise>\n");
			             rotate90AntiClockwise(picInMemory);
			             break;

			        case 5:
			             System.out.print("<rotating 90 degrees clockwise>\n");
			             rotate90Clockwise(picInMemory);
			             break;

			        case 6:
			             System.out.print("<inverting color>\n");
			             invertColor(picInMemory);
			             break;                                                   

			        case 7:
			             System.out.print("Please enter the scale factor (an integer greater than 1): ");
			             factor = in.readInt();
			             if(factor<2)
			             {
			                 System.out.print("factor can not be less than 2\n");
			                 continue;
			             }
			             
			             System.out.println("<scaling by factor: "+factor);
			             scaleByFactor(picInMemory, factor);
			             break; 

			        case 8:
			             System.out.print("Please enter the second picture file: ");
			             filename2 = in.readString();
			             System.out.print("filename2: "+filename2+"\n");
			             
			     	     file f2 = new file(filename2);
			    	    
			    	     picInMemory2 = f2.readFile();

			    	     picInMemory2.print();

			             System.out.println("<finding differences with file: "+filename2+">");
			                       
			             findDifferences(picInMemory, picInMemory2);

			             break; 
			             
			        case 9:
			             System.out.print("Picture width: "+picInMemory.getCols()+", height: "+picInMemory.getRows()+"\n");
			             System.out.print("Please enter the x-start point for cropping: ");
			             x = in.readInt();
			             System.out.print("Please enter the y-start point for cropping: "); 
			             y = in.readInt();
			             System.out.print("Please enter the width for cropping: ");
			             w = in.readInt();
			             System.out.print("Please enter the height for cropping: ");
			             h = in.readInt();
			             
			             if(x<0 || y<0 || x>=picInMemory.getCols()-1 || y>=picInMemory.getRows()-1 || x+w>picInMemory.getCols() || y+h>picInMemory.getRows())
			             {
			                 System.out.print("Crop region is out of boundaries\n");
			                 continue;
			             }
			             
			             System.out.println("<cropping by coordinates: x: "+x+" y: "+y+" with width: "+w+" height:"+h+">");
			             cropRegion(picInMemory, x, y, w, h);
			             break;   

			        case 10:
			             System.out.print("<detecting edges>\n");
			             detectEdges(picInMemory);
			             break;
			             
			        case 11:
			        	 try
			        	 {
			        		 Runtime.getRuntime().exec("cmd cls");
			        	 }
			        	 catch(Exception e)
			        	 {
			        		 System.out.println("Unknown command");
			        	 }
			             break;                                                     

			        case 12:
			             System.out.print("Enter the picture file: ");
			             filename = in.readString();
			             System.out.println(filename);
			             
			     	     file f3 = new file(filename);
			        	    
			    	     picInMemory = f3.readFile();

					     for(i=0; i<picInMemory.getRows(); i++)
					     {
						     for(j=0; j<picInMemory.getCols(); j++)
						     	 System.out.print(picInMemory.pixels[i][j]);
						    
						     System.out.print("\n");
					     }

			             System.out.println("<now the picture file is: "+ filename + ">");
			             break;   

			        case 13:
			             exit = 0;
			             break;   
			                                               
			        default:
			             System.out.print("If you read this something bad has happened\n");
			             break;                                       
			    }//end of switch
			}//end of while
			
			System.exit(0);
		} 
		catch (RuntimeException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}


	}

	
	/**
	 * flips the given picture horizontally
	 * */
	private static void flipHorizontal(picture picInMemory)
	{
	     picture flippedPic = new picture(picInMemory.getRows(), picInMemory.getCols());
	     int j, i;
	     int lastColIndex = picInMemory.getCols()-1;
	     int lastRowIndex = picInMemory.getRows()-1;

	     //flip horizontally
	     for(j=lastColIndex; j>=0; j--)
	     {
	         for(i=0; i<lastRowIndex+1; i++)
	             flippedPic.pixels[lastRowIndex-i][j] = picInMemory.pixels[i][j];
	     }
	     
	     flippedPic.print();
	
	}
	
	
	/**
	 * flips the given picture vertically
	 * */	
	private static void flipVertical(picture picInMemory)
	{
	     picture flippedPic = new picture(picInMemory.getRows(), picInMemory.getCols());
	     int j, i;
	     int lastColIndex = picInMemory.getCols()-1;
	     int lastRowIndex = picInMemory.getRows()-1;

	     //flip vertically 
	     for(j=lastColIndex; j>=0; j--)
	     {
	         for(i=0; i<lastRowIndex+1; i++)
	              flippedPic.pixels[i][lastColIndex-j] = picInMemory.pixels[i][j];
	     }
	     
	     flippedPic.print();
	}
	
	
	/**
	 * rotates the given picture
	 * */
	private static void rotate(picture picInMemory)
	{
	     picture rotatedPic = new picture(picInMemory.getRows(), picInMemory.getCols());
	     picture tempPic = new picture(picInMemory.getRows(), picInMemory.getCols());
	     
	     int j, i;
	     int lastColIndex = picInMemory.getCols()-1;
	     int lastRowIndex = picInMemory.getRows()-1;
	     
	     //flip horizontally
	     for(j=lastColIndex; j>=0; j--)
	     {
	         for(i=0; i<lastRowIndex+1; i++)
	             tempPic.pixels[lastRowIndex-i][j] = picInMemory.pixels[i][j];
	     }

	     //flip vertically    
	     for(j=lastColIndex; j>=0; j--)
	     {
	         for(i=0; i<lastRowIndex+1; i++)
	              rotatedPic.pixels[i][lastColIndex-j] = tempPic.pixels[i][j];
	     }  
	     
	     rotatedPic.print();
		
	}
	
	
	/**
	 * rotates the given picture 90 degrees anti-clockwise
	 * */
	private static void rotate90AntiClockwise(picture picInMemory)
	{
	     picture rotatedPic = new picture(picInMemory.getCols(), picInMemory.getRows());
	     
	     int j, i;
	     int lastColIndex = picInMemory.getCols()-1;
	     int lastRowIndex = picInMemory.getRows()-1;
	     
	     for(j=lastColIndex; j>=0; j--)
	     {
	         for(i=0; i<lastRowIndex+1; i++)
	             rotatedPic.pixels[lastColIndex-j][i] = picInMemory.pixels[i][j];
	     } 
	     
	     rotatedPic.print();
		
	}
	
	
	/**
	 * rotates the given picture 90 degrees clockwise
	 * */	
	private static void rotate90Clockwise(picture picInMemory)
	{
	     picture rotatedPic = new picture(picInMemory.getCols(), picInMemory.getRows());
	     
	     int j, i;
	     int lastColIndex = picInMemory.getCols()-1;
	     int lastRowIndex = picInMemory.getRows()-1;
	     
	     for(i=lastRowIndex; i>=0; i--)
	     {
	          for(j=0; j<lastColIndex+1; j++)
	             rotatedPic.pixels[j][lastRowIndex-i] = picInMemory.pixels[i][j];
	     }

	     rotatedPic.print();
		
	}
	
	
	/**
	 * inverts the color of the given picture
	 * */
	private static void invertColor(picture picInMemory)
	{
	     picture invertedPic = new picture(picInMemory.getRows(), picInMemory.getCols());
	     
	     int j, i;
	     int lastColIndex = picInMemory.getCols()-1;
	     int lastRowIndex = picInMemory.getRows()-1;
	     
	     for(j=0; j<lastColIndex+1; j++)
	     {
	         for(i=0; i<lastRowIndex+1; i++)
	             invertedPic.pixels[i][j] = picInMemory.pixels[i][j] == '#' ? '.' : '#';
	     }   
	     
	     invertedPic.print();
	}
	
	
	/**
	 * scales the given picture with thw given factor
	 * */	
	private static void scaleByFactor(picture picInMemory, int factor)
	{
	     picture scaledPic = new picture(picInMemory.getRows()*factor, picInMemory.getCols()*factor);
	     
	     int lastColIndex = picInMemory.getCols()-1;
	     int lastRowIndex = picInMemory.getRows()-1;
	     int j, i, k, m;
   

	     for(i=0; i<(lastRowIndex+1)*factor; i+=factor)
	     {
	         for(j=0; j<(lastColIndex+1)*factor; j+=factor)
	         {
	             for(k=0; k<factor; k++)
	             {
	                 for(m=0; m<factor; m++)
	                 {
	                     scaledPic.pixels[i+k][j+m] = picInMemory.pixels[i/factor][j/factor];
	                 }
	             }
	         }
	     }
	     
	     scaledPic.print();
	}
	
	
	/**
	 * crops the area from the given picture from the point (x,y) with height h and width w
	 * */	
	private static void cropRegion(picture picInMemory, int x, int y, int width, int height)
	{
	     picture croppedPic = new picture(height, width);
	     
	     int j, i;
	     
	     for(i=x; i<x+height; i++)
	     {
	          for(j=y; j<y+width; j++)
	                   croppedPic.pixels[i-x][j-y] = picInMemory.pixels[i][j];
	     }  
	     
	     croppedPic.print();
	}
	
	
	/**
	 * finds the differences of given 2 pictures
	 * */		
	private static void findDifferences(picture picInMemory, picture picInMemory2)
	{
	     int maxRow = max(picInMemory.getRows()-1, picInMemory2.getRows()-1);
	     int maxCol = max(picInMemory.getCols()-1, picInMemory2.getCols()-1);
	     
		 picture differencePic = new picture(maxRow+1, maxCol+1);
	     int i, j;
	
	     
	     for(i=0; i<maxRow+1; i++)
	     {
	          for(j=0; j<maxCol+1; j++)
	              differencePic.pixels[i][j] = picInMemory.pixels[i][j]==picInMemory2.pixels[i][j] ? '.' : '#';
	     }
	     
	     differencePic.print();
	}
	
	
	/**
	 * detects the edges of the given picture
	 * */			
	private static void detectEdges(picture picInMemory)
	{
	     picture edgePic = new picture(picInMemory.getRows(), picInMemory.getCols());
	     
	     int j, i;
	     int lastColIndex = picInMemory.getCols()-1;
	     int lastRowIndex = picInMemory.getRows()-1;
	     
	     for(i=0; i<lastRowIndex+1; i++)
	     {
	         for(j=0; j<lastColIndex+1; j++)
	         {             
	             if(picInMemory.pixels[i][j] == '#')
	             {
	                  //if the black pixel is on the edges of the picture, then it is a part of edge             
	                  if(i==0 || j==0 || i==lastRowIndex || j==lastColIndex)
	                      edgePic.pixels[i][j] = '#';
	                  else
	                  {
	                      /* if all the pixels around a black pixel is black, then
	                         it is not a edge, otherwise it is considered edge  */
	                      if(picInMemory.pixels[i+1][j] == '#' 
	                      && picInMemory.pixels[i-1][j] == '#' 
	                      && picInMemory.pixels[i][j+1] == '#' 
	                      && picInMemory.pixels[i][j-1] == '#'
	                      && picInMemory.pixels[i-1][j-1] == '#'
	                      && picInMemory.pixels[i+1][j+1] == '#')
	                          edgePic.pixels[i][j] = '.';
	                      else
	                          edgePic.pixels[i][j] = '#';
	                  }                           
	             }
	             else
	                 edgePic.pixels[i][j] = '.';             
	 
	         } // j for end
	     } //i for end   
	     
	     edgePic.print();
	}
	
	
	/**
	 * returns the bigger or equal to of given two numbers
	 */
	private static int max(int n1, int n2)
	{
		if(n1 >= n2)
			return n1;
		else
			return n2;
	}
}
