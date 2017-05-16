package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadHandler extends HttpServlet {
	private String UPLOAD_DIRECTORY = this.getClass().getClassLoader().getResource("").getPath();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] tmpDirArray = UPLOAD_DIRECTORY.split("/");
		StringBuilder rootDir = new StringBuilder();
		for (int a = 0; a < tmpDirArray.length - 2; a++) {
			rootDir.append(tmpDirArray[a] + "/");
		}

		UPLOAD_DIRECTORY = rootDir.toString() + "/assets/images";

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				StringBuilder sb = new StringBuilder();
				sb.append("{");
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
						sb.append("\"location\":\"/assets/images/"+name+"\"");
					}
				}
				sb.append("}");

				response.getWriter().append(sb.toString());
			} catch (Exception ex) {
				response.getWriter().append("Dosya Yükleme Başarısız : " + ex);
			}

		} else {
			response.getWriter().append("Maalesef Bu Servlet yalnızca dosya yükleme isteğini karşılamaktadır.");
		}
	}

}