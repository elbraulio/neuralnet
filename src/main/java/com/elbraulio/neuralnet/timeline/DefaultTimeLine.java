package com.elbraulio.neuralnet.timeline;

import com.elbraulio.neuralnet.epoch.Epoch;
import com.elbraulio.neuralnet.utils.EpochWithInitialValues;
import com.elbraulio.neuralnet.epoch.DefaultEpoch;

import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class DefaultTimeLine implements TimeLine {

    private Epoch currentE;

    public DefaultTimeLine(
            int inputLength,int outputLength, int[] hiddenLength
            ) {


        this.currentE =
                new EpochWithInitialValues(
                        inputLength, outputLength, hiddenLength
                ).epoch();

    }

    @Override
    public void saveOutput(Number output, int layer, int index) {
        this.currentE.saveOutput(output, layer, index);
    }

    @Override
    public Number bias(int layer, int index) {
        return this.currentE.bias(layer, index);
    }

    @Override
    public Number[] weight(int layer, int index) {
        return this.currentE.weight(layer, index);
    }

    @Override
    public Number[] output(int layer) {
        return this.currentE.output(layer);
    }

    @Override
    public void newEpoch(Number[][] newEpochBias, List<List<Number[]>> newEpochWeights) {
        this.currentE =
                new DefaultEpoch(newEpochBias, newEpochWeights)
        ;
    }
}
