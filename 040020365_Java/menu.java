/**
 * Mehmet CAMBAZ
 * 040020365
 * 
 * Advanced Programming Term Project
 * Java : menu.java
 */

/**
 * menu class
 * shows the user menu and gets the choice
 */

public class menu 
{
	//constructor (empty)
	menu()
	{
		
	}
	
	
	/**
	 * shows the user menu and gets the choice
	 */
	public int getMenu()
	{
	    int choice;
	    input i = new input();

	    System.out.print("/*************************************************\\\n");     
	    System.out.print("| Please select an operation:                     |\n");
	    System.out.print("|-------------------------------------------------|\n");
	    System.out.print("| 1. Flip picture horizontally                    |\n");
	    System.out.print("| 2. Flip picture vertically                      |\n");
	    System.out.print("| 3. Rotate the picture                           |\n");
	    System.out.print("| 4. Rotate the picture 90 degress Anti-Clockwise |\n");
	    System.out.print("| 5. Rotate the picture 90 degress Clockwise      |\n");
	    System.out.print("| 6. Invert the color of picture                  |\n");
	    System.out.print("| 7. Scale the picture by given factor            |\n");
	    System.out.print("| 8. Find differences of two pictures             |\n");
	    System.out.print("| 9. Crop a region from the picture               |\n");
	    System.out.print("|10. Detect edges of a picture                    |\n");
	    System.out.print("|11. Clear screen                                 |\n");
	    System.out.print("|12. Change input picture                         |\n");
	    System.out.print("|13. Exit                                         |\n");
	    System.out.print("\\*************************************************/\n");
	     

	    choice = i.readInt();

	    
	    if(choice<=0 || choice>13)
	    {
	        System.out.print("Unrecognized choice");
	        return 0;
	    } 
	    else
	        return choice;    
	}
}
