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

object ReadPagesCategories {

  def PagesCategories(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection): Try[Unit] = Try {
    val pagesCatData = PagesCatData(Array(""),Array(""))
    val pagesCatDataRef = (x: Int) => pagesCatData      
    DataExtraction(log, sssConfig, dbConn, pagesCatDataRef)
    AfterDataExtraction(log, sssConfig, dbConn, pagesCatDataRef)
  }

  // Data structure to store our retrieved records.
  case class PagesCatData( var title: Array[String], var description: Array[String] )



  /*****************************************************************************
  * Title: Function to execute data extraction query.
  * Description: This function gets data from EplSite sqlite database pages categories table
  * Author: Carlos Kassab
  * Date: 08/29/2020
  *
  *****************************************************************************/
  def DataExtraction(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection, pagesCatDataRef: Int => PagesCatData): Unit = {

    log.write(s"Get sql query from file:queries/pagescategories.sql.\n")
    val queryString = Utils.GetSqlQueryFromFile("queries/pagescategories.sql")

    val statement = dbConn.createStatement()

    log.write(s"Execute query:\n$queryString\n")
    val resultSet = statement.executeQuery(queryString)

    while(resultSet.next()) {
      pagesCatDataRef(1).title = pagesCatDataRef(1).title :+ resultSet.getString(1)
      pagesCatDataRef(1).description = pagesCatDataRef(1).description :+ resultSet.getString(2)
    }

  }



  /*****************************************************************************
  * Title: Function to store data extracted from query.
  * Description: This function stores data from EplSite sqlite database pages categories table to a csv file.
  * Author: Carlos Kassab
  * Date: 08/31/2020
  *
  *****************************************************************************/
  def AfterDataExtraction(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection, pagesCatDataRef: Int => PagesCatData): Unit = {

    log.write(s"Writing data to file:${sssConfig("targetdbinfo2")}\n")
    val dataFilePointer = new PrintWriter(new File(sssConfig("targetdbinfo2")))
    dataFilePointer.write("Title, Description\n")
    for( titleNum <- 1 to (pagesCatDataRef(1).title.length - 1)) {
      dataFilePointer.write(pagesCatDataRef(1).title(titleNum) + "," + pagesCatDataRef(1).description(titleNum) + "\n")
      println(s"Title Num: $titleNum - ${pagesCatDataRef(1).description(titleNum)}")
    }
    dataFilePointer.close
  }


} // object ReadEplSiteModules {
