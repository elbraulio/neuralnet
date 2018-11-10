package com.elbraulio.neuralnet.utils;

import com.elbraulio.neuralnet.unit.NeuralUnit;
import com.elbraulio.neuralnet.unit.SaveToTimeline;
import com.elbraulio.neuralnet.unit.Sigmoid;
import com.elbraulio.neuralnet.timeline.TimeLine;
import com.elbraulio.neuralnet.args.FromTimeline;

import java.util.stream.IntStream;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class NetworkBuilder {

    private final int outputLength;
    private final int[] hiddenLength;
    private final TimeLine timeLine;

    public NetworkBuilder(
            TimeLine timeLine, int outputLength, int... hiddenLength
    ) {
        this.outputLength = outputLength;
        this.hiddenLength = hiddenLength;
        this.timeLine = timeLine;
    }

    public NeuralUnit[] array() {
        final NeuralUnit[] units = new NeuralUnit[
                IntStream.of(this.hiddenLength).sum() +
                        this.outputLength
                ];
        int neuronIndex = 0;
        for (int layer = 0; layer < this.hiddenLength.length; layer++) {
            for (int i = 0; i < this.hiddenLength[layer]; i++) {
                units[neuronIndex++] = new SaveToTimeline(
                        new Sigmoid(
                                new FromTimeline(
                                        this.timeLine,
                                        layer,
                                        i
                                )
                        ),
                        layer, i, this.timeLine
                );
            }
        }
        for (int i = 0; i < this.outputLength; i++) {
            units[neuronIndex] = new SaveToTimeline(
                    new Sigmoid(
                            new FromTimeline(
                                    this.timeLine,
                                    this.hiddenLength.length,
                                    i
                            )
                    ), this.hiddenLength.length, i, this.timeLine
            );
        }
        return units;
    }
}
