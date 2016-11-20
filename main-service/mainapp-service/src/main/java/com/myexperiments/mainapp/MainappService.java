package com.myexperiments.mainapp;

import com.myexperiments.mainapp.config.*;


import com.myexperiments.mainapp.resources.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class MainappService extends Application<MainappConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainappService().run(args);
    }

    private final HibernateBundle<MainappConfiguration> hibernateBundle = new HibernateBundle<MainappConfiguration>(

            Void.class
        ) {
        @Override
        public DataSourceFactory getDataSourceFactory(MainappConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "mainapp";
    }

    @Override
    public void initialize(Bootstrap<MainappConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/app/", "/", "index.html"));
        bootstrap.addBundle(hibernateBundle);
        ObjectMapper mapper = bootstrap.getObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(MainappConfiguration configuration,
                    Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/mainapp/*");


        environment.jersey().register(new UserResource());
        environment.jersey().register(new CalculationResource());

    }
}
