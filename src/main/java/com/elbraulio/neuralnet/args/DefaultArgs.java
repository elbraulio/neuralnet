package com.elbraulio.neuralnet.args;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DefaultArgs implements NeuralArgs {
    private final Number bias;
    private final Number[] weights;

    public DefaultArgs(Number bias, Number ... weights) {
        this.bias = bias;
        this.weights = weights;
    }

    @Override
    public Number bias() {
        return this.bias;
    }

    @Override
    public Number[] weights() {
        return this.weights;
    }
}
