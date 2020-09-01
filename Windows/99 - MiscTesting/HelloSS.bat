::#! 
@echo off 
call scala ^
 -i Utils.scala ^
 -i HelloSS.scala %*
goto :eof 
::!# 


