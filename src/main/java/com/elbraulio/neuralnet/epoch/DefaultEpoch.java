package com.elbraulio.neuralnet.epoch;

import com.elbraulio.neuralnet.utils.MatrixLengthCopy;

import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class DefaultEpoch implements Epoch {

    private final Number[][] bias;
    private final List<List<Number[]>> weight;
    private final Number[][] output;

    public DefaultEpoch(Number[][] newEpochBias, List<List<Number[]>> newEpochWeights) {
        this.bias = newEpochBias;
        this.weight = newEpochWeights;
        this.output = new MatrixLengthCopy(newEpochBias).matrix();
    }

    /**
     * {@inheritDoc}
     *
     * @param output output to be stored.
     * @param layer layer where the neuron is positioned.
     * @param index neuron's position inside the layer.
     */
    @Override
    public void saveOutput(Number output, int layer, int index) {
        this.output[layer][index] = output.doubleValue();
    }

    @Override
    public Number bias(int layer, int index) {
        return this.bias[layer][index];
    }

    @Override
    public Number[] weight(int layer, int index) {
        return this.weight.get(layer).get(index);
    }

    @Override
    public Number[] output(int layer) {
        return this.output[layer];
    }
}
