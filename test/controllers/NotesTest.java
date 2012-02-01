package controllers;

import com.google.common.collect.ImmutableMap;
import models.Note;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

import java.util.List;

import static org.hamcrest.Matchers.*;

/**
 * Date: 17.01.2012 at 21:37
 *
 * @author Marcin Swierczynski
 */
public class NotesTest extends FunctionalTest {

	@Before
	public void setUp() throws Exception {
		Fixtures.deleteDatabase();
	}

	@Test
	public void notesListResponds() throws Exception {
		Response response = GET("/notes/list");
		assertIsOk(response);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void showNotesList() throws Exception {
		Note note1 = new Note("Note1 title\n note1 content").save();
		Note note2 = new Note("Note2 title\n note2 content").save();

		Response response = GET("/notes/list");

		List<Note> notes = (List<Note>) renderArgs("notes");
		assertThat(notes.get(0), is(note1));
		assertThat(notes.get(1), is(note2));

		String responseContent = response.out.toString("UTF-8");
		assertThat(responseContent, containsString(note1.getTitle()));
		assertThat(responseContent, containsString(note1.getContentWithoutTitle()));
		assertThat(responseContent, containsString(note2.getTitle()));
		assertThat(responseContent, containsString(note2.getContentWithoutTitle()));
	}

	@Test
	public void saveNewNote() throws Exception {
		POST("/notes/saveNote", ImmutableMap.of("note.content", "Note content"));

		List<Note> allNotes = Note.findAll();
		assertThat(allNotes.size(), is(1));
		assertThat(allNotes.get(0).content, is(equalTo("Note content")));
	}

	@Test
	public void rejectNoteWithoutContent() throws Exception {
		POST("/notes/saveNote", ImmutableMap.of("note.content", ""));
		assertThat(Note.count(), is(0L));
	}

	@Test
	public void rejectNoteWithTooShortContent() throws Exception {
		POST("/notes/saveNote", ImmutableMap.of("note.content", "test"));
		assertThat(Note.count(), is(0L));
	}
}
