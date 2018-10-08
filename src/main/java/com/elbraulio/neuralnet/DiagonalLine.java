package com.elbraulio.neuralnet;

import java.util.Deque;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DiagonalLine implements Supervise {
    private final Number learningRate;
    private final Deque<NeuralArgs> args;
    private final Desired desired;

    public DiagonalLine(Number learningRate) {
        this(
                learningRate,
                new AsDeque<NeuralArgs>(
                        new DefaultArgs(
                                new Random().nextDouble() * 4 - 2,
                                new Random().nextDouble() * 4 - 2,
                                new Random().nextDouble() * 4 - 2
                        )
                ).deque(),
                new AboveBelow()
        );
    }

    public DiagonalLine(
            Number learningRate, Deque<NeuralArgs> args, Desired desired
    ) {

        this.learningRate = learningRate;
        this.args = args;
        this.desired = desired;
    }

    @Override
    public NeuralUnit perceptron() {
        Number[] input = new Number[]{
                new Random().nextDouble() * 240 - 120,
                new Random().nextDouble() * 240 - 120
        };
        NeuralArgs newArgs = new DefaultLearning(this.learningRate).newArgs(
                this.desired.output(input), this.args.getLast(), input
        );
        this.args.addLast(newArgs);
        return new Perceptron(newArgs);
    }
}
