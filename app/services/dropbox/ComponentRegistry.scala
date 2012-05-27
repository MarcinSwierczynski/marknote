package services.dropbox

/**
 * Date: 27.05.2012 at 13:17
 *
 * @author Marcin Swierczynski
 */

object ComponentRegistry extends DropboxComponent {
	val dropboxService = new DropboxService
}
