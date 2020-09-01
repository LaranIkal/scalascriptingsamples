/*******************************************************************************
* Title:  Main script that runs a function from utils.scala, Utils object.
* Author: Carlos Kassab
* Date: 08/20/2020
*
*******************************************************************************/

object MyApp extends App {
  def UsingParms(params: Array[String]): Unit = {

    println("Hello, " + params(0) + ".....!!!!!")
    println("Welcome to Scala Scripting, an elegant way to scripting.....!!!!!") 

    if(params.length == 2) {
      Utils.HelloWithParams(params)
    } else {
      println("If you tell me your age I will show it on screen.")
    }

  }
}

