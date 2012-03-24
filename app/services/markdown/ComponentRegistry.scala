package services.markdown

/**
 * Date: 18.03.2012 at 21:19
 *
 * @author Marcin Swierczynski
 */

object ComponentRegistry extends MarkdownConverterComponent {
	val markdownConverter = new MarkdownConverter
}