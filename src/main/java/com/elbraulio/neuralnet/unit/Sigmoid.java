package com.elbraulio.neuralnet.unit;

import com.elbraulio.neuralnet.utils.SigmoidDecision;
import com.elbraulio.neuralnet.args.DefaultArgs;
import com.elbraulio.neuralnet.args.NeuralArgs;
import com.elbraulio.neuralnet.utils.DotProduct;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class Sigmoid implements NeuralUnit {
    private final NeuralArgs args;

    public Sigmoid(Number bias, Number ... weights){
        this(new DefaultArgs(bias, weights));
    }

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
