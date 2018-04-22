package com.example.consumer

import com.twitter.finagle.Http
import com.twitter.finagle.http.{Method, Request, Response, Version}
import com.twitter.util.{Await, Future}

object ProviderClient {

  def get(baseUrl: String): Future[Response] = {
    val client = Http.client
      .withLabel("finagle-client")
      .newService(baseUrl)
    val request = Request(Version.Http11, Method.Get, "/results")
    client(request)
  }
}