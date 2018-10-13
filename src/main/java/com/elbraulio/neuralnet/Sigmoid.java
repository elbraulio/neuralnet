package com.elbraulio.neuralnet;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class Sigmoid implements NeuralUnit {
    private final NeuralArgs args;

    public Sigmoid(NeuralArgs args) {
        this.args = args;
    }

    @Override
    public Number feed(Number... inputs) {
        return new SigmoidDecision(
                new DotProduct(this.args.weights(), inputs), this.args.bias()
        );
    }
}
