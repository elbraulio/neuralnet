package com.elbraulio.neuralnet;


import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class PerceptronTest {

    @Test
    public void nandOperation11(){
        assertThat(
                new Perceptron(
                        3, -2, -2
                ).output(1, 1).intValue(),
                CoreMatchers.is(0)
        );
    }

    @Test
    public void nandOperation10(){
        assertThat(
                new Perceptron(
                        3, -2, -2
                ).output(1, 0).intValue(),
                CoreMatchers.is(1)
        );
    }

    @Test
    public void nandOperation01(){
        assertThat(
                new Perceptron(
                        3, -2, -2
                ).output(0, 1).intValue(),
                CoreMatchers.is(1)
        );
    }

    @Test
    public void nandOperation00(){
        assertThat(
                new Perceptron(
                        3, -2, -2
                ).output(0, 0).intValue(),
                CoreMatchers.is(1)
        );
    }

    @Test
    public void andOperation11(){
        assertThat(
                new Perceptron(
                        -1.5, 1, 1
                ).output(1, 1).intValue(),
                CoreMatchers.is(1)
        );
    }

    @Test
    public void andOperation10(){
        assertThat(
                new Perceptron(
                        -1.5, 1, 1
                ).output(1, 0).intValue(),
                CoreMatchers.is(0)
        );
    }

    @Test
    public void andOperation01(){
        assertThat(
                new Perceptron(
                        -1.5, 1, 1
                ).output(0, 1).intValue(),
                CoreMatchers.is(0)
        );
    }

    @Test
    public void andOperation00(){
        assertThat(
                new Perceptron(
                        -1.5, 1, 1
                ).output(0, 0).intValue(),
                CoreMatchers.is(0)
        );
    }

    @Test
    public void orOperation11(){
        assertThat(
                new Perceptron(
                        -0.5, 1, 1
                ).output(1, 1).intValue(),
                CoreMatchers.is(1)
        );
    }

    @Test
    public void orOperation10(){
        assertThat(
                new Perceptron(
                        -0.5, 1, 1
                ).output(1, 0).intValue(),
                CoreMatchers.is(1)
        );
    }

    @Test
    public void orOperation01(){
        assertThat(
                new Perceptron(
                        -0.5, 1, 1
                ).output(0, 1).intValue(),
                CoreMatchers.is(1)
        );
    }

    @Test
    public void orOperation00(){
        assertThat(
                new Perceptron(
                        -0.5, 1, 1
                ).output(0, 0).intValue(),
                CoreMatchers.is(0)
        );
    }
}