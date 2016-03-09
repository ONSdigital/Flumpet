package com.github.onsdigital.flumpet.api;

import com.github.davidcarboni.restolino.framework.Api;
import com.github.onsdigital.flumpet.storage.FtpPublisher;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import java.io.IOException;

@Api
public class Stream {

    private FtpPublisher ftp = new FtpPublisher();

    @GET
    public String get(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String contentType = "text/plain";

        String filename = request.getParameter("filename");
        System.out.println("ftp >>>>>>>> stream filename: " + filename);

        String contents = ftp.get(filename);
        System.out.println("ftp >>>>>>>> stream filename: " + filename + " contents: " + contents);

        if (filename.endsWith(".jpg")) {
            contentType = "image/jpeg";
        }

        System.out.println("ftp >>>>>>>> stream contentType: " + contentType);
        response.setContentType(contentType);

        ServletOutputStream out = response.getOutputStream();
        out.write(contents.getBytes());

        return null;
    }
}
