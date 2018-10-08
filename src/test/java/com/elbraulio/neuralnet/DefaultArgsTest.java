package com.elbraulio.neuralnet;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DefaultArgsTest {
    @Test
    public void brainlessObject() {
        final NeuralArgs args = new DefaultArgs(1, 0, 2, 3);
        assertThat(args.bias(), is(1));
        assertThat(args.weights(), is(new Number[]{0, 2, 3}));
    }
}