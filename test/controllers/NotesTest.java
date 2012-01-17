package controllers;

import org.junit.Test;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

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

}
