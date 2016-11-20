package com.myexperiments.mainapp.resources;

import com.codahale.metrics.annotation.Timed;
import com.myexperiments.mainapp.models.Calculation;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaustubh on 11/20/16.
 */
@Path("/calculation")
@Slf4j
public class CalculationResource {

    private static final String PATTERN = "^-?+\\d+\\.?+\\d*$";

    @Path("/power")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @Timed
    public Calculation pow(@QueryParam("base") final String base, @QueryParam("exponent") String exponent) {
        List<String> input = new ArrayList<>();
        input.add(base);
        input.add(exponent);
        List<String> output = new ArrayList();
        String powValue = "";
        if (base != null && exponent != null && base.matches(PATTERN) && exponent.matches(PATTERN)) {
            powValue = String.valueOf(Math.pow(Double.valueOf(base), Double.valueOf(exponent)));
        } else {
            powValue = "Base or/and Exponent is/are not set to numeric value.";
        }
        output.add(powValue);
        return new Calculation(input, output, "power");
    }

    @Path("/sqrt/{value}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @Timed
    public Calculation sqrt(@PathParam("value") String aValue) {
        List<String> input = new ArrayList();
        input.add(aValue);
        List<String> output = new ArrayList();
        String sqrtValue = "";
        if (aValue != null && aValue.matches(PATTERN)) {
            sqrtValue = String.valueOf(Math.sqrt(Double.valueOf(aValue)));
        } else {
            sqrtValue = "Input value is not set to numeric value.";
        }
        output.add(sqrtValue);
        return new Calculation(input, output, "sqrt");
    }
}
