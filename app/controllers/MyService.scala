package controllers

import controllers.Application.{Address, Person, Request}
import play.api.libs.json._
/**
  * Created by muhammed on 4/6/17.
  */
class MyService {

  def processRequest(request: Request): JsValue ={
    val result = Person(request.name,
                            Address(request.number, request.street))
    val res: JsValue = Json.toJson(result)
    res
  }

  def getReponse(json: JsValue): Option[JsValue] = {
   val personOpt: JsResult[Request] = Json.fromJson[Request](json)
    personOpt match {
      case JsError(errors) => None
      case JsSuccess(request, _) => Some(processRequest(request))
    }
  }
}