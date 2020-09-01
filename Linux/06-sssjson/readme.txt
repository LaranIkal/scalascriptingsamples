/*******************************************************************************
* title:  Readme.
* author: Carlos Kassab
* date: 08/26/2020
* Description: Follow instructions in this readme file to download dependencies
* Before running the program.
*
*
*
*******************************************************************************/



- Download Apache Maven.

- Untar it: tar -xvf 

- Create directory jarlib inside maven directory.

- Edit conf/settings.xml and set the local repository to the previously created directory.
 <localRepository>/home/Apps/apache-maven-3.6.3/jarlib</localRepository>
 
- Create the pom.xml file as you can see in this directory. 


To run maven and download all dependencies, open a command window in your project directory
where your pom file is located and run maven this way: 

mvn dependency:tree 

- After running maven this way, you will see the dependency tree, it is important
for you to copy all dependencies downloaded by maven under directory jarlib to your project jarlib directory.


Note. When running maven, you will see something like this:

07-sssjson>mvn dependency:tree

[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< org.example:sssjson >-------------------------
[INFO] Building sssjson 1.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ sssjson ---
[INFO] org.example:sssjson:jar:1.0
[INFO] \- com.lihaoyi:ujson_2.13:jar:1.2.0:compile
[INFO]    \- com.lihaoyi:upickle-core_2.13:jar:1.2.0:compile
[INFO]       +- org.scala-lang.modules:scala-collection-compat_2.13:jar:2.1.4:compile
[INFO]       |  \- org.scala-lang:scala-library:jar:2.13.1:compile
[INFO]       \- com.lihaoyi:geny_2.13:jar:0.6.2:compile
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.264 s
[INFO] Finished at: 2020-08-26T11:20:43-05:00
[INFO] ------------------------------------------------------------------------

**** Just copy the the jar files not related to the language itself, these
**** already are inside scala directory.
