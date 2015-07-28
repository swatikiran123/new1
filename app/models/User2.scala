package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class User2(
 updateUser: String,
	password: String,
	firstName:String,
	lastName:String,
	phonenumber:String
	
	
	
)


object User2 {

def updateForm(key2: User2) = {


DB.withConnection { implicit connection =>
SQL(
  "UPDATE datause SET password= {password}, firstName = {firstName},lastName = {lastName},phonenumber = {phonenumber} WHERE email = {email}")
 .on ('email-> key2.updateUser,'password->key2.password,
     'firstName-> key2.firstName,
      'lastName -> key2.lastName,
      'phonenumber->key2.phonenumber)
      			.executeUpdate()
  } 

/*def updatedata1(key:DeleteUserData) {
    DB.withConnection{ implicit c=>
    SQL("update usersdata set username = {username1},password = {password1} where username = {username}")
    .on('username-> key.username,'username1-> key.username1,'password1->key.password1).executeUpdate() 
    }
  } 
    SQL("update usersdata set username = 'ram',password= 'kiran' where username = {username}")
    .on('username-> key.username).executeUpdate() */
}


}
