package com.elbraulio.neuralnet;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class XORTrainerTest {
    @Test
    public void noLearning() {
        final Supervise wisdom = new XORTrainer(0);
        final NeuralUnit initial = wisdom.perceptron();
        for (int i = 0; i < 100; i++) {
            wisdom.perceptron();
        }
        assertThat(
                wisdom.perceptron().feed(1, 1).doubleValue(),
                is(initial.feed(1, 1).doubleValue())
        );
    }
}