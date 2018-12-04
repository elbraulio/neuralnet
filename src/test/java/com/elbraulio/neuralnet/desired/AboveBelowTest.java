package com.elbraulio.neuralnet.desired;

import com.elbraulio.neuralnet.desired.AboveBelow;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class AboveBelowTest {
    @Test
    public void below() {
        assertThat(new AboveBelow().output(5, 0), is(0));
    }

    @Test
    public void above() {
        assertThat(new AboveBelow().output(0, 5), is(1));
    }
}