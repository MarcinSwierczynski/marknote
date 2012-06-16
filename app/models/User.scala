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

case class User(email: String, name: String, password: String,
                dropboxUID: Option[String], dropboxAccessTokenKey: Option[String], dropboxAccessTokenSecret: Option[String])

object User {

	def findByEmail(email: String): Option[User] = {
		DB.withConnection {implicit connection =>
			SQL("select * from user0 where email = {email}").on('email -> email).as(User.user.singleOpt)
		}
	}

	def findAll: Seq[User] = {
		DB.withConnection {implicit connection =>
			SQL("select * from user0").as(User.user *)
		}
	}

	def authenticate(email: String, password: String): Option[User] = {
		DB.withConnection {implicit connection =>
			SQL("""select * from user0 where email = {email} and password = {password}""").on(
				'email -> email, 'password -> password).as(User.user.singleOpt)
		}
	}

	def create(user: User): User = {
		DB.withConnection {implicit connection =>
			SQL("""insert into user0 values ({email}, {name}, {password}, {dropbox_uid}, {dropbox_access_token_key}, {dropbox_access_token_secret})""").on(
				'email -> user.email, 'name -> user.name, 'password -> user.password,
				'dropbox_uid -> user.dropboxUID, 'dropbox_access_token_key -> user.dropboxAccessTokenKey, 'dropbox_access_token_secret -> user.dropboxAccessTokenSecret).executeUpdate()
			user
		}
	}

	def persistDropboxCredentials(dropboxUID: String, dropboxAccessTokenKey: String, dropboxAccessTokenSecret: String, userEmail: String) {
		DB.withConnection {implicit connection =>
			SQL("""update user0 set dropbox_uid = {dropbox_uid}, dropbox_access_token_key = {dropbox_access_token_key}, dropbox_access_token_secret = {dropbox_access_token_secret} where email = {email}""").
					on('dropbox_uid -> dropboxUID, 'dropbox_access_token_key -> dropboxAccessTokenKey, 'dropbox_access_token_secret -> dropboxAccessTokenSecret, 'email -> userEmail).
					executeUpdate()
		}
	}

	val user = {
		get[String]("user0.email") ~
		get[String]("user0.name") ~
		get[String]("user0.password") ~
		get[Option[String]]("user0.dropbox_uid") ~
		get[Option[String]]("user0.dropbox_access_token_key") ~
		get[Option[String]]("user0.dropbox_access_token_secret") map {
			case email ~ name ~ password ~ dropboxUid ~ dropboxAccessTokenKey ~ dropboxAccessTokenSecret
				=> User(email, name, password, dropboxUid, dropboxAccessTokenKey, dropboxAccessTokenSecret)
		}
	}

}
