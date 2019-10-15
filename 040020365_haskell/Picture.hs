--------------------------------------------------------------------
-- 
--  Mehmet CAMBAZ
--  040020365
--  
--  Advanced Programming Term Project
--  Haskell : Picture.hs
-- 
--------------------------------------------------------------------

-- The module that contains picture process operations
module Picture where

-- include List and operations
import List

-- define type Picture as a list of list of characters
type Picture = [[Char]]

-- a blank white picture.
-- a picture will be represented like this at memory
white :: Picture

white = ["............",
         "............",
         "............",
         "............",
         "............",
         "............",
         "............",
         "............",
         "............",
         "............",
         "............",
         "............"]

-- Function that prints picture to the screen.
printPicture :: Picture -> IO ()
printPicture = putStr . concat . map (++"\n")


-- Function that flips the given picture vertically
flipVertical :: Picture -> Picture
flipVertical = map reverse


-- Function that flips the given picture horizontally
flipHorizontal :: Picture -> Picture
flipHorizontal = reverse


-- Function that rotates the given picture
rotate :: Picture -> Picture
rotate = flipHorizontal . flipVertical 


-- Function that rotates the given picture 90 degree anti-clockwise
rotate90ac :: Picture -> Picture
rotate90ac =  transpose . reverse


-- Function that rotates the given picture 90 degree clockwise			 
rotate90 :: Picture -> Picture
rotate90 = rotate90ac . rotate


-- Function that inverts the color of the given picture
invertColor :: Picture -> Picture
invertColor = map (map invert)


-- Function that inverts the color of a pixel
invert :: Char -> Char
invert ch = if ch == '.' then '#' else '.'
