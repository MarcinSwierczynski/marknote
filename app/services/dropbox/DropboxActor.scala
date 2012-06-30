package services.dropbox

import akka.actor.Actor
import models.User

/**
 * Date: 30.06.2012 at 14:01
 *
 * @author Marcin Swierczynski
 */

case class SynchronizeNotesOfUser(user: User)

class DropboxActor extends Actor {

	val dropboxService = ComponentRegistry.dropboxService

	protected def receive = {
		case SynchronizeNotesOfUser(user) =>
			try {
				dropboxService.persistNotesOfUser(user)
				println("Notes synchronized to Dropbox")
			}
			catch {
				case e: Exception => sender ! println("Notes cannot be synchronized to Dropbox. Reason: "+e.getMessage)
			}
	}

}
