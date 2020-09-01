/*******************************************************************************
* title:  Main script to open a csv file and writes to a log file.
* author: Carlos Kassab
* date: 08/27/2020
*
*******************************************************************************/

import scala.util.{Try, Success, Failure}
import scala.collection.mutable.Map

object MyApp extends App {

  def Main: Unit = {
    println("\n Open a file and write to log using Scala Script. \n")

    val log = Utils.GetLogFilePointer // Create log file and return PrintWriter
    val sssconfig = Utils.GetConfigInfoFromFile(System.getProperty("user.dir")+"/config.hash") // get config info
    OpenFileWithLog(log, sssconfig)
    log.close // Close log file.

  }


  def OpenFileWithLog(log: java.io.PrintWriter, sssconfig: Map[String, String]): Unit = {

    log.write("Starting Function OpenFileWithLog.\n")
    //###===>>> USING SOURCE DB INFO FROM sssconfig VARIABLE
    val myFileName = sssconfig("sourcedbinfo")   //"data/agenda.csv"
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
