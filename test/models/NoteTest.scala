package models

import org.specs2.mutable.Specification
import play.api.test._
import play.api.test.Helpers._


/**
 * Date: 23.03.2012 at 22:55
 *
 * @author Marcin Swierczynski
 */

class NoteTest extends Specification {

	"persist and retrieve a note" in {
		running(FakeApplication()) {
			Note.create("Note1")

			val notes: List[Note] = Note.findByContent("Note1")
			notes.size must beEqualTo(1)
			notes.head.content must beEqualTo("Note1")
		}
	}

	"giving a note" should {
		"be able to to get its title" in {
			val note = Note(0, "Note 1 title\nNote 1 content")
			note.title() must beEqualTo("Note 1 title")
		}
		"be able to to get its content without title" in {
			val note = Note(0, "Note 1 title\nNote 1 content")
			note.contentWithoutTitle() must beEqualTo("Note 1 content")
		}
	}

}
