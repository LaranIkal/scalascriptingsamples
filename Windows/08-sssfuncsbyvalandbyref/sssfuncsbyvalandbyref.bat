::#! 
@echo off 
call scala ^
 -i utils/utils.scala ^
 -i sssfuncsbyvalandbyref.scala -deprecation -classpath "." %0 %*
goto :eof 
::!# 

MyApp.Main

// This program opens a csv file and print it to the screen.
// First it loads the file to an array, the array is passed as value and printed, then
// the array is passed as reference and printed.

// Important link related to passing to functions by value and by reference:
// https://stackoverflow.com/questions/4790050/can-scala-call-by-reference

// Enjoy :)

