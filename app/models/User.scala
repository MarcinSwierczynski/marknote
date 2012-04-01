package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

/**
 * Date: 01.04.2012 at 11:35
 *
 * @author Marcin Swierczynski
 */

case class User(email: String, name: String, password: String)

object User {

	def findByEmail(email: String): Option[User] = {
		DB.withConnection {implicit connection =>
			SQL("select * from user where email = {email}").on('email -> email).as(User.user.singleOpt)
		}
	}

	def findAll: Seq[User] = {
		DB.withConnection {implicit connection =>
			SQL("select * from user").as(User.user *)
		}
	}

	def authenticate(email: String, password: String): Option[User] = {
		DB.withConnection {implicit connection =>
			SQL("""select * from user where email = {email} and password = {password}""").on(
				'email -> email, 'password -> password).as(User.user.singleOpt)
		}
	}

	def create(user: User): User = {
		DB.withConnection {implicit connection =>
			SQL("""insert into user values ({email}, {name}, {password})""").on(
				'email -> user.email, 'name -> user.name, 'password -> user.password).executeUpdate()
			user
		}
	}

	val user = {
		get[String]("user.email") ~
		get[String]("user.name") ~
		get[String]("user.password") map {
			case email ~ name ~ password => User(email, name, password)
		}
	}

}
