package controllers;

import models.Note;
import play.mvc.Controller;

import java.util.List;

/**
 * Date: 17.01.2012 at 21:31
 *
 * @author Marcin Swierczynski
 */
public class Notes extends Controller {

	public static void list() {
		List<Note> notes = Note.findAll();
		render(notes);
	}

}
