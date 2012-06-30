package services.dropbox

import com.dropbox.client2.session.WebAuthSession.WebAuthInfo
import config.Config
import com.dropbox.client2.session.{AccessTokenPair, RequestTokenPair, WebAuthSession, AppKeyPair}
import models.{Note, User}
import com.dropbox.client2.DropboxAPI
import java.io.{FileInputStream, File}

/**
 * Date: 27.05.2012 at 13:15
 *
 * @author Marcin Swierczynski
 */

trait DropboxComponent {

	val dropboxService: DropboxService

	class DropboxService {

		def authenticateUser(urlAfterAuth: String): WebAuthInfo = {
			val webAuthSession: WebAuthSession = createAnonymousWebAuthSession()
			webAuthSession.getAuthInfo(urlAfterAuth)
		}

		private def createAnonymousWebAuthSession(): WebAuthSession = {
			val appKeyPair: AppKeyPair = new AppKeyPair(Config.DROPBOX_APP_KEY, Config.DROPBOX_APP_SECRET)
			new WebAuthSession(appKeyPair, Config.DROPBOX_ACCESS_TYPE)
		}

		def persistAccessToken(requestToken: String, requestTokenSecret: String, user: User) {
			val webAuthSession: WebAuthSession = createAnonymousWebAuthSession()
			val dropboxUID: String = webAuthSession.retrieveWebAccessToken(new RequestTokenPair(requestToken, requestTokenSecret))
			val accessTokenKey: String = webAuthSession.getAccessTokenPair.key
			val accessTokenSecret: String = webAuthSession.getAccessTokenPair.secret
			User.persistDropboxCredentials(dropboxUID, accessTokenKey, accessTokenSecret, user.email)
		}

		def saveFile(file: File, user: User) {
			val stream: FileInputStream = new FileInputStream(file)
			try {
				val dropboxApi: DropboxAPI[WebAuthSession] = getAuthenticatedDropboxApi(user)
				dropboxApi.putFileOverwrite("/" + file.getName, stream, file.length(), null)
			}
			catch {
				case e: Exception => println(e.getMessage)
			}
			finally {
				if (stream != null) {
					try {
						stream.close()
					}
					catch {
						case e: Exception => println(e.getMessage)
					}
				}
			}
		}

		def persistNotesOfUser(user: User) {
			// TODO get notes of a given user
			Note.all().foreach {
				note =>
					val file: File = Note.saveToFile(note)
					dropboxService.saveFile(file, user)
					file.delete()
			}
		}

		private def getAuthenticatedDropboxApi(user: User): DropboxAPI[WebAuthSession] = {
			new DropboxAPI[WebAuthSession](getAuthenticatedWebAuthSession(user))
		}

		private def getAuthenticatedWebAuthSession(user: User): WebAuthSession = {
			val appKeyPair: AppKeyPair = new AppKeyPair(Config.DROPBOX_APP_KEY, Config.DROPBOX_APP_SECRET)
			val accessTokenPair: AccessTokenPair =
				new AccessTokenPair(user.dropboxAccessTokenKey.getOrElse(""), user.dropboxAccessTokenSecret.getOrElse(""))
			new WebAuthSession(appKeyPair, Config.DROPBOX_ACCESS_TYPE, accessTokenPair)
		}

	}

}
