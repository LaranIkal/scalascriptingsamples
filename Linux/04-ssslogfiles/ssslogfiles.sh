#!/bin/sh
exec scala \
 -i utils/utils.scala \
 -i ssslogfiles.scala -deprecation -classpath "." "$0" "$@"
!#

MyApp.Main

// This program opens a csv file and print it to the screen.
// it is using a structured way to run a scala script.

// Important link related to writing to files in Scala:
// https://alvinalexander.com/scala/how-to-write-text-files-in-scala-printwriter-filewriter/

// Enjoy :)

