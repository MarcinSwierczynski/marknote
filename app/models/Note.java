package models;

import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Date: 16.01.2012 at 21:28
 *
 * @author Marcin Swierczynski
 */
@Entity
public class Note extends Model {

	@Required
	@Lob
	@MinSize(5)
	public String content;

	public Note(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return content;
	}
}
