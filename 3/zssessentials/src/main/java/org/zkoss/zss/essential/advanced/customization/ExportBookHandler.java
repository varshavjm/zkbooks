package org.zkoss.zss.essential.advanced.customization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.zkoss.util.media.AMedia;
import org.zkoss.zss.api.Exporter;
import org.zkoss.zss.api.Exporters;
import org.zkoss.zss.api.model.Book;
import org.zkoss.zss.api.model.Sheet;
import org.zkoss.zss.ui.UserActionContext;
import org.zkoss.zss.ui.UserActionHandler;
import org.zkoss.zul.Filedownload;

/**
 * Export current book as a PDF file to download.
 * @author Hawk
 *
 */
public class ExportBookHandler implements UserActionHandler {

	private Exporter exporter= Exporters.getExporter("pdf");
	
	@Override
	public boolean isEnabled(Book book, Sheet sheet) {
		return book!=null;
	}

	@Override
	public boolean process(UserActionContext context) {
		File file;
		try {
			file = saveBookAsTempFile(context.getBook());
			String bookName = context.getBook().getBookName();
			String fileName = bookName.substring(0, bookName.indexOf('.'));
			Filedownload.save(new AMedia(fileName, null, "application/pdf", file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public File saveBookAsTempFile(Book book) throws IOException{
		
		File f = File.createTempFile("temp_" + book.getBookName(), ".pdf");
		f.deleteOnExit();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			exporter.export(book, fos);
		}finally{
			if(fos!=null){
				fos.close();
			}
		}
		return f;
	}

}
