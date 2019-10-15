#
# Mehmet CAMBAZ
# 040020365
#
# Advanced Programming Term Project
# Python : picture.py
#

##############################
# picture class              #
# used to represent pictures #
##############################
class picture:
    rows = 0        #number of rows in the picture (height)
    cols = 0        #number of columns in the picture (width)
    pixels = []     #picture pixel content

    #constructor
    def __init__(self, row, col, filename=""):
        self.rows = row
        self.cols = col
        self.pixels = []        
        
        if filename != "":
            f = open(filename, 'r')        
            
            for i in range(row):
                column = []
                l = f.readline()
                for j in range(col):
                    column.append(l[j])    
                self.pixels.append(column)  
            
            f.close()

    #prints picture content    
    def printPixels(self):
        for i in range(self.rows):
            for j in range(self.cols):
                print self.pixels[i][j],
            print '\n',
                
