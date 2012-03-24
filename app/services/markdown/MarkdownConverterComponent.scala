package services.markdown

import com.petebevin.markdown.MarkdownProcessor

/**
 * Date: 18.03.2012 at 20:10
 *
 * @author Marcin Swierczynski
 */

trait MarkdownConverterComponent {

	val markdownConverter: MarkdownConverter

	class MarkdownConverter {
		def convert(content: String): String = createMarkdownProcessor().markdown(content)

		def createMarkdownProcessor(): MarkdownProcessor = {
			new MarkdownProcessor()
		}
	}

}
