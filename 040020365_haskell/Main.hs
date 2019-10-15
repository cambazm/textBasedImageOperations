--------------------------------------------------------------------
-- 
--  Mehmet CAMBAZ
--  040020365
--  
--  Advanced Programming Term Project
--  Haskell : Main.hs
-- 
--------------------------------------------------------------------

-- the module that the main function exist
module Main where

-- include Picture module (contains picture process functions)
import Picture
-- include input/output module for file and user I/O 
import IO


main = do putStr "Enter the picture file: "
	  fileName <- getLine			--get picture file from user
	  text <- catch (readFile fileName)	--if the file do not exist an empty picture will be created
                        (\_ -> return "")
	  putStrLn "The picture is: "
	  printPicture (lines text)		--show the picture and process all operations, show results
	  putStrLn "<flipping horizontally>"
	  printPicture (flipHorizontal(lines text))
	  putStrLn "<flipping vertically>"
	  printPicture (flipVertical(lines text))
	  putStrLn "<rotating>"
	  printPicture (rotate(lines text))
	  putStrLn "<rotating 90 degrees anti-clockwise>"
	  printPicture (rotate90ac(lines text))
	  putStrLn "<rotating 90 degrees clockwise>"
	  printPicture (rotate90(lines text))
	  putStrLn "<inverting color>"
	  printPicture (invertColor(lines text))
