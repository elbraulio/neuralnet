package com.elbraulio.neuralnet.unit;

import com.elbraulio.neuralnet.args.NeuralArgs;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public interface NeuralUnit {

    Number feed(Number... inputs);

    NeuralArgs args();
}
