package com.github.onsdigital.flumpet.api;

import com.github.davidcarboni.restolino.framework.Api;
import com.github.onsdigital.flumpet.helpers.*;
import com.github.onsdigital.flumpet.json.EncryptedPayload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.IOException;

/**
 * Add a message to the queue.
 */
@Api
public class Queue {

    @GET
    public String length(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //TODO: return the size of the queue

        return "TODO: return the size of the queue";
    }

    @POST
    public String add(HttpServletRequest request, HttpServletResponse response, EncryptedPayload payload) throws IOException, InterruptedException {

        System.out.println("queue >>>>>>>> request: " + Json.format(payload));

        System.out.println("queue >>>>>>>> add message: " + payload.getContents());
        Tx.sendMessage(payload.getContents());

        return "{ }";
    }
}
