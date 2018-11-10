package com.elbraulio.neuralnet.epoch;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public interface Epoch {
    /**
     * Save the output of a neuron in a given layer and index.
     *
     * @param output output to be stored.
     * @param layer  layer where the neuron is positioned.
     * @param index  neuron's position inside the layer.
     */
    void saveOutput(Number output, int layer, int index);

    Number bias(int layer, int index);

    Number[] weight(int layer, int index);

    Number[] output(int layer);
}
