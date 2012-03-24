package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import services.markdown.ComponentRegistry

/**
 * Date: 17.03.2012 at 14:20
 *
 * @author Marcin Swierczynski
 */

case class Note(id: Long, content: String) {

	val NEW_LINE_SEPARATOR: String = "\n"
	val markdownConverter = ComponentRegistry.markdownConverter

	def title(): String = content.split(NEW_LINE_SEPARATOR)(0)

	def contentWithoutTitle(): String = content.split(NEW_LINE_SEPARATOR).drop(1) mkString NEW_LINE_SEPARATOR

	def convertedContent(): String = markdownConverter.convert(contentWithoutTitle())

}

object Note {

	def all(): List[Note] = DB.withConnection { implicit c =>
		SQL("select * from note").as(note *)
	}

	def create(content: String) {
		DB.withConnection { implicit c =>
			SQL("insert into note (content) values ({content})").on('content -> content).executeUpdate()
		}
	}

	def delete(id: Long) {
		DB.withConnection { implicit c =>
			SQL("delete from note where id = {id}").on('id -> id).executeUpdate()
		}
	}
	
	def findByContent(content: String): List[Note] = DB.withConnection { implicit c =>
		SQL("select * from note where content like '%{content}%'").on('content -> content).as(note *)
	}

	val note = {
		get[Long]("id") ~
		get[String]("content") map {
			case id~label => Note(id, label)
		}
	}
}
