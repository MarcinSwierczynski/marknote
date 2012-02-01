package services.markdown;

import com.petebevin.markdown.MarkdownProcessor;

/**
 * Date: 01.02.2012 at 20:00
 *
 * @author Marcin Swierczynski
 */
public class MarkdownConverter implements ContentConverter {

	public String convert(String content) {
		// TODO handle exceptions
		return createMarkdownProcessor().markdown(content);
	}

	public MarkdownProcessor createMarkdownProcessor() {
		return new MarkdownProcessor();
	}
}
