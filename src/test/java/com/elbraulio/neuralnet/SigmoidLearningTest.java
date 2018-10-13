package com.elbraulio.neuralnet;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class SigmoidLearningTest {
    @Test
    public void noLearningRate() {
        NeuralArgs newArgs = new SigmoidLearning(
                0,
                (desired, output) -> true
                ).newArgs(
                0, new DefaultArgs(1d, 1d, 1d), 1, 1
        );
        assertThat(newArgs.bias(), is(1d));
        assertThat(newArgs.weights(), is(new Number[]{1d, 1d}));
    }

    @Test
    public void learnFromCorrectOnes() {
        NeuralArgs newArgs = new SigmoidLearning(1d).newArgs(
                1, new DefaultArgs(1d, 1d, 1d), 1, 1
        );
        assertThat(newArgs.bias().doubleValue(), greaterThan(1d));
        assertThat(
                newArgs.weights(),
                is(new Number[]{1.0474258731775667, 1.0474258731775667}));
    }
}