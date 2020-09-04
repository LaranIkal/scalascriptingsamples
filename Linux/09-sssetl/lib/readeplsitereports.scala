/*******************************************************************************
* Title:  Functions for EplSite reports ETL using mutable collection ArrayBuffer
* Author: Carlos Kassab
* Date: 09/04/2020
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
import scala.collection.mutable.ArrayBuffer


object ReadEplSiteReports {

  def Reports(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection): Try[Unit] = Try {
    // Using an Scala mutable collection, this way we will be able to add new elements to our collection in a native way.
    // please see this links: https://alvinalexander.com/scala/make-arraybuffer-class-default-mutable-indexed-sequence-scala-cookbook/
    // https://alvinalexander.com/scala/how-to-choose-collection-class-scala-cookbook/
    // https://alvinalexander.com/scala/understanding-performance-scala-collections-classes-methods-cookbook/
    // According to this article: https://www.baeldung.com/scala/mutability, mutable collections are faster, the compiler does not need
    // to create a new collection when each item is added.
    val reportsData:ArrayBuffer[Report] = ArrayBuffer.empty // Creating our Array
    val reportsDataRef = (x: Int) => reportsData // Creating a reference to our array in order to just point to it and not need to move it.
    DataExtraction(log, sssConfig, dbConn, reportsDataRef)    
    AfterDataExtraction(log, sssConfig, dbConn, reportsDataRef)
  }

  // Data structure to store our retrieved records.
  private case class Report( reportId: Int, description: String )


  /*****************************************************************************
  * Title: Function to execute data extraction query.
  * Description: This function gets data from EplSite sqlite database report groups table
  * Author: Carlos Kassab
  * Date: 09/04/2020
  *
  *****************************************************************************/
  private def DataExtraction(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection, reportsDataRef: Int => ArrayBuffer[Report]): Unit = {

    log.write(s"Get sql query from file:queries/reportgroups.sql.\n")
    val queryString = Utils.GetSqlQueryFromFile("queries/reportgroups.sql")

    val statement = dbConn.createStatement()

    log.write(s"Execute query:\n$queryString\n")
    val resultSet = statement.executeQuery(queryString)

    while(resultSet.next()) {
      reportsDataRef(1) += Report( resultSet.getInt(1), resultSet.getString(2) )
    }
    resultSet.close()
    statement.close()
  }



  /*****************************************************************************
  * Title: Function to store data extracted from query.
  * Description: This function stores data from EplSite sqlite database report groups table to a csv file.
  * Author: Carlos Kassab
  * Date: 09/04/2020
  *
  *****************************************************************************/
  private def AfterDataExtraction(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection, reportsDataRef: Int => ArrayBuffer[Report]): Unit = {

    log.write(s"Writing data to file:${sssConfig("targetdbinfo4")}\n")
    val dataFilePointer = new PrintWriter(new File(sssConfig("targetdbinfo4")))
    dataFilePointer.write("Report Group Id, Report Group Description\n")

    println("Report Id, Report Description")
    // Get all records from our ArrayBuffer and print them to file and to screen.
    reportsDataRef(1).collect{case Report(reportId, description) => dataFilePointer.write(s"$reportId,$description\n")}
    reportsDataRef(1).collect{case Report(reportId, description) => println(s"$reportId,$description")}
    dataFilePointer.close
  }


} // object ReadEplSiteModules {



