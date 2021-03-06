package com.github.onsdigital.flumpet.api;

import com.github.davidcarboni.restolino.framework.Api;
import com.github.onsdigital.flumpet.storage.FtpPublisher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import java.io.IOException;

@Api
public class Get {

    private FtpPublisher ftp = new FtpPublisher();

    @GET
    public String get(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String filename = request.getParameter("filename");
        System.out.println("ftp >>>>>>>> get filename: " + filename);

        byte[] contents = ftp.get(filename);
        System.out.println("ftp >>>>>>>> get filename: " + filename + " contents length: " + contents.length);

        return new String(contents);
    }
}
