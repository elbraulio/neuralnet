package com.elbraulio.neuralnet.supervise;

import com.elbraulio.neuralnet.learning.PerceptronLearning;
import com.elbraulio.neuralnet.args.DefaultArgs;
import com.elbraulio.neuralnet.args.NeuralArgs;
import com.elbraulio.neuralnet.desired.Desired;
import com.elbraulio.neuralnet.desired.DesiredXor;
import com.elbraulio.neuralnet.unit.NeuralUnit;
import com.elbraulio.neuralnet.unit.Sigmoid;
import com.elbraulio.neuralnet.utils.AsDeque;

import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class XORTrainer implements Supervise {
    private final double learningRate;
    private final Desired desired;
    private final Deque<NeuralArgs> args;

    public XORTrainer(double learningRate) {
        this.learningRate = learningRate;
        this.args = new AsDeque<NeuralArgs>(
                new DefaultArgs(
                        ThreadLocalRandom.current().nextDouble(-2d, 2d),
                        ThreadLocalRandom.current().nextDouble(-2d, 2d),
                        ThreadLocalRandom.current().nextDouble(-2d, 2d)
                )
        ).deque();
        this.desired = new DesiredXor();
    }

    @Override
    public NeuralUnit perceptron() {
        int x = ThreadLocalRandom.current().nextDouble() >= 0.5 ? 1 : 0;
        int y = ThreadLocalRandom.current().nextDouble() >= 0.5 ? 1 : 0;
        NeuralArgs newArgs = new PerceptronLearning(this.learningRate).newArgs(
                this.desired.output(x, y),
                this.args.getLast(), x, y
        );
        this.args.addLast(newArgs);
        return new Sigmoid(newArgs);
    }
}
