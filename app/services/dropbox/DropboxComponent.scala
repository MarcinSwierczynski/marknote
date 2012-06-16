package services.dropbox

import com.dropbox.client2.session.WebAuthSession.WebAuthInfo
import config.Config
import com.dropbox.client2.session.{RequestTokenPair, WebAuthSession, AppKeyPair}
import models.User

/**
 * Date: 27.05.2012 at 13:15
 *
 * @author Marcin Swierczynski
 */

trait DropboxComponent {

	val dropboxService: DropboxService

	class DropboxService {

		def authenticateUser(): WebAuthInfo = {
			val webAuthSession: WebAuthSession = createAnonymousWebAuthSession()
			// TODO remove hardcoded url
			webAuthSession.getAuthInfo("http://localhost:9000/dropbox/authenticated")
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

	}

}
