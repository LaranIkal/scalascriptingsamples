/*******************************************************************************
* title:  Main script that runs a function from utils.scala, Utils object.
* author: Carlos Kassab
* date: 08/20/2020
*
*******************************************************************************/

import scala.util.{Try, Success, Failure}

object MyApp extends App {
  def Main: Unit = {
    println("Showing db access using Scala Script.")
    Utils.PrintSomething // Running a function from utils.scala file

    RunSqlQuery
  }


  def RunSqlQuery: Unit = {

    val dbInfo = "jdbc:sqlite:data/EplSite.sqlite"

    // This is a sample in case you have PostgreSQL:
    //val dbInfo = "jdbc:postgresql://" + serverIp + ":" + cPortNumber + "/" + cDbName

    Utils.DbConnection("sqlite", dbInfo, "", "") match
    {
      // Success and Failure are part of Scala Try syntactic sugar.
      // Success sub-class returns the success object in our variable ssConnection from Utils.DbConnection
      case Success(ssConnection) => {
        val statement = ssConnection.createStatement()

        val queryString = Utils.GetSqlQueryFromFile("queries/modules.sql")
        val resultSet = statement.executeQuery(queryString)
        println("Printing records from database table:")
        while(resultSet.next()) {
          println(resultSet.getString(1))
        }
        ssConnection.close()
      }
      // Failure sub-class returns an exception object with exception info in our varibale f.
      case Failure(f) => println(s"Failure found:$f")
    }

  } // def RunSqlQuery {

}
