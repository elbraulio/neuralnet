package com.elbraulio.neuralnet;

import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DiagonalLine implements Supervise {
    private final Number learningRate;
    private final Deque<NeuralArgs> args;

    public DiagonalLine(Number learningRate) {
        this.learningRate = learningRate;
        this.args = new AsDeque<NeuralArgs>(
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
        NeuralArgs newArgs = new DefaultLearning(this.learningRate)
                .newArgs(
                        input[1].doubleValue() -
                                input[0].doubleValue() <= 0 ? 0 : 1,
                        this.args.getLast(),
                        input
                );
        this.args.addLast(newArgs);
        return new Perceptron(newArgs);
    }
}
