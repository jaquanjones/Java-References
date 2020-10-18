package webtest.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/Upload")
public class Upload extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        ArrayList<Uploads> uploads = new ArrayList<>();
        // GET INFO OF THE FILES
        final String dirName = getServletContext().getRealPath("/WEB-INF/files");

        // CREATE FILE OBJECT REPRESENTING FOLDER
        File directory = new File(dirName);

        // USE LISTFILES() TO GET ALL THE FILES IN THE FOLDER
        File[] files = directory.listFiles();

        // DETERMINE METHODS TO GET FILE NAME, LAST MODIFIED TIME, & SIZE
        String name;
        long date;
        long size;
        //int count = 0;

        for (File f : files) {
            //Uploads up = uploads.get(count);
            name = f.getName();
            date = f.lastModified();
            size = f.length();
            Uploads up = new Uploads(name, date, size);
            uploads.add(up);
            //list.add(uploads(name, date, size));
        }
        getServletContext().setAttribute("row", uploads);
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //ArrayList<Uploads> uploads = new ArrayList<>();
        //List<Uploads> uploads = (ArrayList<Uploads>) getServletContext().getAttribute("row");


        //getServletContext().setAttribute("row", uploads);

        // OPTION 1: LOOP PRINT METHOD FOR EACH OF ABOVE DETAILS
        // OPTION 2: STORE INFO AS MAP AND ITERATE THROUGH MAP FOR PRINT LINES

        ServletContext context = getServletContext();
        ArrayList<Uploads> uploads = (ArrayList<Uploads>) context.getAttribute("row");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html lang=\"en\"><head><title>CS3220 Lab 11</title></head><body>");
        out.println("<div style=\"margin-left: 40px;\"><form action=\"Upload\" method=\"post\" enctype=\"multipart/form-data\">");
        out.println("<input type=\"file\" name=\"file\"/><input type=\"submit\" name=\"upload\" value=\"Upload\"></form></div><br>");
        out.println("<div style=\"margin-left: 40px\"><table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px\">");
        out.println("<thead><tr>");
        out.println("<th scope=\"col\">Name</th><th scope=\"col\">Date Uploaded</th><th scope=\"col\">Size (Bytes)</th>");
        out.println("</tr></thead><tbody>");

        for (int i = 0; i < uploads.size(); i++) {
            Uploads upload = uploads.get(i);
            out.println("<tr><td><a href=\"Download?name=" + upload.getName() + "\">" + upload.getName() + "</a></td><td>" + upload.getDate()
                    + "</td><td style=\"text-align: right\">" + upload.getSize() + "</td></tr>");
        }

        out.println("</tbody></table></div></body></html>");

    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        ArrayList<Uploads> uploads = (ArrayList<Uploads>) context.getAttribute("row");

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig()
                .getServletContext();
        File repository = (File) servletContext
                .getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Count how many files are uploaded
        int count = 0;

        // The directory we want to save the uploaded files to.
        String fileDir = getServletContext().getRealPath("/WEB-INF/files");


        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                // If the item is not a form field - meaning it's an uploaded
                // file, we save it to the target dir
                if (!item.isFormField()) {
                    // item.getName() will return the full path of the uploaded
                    // file, e.g. "C:/My Documents/files/test.txt", but we only
                    // want the file name part, which is why we first create a
                    // File object, then use File.getName() to get the file
                    // name.
                    String fileName = (new File(item.getName())).getName();
                    File file = new File(fileDir, fileName);
                    item.write(file);
                    ++count;
                    uploads.add(new Uploads(fileName, file.lastModified(), file.length()));
                }
            }

        } catch (Exception e) {
            throw new IOException(e);
        }

        response.sendRedirect("Upload");
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();

    }


}


