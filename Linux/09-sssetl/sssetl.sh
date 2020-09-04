#!/bin/sh
exec scala \
 -i lib/utils.scala \
 -i lib/readeplsitemodules.scala \
 -i lib/readpagescategories.scala \
 -i lib/readeplsitereportgroups.scala \
 -i lib/readeplsitereports.scala \
 -i sssetl.scala -deprecation -classpath ".:jdbclib/sqlite-jdbc-3.32.3.2.jar" "$0" "$@"
!#

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
  val usage = """Usage: sssetl.sh <processnumber(s) separated by blank space> 
  - 10: Modules
  - 20: Pages Categories
  - 30: Report Groups
  - 40: EplSite Reports
  - 99: All Processes
  Note. The last two examples are more Scala best practices.
  """ 
  
  println(usage)
}


// This program connects to a sqlite DB, runs a sql query to get the data from 
// source db and then insert it to target db

// Important direct links:
// https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/

// Enjoy :)

