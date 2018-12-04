package com.elbraulio.neuralnet.timeline;

import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public interface TimeLine {
    void saveOutput(Number output, int layer, int index);

    Number bias(int layer, int index);

    Number[] weight(int layer, int index);

    Number[] output(int layer);

    void newEpoch(Number[][] newEpochBias, List<List<Number[]>> newEpochWeights);
}
