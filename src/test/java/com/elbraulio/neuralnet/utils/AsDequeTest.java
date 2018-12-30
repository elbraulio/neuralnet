package com.elbraulio.neuralnet.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class AsDequeTest {
    @Test
    public void withInitialValues() {
        assertThat(
                new AsDeque<>("hola").deque().getLast(),
                is("hola")
        );
    }
}