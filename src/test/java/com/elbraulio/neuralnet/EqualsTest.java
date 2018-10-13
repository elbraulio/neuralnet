package com.elbraulio.neuralnet;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class EqualsTest {
    @Test
    public void equals() {
        assertThat(new Equals().isOnRange(1, 1), is(true));
    }
}