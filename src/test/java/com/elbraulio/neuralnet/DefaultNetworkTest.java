package com.elbraulio.neuralnet;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DefaultNetworkTest {
    @Test
    public void noLearning() {
        final NeuralNetwork network = new DefaultNetwork(
                1, 2, 1, 2
        );
        final Number[] output1 = network.feed(1, 1);
        assertThat(
                output1[0].doubleValue(),
                is(network.feed(1, 1)[0].doubleValue())
        );
    }

    @Test
    public void noLearningLargeNetwork() {
        final NeuralNetwork network = new DefaultNetwork(
                1, 2, 1, 2, 3, 4
        );
        final Number[] output1 = network.feed(1, 1);
        assertThat(
                output1[0].doubleValue(),
                is(network.feed(1, 1)[0].doubleValue())
        );
    }

    @Test
    public void changing() {
        final NeuralNetwork network = new DefaultNetwork(
                1, 2, 1, 2, 3, 4
        );
        final Number[] output1 = network.feed(1, 1);
        network.toTrain(
                new DesiredOutput() {
                    @Override
                    public boolean isDesired(Number[] input, Number[] output) {
                        return false;
                    }

                    @Override
                    public Number[] desired(Number[] input) {
                        return new Number[]{1};
                    }
                }, 1, 1
        );
        assertThat(
                output1[0].doubleValue(),
                not(network.feed(1, 1)[0].doubleValue())
        );
    }

    @Test
    public void notChangingWhenIsCorrect() {
        final NeuralNetwork network = new DefaultNetwork(
                1, 2, 1, 2, 3, 4
        );
        final Number[] output1 = network.feed(1, 1);
        network.toTrain(
                new DesiredOutput() {
                    @Override
                    public boolean isDesired(Number[] input, Number[] output) {
                        return true;
                    }

                    @Override
                    public Number[] desired(Number[] input) {
                        return new Number[]{1};
                    }
                }, 1, 1
        );
        assertThat(
                output1[0].doubleValue(),
                is(network.feed(1, 1)[0].doubleValue())
        );
    }
}