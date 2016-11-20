package com.myexperiments.mainapp.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.LongParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Slf4j
public class UserResource {

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public String getUser(@PathParam("id") final LongParam id) {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: >>> " + id);
        return sb.toString();
    }

}
