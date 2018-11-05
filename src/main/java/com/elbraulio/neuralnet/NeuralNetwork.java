package com.elbraulio.neuralnet;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public interface NeuralNetwork {
    Number[] feed(Number ... input);
    void toTrain(DesiredOutput desired, Number ... input);
}
