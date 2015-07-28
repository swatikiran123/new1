package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class User1(
	deleteUser: String
	
)


object User1 {


	def delete(key1: User1) {
		DB.withConnection{  implicit c=> 
		SQL("delete from datause where email={email}")
			.on ("email"-> key1.deleteUser)
			.executeUpdate()
		}
	}
 

}
