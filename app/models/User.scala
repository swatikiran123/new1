package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class User(
	email:String,
	password: String,
	firstName:String,
	lastName:String,
	phonenumber:String
)


object User {


	val parser: RowParser[User] = {
		get[String]("datause.email") ~ 
		get[String]("datause.password") ~ 
		get[String]("datause.firstName") ~
		get[String]("datause.lastName") ~
		get[String]("datause.phonenumber") map {
	    	case email ~ password ~ firstName ~ lastName ~ phonenumber
	    		=> User(email,password,firstName,lastName,phonenumber)
	    	}
	}

	
    def findAll: List[User] = {
        DB.withConnection { implicit connection =>
            SQL("SELECT * FROM datause").as(parser *)
        }
    }


	def add(key: User) {
		DB.withConnection{  implicit c=> 
		SQL("""insert into datause (email, password, firstName, lastName, phonenumber ) 
			values({email},{password},{firstName},{lastName},{phonenumber})""")
			.on ("email"-> key.email, "password"->key.password,
			"firstName"-> key.firstName,
			"lastName" -> key.lastName,
			"phonenumber"->key.phonenumber)
			.executeInsert()
		}
	}
 

}

