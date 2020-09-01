::#! 
@echo off 
call scala ^
 -i lib/utils.scala ^
 -i lib/readeplsitemodules.scala ^
 -i lib/readpagescategories.scala ^
 -i sssetl.scala -classpath ".;jdbclib/sqlite-jdbc-3.32.3.2.jar" %0 %*
goto :eof 
::!# 

// ===>>> IMPORTANT:BEFORE RUNNING THIS PROGRAM PLEASE DOWNLOAD THE JDBC DRIVER.
// Download sqlite JDBC from:
// https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.32.3.2/
// copy sqlite JDBC to jdbclib directory.

// Checking arguments
if( args.length < 1 ) {
  Usage
  System.exit(0)
} else if(args.length > 1) {
    SssEtl.Main(args.filter(_ != "99"))
} else {
    SssEtl.Main(args)  
}  



object Usage {

  // 3 double quotes in case you want to include quotes inside the usage text.
  val usage = """Usage: sssetl.bat <processnumber(s) separated by blank space> 
  - 10: Modules
  - 20: Pages Categories
  - 99: All Processes
  """ 
  
  println(usage)
}


// This program connects to a sqlite DB, runs a sql query to get the data from 
// source db and then insert it to target db

// Important direct links:
// https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/

// Enjoy :)

