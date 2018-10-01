package com.elbraulio.neuralnet;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class Perceptron implements NeuralUnit {
    private final Number bias;
    private final Number[] weights;

    public Perceptron(Number bias, Number... weights){

        this.bias = bias;
        this.weights = weights;
    }

    @Override
    public Number output(Number... inputs) {
        return new DefaultBiasOperation(
                new DotProduct(this.weights, inputs), this.bias
        );
    }
}
