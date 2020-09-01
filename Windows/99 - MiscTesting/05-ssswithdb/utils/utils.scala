/*******************************************************************************
* title:  Misc Utils for Scala Scripting
* author: Carlos Kassab
* date: 08/20/2020
*
*******************************************************************************/

import java.sql
import java.sql.DriverManager
import java.sql.Connection
import java.io.{FileNotFoundException, IOException}

import scala.util.Try

object Utils
{
  /******************************************************************************
  * Title: Connect to database and return SQL Connection
  * Author: Carlos Kassab, Date: 08/22/2020
  ******************************************************************************/
  def DbConnection(dbType: String, dbInfo: String, dbUserName: String, dbPassw: String): Try[sql.Connection] =
    Try { // Scala Syntactic sugar for try
      dbType match {
        case "pgsql" => {
          // Make PostgreSQL DB connection and return it
          val driver = "org.postgresql.Driver"
          Class.forName(driver)
          DriverManager.getConnection(dbInfo, dbUserName, dbPassw)
        }
        case "sqlite" => {
          // Make SQLITE DB connection and return it
          println("Make SQLITE DB connection and return it.")
          val driver = "org.sqlite.JDBC"
          Class.forName(driver)
          DriverManager.getConnection(dbInfo)
        }
      }
    }



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




    def PrintSomething: Unit = {
      println("Printing Something")
    }
    
}

