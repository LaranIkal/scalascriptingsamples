/*******************************************************************************
* Title:  Functions for EplSite report groups  ETL
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


object ReadReportGroups {

  def ReportGroups(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection): Try[Unit] = Try {
    val repGroupsData = DataExtraction(log, sssConfig, dbConn)
    val repGroupsDataRef = (x: Int) => repGroupsData
    AfterDataExtraction(log, sssConfig, dbConn, repGroupsDataRef)
  }

  // Data structure to store our retrieved records.
  private case class ReportGroup( repGroupId: Int, description: String )


  /*****************************************************************************
  * Title: Function to execute data extraction query.
  * Description: This function gets data from EplSite sqlite database report groups table
  * Author: Carlos Kassab
  * Date: 09/04/2020
  *
  *****************************************************************************/
  private def DataExtraction(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection): Vector[ReportGroup] = {

    // Using a mutable variable with and immutable collection, this way we will be able to add new elements to our collection.
    // what really will be happening is that Scala will point to a new collection each time we use :+ method.
    // The repGroupsData variable is mutable—like a non-final field in Java—so it’s actually being reassigned to a new collection.
    // please see this links: https://alvinalexander.com/scala/understanding-mutable-variables-with-immutable-collections-scala/
    // https://alvinalexander.com/scala/make-vector-class-default-immutable-sequence-scala-cookbook/
    // https://alvinalexander.com/scala/how-to-choose-collection-class-scala-cookbook/
    // https://alvinalexander.com/scala/understanding-performance-scala-collections-classes-methods-cookbook/
    var repGroupsData:Vector[ReportGroup] = Vector.empty

    log.write(s"Get sql query from file:queries/reportgroups.sql.\n")
    val queryString = Utils.GetSqlQueryFromFile("queries/reportgroups.sql")

    val statement = dbConn.createStatement()

    log.write(s"Execute query:\n$queryString\n")
    val resultSet = statement.executeQuery(queryString)

    while(resultSet.next()) {
      repGroupsData = repGroupsData :+ ReportGroup( resultSet.getInt(1), resultSet.getString(2) )
    }
    resultSet.close()
    statement.close()
    repGroupsData
  }



  /*****************************************************************************
  * Title: Function to store data extracted from query.
  * Description: This function stores data from EplSite sqlite database report groups table to a csv file.
  * Author: Carlos Kassab
  * Date: 09/04/2020
  *
  *****************************************************************************/
  private def AfterDataExtraction(log: java.io.PrintWriter, sssConfig: Map[String, String], dbConn: Connection, repGroupsDataRef: Int => Vector[ReportGroup]): Unit = {

    log.write(s"Writing data to file:${sssConfig("targetdbinfo3")}\n")
    val dataFilePointer = new PrintWriter(new File(sssConfig("targetdbinfo3")))
    dataFilePointer.write("Report Group Id, Report Group Description\n")

    println("Report Group Id, Report Group Description")
    // Plase see this link: https://alvinalexander.com/scala/vector-class-methods-syntax-examples/
    // Get all records from our Vector and print them to file and to screen.
    repGroupsDataRef(1).collect{case ReportGroup(reportGroupId, description) => dataFilePointer.write(s"$reportGroupId,$description\n")}
    repGroupsDataRef(1).collect{case ReportGroup(reportGroupId, description) => println(s"$reportGroupId,$description")}

    dataFilePointer.close
  }


} // object ReadEplSiteModules {



