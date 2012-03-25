package controllers

import org.specs2.mutable.Specification
import play.api.test._
import play.api.test.Helpers._
import models.Note
import play.mvc.Http.Status.{BAD_REQUEST, OK, SEE_OTHER}

/**
 * Date: 22.03.2012 at 21:03
 *
 * @author Marcin Swierczynski
 */

class NotesTest extends Specification {

	"respond to the Notes action" in {
		running(FakeApplication()) {
			val result = controllers.Notes.notes(FakeRequest())
			status(result) must equalTo(OK)
		}
	}

	"show notes list" in {
		running(FakeApplication()) {
			Note.create("Note1 title\\n note1 content")
			Note.create("Note2 title\\n note2 content")

			val result = controllers.Notes.notes(FakeRequest())
			contentAsString(result) must contain("Note1 title")
			contentAsString(result) must contain("Note2 title")
			contentAsString(result) must contain("note1 content")
			contentAsString(result) must contain("note2 content")
		}
	}

	"save new note" should {
		"persist note to database" in {
			running(FakeApplication()) {
				controllers.Notes.newNote(FakeRequest().withFormUrlEncodedBody(("content", "Note1")))
				val notes: List[Note] = Note.findByContent("Note1")
				notes.size must beEqualTo(1)
				notes.head.content must beEqualTo("Note1")
			}
		}
		"redirect to notes list" in {
			running(FakeApplication()) {
				val result = controllers.Notes.newNote(FakeRequest().withFormUrlEncodedBody(("content", "Note1")))
				status(result) must equalTo(SEE_OTHER)
			}
		}
	}

	"impossible to save empty note" in {
		running(FakeApplication()) {
			val result = controllers.Notes.newNote(FakeRequest())
			status(result) must equalTo(BAD_REQUEST)
		}
	}

	"impossible to save note shorter than 5 characters" in {
		running(FakeApplication()) {
			val result = controllers.Notes.newNote(FakeRequest().withFormUrlEncodedBody(("content", "Note")))
			status(result) must equalTo(BAD_REQUEST)
		}
	}

	"delete note" in {
		running(FakeApplication()) {
			controllers.Notes.newNote(FakeRequest().withFormUrlEncodedBody(("content", "Note1")))
			val newNote: Note = Note.findByContent("Note1").head

			val result = controllers.Notes.deleteNote(newNote.id)(FakeRequest())
			status(result) must equalTo(SEE_OTHER)
			Note.findByContent("Note1").size must beEqualTo(0)
		}
	}

}
