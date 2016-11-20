package com.myexperiments.mainapp.models;

import lombok.Data;

import java.util.List;

/**
 * Created by kaustubh on 11/20/16.
 */
@Data
public class Calculation {

    String function;
    private List<String> input;
    private List<String> output;

    public Calculation(List<String> input, List<String> output, String function) {
        this.function = function;
        this.input = input;
        this.output = output;
    }
}
