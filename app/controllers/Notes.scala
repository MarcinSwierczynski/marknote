package controllers

import play.api._
import play.api.data.Forms._
import data.Form
import play.api.mvc._
import models.{User, Note}

/**
 * Date: 17.03.2012 at 14:06
 *
 * @author Marcin Swierczynski
 */

object Notes extends Controller with Secured {

	def notes = IsAuthenticated { username => _ =>
		User.findByEmail(username).map { user =>
			Ok(views.html.Notes.list(Note.all(), noteForm))
		}.getOrElse(Forbidden)
	}

	val noteForm = Form(
		"content" -> nonEmptyText(minLength = 5)
	)

	def newNote = Action { implicit request =>
		noteForm.bindFromRequest.fold(
			errors => BadRequest(views.html.Notes.list(Note.all(), errors)),
			content => {
				Note.create(content)
				Redirect(routes.Notes.notes())
			}
		)
	}

	def deleteNote(id: Long) = Action {
		Note.delete(id)
		Redirect(routes.Notes.notes())
	}

}
