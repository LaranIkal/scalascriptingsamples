/*******************************************************************************
* title:  Misc Utils for Scala Scripting
* author: Carlos Kassab
* date: 08/20/2020
*
*******************************************************************************/

import java.sql
import java.sql.DriverManager
import java.sql.Connection
import scala.util.Try

object Utils
{
  /******************************************************************************
  * Title: Connect to database and return SQL Connection
  * Author: Carlos Kassab, Date: 08/22/2020
  ******************************************************************************/
  def DbConnection(dbType: String, dbInfo: String, dbUserName: String, dbPassw: String): Try[sql.Connection] =
    Try { // Scala try for functional error handling.
      dbType match {
        case "pgsql" => {
          // Make PostgreSQL DB connection and return it
          val driver = "org.postgresql.Driver"
          Class.forName(driver)
          DriverManager.getConnection(dbInfo, dbUserName, dbPassw)
        }
        case "sqlite" => {
          // Make SQLITE DB connection and return it
          val driver = "org.sqlite.JDBC"
          Class.forName(driver)
          DriverManager.getConnection(dbInfo)
        } 
      }
    }



  def PrintSomething: Unit = {
    println("Printing Something")
  }
    
}

