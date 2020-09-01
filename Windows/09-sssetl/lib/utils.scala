/*******************************************************************************
* title:  Misc Utils for Scala Scripting
* author: Carlos Kassab
* date: 08/27/2020
*
*******************************************************************************/

import java.sql
import java.sql.DriverManager
import java.sql.Connection
import java.util.Calendar
import java.io.PrintWriter
import java.io.File
import java.io.{FileNotFoundException, IOException}

import scala.util.Try
import scala.collection.mutable.Map
import scala.io.Source


object Utils
{

  /******************************************************************************
  * Title: Connect to database and return SQL Connection
  * Author: Carlos Kassab, Date: 08/22/2020
  ******************************************************************************/
  def DbConnection(dbType: String, dbInfo: String, dbUserName: String, dbPassw: String): Try[sql.Connection] =
    Try { // Scala error handling.
      dbType match {
        case "pgsl" => {
          // Make PostgreSQL DB connection and return it.
          val driver = "org.postgresql.Driver"
          Class.forName(driver)
          DriverManager.getConnection(dbInfo, dbUserName, dbPassw)
        }
        case "sqlite" => {
          // Make SQLITE DB connection and return it.
          val driver = "org.sqlite.JDBC"
          Class.forName(driver)
          DriverManager.getConnection(dbInfo)
        }
      }
    }



  /******************************************************************************
  * Title: Open file, read its content and convert it to a big string.
  * Author: Carlos Kassab, Date: 08/31/2020
  ******************************************************************************/
  def GetSqlQueryFromFile(queryFileName: String): String = {
    try {
      // Read SQL query from file and return a long string
      val dataFromFile = scala.io.Source.fromFile(queryFileName).mkString
      dataFromFile // Returns data from file
    } catch {
      case e: FileNotFoundException => println(s"$e \n When trying to read query from file: $queryFileName \n"); "Exception Found."
      case e: IOException => println(s"Got an IOException!: $e \n When reading query from file: $queryFileName \n"); "Exception Found."
      case e: Exception => println(s"Got an unhandled Exception!: $e \n When reading query from file: $queryFileName \n"); "Exception Found."
    } finally scala.io.Source.fromFile(queryFileName).close()
  }




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



  def GetLogFilePointer: PrintWriter = {
    val today = Calendar.getInstance()
    
    var logFileName = "log/" + today.get(Calendar.YEAR).toString

    //Months are indexed from 0 not 1
    if( (today.get(Calendar.MONTH)+1) < 10 ) {
      logFileName += "0" + (today.get(Calendar.MONTH)+1).toString
    } else {
      logFileName += (today.get(Calendar.MONTH)+1).toString
    }
    
    if( today.get(Calendar.DAY_OF_MONTH) < 10 ) {
      logFileName += "0" + today.get(Calendar.DAY_OF_MONTH).toString
    } else {
      logFileName += today.get(Calendar.DAY_OF_MONTH).toString
    }

    logFileName += today.get(Calendar.HOUR_OF_DAY).toString +
                   today.get(Calendar.MINUTE).toString +
                   today.get(Calendar.SECOND).toString + ".log"

    val logPointer = new PrintWriter(new File(logFileName))

    return( logPointer )
  }



  def DeleteLogFiles(daysToKeep: Int): Unit = {

      //First get today's information
      val today = Calendar.getInstance()
      var todayNumber = today.get(Calendar.YEAR).toString

      if( (today.get(Calendar.MONTH)+1) < 10 ) {
        todayNumber += "0" + (today.get(Calendar.MONTH)+1).toString
      } else {
        todayNumber += (today.get(Calendar.MONTH)+1).toString
      }
      
      if( today.get(Calendar.DAY_OF_MONTH) < 10 ) {
        todayNumber += "0" + today.get(Calendar.DAY_OF_MONTH).toString
      } else {
        todayNumber += today.get(Calendar.DAY_OF_MONTH).toString
      }
      
      // Get log directory files.
      val logDir = new File("log")
      val logFiles = logDir.listFiles.filter(_.isFile).toList
      val logFileNames = (logFiles map (todayNumber.toDouble -_.toString.slice(4,12).toDouble))
      
      // Delete files greater or equal to passed days.
      for(fileCount <- 0 to logFileNames.length-1 ){
        if( logFileNames(fileCount) >= daysToKeep ) logFiles(fileCount).delete()
      }
      
  }



  def PrintSomething: Unit = {
    println("Printing Something")
  }
    
}

