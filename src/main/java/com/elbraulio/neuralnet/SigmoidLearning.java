package com.elbraulio.neuralnet;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class SigmoidLearning implements Learning {

    private final Number learningRate;

    public SigmoidLearning(Number learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public NeuralArgs newArgs(
            Number desired, NeuralArgs lastArgs, Number... input
    ) {
        Number output = new Sigmoid(lastArgs).feed(input);
        if (desired.intValue() != output.intValue()) {
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