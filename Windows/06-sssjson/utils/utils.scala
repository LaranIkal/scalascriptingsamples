/*******************************************************************************
* title:  Misc Utils for Scala Scripting
* author: Carlos Kassab
* date: 08/26/2020
*
*******************************************************************************/

import scala.util.Try
import scala.io.Source
import java.io.File
import java.io.PrintWriter
import java.util.Calendar
import ujson._


object Utils
{
  /******************************************************************************
  * Title: Open JSON file, get fields and print each record to screen.
  * Author: Carlos Kassab, Date: 08/26/2020
  ******************************************************************************/

  def DisplayJSONData(fileName: String, log: java.io.PrintWriter): Try[Unit] = Try {

    log.write(s"Open file: $fileName\n")
    // Two steps just to be less cryptic
    val fileData = Source.fromFile(fileName).mkString
    val jsonData = Value(ujson.read(fileData))

    log.write("Printing records from file.\n")
    //print(ujson.write(jsonData, indent = 2)) // To show a way to print the whole json structure

    for( record <- 1 to 3) {
      println(s"\n\nPrinting record: $record")
      print(ujson.write(jsonData("Agenda")(record.toString), indent = 2))
    }

  }


  def GetLogFilePointer: PrintWriter = {
    val today = Calendar.getInstance()
    //Months are indexed from 0 not 1
    val logFileName = "log/log_" + today.get(Calendar.YEAR).toString + 
                      (today.get(Calendar.MONTH)+1).toString +
                      today.get(Calendar.DAY_OF_MONTH).toString +
                      today.get(Calendar.HOUR_OF_DAY).toString +
                      today.get(Calendar.MINUTE).toString +
                      today.get(Calendar.SECOND).toString + ".log"

      val logPointer = new PrintWriter(new File(logFileName))

      return( logPointer )
  }
    
}

