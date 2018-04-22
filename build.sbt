
import com.itv.scalapact.plugin._

name := "consumer"

organization := "com.example"

scalaVersion := "2.12.1"

version := "0.0.1"

enablePlugins(ScalaPactPlugin)

libraryDependencies ++=
  Seq(
    "org.slf4j"     %  "slf4j-simple"             % "1.6.4",
    "io.circe" 		%% "circe-core"				  % "0.9.3",
	"io.circe" 		%% "circe-generic"			  % "0.9.3",
	"io.circe" 		%% "circe-parser"			  % "0.9.3",

    "com.twitter" 	%% "finagle-http" 			  % "18.4.0",
    "org.scalatest" %% "scalatest"                % "3.0.1"          % "test",
	"com.itv"       %% "scalapact-circe-0-9"      % "2.2.3" 		 % "test",
	"com.itv"       %% "scalapact-http4s-0-18-0"  % "2.2.3"  		 % "test",
	"com.itv"       %% "scalapact-scalatest"      % "2.2.3" 		 % "test"
  )

scalaPactEnv := ScalaPactEnv.default.withPort(8080)
