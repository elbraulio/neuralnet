package com.elbraulio.neuralnet;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public interface Learning {
    NeuralArgs newArgs(Number desired, NeuralArgs lastArgs, Number... input);
}
