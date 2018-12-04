package com.elbraulio.neuralnet.learning;

import com.elbraulio.neuralnet.args.DefaultArgs;
import com.elbraulio.neuralnet.args.NeuralArgs;
import com.elbraulio.neuralnet.learning.Learning;
import com.elbraulio.neuralnet.unit.Perceptron;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class PerceptronLearning implements Learning {

    private final Number learningRate;

    public PerceptronLearning(Number learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public NeuralArgs newArgs(
            Number desired, NeuralArgs lastArgs, Number... input
    ) {
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
            return new DefaultArgs(newBias, newWeight);
        } else {
            return lastArgs;
        }
    }
}
