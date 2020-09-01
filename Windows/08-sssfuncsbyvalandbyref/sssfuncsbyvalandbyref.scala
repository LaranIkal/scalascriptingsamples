/*******************************************************************************
* title:  Main script to open a csv file and writes to a log file.
* author: Carlos Kassab
* date: 08/28/2020
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

    val fileData = Utils.LoadFileByValue(myFileName, log)
    log.write("Printing file data to screen using array by value.\n")
    println("Printing file data to screen using array by value:\n")
    fileData.foreach(println)

    val initArraysRef = Utils.InitArrays(Array("")) // Initialize the array
    val fileDataArray = (x: Int) => initArraysRef // Creates the reference to the array
    Utils.LoadFileByRef(myFileName, log, fileDataArray)

    if( fileDataArray(1).fileDataRef.filter(_ != "").length > 0  ){
      log.write("Printing file data to screen using array by reference.\n")
      println("\n\n\nPrinting file data to screen using array by reference:")      
      fileDataArray(1).fileDataRef.foreach(println)
    }

  } 


}
