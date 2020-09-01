::#! 
@echo off 
call scala ^
 -i utils/utils.scala ^
 -i sssconfigfile.scala -deprecation -classpath "." %0 %*
goto :eof 
::!# 

MyApp.Main

// This program reads a config file and opens a csv file based on the config 
// parameters and print it to the screen.

// Enjoy :)

