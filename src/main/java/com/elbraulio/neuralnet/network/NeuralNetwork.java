package com.elbraulio.neuralnet.network;

import com.elbraulio.neuralnet.desired.DesiredOutput;
import com.elbraulio.neuralnet.unit.NeuralUnit;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public interface NeuralNetwork {
    NeuralUnit[] neurons();

    Number[] feed(Number ... input);
    void toTrain(DesiredOutput desired, Number ... input);
}
