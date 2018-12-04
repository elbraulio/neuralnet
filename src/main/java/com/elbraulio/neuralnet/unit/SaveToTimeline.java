package com.elbraulio.neuralnet.unit;

import com.elbraulio.neuralnet.timeline.TimeLine;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class SaveToTimeline implements NeuralUnit {
    private final NeuralUnit unit;
    private final int layer;
    private final int index;
    private final TimeLine timeLine;

    public SaveToTimeline(
            NeuralUnit unit, int layer, int index, TimeLine timeLine
    ) {
        this.unit = unit;
        this.layer = layer;
        this.index = index;
        this.timeLine = timeLine;
    }

    @Override
    public Number feed(Number... inputs) {
        final Number output = this.unit.feed(inputs);
        this.timeLine.saveOutput(output, this.layer, this.index);
        return output;
    }
}
