package com.myexperiments.mainappattachment.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Slf4j
@Path("/attachment")
public class AttachmentResource {

    @Path("/upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @Timed
    public Response uploadAttachment(@FormDataParam("file") final InputStream uploadedInputStream,
                                     @FormDataParam("file") final FormDataContentDisposition fileDetail,
                                     @FormDataParam("model") final String entityJson) {
        return Response.ok(new FileResponse(fileDetail.getFileName())).build();
    }

    @Getter
    @Setter
    private class FileResponse {
        private String name;
        private String url;

        public FileResponse (final String name) {
            this.name = name;
        }
    }

}
