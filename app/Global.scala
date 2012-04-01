import play.api._

import models._

object Global extends GlobalSettings {

	override def onStart(app: Application) {
		InitialData.insert()
	}

}

/**
 * Initial set of data to be imported 
 * in the sample application.
 */
object InitialData {

	def date(str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(str)

	def insert() {

		if (User.findAll.isEmpty) {
			Seq(
				User("user@marknote.com", "Power User", "password")
			).foreach(User.create)
		}

	}

}