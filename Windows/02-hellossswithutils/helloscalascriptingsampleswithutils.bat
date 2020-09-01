::#! 
@echo off 
call scala ^
 -i utils.scala ^
 -i helloscalascriptingsampleswithutils.scala -classpath "."  %0 %*
goto :eof 
::!# 

MyApp.MainProcess

// This is an elegant way to see a Scala Script working.
// This batch file is just the launcher for our App.
// I do this to keep order in the app and for convenience with the IDEs/Editors

// What does it do?
// 1.- Load utils.scala
// 2.- Load helloscalascriptingsampleswithutils.scala, our main scala script
// 3.- Runs the entry object of our app.

// Enjoy :)

