package com.elbraulio.neuralnet.unit;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class SigmoidTest {
    @Test
    public void nandOperation11() {
        assertThat(
                new Sigmoid(
                        3, -2, -2
                ).feed(1, 1).doubleValue(),
                lessThan(0.5)
        );
    }

    @Test
    public void nandOperation10() {
        assertThat(
                new Sigmoid(
                        3, -2, -2
                ).feed(1, 0).doubleValue(),
                greaterThan(0.5)
        );
    }

    @Test
    public void nandOperation01() {
        assertThat(
                new Sigmoid(
                        3, -2, -2
                ).feed(0, 1).doubleValue(),
                greaterThan(0.5)
        );
    }

    @Test
    public void nandOperation00() {
        assertThat(
                new Sigmoid(
                        3, -2, -2
                ).feed(0, 0).doubleValue(),
                greaterThan(0.5)
        );
    }
}