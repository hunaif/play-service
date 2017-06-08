package controllers

import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  case class Request(number: Seq[Int],street:String,name:String)
  case class Address(number: Seq[Int], street: String)
  case class Person(name: String, address: Address)

  implicit val addressWrites = Json.writes[Address]
  implicit val personWrites = Json.writes[Person]
  implicit  val requestRead = Json.reads[Request]

  def myController = Action(parse.json) { request =>
    val json: JsValue = request.body
    val myService: MyService = new MyService
    val result = myService.getReponse(json)
    result match {
      case Some(json) => Ok(json)
      case  None => BadRequest("Bad Request")
    }
  }
}