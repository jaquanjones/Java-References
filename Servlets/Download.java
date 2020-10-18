package webtest.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Download")
public class Download extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Download() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        final String dirName = "/WEB-INF/files";
        String name = request.getParameter("name");
        // Get the path to the file and create a java.io.File object
        String path = getServletContext()
                .getRealPath(dirName + name);
        File file = new File(path);
        ServletContext context = getServletContext();

        String mimeType = context.getMimeType(path);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        // Set the response headers. File.length() returns the size of the file
        // as a long, which we need to convert to a String.
        //String contentType = request.getContentType();
        response.setContentType(mimeType);
        response.setContentLength((int) file.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());

        response.setHeader(headerKey, headerValue);

        // Binary files need to read/written in bytes.
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) > 0)
            out.write(buffer, 0, bytesRead);
        in.close();
    }

}
