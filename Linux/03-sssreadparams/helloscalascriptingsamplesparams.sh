#!/bin/sh
exec scala \
 -i utils.scala \
 -i helloscalascriptingsamplesparams.scala -classpath "." "$0" "$@"
!#


// Checking arguments
if( args.length < 1 ) {
  Usage
  System.exit(0)
} else {
  MyApp.UsingParms(args)
}


object Usage {
  println("Hey, i need at least one parameter.")

  // 3 double quotes in case you want to include quotes inside the usage text.
  val usage = """Usage: helloscalascriptingsamplesparams.sh <yourname> [<yourage>]""" 
  
  println(usage)
}


// Enjoy :)

