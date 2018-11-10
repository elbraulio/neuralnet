package com.elbraulio.neuralnet.learning;

import com.elbraulio.neuralnet.args.DefaultArgs;
import com.elbraulio.neuralnet.args.NeuralArgs;
import com.elbraulio.neuralnet.desired.Check;
import com.elbraulio.neuralnet.desired.Equals;
import com.elbraulio.neuralnet.learning.Learning;
import com.elbraulio.neuralnet.unit.Sigmoid;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class SigmoidLearning implements Learning {

    private final Number learningRate;
    private final Check check;

    public SigmoidLearning(Number learningRate) {
        this(learningRate, new Equals());
    }

    public SigmoidLearning(Number learningRate, Check check) {
        this.learningRate = learningRate;
        this.check = check;
    }

    @Override
    public NeuralArgs newArgs(
            Number desired, NeuralArgs lastArgs, Number... input
    ) {
        Number output = new Sigmoid(lastArgs).feed(input);
        if (
                !this.check.isOnRange(
                        desired.doubleValue(), output.doubleValue()
                )
        ) {
            Number difference = desired.doubleValue() - output.doubleValue();
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