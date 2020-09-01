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



object Utils
{
  /******************************************************************************
  * Title: Open file, load it to an array, pass it by value.
  * Author: Carlos Kassab, Date: 08/28/2020
  ******************************************************************************/

  def LoadFileByValue(fileName: String, log: java.io.PrintWriter): Array[String] = {

    log.write(s"Open file: $fileName\n")
    val dataBuffer = Source.fromFile(fileName)
    var lineNumber = 0
    val fileData: Array[String] = new Array[String](100)

    log.write("Loading file to an array.\n")
    for( line <- dataBuffer.getLines()) {
      fileData(lineNumber) = line
      lineNumber +=1
    }
    dataBuffer.close
    return(fileData.filter(_ != null))
  }


  case class InitArrays( var fileDataRef: Array[String] )

  /******************************************************************************
  * Title: Open file, load it to an array by reference.
  * Author: Carlos Kassab, Date: 08/28/2020
  ******************************************************************************/

  def LoadFileByRef( fileName: String, log: java.io.PrintWriter, fileData: Int => InitArrays ): Unit = {

    log.write(s"Open file: $fileName\n")
    val dataBuffer = Source.fromFile(fileName)
    log.write("Loading file to an array.\n")
    for( line <- dataBuffer.getLines()) {
      fileData(1).fileDataRef =  fileData(1).fileDataRef :+ line
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

