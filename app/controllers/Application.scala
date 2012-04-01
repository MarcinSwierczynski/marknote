package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.{Note, User}

object Application extends Controller {

	def index = Action {
		Redirect(routes.Application.login())
	}

	def login = Action {
		implicit request =>
			Ok(views.html.login(loginForm))
	}

	val loginForm = Form(
		tuple(
			"email" -> text,
			"password" -> text
		) verifying("Invalid email or password", result => result match {
			case (email, password) => User.authenticate(email, password).isDefined
		})
	)

	def authenticate = Action {
		implicit request =>
			loginForm.bindFromRequest.fold(
				formWithErrors => BadRequest(views.html.login(formWithErrors)),
				user => Redirect(routes.Notes.notes).withSession("email" -> user._1)
			)
	}

	def logout = Action {
		Redirect(routes.Application.login).withNewSession.flashing(
			"success" -> "You've been successfully logged out"
		)
	}

}

trait Secured {

	def IsOwnerOf(noteId: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
		if (Note.isOwner(noteId, user)) {
			f(user)(request)
		} else {
			Results.Forbidden
		}
	}

	def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(username, onUnauthorized) { user =>
		Action(request => f(user)(request))
	}

	private def username(request: RequestHeader) = request.session.get("email")

	private def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Application.login)

}
