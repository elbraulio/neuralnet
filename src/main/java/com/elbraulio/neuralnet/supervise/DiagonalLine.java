package com.elbraulio.neuralnet.supervise;

import com.elbraulio.neuralnet.args.DefaultArgs;
import com.elbraulio.neuralnet.args.NeuralArgs;
import com.elbraulio.neuralnet.desired.AboveBelow;
import com.elbraulio.neuralnet.desired.Desired;
import com.elbraulio.neuralnet.learning.PerceptronLearning;
import com.elbraulio.neuralnet.unit.NeuralUnit;
import com.elbraulio.neuralnet.unit.Perceptron;
import com.elbraulio.neuralnet.utils.AsDeque;

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
                                ThreadLocalRandom.current().nextDouble(-2d, 2d),
                                ThreadLocalRandom.current().nextDouble(-2d, 2d),
                                ThreadLocalRandom.current().nextDouble(-2d, 2d)
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
        NeuralArgs newArgs = new PerceptronLearning(this.learningRate).newArgs(
                this.desired.output(input), this.args.getLast(), input
        );
        this.args.addLast(newArgs);
        return new Perceptron(newArgs);
    }
}
