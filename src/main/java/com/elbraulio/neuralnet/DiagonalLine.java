package com.elbraulio.neuralnet;

import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DiagonalLine implements Supervise {
    private final Number learningRate;
    private final Deque<DefaultArgs> args;

    public DiagonalLine(Number learningRate) {
        this.learningRate = learningRate;
        this.args = new AsDeque<>(
                new DefaultArgs(
                        ThreadLocalRandom.current()
                                .nextDouble(-2d, 2d),
                        ThreadLocalRandom.current()
                                .nextDouble(-2d, 2d),
                        ThreadLocalRandom.current()
                                .nextDouble(-2d, 2d)
                )
        ).deque();
    }

    @Override
    public NeuralUnit perceptron() {
        Number[] input = new Number[]{
                ThreadLocalRandom.current().nextInt(-120, 120),
                ThreadLocalRandom.current().nextInt(-120, 120)
        };
        Number desired =
                input[1].doubleValue() - input[0].doubleValue() <= 0 ? 0 : 1;
        DefaultArgs lastArgs = this.args.getLast();
        Number output = new Perceptron(lastArgs).feed(input);
        if (desired.intValue() != output.intValue()) {
            Number difference = desired.intValue() - output.intValue();
            Number[] newWeight = new Number[lastArgs.weights().length];
            for (int i = 0; i < newWeight.length; i++) {
                newWeight[i] = lastArgs.weights()[i].doubleValue() +
                        (
                                this.learningRate.doubleValue() *
                                        input[i].doubleValue() *
                                        difference.doubleValue()
                        );
            }
            Number newBias = lastArgs.bias().doubleValue() +
                    (
                            this.learningRate.doubleValue() *
                                    difference.doubleValue()
                    );
            this.args.addLast(new DefaultArgs(newBias, newWeight));
            return new Perceptron(newBias, newWeight);
        } else {
            this.args.addLast(lastArgs);
            return new Perceptron(lastArgs);
        }
    }
}
