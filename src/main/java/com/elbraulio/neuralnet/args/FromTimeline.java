package com.elbraulio.neuralnet.args;

import com.elbraulio.neuralnet.timeline.TimeLine;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class FromTimeline implements NeuralArgs {
    private final TimeLine timeLine;
    private final int layer;
    private final int index;

    public FromTimeline(TimeLine timeLine, int layer, int index) {
        this.timeLine = timeLine;
        this.layer = layer;
        this.index = index;
    }

    @Override
    public Number bias() {
        return this.timeLine.bias(this.layer, this.index);
    }

    @Override
    public Number[] weights() {
        return this.timeLine.weight(this.layer, this.index);
    }
}
