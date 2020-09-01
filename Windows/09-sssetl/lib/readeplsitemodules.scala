/*******************************************************************************
* Title:  Functions for EplSite modules ETL
* Author: Carlos Kassab
* Date: 08/29/2020
*
*******************************************************************************/

import java.io.PrintWriter
import java.sql.DriverManager
import java.sql.Connection
import java.io.PrintWriter
import java.io.File

import scala.io.Source
import scala.util.{Try, Success, Failure}
import scala.collection.mutable.Map

object ReadEplSiteModules {

  def ReadModules(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection): Try[Unit] = Try {
    val modulesData = ModulesData(Array(""),Array(0))
    val modulesDataRef = (x: Int) => modulesData      
    DataExtraction(log, sssConfig, dbConn, modulesDataRef)
    AfterDataExtraction(log, sssConfig, dbConn, modulesDataRef)
  }

  // Data structure to store our retrieved records.
  case class ModulesData( var title: Array[String], var sactive: Array[Int] )



  /*****************************************************************************
  * Title: Function to execute data extraction query.
  * Description: This function gets data from EplSite sqlite database modules table
  * Author: Carlos Kassab
  * Date: 08/29/2020
  *
  *****************************************************************************/
  def DataExtraction(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection, modulesDataRef: Int => ModulesData): Unit = {

    log.write(s"Get sql query from file:queries/modules.sql.\n")
    val queryString = Utils.GetSqlQueryFromFile("queries/modules.sql")

    val statement = dbConn.createStatement()

    log.write(s"Execute query:\n$queryString\n")
    val resultSet = statement.executeQuery(queryString)

    while(resultSet.next()) {
      modulesDataRef(1).title = modulesDataRef(1).title :+ resultSet.getString(1)
      modulesDataRef(1).sactive = modulesDataRef(1).sactive :+ resultSet.getInt(2)
    }

  }



  /*****************************************************************************
  * Title: Function to store data extracted from query.
  * Description: This function stores data from EplSite sqlite database modules table to a csv file.
  * Author: Carlos Kassab
  * Date: 08/31/2020
  *
  *****************************************************************************/
  def AfterDataExtraction(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection, modulesDataRef: Int => ModulesData): Unit = {

    log.write(s"Writing data to file:${sssConfig("targetdbinfo")}\n")
    val dataFilePointer = new PrintWriter(new File(sssConfig("targetdbinfo")))
    dataFilePointer.write("Title, IsActive\n")
    for( titleNum <- 1 to (modulesDataRef(1).title.length - 1)) {
      dataFilePointer.write(modulesDataRef(1).title(titleNum) + "," + modulesDataRef(1).sactive(titleNum) + "\n")
      println(s"Title Num: $titleNum - ${modulesDataRef(1).title(titleNum)}")
    }
    dataFilePointer.close
  }


} // object ReadEplSiteModules {
