package controllers;

import models.Note;
import org.junit.Test;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Date: 17.01.2012 at 21:37
 *
 * @author Marcin Swierczynski
 */
public class NotesTest extends FunctionalTest {

	@Test
	public void notesListResponds() throws Exception {
		Response response = GET("/notes/list");
		assertIsOk(response);
	}

	@Test
	public void showNotesList() throws Exception {
		Note note1 = new Note("Note1").save();
		Note note2 = new Note("Note2").save();

		Response response = GET("/notes/list");

		List<Note> notes = (List<Note>) renderArgs("notes");
		assertThat(notes.get(0), is(note1));
		assertThat(notes.get(1), is(note2));

		String responseContent = response.out.toString("UTF-8");
		assertThat(responseContent, containsString(note1.content));
		assertThat(responseContent, containsString(note2.content));
	}

}
