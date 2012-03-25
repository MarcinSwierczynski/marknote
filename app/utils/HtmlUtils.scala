package utils

/**
 * Date: 25.03.2012 at 17:06
 *
 * @author Marcin Swierczynski
 */

object HtmlUtils {

	def stripHtmlTags(content: String): String = content.replaceAll("""<(?!\/?a(?=>|\s.*>))\/?.*?>""", "")
	
}
