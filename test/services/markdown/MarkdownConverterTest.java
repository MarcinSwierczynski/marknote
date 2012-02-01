package services.markdown;

import com.petebevin.markdown.MarkdownProcessor;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Date: 01.02.2012 at 21:43
 *
 * @author Marcin Swierczynski
 */
public class MarkdownConverterTest {

	@Test
	public void contentProcessed() throws Exception {
		MarkdownConverter markdownConverterSpy = spy(new MarkdownConverter());

		MarkdownProcessor markdownProcessorMock = mock(MarkdownProcessor.class);
		when(markdownConverterSpy.createMarkdownProcessor()).thenReturn(markdownProcessorMock);

		String content = "content to convert";
		markdownConverterSpy.convert(content);
		
		verify(markdownProcessorMock).markdown(content);
	}

}
