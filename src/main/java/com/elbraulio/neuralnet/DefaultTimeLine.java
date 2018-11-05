package com.elbraulio.neuralnet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class DefaultTimeLine implements TimeLine {

    private final List<Epoch> epoches;
    private int epoch;

    public DefaultTimeLine(
            int inputLength,int outputLength, int[] hiddenLength
            ) {
        this.epoches = new LinkedList<>();
        this.epoch = 0;

        this.epoches.add(
                new EpochWithInitialValues(
                        inputLength, outputLength, hiddenLength
                ).epoch()
        );
    }

    @Override
    public void saveOutput(Number output, int layer, int index) {
        this.epoches.get(this.epoch).saveOutput(output, layer, index);
    }

    @Override
    public Number bias(int layer, int index) {
        return this.epoches.get(this.epoch).bias(layer, index);
    }

    @Override
    public Number[] weight(int layer, int index) {
        return this.epoches.get(this.epoch).weight(layer, index);
    }

    @Override
    public Number[] output(int layer) {
        return this.epoches.get(this.epoch).output(layer);
    }

    @Override
    public void newEpoch(Number[][] newEpochBias, List<List<Number[]>> newEpochWeights) {
        this.epoches.add(
                new DefaultEpoch(newEpochBias, newEpochWeights)
        );
        this.epoch++;
    }
}
