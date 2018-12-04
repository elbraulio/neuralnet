package com.elbraulio.neuralnet.unit;

import com.elbraulio.neuralnet.args.NeuralArgs;
import com.elbraulio.neuralnet.unit.NeuralUnit;
import com.elbraulio.neuralnet.utils.DefaultBiasOperation;
import com.elbraulio.neuralnet.utils.DotProduct;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class Perceptron implements NeuralUnit {
    private final Number bias;
    private final Number[] weights;

    public Perceptron(NeuralArgs args){
        this(args.bias(), args.weights());
    }

    public Perceptron(Number bias, Number... weights) {

        this.bias = bias;
        this.weights = weights;
    }

    @Override
    public Number feed(Number... inputs) {
        return new DefaultBiasOperation(
                new DotProduct(this.weights, inputs), this.bias
        );
    }
}
