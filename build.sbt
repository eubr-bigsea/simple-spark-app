name := "simple-spark-app"

version := "1.0"

scalaVersion := "2.10.5"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0" % "provided"
libraryDependencies +=   "org.apache.spark" %% "spark-mllib" % "1.6.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-graphx" % "1.6.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.6.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.0" % "provided"
libraryDependencies += "org.apache.kafka" % "kafka_2.10" % "0.9.0.0"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % "1.6.0"

assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
