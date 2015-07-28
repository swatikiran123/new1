package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._


import models.User
import models.User1
import models.User2

class Application extends Controller {

var userForm: Form[User] = Form {
    mapping(
      "email" -> nonEmptyText,
      "password" ->nonEmptyText,
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "phonenumber" -> nonEmptyText
    )(User.apply)(User.unapply)
  }

var userUpdate: Form[User2] = Form {
    mapping(
      "updateUser"-> nonEmptyText,
      "password" ->nonEmptyText,
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "phonenumber" -> nonEmptyText
      
    )(User2.apply)(User2.unapply)
  }

var userDelete: Form[User1] = Form {
    mapping(
    	"deleteUser"-> nonEmptyText
       )(User1.apply)(User1.unapply)
  }

	def index = Action {
		Ok(views.html.index("Your new application is ready."))
	}

	def userlogin = Action { implicit request =>
		Ok(views.html.register.userlogin("userlogin"))
	}

	def reg = Action { implicit request =>
		Ok(views.html.register.reg(userForm))
	}

	def welcome = Action { implicit request =>
		
		def values =  userForm.bind(userForm.bindFromRequest.data).get
		val int = User.add(values)
		
		val users = User.findAll
		Ok(views.html.register.welcome(users,userDelete,userUpdate))

		
	}
		
		def userList = Action { implicit request =>
		
		def values1 =  userDelete.bind(userDelete.bindFromRequest.data).get
		val int = User1.delete(values1)
		val users = User.findAll
		Ok(views.html.register.userList(users))
	}

		
	
		def updateForm = Action { implicit request =>

			//rever map
		Ok(views.html.register.updateForm(userUpdate))
	}
	
	def userListupdated =  Action { implicit request =>
		
		def values2 =  userUpdate.bind(userUpdate.bindFromRequest.data).get
		val int = User2.updateForm(values2)
		val users = User.findAll
		Ok(views.html.register.userListupdated(users))
	}
	
	
}
