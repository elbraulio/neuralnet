package com.elbraulio.neuralnet;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class SigmoidDecisionTest {

    @Test
    public void intValue() {
        assertThat(
                new SigmoidDecision(1, 1).intValue(),
                is(1)
        );
        assertThat(
                new SigmoidDecision(-10, 1).intValue(),
                is(0)
        );
    }

    @Test
    public void longValue() {
        assertThat(
                new SigmoidDecision(1, 1).longValue(),
                is(1l)
        );
        assertThat(
                new SigmoidDecision(-10, 1).longValue(),
                is(0l)
        );
    }

    @Test
    public void floatValue() {
        assertThat(
                new SigmoidDecision(-1, 1).floatValue(),
                is(1f)
        );
        assertThat(
                new SigmoidDecision(-10, 1).floatValue(),
                is(0f)
        );
    }

    @Test
    public void doubleValue() {
        assertThat(
                new SigmoidDecision(1, 1).doubleValue(),
                greaterThan(0.11)
        );
    }
}