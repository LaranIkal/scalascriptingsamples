#!/bin/sh
exec scala -i "Utils.scala" \
-classpath ".:$VUUBASE/jdbc/postgresql-42.2.12.jar:$VUUBASE/jdbc/ojdbc7.jar" "$0" "$@"
!#


import java.sql.Connection
import scala.util.{Try, Success, Failure}

object orajdbctest33 extends App {

  println("Starting...")
  // Database connection information
  val dbType = "oracle"
  val server = "10.230.17.26"
  val portNumber = "1521"
  val dbName = "FXDB"
  val userName = "arbor"
  val passw = "arbor123"

  //Connect to db and run query
  Utils.dbConnection(dbType, server, portNumber, dbName, userName, passw) match  //Check if the return value match the next.
  {
    // Success sub-class returns the success object in our variable vuuConnection from Utils.dbConnection
    case Success(vuuConnection) =>
    {
      println(vuuConnection)
      RunQuery(vuuConnection)
      vuuConnection.close()
    }
    // Failure sub-class returns an exception object with exception info in our variable f.
    case Failure(f) => println(f)
  }


  Utils.recibido(dbType, server, portNumber, dbName, userName, passw) match  //Check if the return value match the next.
  {
    // Success sub-class returns the success object in our variable vuuConnection from Utils.dbConnection
    case Success(mensaje) =>
    {
      println(mensaje)
    }
    // Failure sub-class returns an exception object with exception info in our variable f.
    case Failure(f) => println(f)
  }



  def RunQuery(connection: Connection): Unit = {
    try
      {
        // make the connection
        //Class.forName(driver)
        //connection = DriverManager.getConnection(url, username, password)

        // create the statement, and run the select query
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT table_name FROM ALL_TABLES WHERE ROWNUM <= 10")
        while ( resultSet.next() ) {
          val tableName = resultSet.getString("table_name")
          println(s"TableName:$tableName")
        }

      } catch {
      case th: Throwable => println("found a unknown exception"+th.printStackTrace())
    }
  }


}

//orajdbctest.main(args)




