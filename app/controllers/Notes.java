package controllers;

import models.Note;
import play.data.validation.Valid;
import play.mvc.Controller;
import services.markdown.ContentConverter;

import javax.inject.Inject;
import java.util.List;

import static play.data.validation.Validation.hasErrors;

/**
 * Date: 17.01.2012 at 21:31
 *
 * @author Marcin Swierczynski
 */
public class Notes extends Controller {

	@Inject
	static ContentConverter markdownConverter;

	public static void list() {
		List<Note> notes = Note.findAll();
		render(notes);
	}

	public static void addNote() {
		render();
	}

	public static void saveNote(@Valid Note note) {
		if (hasErrors()) {
			render("Notes/addNote.html", note);
		}
		note.save();
		list();
	}

}
