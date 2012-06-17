package controllers

import play.api.mvc.Controller
import models.{Note, User}
import services.dropbox.ComponentRegistry
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo
import java.io.File

/**
 * Date: 27.05.2012 at 13:02
 *
 * @author Marcin Swierczynski
 */

object Dropbox extends Controller with Secured {

	val dropboxService = ComponentRegistry.dropboxService

	val REQUEST_TOKEN = "requestToken"
	val REQUEST_TOKEN_SECRET = "requestTokenSecret"

	def authenticateDropboxUser = IsAuthenticated { username => implicit request =>
		User.findByEmail(username).map { user =>
			val urlAfterAuth: String = routes.Dropbox.authenticated().absoluteURL()
			val authInfo: WebAuthInfo = dropboxService.authenticateUser(urlAfterAuth)
			Redirect(authInfo.url).withSession(request.session +
				(REQUEST_TOKEN -> authInfo.requestTokenPair.key) +
				(REQUEST_TOKEN_SECRET -> authInfo.requestTokenPair.secret)
			)
		}.getOrElse(Forbidden)
	}

	def authenticated = IsAuthenticated { username => request =>
		User.findByEmail(username).map { user =>
			val requestToken = request.session.get(REQUEST_TOKEN).getOrElse("")
			val requestTokenSecret = request.session.get(REQUEST_TOKEN_SECRET).getOrElse("")

			val oauthToken = request.queryString.getOrElse("oauth_token", null).head
			if (requestToken != oauthToken) {
				throw new RuntimeException("Wrong token!")
			}

			dropboxService.persistAccessToken(requestToken, requestTokenSecret, user)

			Redirect(routes.Notes.notes())
		}.getOrElse(Forbidden)
	}

	def persistNotes = IsAuthenticated { username => _ =>
		User.findByEmail(username).map { user =>
			Note.all().foreach { note =>
				val file: File = Note.saveToFile(note)
				dropboxService.saveFile(file, user)
				file.delete()
			}
			Redirect(routes.Notes.notes())
		}.getOrElse(Forbidden)
	}

}
