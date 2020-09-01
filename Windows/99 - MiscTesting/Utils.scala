/*******************************************************************************
* title:  Misc Utils for Scala Scripting
* author: Carlos Kassab
* date: 07/01/2020
*
*******************************************************************************/

import java.sql
import java.sql.DriverManager
import scala.util.Try

object Utils
{
  def DbConnection(cDbType: String, cServer: String, cPortNumber: String, cDbName: String, cUserName: String, cPassw: String) =
    Try {
      cDbType match {
        case "oracle" => {
          // make the DB connection and return it
          val driver = "oracle.jdbc.OracleDriver"
          val url = "jdbc:oracle:thin:@" + cServer + ":" + cPortNumber + "/" + cDbName
          Class.forName(driver)
          DriverManager.getConnection(url, cUserName, cPassw)
        }: sql.Connection
      }
    }

    def PrintSomething: Unit = {
      println("Printing Something")
    }
    
}

