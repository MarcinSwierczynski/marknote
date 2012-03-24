package services.markdown

import org.specs2.mutable.Specification
import org.specs2.mock._
import com.petebevin.markdown.MarkdownProcessor

/**
 * Date: 24.03.2012 at 14:11
 *
 * @author Marcin Swierczynski
 */

class MarkdownConverterTest extends Specification with Mockito {

	"calls markdown processor" in {
		val markdownConverterComponent = new MarkdownConverterComponent {
			val markdownConverter: MarkdownConverter = spy(new MarkdownConverter)
		}

		val markdownProcessorMock: MarkdownProcessor = mock[MarkdownProcessor]
		markdownConverterComponent.markdownConverter.createMarkdownProcessor() returns markdownProcessorMock

		val content = "Content to convert"
		markdownConverterComponent.markdownConverter.convert(content)

		there was one(markdownProcessorMock).markdown(content)
	}

}
