package models;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.modules.guice.InjectSupport;
import services.markdown.ContentConverter;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.ArrayList;

/**
 * Date: 16.01.2012 at 21:28
 *
 * @author Marcin Swierczynski
 */
@InjectSupport
@Entity
public class Note extends Model {

	private static final String NEW_LINE_SEPARATOR = "\n";

	@Inject
	static ContentConverter markdownConverter;

	@Required
	@Lob
	@MinSize(5)
	public String content;

	public Note(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return getTitle();
	}

	public String getTitle() {
		return splitOnLines().get(0);
	}
	
	public String getContentWithoutTitle() {
		ArrayList<String> noteLines = splitOnLines();
		noteLines.remove(0);
		String contentWithoutTitle = Joiner.on(NEW_LINE_SEPARATOR).join(noteLines);
		return contentWithoutTitle.trim();
	}

	public String getConvertedContent() {
		return markdownConverter.convert(getContentWithoutTitle());
	}

	private ArrayList<String> splitOnLines() {
		return Lists.newArrayList(Splitter.on(NEW_LINE_SEPARATOR).split(content));
	}
}
