#
# Mehmet CAMBAZ
# 040020365
#
# Advanced Programming Term Project
# Python : main.py
#

import os                #import operating system available commandline
from picture import *    #include picture class


#displays menu and gets the user's choice
def getMenu():

    print "/*************************************************\\"     
    print "| Please select an operation:                     |"
    print "|-------------------------------------------------|"
    print "| 1. Flip picture horizontally                    |"
    print "| 2. Flip picture vertically                      |"
    print "| 3. Rotate the picture                           |"
    print "| 4. Rotate the picture 90 degress Anti-Clockwise |"
    print "| 5. Rotate the picture 90 degress Clockwise      |"
    print "| 6. Invert the color of picture                  |"
    print "| 7. Scale the picture by given factor            |"
    print "| 8. Find differences of two pictures             |"
    print "| 9. Crop a region from the picture               |"
    print "|10. Detect edges of the picture                  |"
    print "|11. Clear screen                                 |"
    print "|12. Change input picture                         |"
    print "|13. Exit                                         |"
    print "\\*************************************************/"
         
    try:
        choice = int(raw_input("Enter your choice: "))
    except ValueError:
        print "The choice is invalid"
        return 0

       
    if choice<=0 or choice>13:
        print "Unrecognized choice"
        return 0 
    else:
        return choice    


#flips the given picture horizontally
def flipHorizontal(pic):
    
    flippedPic = picture(pic.rows, pic.cols)
    
    for i in range(pic.rows):
        column = []
        for j in range(pic.cols):
            column.append(pic.pixels[pic.rows-1-i][j])    
        flippedPic.pixels.append(column)
            
    flippedPic.printPixels()


#flips the given picture vertically
def flipVertical(pic):
    
    vflippedPic = picture(pic.rows, pic.cols)
    
    for i in range(pic.rows):
        column = []
        for j in range(pic.cols):
            column.append(picInMemory.pixels[i][pic.cols-1-j])    
        vflippedPic.pixels.append(column)
            
    vflippedPic.printPixels()


#rotates the given picture    
def rotate(pic):
    
    rotatedPic = picture(pic.rows, pic.cols)
    temppixels = []
    
    for i in range(pic.rows):
        column1 = []
        for j in range(pic.cols):
            column1.append(pic.pixels[pic.rows-1-i][j])    
        temppixels.append(column1)
        
    for i in range(pic.rows):
        column2 = []
        for j in range(pic.cols):
            column2.append(temppixels[i][pic.cols-1-j])    
        rotatedPic.pixels.append(column2)  
        
    rotatedPic.printPixels()


#flips the given picture 90 degrees anti-clockwise
def rotate90AntiClockwise(pic):
    
    rotated90ac = picture(pic.cols, pic.rows)
    
    for i in range(pic.cols):
        column = []
        for j in range(pic.rows):
            column.append(pic.pixels[j][pic.cols-1-i])    
        rotated90ac.pixels.append(column)
        
    rotated90ac.printPixels()


#flips the given picture 90 degrees clockwise
def rotate90Clockwise(pic):
    
    rotated90c = picture(pic.cols, pic.rows)
    
    for i in range(pic.cols):
        column = []
        for j in range(pic.rows):
            column.append(pic.pixels[pic.rows-1-j][i])    
        rotated90c.pixels.append(column)
        
    rotated90c.printPixels()


#inverts the color of the given picture
def invertColor(pic):
    
    inverted = picture(pic.rows, pic.cols)
    
    for i in range(pic.rows):
        column = []
        for j in range(pic.cols):
            if pic.pixels[i][j] == '#':
                column.append('.')
            else:
                column.append('#')
   
        inverted.pixels.append(column)
        
    inverted.printPixels()    


#scales the given picture with thw given factor
def scaleByFactor(pic, factor):
    
    row = pic.rows*factor
    col = pic.cols*factor
    scaledPic = picture(row, col)

    for i in range(pic.rows):
        temppixels = []
        for j in range(pic.cols):            
            for k in range(factor):
                temppixels.append(pic.pixels[i][j])
        for m in range(factor):
            scaledPic.pixels.append(temppixels)
            
    scaledPic.printPixels()


#finds the differences of given 2 pictures
def findDifferences(pic, pic2):

    #if pictures are not the same size, the minimum size will be the size of difference picture
    minRow = min(pic.rows, pic2.rows)
    minCol = min(pic.cols, pic2.cols)
    
    differencePic = picture(minRow, minCol)
    
    for i in range(minRow):
        column = []
        for j in range(minCol):
            if pic.pixels[i][j] == pic2.pixels[i][j]:
                column.append('.')
            else:
                column.append('#')
        differencePic.pixels.append(column)        
        
    differencePic.printPixels()


#crops the area from the given picture from the point (x,y) with height h and width w
def crop(pic, x, y, w, h):

    croppedPic = picture(h, w)
    
    for i in range(h):
        tempcols = []
        for j in range(w):
            tempcols.append(pic.pixels[x+i][y+j])
        croppedPic.pixels.append(tempcols)

    croppedPic.printPixels()


#detects the edges of the given picture
def detectEdges(pic):
    
    edgePic = picture(pic.rows, pic.cols)
    
    for i in range(pic.rows):
        column = []
        for j in range(pic.cols):             
            if pic.pixels[i][j] == '#':
                #if the black pixel is on the edges of the picture, then it is a part of edge             
                if i==0 or j==0 or i==pic.rows-1 or j==pic.cols-1:
                    column.append('#')
                # if all the pixels around a black pixel is black, then
                #   it is not a edge, otherwise it is considered edge 
                else:                   
                    if pic.pixels[i+1][j] == '#' and pic.pixels[i-1][j] == '#' and pic.pixels[i][j+1] == '#' and pic.pixels[i][j-1] == '#' and pic.pixels[i-1][j-1] == '#' and pic.pixels[i+1][j+1] == '#':
                        column.append('.')
                    else:
                        column.append('#')
            else:
                column.append('.')
                
        edgePic.pixels.append(column)
                

    edgePic.printPixels();    
    

#returns the smaller or equal to of given two numbers
def min(n1, n2):
    
    if n1 <= n2:
        return n1
    else:
        return n2
    
    
    
#############################
# main function from now on #
#############################

#global variables
global filename, file, lines, col, row, picInMemory

#get picture file name
filename = str(raw_input("Enter the picture file: "))

#getting number of rows and columns
filename = filename.strip()
try:
    file = open(filename, 'r')
except IOError:
    print "Can not open the file: ", filename
else:
    lines = file.readlines();
    col = len(lines[0])
    col = col-1
    row = len(lines)
    file.close()
    
    #create a picture object from the given file
    picInMemory = picture(row, col, filename)
    #inform the user about picture
    print "Number of rows: ", picInMemory.rows
    print "Number of columns: ", picInMemory.cols    
    #show picture to user
    picInMemory.printPixels()
    
    exit = False
    
    #process loop
    while (not exit):    
    
        #show menu to user and get choice
        choice = getMenu()
    
        if choice == 13:
            exit = True
        elif choice == 1:
            print "<flipping horizontally>"
            flipHorizontal(picInMemory)
        elif choice == 2:    
            print "<flipping vertically>"
            flipVertical(picInMemory)
        elif choice == 3:    
            print "<rotating>"
            rotate(picInMemory)
        elif choice == 4:
            print "<rotating 90 degrees anti-clockwise>"
            rotate90AntiClockwise(picInMemory)
        elif choice == 5:
            print "<rotating 90 degrees clockwise>"
            rotate90Clockwise(picInMemory)
        elif choice == 6:
            print "<inverting color>"
            invertColor(picInMemory)
        elif choice == 7:
            try:
                factor = int(raw_input("Please enter the scale factor (an integer greater than 1): "))
            except ValueError:
                print "invalid input"
                continue
            
            if factor>1:
                scaleByFactor(picInMemory, factor)
            else:
                print "factor can not be less than or equal to 1"
        elif choice == 8:
            filename2 = str(raw_input("Please enter the second picture file: "))
            
            #getting number of rows and columns
            filename2 = filename2.strip()
            try:
                file2 = open(filename2, 'r')
            except IOError:
                print "Can not open the file: ", filename2
            else:
                lines2 = file2.readlines();
                col2 = len(lines2[0])
                col2 = col2-1
                row2 = len(lines2)
                file2.close()
                
                #create a second picture object from the given file
                picInMemory2 = picture(row2, col2, filename2)
                print "The second picture is: "
                picInMemory2.printPixels()
                #print "\n"
                print "<the difference picture>"
                findDifferences(picInMemory, picInMemory2)
        elif choice == 9:
            print "Picture height:  ", picInMemory.rows
            print "Picture width :  ", picInMemory.cols
            try:
                x = int(raw_input("Please enter the x-start point for cropping: "))
                y = int(raw_input("Please enter the y-start point for cropping: "))
                w = int(raw_input("Please enter the width for cropping: "))
                h = int(raw_input("Please enter the height for cropping: "))
            except ValueError:
                print "invalid input"
                continue
            else:
                if x<0 or y<0 or x>=picInMemory.cols-1 or y>=picInMemory.rows-1 or x+w>picInMemory.cols or y+h>picInMemory.rows:
                    print "Crop region is out of boundaries"
                    continue
                else:
                    print "<cropped picture>"
                    crop(picInMemory, x, y, w, h)
        elif choice == 10:
            print "<detecting edges>"
            detectEdges(picInMemory)
        elif choice == 11:
            os.system('cls')
        elif choice == 12:
            filename = str(raw_input("Enter the picture file: "))

            #getting number of rows and columns
            filename = filename.strip()
            try:
                file = open(filename, 'r')
            except IOError:
                print "can not open the file: ", filename
                continue
            else:
                lines = file.readlines();
                col = len(lines[0])
                col = col-1
                row = len(lines)
                file.close()
                
                #create a picture object from the given file
                picInMemory = picture(row, col, filename)
                #inform the user about the new picture
                print "Number of rows: ", picInMemory.rows
                print "Number of columns: ", picInMemory.cols
                #show the new picture to user
                print "New picture is: "
                picInMemory.printPixels()
        else:
            pass
