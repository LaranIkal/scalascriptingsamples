/*******************************************************************************
* Title:  Misc Utils for Scala Scripting
* Author: Carlos Kassab
* Date: 08/20/2020
*
*******************************************************************************/

object Utils
{
  def PrintSomething: Unit = {
    println("Printing Something in an elegant way from a scala script.")
  }



  def HelloWithParams(myParams: Array[String]): Unit = {
      println("You are " + myParams(1) + " years old." )
  }

}

