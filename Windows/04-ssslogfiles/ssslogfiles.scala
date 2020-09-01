/*******************************************************************************
* title:  Main script to open a csv file and writes to a log file.
* author: Carlos Kassab
* date: 08/22/2020
*
*******************************************************************************/

import scala.util.{Try, Success, Failure}


object MyApp extends App {

  def Main: Unit = {
    println("\n Open a file and write to log using Scala Script. \n")

    val log = Utils.GetLogFilePointer // Create log file and return PrintWriter
    OpenFileWithLog(log)
    log.close // Close log file.

  }


  def OpenFileWithLog(log: java.io.PrintWriter): Unit = {

    log.write("Starting Function OpenFileWithLog.\n")
    val myFileName = "data/agenda.csv"
    log.write(s"The Name of Our file: $myFileName\n")

    Utils.DisplayCSVFile(myFileName, log) match
    {
      case Success(_) => { 
        println("File successfully printed.")
        log.write(s"File $myFileName successfully printed, check log file under log directory.\n")
      }
      case Failure(f) => {
        println(s"There are problems printing file $myFileName: $f")
        log.write(s"There are problems printing file $myFileName: $f\n")
      }
    }

  } 


}
