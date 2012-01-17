package models;

import org.junit.Test;
import play.test.UnitTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Date: 17.01.2012 at 21:52
 *
 * @author Marcin Swierczynski
 */
public class NoteTest extends UnitTest {

	@Test
	public void createAndRetrieveNote() throws Exception {
		new Note("Title\n content").save();
		Note note = Note.find("byContent", "Title\n content").first();

		assertThat(note, is(notNullValue()));
		assertThat(note.content, is("Title\n content"));
	}
}
