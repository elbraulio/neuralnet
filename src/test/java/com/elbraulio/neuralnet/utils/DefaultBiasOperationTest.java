package com.elbraulio.neuralnet.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DefaultBiasOperationTest {

    @Test
    public void intValue() {
        assertThat(
                new DefaultBiasOperation(3, -1.5).intValue(),
                is(1)
        );
        assertThat(
                new DefaultBiasOperation(-3, -1.5).intValue(),
                is(0)
        );
    }

    @Test
    public void longValue() {
        assertThat(
                new DefaultBiasOperation(3L, -1.5).longValue(),
                is(1L)
        );
        assertThat(
                new DefaultBiasOperation(-3L, -1.5).longValue(),
                is(0L)
        );
    }

    @Test
    public void floatValue() {
        assertThat(
                new DefaultBiasOperation(3f, -1.5f).floatValue(),
                is(1f)
        );
        assertThat(
                new DefaultBiasOperation(-3f, -1.5f).floatValue(),
                is(0f)
        );
    }

    @Test
    public void doubleValue() {
        assertThat(
                new DefaultBiasOperation(3.3d, -3.31d).doubleValue(),
                is(0d)
        );
        assertThat(
                new DefaultBiasOperation(3.4d, -3.3d).doubleValue(),
                is(0d)
        );
        assertThat(
                new DefaultBiasOperation(4d, -3.3d).doubleValue(),
                is(1d)
        );
    }
}