package com.example.consumer

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import org.scalatest.{WordSpec, Matchers}
import com.twitter.util.Await

case class Results(count: Int, results: List[String])

class ProviderClientSpec extends WordSpec with Matchers {

  // The import contains two things:
  // 1. The consumer test DSL/Builder
  // 2. Helper implicits, for instance, values will automatically be converted
  //    to Option types where the DSL requires it.
  import com.itv.scalapact.ScalaPactForger._

  "Connecting to the Provider service" should {
    "be able to fetch results" in {

      val people = List("Bob", "Fred", "Harry")

      val body = Results(
          count = 3,
          results = people
        ).asJson.toString
      

      forgePact
        .between("Consumer")
        .and("Provider")
        .addInteraction(
          interaction
            .description("Fetching results")
            .given("Results: Bob, Fred, Harry")
            .uponReceiving("/results")
            .willRespondWith(200, body)
        )
        .runConsumerTest { mockConfig =>

          val results = Await.result(ProviderClient.get(mockConfig.host + ":" + mockConfig.port))

          results.contentString shouldEqual body

        }
    }
  }
}
