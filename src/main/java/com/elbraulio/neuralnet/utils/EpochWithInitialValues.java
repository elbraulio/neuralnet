package com.elbraulio.neuralnet.utils;

import com.elbraulio.neuralnet.epoch.DefaultEpoch;
import com.elbraulio.neuralnet.epoch.Epoch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class EpochWithInitialValues {
    private final int outputLength;
    private final int[] hiddenLength;
    private int inputLength;

    public EpochWithInitialValues(int inputLength, int outputLength,
                                  int[] hiddenLength
    ) {
        this.inputLength = inputLength;
        this.outputLength = outputLength;
        this.hiddenLength = hiddenLength;
    }

    public Epoch epoch() {
        Number[][] bias = new MatrixByLength(this.outputLength,
                this.hiddenLength).matrix();
        for (int i = 0; i < this.hiddenLength.length; i++) {
            for (int j = 0; j < this.hiddenLength[i]; j++) {
                bias[i][j] = ThreadLocalRandom.current()
                        .nextDouble(-2d, 2d);
            }
        }
        for (int i = 0; i < this.outputLength; i++) {
            bias[this.hiddenLength.length][i] = ThreadLocalRandom.current()
                    .nextDouble(-2d, 2d);
        }
        List<List<Number[]>> weights =
                new ArrayList<>(this.hiddenLength.length + 1);
        weights.add(new ArrayList<>(this.hiddenLength[0]));
        for (int j = 0; j < this.hiddenLength[0]; j++) {
            Number[] newWeights = new Number[this.inputLength];
            for (int k = 0; k < newWeights.length; k++) {
                newWeights[k] = ThreadLocalRandom.current()
                        .nextDouble(-2d, 2d);
            }
            weights.get(0).add(newWeights);
        }
        for (int i = 1; i < this.hiddenLength.length; i++) {
            weights.add(new ArrayList<>(this.hiddenLength[i]));
            for (int j = 0; j < this.hiddenLength[i]; j++) {
                Number[] newWeights = new Number[this.hiddenLength[i - 1]];
                for (int k = 0; k < newWeights.length; k++) {
                    newWeights[k] = ThreadLocalRandom.current()
                            .nextDouble(-2d, 2d);
                }
                weights.get(i).add(newWeights);
            }
        }
        weights.add(new ArrayList<>(this.outputLength));
        for (int i = 0; i < this.outputLength; i++) {
            Number[] newWeights =
                    new Number[this.hiddenLength[this.hiddenLength.length-1]];
            for (int j = 0; j < newWeights.length; j++) {
                newWeights[j] = ThreadLocalRandom.current()
                        .nextDouble(-2d, 2d);
            }
            weights.get(this.hiddenLength.length).add(newWeights);
        }
        return new DefaultEpoch(bias, weights);
    }
}
