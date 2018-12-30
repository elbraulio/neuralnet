package com.elbraulio.neuralnet.desired;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DesiredXorTest {
    @Test
    public void case00() {
        assertThat(new DesiredXor().output(0, 0).intValue(), is(0));
    }

    @Test
    public void case01() {
        assertThat(new DesiredXor().output(0, 1).intValue(), is(1));
    }

    @Test
    public void case10() {
        assertThat(new DesiredXor().output(1, 0).intValue(), is(1));
    }

    @Test
    public void case11() {
        assertThat(new DesiredXor().output(1, 1).intValue(), is(0));
    }
}