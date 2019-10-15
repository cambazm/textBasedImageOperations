/**
 * Mehmet CAMBAZ
 * 040020365
 * 
 * Advanced Programming Term Project
 * C : 040020365_c.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX(x, y) x>=y ? x : y

/*
 * represents the picture
 */
typedef struct pic_s
{
    char pixels[100][100];        
} pic_t;

int menu();
void flipHorizontal(pic_t, int, int);
void flipVertical(pic_t, int, int);
void rotate(pic_t, int, int);
void rotate90AntiClockwise(pic_t, int, int);
void rotate90Clockwise(pic_t, int, int);
void invertColor(pic_t, int, int);
void scaleByFactor(pic_t, int, int, int);  
void cropRegion(pic_t, int, int, int, int);
void findDifferences(pic_t, pic_t, int, int, int, int);
void detectEdges(pic_t, int, int);


/*
 * main function
 */
int main()
{
    pic_t picInMemory, picInMemory2;
    FILE *file, *file2;
    char filename[15];
    char filename2[15];   
    char row[100] = { '\0' };
    int i, j, rows, cols, rows2, cols2, factor=1, x, y, w, h;
    int choice=0, exit=1;

        
    printf("Enter the picture file: ");
    scanf("%s",&filename);
    
    file = fopen(filename, "r");
    if(!file) 
    {
        printf("File \"%s\" cannot be reached, check it\n", filename);
        return 1;   
    }
    
    /* take all file to memory in picInMemory variable
       not effective but we can not know how and what kind of 
       a file came */
    i=0;
    while( fscanf(file, "%s", row) > 0 )
	{
        if(strcmp(row, "\n")!= 0) {
            strcpy(picInMemory.pixels[i], row);
            printf("%s\n",picInMemory.pixels[i]);
            i++;
        }
        else
            break;
	}
    //printf("%d lines have been read\n", i);

    //get number of rows
    rows = i;
	 //get number of columns
    cols = strlen(picInMemory.pixels[0])-1;

    printf("Picture width: %d, height: %d\n", cols+1, rows);
    
    
    while(exit)
    {
        //get menu choice from user
        choice = menu();
        
        switch(choice)
        {
            case 0:
                 break;
                 
            case 1:
                 printf("<flipping horizontally>\n");
                 flipHorizontal(picInMemory, rows, cols);
                 break;
                 
            case 2:
                 printf("<flipping vertically>\n");
                 flipVertical(picInMemory, rows, cols);
                 break;
                 
            case 3:
                 printf("<rotating>\n");
                 rotate(picInMemory, rows, cols);
                 break;

            case 4:
                 printf("<rotating 90 degrees anti-clockwise>\n");
                 rotate90AntiClockwise(picInMemory, rows, cols);
                 break;

            case 5:
                 printf("<rotating 90 degrees clockwise>\n");
                 rotate90Clockwise(picInMemory, rows, cols);
                 break;

            case 6:
                 printf("<inverting color>\n");
                 invertColor(picInMemory, rows, cols);
                 break;                                                   

            case 7:
                 printf("Please enter the scale factor (an integer greater than 1): ");
                 scanf("%d", &factor);
                 if(factor<2)
                 {
                     printf("factor can not be less than 2\n");
                     continue;
                 }
                 
                 printf("<scaling by factor %d>\n", factor);
                 scaleByFactor(picInMemory, rows, cols, factor);
                 break; 

            case 8:
                 printf("Please enter the second picture file: ");
                 scanf("%s", &filename2);
                 
                 file2 = fopen(filename2, "r");
                 if(!file2) 
                 {
                     printf("File \"%s\" cannot be reached, check it\n", filename2);
                     continue;   
                 }
                
                 /* take all file to memory in picInMemory variable
                   not effective but we can not know how and what kind of 
                   a file came */
                 i=0;
                 while( fscanf(file2, "%s", row) > 0 )
            	 {
                     if(strcmp(row, "\n")!= 0) {
                         strcpy(picInMemory2.pixels[i], row);
                         printf("%s\n",picInMemory2.pixels[i]);
                         i++;
                     }
                     else
                         break;
            	 }
                 //printf("%d lines have been read\n", i);
            
                 //get number of rows
                 rows2 = i;
            	 //get number of columns
                 cols2 = strlen(picInMemory2.pixels[0])-1; 
                 
                 printf("Picture width: %d, height: %d\n", cols2+1, rows2);
                 
                 printf("<finding differences with file %s>\n", filename2);
                           
                 findDifferences(picInMemory, picInMemory2, rows, cols, rows2, cols2);

                 fclose(file2);
                 break; 
                 
            case 9:
                 printf("Picture width: %d, height: %d\n", cols+1, rows);
                 printf("Please enter the x-start point for cropping: ");
                 scanf("%d", &x);
                 printf("Please enter the y-start point for cropping: "); 
                 scanf("%d", &y);
                 printf("Please enter the width for cropping: ");
                 scanf("%d", &w);
                 printf("Please enter the height for cropping: ");
                 scanf("%d", &h);
                 
                 
                 if(x<0 || y<0 || x>=cols || y>=rows-1 || x+w>cols+1 || y+h>rows)
                 {
                     printf("Crop region is out of boundaries\n");
                     continue;
                 }
                 
                 printf("<cropping by coordinates: x:%d y:%d w:%d h:%d>\n", x, y, w, h);
                 cropRegion(picInMemory, x, y, w, h);
                 break;   

            case 10:
                 printf("<detecting edges>\n");
                 detectEdges(picInMemory, rows, cols);
                 break;
                 
            case 11:
                 system("cls");
                 break;                                                     

            case 12:
                 printf("Enter the picture file: ");
                 scanf("%s",&filename);
                 printf("%s\n",filename);
                
                 file = fopen(filename, "r");
                 if(!file) 
                 {
                     printf("File \"%s\" cannot be reached, check it\n", filename);
                     continue;   
                 }
                
                 /* take all file to memory in picInMemory variable
                   not effective but we can not know how and what kind of 
                   a file came */
                 i=0;
                 while( fscanf(file, "%s", row) > 0 )
            	 {
                     if(strcmp(row, "\n")!= 0) {
                         strcpy(picInMemory.pixels[i], row);
                         printf("%s\n",picInMemory.pixels[i]);
                         i++;
                     }
                     else
                         break;
            	 }
                 //printf("%d lines have been read\n", i);
            
                 //get number of rows
                 rows = i;
            	 //get number of columns
                 cols = strlen(picInMemory.pixels[0])-1;
                 
                 printf("Picture width: %d, height: %d\n", cols+1, rows);
                 
                 printf("<now the picture file is: %s >\n", filename);
                 break;   

            case 13:
                 exit = 0;
                 break;   
                                                   
            default:
                 printf("If you read this something bad has happened\n");
                 break;                                       
        }//end of switch
    }//end of while

    fclose(file);
  
  
    return 0;
}


/*
 * shows menu and gets the choice
 */
int menu()
{
    int choice;
    
    printf("/*************************************************\\\n");     
    printf("| Please select an operation:                     |\n");
    printf("|-------------------------------------------------|\n");
    printf("| 1. Flip picture horizontally                    |\n");
    printf("| 2. Flip picture vertically                      |\n");
    printf("| 3. Rotate the picture                           |\n");
    printf("| 4. Rotate the picture 90 degress Anti-Clockwise |\n");
    printf("| 5. Rotate the picture 90 degress Clockwise      |\n");
    printf("| 6. Invert the color of picture                  |\n");
    printf("| 7. Scale the picture by given factor            |\n");
    printf("| 8. Find differences of two pictures             |\n");
    printf("| 9. Crop a region from the picture               |\n");
    printf("|10. Detect edges of a picture                    |\n");
    printf("|11. Clear screen                                 |\n");
    printf("|12. Change input picture                         |\n");
    printf("|13. Exit                                         |\n");
    printf("\\*************************************************/\n");
     

    scanf("%d",&choice);
    
    if(choice<=0 || choice>13)
    {
        printf("Unrecognized choice\n");
        return 0;
    } 
    else
        return choice;    
}


/*
 * flips the given picture horizontally
 */
void flipHorizontal(pic_t picInMemory, int lastRowIndex, int lastColIndex)
{
     FILE *file;
     pic_t flippedPic;
     int j, i;
     char newfile[] = "HorizontallyFlipped.txt";

     //flip horizontally
     for(j=lastColIndex; j>=0; j--)
     {
         for(i=0; i<lastRowIndex+1; i++)
             flippedPic.pixels[lastRowIndex-1-i][j] = picInMemory.pixels[i][j];
     }
     
     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<lastRowIndex; i++)
     {
         for(j=0; j<lastColIndex+1; j++)
         {    
              printf("%c",flippedPic.pixels[i][j]);
  	 	      fputc(flippedPic.pixels[i][j], file);
         }
         printf("\n");
    	 fputs("\n", file);
     }

     fclose(file);
}


/*
 * flips the given picture vertically
 */	
void flipVertical(pic_t picInMemory, int lastRowIndex, int lastColIndex)
{
     FILE *file;
     pic_t flippedPic;
     int j, i;
     char newfile[] = "VerticallyFlipped.txt";

     //flip vertically    
     for(j=lastColIndex; j>=0; j--)
     {
         for(i=0; i<lastRowIndex+1; i++)
              flippedPic.pixels[i][lastColIndex-j] = picInMemory.pixels[i][j];
     }     

     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<lastRowIndex; i++)
     {
         for(j=0; j<lastColIndex+1; j++)
         {    
              printf("%c",flippedPic.pixels[i][j]);
  	 	      fputc(flippedPic.pixels[i][j], file);
         }
         printf("\n");
    	 fputs("\n", file);
     }

     fclose(file);
    
}


/*
 * rotates the given picture
 */
void rotate(pic_t picInMemory, int lastRowIndex, int lastColIndex)
{
     FILE *file;
     pic_t rotatedPic, tempPic;
     int j, i;
     char newfile[] = "Rotated.txt";

     //flip horizontally
     for(j=lastColIndex; j>=0; j--)
     {
         for(i=0; i<lastRowIndex+1; i++)
             tempPic.pixels[lastRowIndex-1-i][j] = picInMemory.pixels[i][j];
     }

     //flip vertically    
     for(j=lastColIndex; j>=0; j--)
     {
         for(i=0; i<lastRowIndex+1; i++)
              rotatedPic.pixels[i][lastColIndex-j] = tempPic.pixels[i][j];
     }     

     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<lastRowIndex; i++)
     {
         for(j=0; j<lastColIndex+1; j++)
         {    
              printf("%c",rotatedPic.pixels[i][j]);
  	 	      fputc(rotatedPic.pixels[i][j], file);
         }
         printf("\n");
    	 fputs("\n", file);
     }

     fclose(file);     
}


/*
 * rotates the given picture 90 degrees anti-clockwise
 */
void rotate90AntiClockwise(pic_t picInMemory, int lastRowIndex, int lastColIndex)
{
     FILE *file;
     pic_t rotatedPic;
     int j, i;
     char newfile[] = "Rotated90AC.txt";

     for(j=lastColIndex; j>=0; j--)
     {
         for(i=0; i<lastRowIndex+1; i++)
             rotatedPic.pixels[lastColIndex-j][i] = picInMemory.pixels[i][j];
     }    

     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<lastColIndex+1; i++)
     {
          for(j=0; j<lastRowIndex+1; j++)
          {    
              printf("%c",rotatedPic.pixels[i][j]);
              fputc(rotatedPic.pixels[i][j], file);
          }
          printf("\n");
          fputs("\n", file);
     }

     fclose(file);     
}


/*
 * rotates the given picture 90 degrees clockwise
 */
void rotate90Clockwise(pic_t picInMemory, int lastRowIndex, int lastColIndex)
{
     FILE *file;
     pic_t rotatedPic;
     int j, i;
     char newfile[] = "Rotated90C.txt";

     for(i=lastRowIndex; i>=0; i--)
     {
          for(j=0; j<lastColIndex+1; j++)
             rotatedPic.pixels[j][lastRowIndex-i] = picInMemory.pixels[i][j];
     }     

     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<lastColIndex+1; i++)
     {
          for(j=1; j<lastRowIndex+1; j++)
          {    
              printf("%c",rotatedPic.pixels[i][j]);
              fputc(rotatedPic.pixels[i][j], file);
          }
          printf("\n");
          fputs("\n", file);
     }

     fclose(file);     
}


/*
 * inverts the color of the given picture
 */
void invertColor(pic_t picInMemory, int lastRowIndex, int lastColIndex)
{
     FILE *file;
     pic_t invertedPic;
     int j, i;
     char newfile[] = "ColorInverted.txt";     

     for(j=0; j<lastColIndex+1; j++)
     {
         for(i=0; i<lastRowIndex+1; i++)
             invertedPic.pixels[i][j] = picInMemory.pixels[i][j] == '#' ? '.' : '#';
     }    

     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<lastRowIndex; i++)
     {
         for(j=0; j<lastColIndex+1; j++)
         {    
              printf("%c",invertedPic.pixels[i][j]);
  	 	      fputc(invertedPic.pixels[i][j], file);
         }
         printf("\n");
    	 fputs("\n", file);
     }

     fclose(file); 
}


/*
 * scales the given picture with thw given factor
 */
void scaleByFactor(pic_t picInMemory, int lastRowIndex, int lastColIndex, int factor)
{
     FILE *file;
     pic_t scaledPic;
     int j, i, k, m;
     char newfile[] = "Scaled.txt";     

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

     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<(lastRowIndex)*factor; i++)
     {
         for(j=0; j<(lastColIndex+1)*factor; j++)
         {    
              printf("%c",scaledPic.pixels[i][j]);
  	 	      fputc(scaledPic.pixels[i][j], file);
         }
         printf("\n");
    	 fputs("\n", file);
     }

     fclose(file);      
}


/*
 * finds the differences of given 2 pictures
 */
void findDifferences(pic_t picInMemory1, pic_t picInMemory2, int lastRowIndex1, int lastColIndex1, int lastRowIndex2, int lastColIndex2)
{
     FILE *file;
     pic_t differencePic;
     int i, j, maxRow, maxCol;
     char newfile[] = "Difference.txt";

     maxRow = MAX(lastRowIndex1, lastRowIndex2);
     maxCol = MAX(lastColIndex1, lastColIndex2);
     
     for(i=0; i<maxRow; i++)
     {
          for(j=0; j<maxCol+1; j++)
              differencePic.pixels[i][j] = picInMemory1.pixels[i][j]==picInMemory2.pixels[i][j] ? '.' : '#';
     }
     
     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<maxRow; i++)
     {
         for(j=0; j<maxCol+1; j++)
         {    
             printf("%c", differencePic.pixels[i][j]);
      	 	 fputc(differencePic.pixels[i][j], file);
         }
         printf("\n");
    	 fputs("\n", file);
     }

     fclose(file);             
}


/*
 * crops the area from the given picture from the point (x,y) with height h and width w
 */
void cropRegion(pic_t picInMemory, int x, int y, int w, int h)
{
     FILE *file;
     pic_t croppedPic;
     int i, j;
     char newfile[] = "Cropped.txt";     

     for(i=x; i<x+h; i++)
     {
          for(j=y; j<y+w; j++)
                   croppedPic.pixels[i-x][j-y] = picInMemory.pixels[i][j];
     }     

     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<h; i++)
     {
         for(j=0; j<w; j++) 
         {   
              printf("%c", croppedPic.pixels[i][j]);
              fputc(croppedPic.pixels[i][j], file);
         }
         printf("\n");
         fputs("\n", file);
     }

     fclose(file);      
}


/*
 * detects the edges of the given picture
 */
void detectEdges(pic_t picInMemory, int lastRowIndex, int lastColIndex)
{
     FILE *file;
     pic_t edgePic;
     int j, i;
     char newfile[] = "Edges.txt";     

     for(i=0; i<lastRowIndex; i++)
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

     file = fopen(newfile, "w");
     
     //write to file
     for(i=0; i<lastRowIndex; i++)
     {
         for(j=0; j<lastColIndex+1; j++)
         {    
             printf("%c",edgePic.pixels[i][j]);
             fputc(edgePic.pixels[i][j], file);
         }
  	 	 printf("\n");
    	 fputs("\n", file);
     }

     fclose(file);  
}

