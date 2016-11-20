package com.myexperiments.mainappattachment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myexperiments.mainappattachment.config.MainappattachmentConfiguration;
import com.myexperiments.mainappattachment.resources.AttachmentResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@Slf4j
public class MainappattachmentService extends Application<MainappattachmentConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainappattachmentService().run(args);
    }

    private final HibernateBundle<MainappattachmentConfiguration> hibernateBundle = new HibernateBundle<MainappattachmentConfiguration>(

            Void.class
        ) {
        @Override
        public DataSourceFactory getDataSourceFactory(MainappattachmentConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "mainappattachment";
    }

    @Override
    public void initialize(Bootstrap<MainappattachmentConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/app/", "/", "index.html"));
        bootstrap.addBundle(hibernateBundle);
        ObjectMapper mapper = bootstrap.getObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(MainappattachmentConfiguration configuration,
                    Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/mainappattachment/*");


        environment.jersey().register(new AttachmentResource());
        environment.jersey().register(new MultiPartFeature());
    }
}
