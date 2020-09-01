#!/bin/sh
exec scala \
 -i utils/utils.scala \
 -i sssjson.scala -deprecation -classpath "jarlib/ujson_2.13-1.2.0.jar:jarlib/upickle-core_2.13-1.2.0.jar:jarlib/geny_2.13-0.6.2.jar" "$0" "$@"
!#

MyApp.Main

// This program reads a json files and prints each record to the screen.
// it is using a structured way to run a scala script.

// Important link related to writing to files in Scala:
// https://alvinalexander.com/scala/how-to-write-text-files-in-scala-printwriter-filewriter/

// For JSON files, check this link:
// https://www.lihaoyi.com/post/uJsonfastflexibleandintuitiveJSONforScala.html

// Enjoy :)

