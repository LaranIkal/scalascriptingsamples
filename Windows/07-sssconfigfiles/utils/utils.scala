/*******************************************************************************
* title:  Misc Utils for Scala Scripting
* author: Carlos Kassab
* date: 08/20/2020
*
*******************************************************************************/

import scala.util.Try
import scala.io.Source
import java.io.File
import java.io.PrintWriter
import java.util.Calendar
import scala.collection.mutable.Map


object Utils
{

  /******************************************************************************
  * Title: Open config file in hash format, split fields and return Scala hash
  * Author: Carlos Kassab, Date: 08/27/2020
  ******************************************************************************/
  def GetConfigInfoFromFile(configFileName: String): Map[String, String] = {

    val myConfig = Source.fromFile(configFileName) // open the file
    val configMap: Map[String, String] = Map()

    for ( line <- myConfig.getLines ) {
      if( line.slice(0,1) != "#" && line.trim.length > 3) {
        val configArray = line.split("\\s*->\\s*")
        configMap += ( configArray(0) -> configArray(1) )
      }
    }
    
    return(configMap)
  }


  /******************************************************************************
  * Title: Open csv, split fields and print each record to screen.
  * Author: Carlos Kassab, Date: 08/22/2020
  ******************************************************************************/

  def DisplayCSVFile(fileName: String, log: java.io.PrintWriter): Try[Unit] = Try {

    log.write(s"Open file: $fileName\n")
    val dataBuffer = Source.fromFile(fileName)

    log.write("Printing lines from file.\n")
    for( line <- dataBuffer.getLines()) {
      val record = line.split(",")
      println(s" Name:${record(0)} \n Mobile Phone: ${record(1)} \n Birthday: ${record(2)}\n\n")
    }
    dataBuffer.close

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

