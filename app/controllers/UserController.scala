package controllers

/**
  * Created by Serhii on 8/18/2016.
  */
import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString

import play.api.libs.concurrent.Execution.Implicits._

class UserController @Inject() (ws: WSClient) extends Controller {


  def getUsers=Action.async{
    val url :String="http://localhost:9000/user"
    val request: WSRequest = ws.url(url)
    val complexRequest: WSRequest =
      request.withHeaders("Accept" -> "application/json")
         .withRequestTimeout(10.seconds)

    complexRequest.get().map {
      wsResponce => Ok(wsResponce.body)
   }
  }
}
