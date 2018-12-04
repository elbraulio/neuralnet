package com.elbraulio.neuralnet.network;

import com.elbraulio.neuralnet.desired.DesiredOutput;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public interface NeuralNetwork {
    Number[] feed(Number ... input);
    void toTrain(DesiredOutput desired, Number ... input);
}
