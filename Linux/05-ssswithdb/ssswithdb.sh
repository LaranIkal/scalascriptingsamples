#!/bin/sh
exec scala \
 -i utils/utils.scala  \
 -i ssswithdb.scala -classpath ".:jdbclib/sqlite-jdbc-3.32.3.2.jar" "$0" "$@"
!#

MyApp.Main

// ===>>> IMPORTANT:BEFORE RUNNING THIS PROGRAM PLEASE DOWNLOAD THE JDBC DRIVER.


// This program connects to a sqlite DB and runs a sql query
// it is using a structured way to run a scala script.


// Download sqlite JDBC from:
// https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.32.3.2/
// copy sqlite JDBC to jdbclib directory.


// Important direct links:
// https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/

// Enjoy :)

