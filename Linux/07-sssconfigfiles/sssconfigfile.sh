#!/bin/sh
exec scala \
 -i utils/utils.scala \
 -i sssconfigfile.scala -deprecation -classpath "." "$0" "$@"
!#

MyApp.Main

// This program reads a config file and opens a csv file based on the config 
// parameters and print it to the screen.

// Enjoy :)

