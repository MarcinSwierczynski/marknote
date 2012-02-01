package guice;

import com.google.inject.AbstractModule;
import services.markdown.ContentConverter;
import services.markdown.MarkdownConverter;

/**
 * Date: 01.02.2012 at 20:01
 *
 * @author Marcin Swierczynski
 */
public class ContentConverterModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ContentConverter.class).to(MarkdownConverter.class);
	}

}
