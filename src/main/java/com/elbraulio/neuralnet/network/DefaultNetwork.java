package com.elbraulio.neuralnet.network;

import com.elbraulio.neuralnet.desired.DesiredOutput;
import com.elbraulio.neuralnet.timeline.DefaultTimeLine;
import com.elbraulio.neuralnet.timeline.TimeLine;
import com.elbraulio.neuralnet.unit.NeuralUnit;
import com.elbraulio.neuralnet.utils.MatrixByLength;
import com.elbraulio.neuralnet.utils.NetworkBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class DefaultNetwork implements NeuralNetwork {

    private final NeuralUnit[] neuralUnits;
    private final TimeLine timeLine;
    private final double learningRate;
    private final int outputLength;
    private final int[] hiddenLength;

    public DefaultNetwork(
            double learningRate, int inputLength, int outputLength,
            int... hiddenLength
    ) {
        this.learningRate = learningRate;
        this.outputLength = outputLength;
        this.hiddenLength = hiddenLength;
        this.timeLine = new DefaultTimeLine(
                inputLength, outputLength, hiddenLength);
        this.neuralUnits = new NetworkBuilder(
                this.timeLine, outputLength, hiddenLength
        ).array();
    }

    @Override
    public Number[] feed(Number... input) {
        // feed first layout with input
        for (int i = 0; i < this.hiddenLength[0]; i++) {
            this.neuralUnits[i].feed(input);
        }
        // other layouts feed by previous layout
        int neuronIndex = this.hiddenLength[0];
        // for each layer
        for (int i = 1; i < this.hiddenLength.length; i++) {
            // for each neuron inside layer i
            for (int j = 0; j < this.hiddenLength[i]; j++) {
                // feed with previous layer's output
                this.neuralUnits[neuronIndex++].feed(
                        this.timeLine.output(i - 1)
                );
            }
        }
        // feed output layer
        for (int i = 0; i < this.outputLength; i++) {
            this.neuralUnits[neuronIndex++].feed(
                    this.timeLine.output(this.hiddenLength.length - 1)
            );
        }
        // return output layer
        return this.timeLine.output(this.hiddenLength.length);
    }

    @Override
    public void toTrain(DesiredOutput desiredOutput, Number... input) {
        // back-propagation
        Number[] output = this.feed(input);
        Number[] desired = desiredOutput.desired(input);
        if(desiredOutput.isDesired(input, output)){
            desired = output;
        }
        Number[][] deltas = new MatrixByLength(
                this.outputLength, this.hiddenLength
        ).matrix();
        // output error and deltas
        Number[] outputOutputLayer = this.timeLine.output(this.hiddenLength.length);
        for (int j = 0; j < outputOutputLayer.length; j++) {
            double error = desired[j].doubleValue() -
                    outputOutputLayer[j].doubleValue();
            double delta =
                    error * outputOutputLayer[j].doubleValue()
                            * (1d - outputOutputLayer[j].doubleValue());
            deltas[this.hiddenLength.length][j] = delta;
        }
        // last hidden layer neuron error and deltas
        for (int j = 0; j < this.hiddenLength[this.hiddenLength.length - 1]; j++) {
            double error = 0d;
            for (int k = 0; k < this.outputLength; k++) {
                Number[] nextLayerWeights =
                        this.timeLine.weight(this.hiddenLength.length, k);
                error += deltas[this.hiddenLength.length][k].doubleValue() * nextLayerWeights[j].doubleValue();
            }
            double delta =
                    error * this.timeLine.output(this.hiddenLength.length - 1)[j].doubleValue() *
                            (1 - this.timeLine.output(this.hiddenLength.length - 1)[j].doubleValue());
            deltas[this.hiddenLength.length - 1][j] = delta;
        }
        // for each hidden layer neuron
        for (int i = this.hiddenLength.length - 2; i >= 0; i--) {
            for (int j = 0; j < this.hiddenLength[i]; j++) {
                double error = 0d;
                for (int k = 0; k < this.hiddenLength[i + 1]; k++) {
                    Number[] nextLayerWeights = this.timeLine.weight(i + 1, k);
                    error += deltas[i + 1][k].doubleValue() * nextLayerWeights[j].doubleValue();
                }
                double delta =
                        error * this.timeLine.output(i)[j].doubleValue() *
                                (1 - this.timeLine.output(i)[j].doubleValue());
                deltas[i][j] = delta;
            }
        }
        // update bias and weights
        List<List<Number[]>> newEpochWeights = new ArrayList<>();
        Number[][] newEpochBias = new MatrixByLength(
                this.outputLength, this.hiddenLength
        ).matrix();
        // update weight and bias for first hidden layer
        newEpochWeights.add(new ArrayList<>(this.hiddenLength[0]));
        for (int i = 0; i < this.hiddenLength[0]; i++) {
            Number[] weights = this.timeLine.weight(0, i);
            Number[] newWeights = new Number[weights.length];
            for (int k = 0; k < weights.length; k++) {
                newWeights[k] =
                        weights[k].doubleValue() + (
                                this.learningRate *
                                        deltas[0][i].doubleValue() *
                                        input[k].doubleValue()
                        );
            }
            double bias = this.timeLine.bias(0, i).doubleValue() +
                    (this.learningRate * deltas[0][i].doubleValue());
            newEpochWeights.get(0).add(newWeights);
            newEpochBias[0][i] = bias;
        }
        // update weight and bias for hidden layer
        for (int j = 1; j < this.hiddenLength.length; j++) {
            newEpochWeights.add(new ArrayList<>(this.hiddenLength[j]));
            for (int i = 0; i < this.hiddenLength[j]; i++) {
                Number[] weights = this.timeLine.weight(j, i);
                Number[] newWeights = new Number[weights.length];
                for (int k = 0; k < weights.length; k++) {
                    newWeights[k] =
                            weights[k].doubleValue() + (
                                    this.learningRate *
                                            deltas[j][i].doubleValue() *
                                            this.timeLine.output(j - 1)[k]
                                                    .doubleValue()
                            );
                }
                double bias = this.timeLine.bias(j, i).doubleValue() +
                        (this.learningRate * deltas[j][i].doubleValue());
                newEpochWeights.get(j).add(newWeights);
                newEpochBias[j][i] = bias;
            }
        }
        // update weight and bias for output layer
        newEpochWeights.add(new ArrayList<>(this.outputLength));
        for (int j = 0; j < outputOutputLayer.length; j++) {
            Number[] weights = this.timeLine.weight(
                    this.hiddenLength.length, j
            );
            Number[] newWeights = new Number[weights.length];
            for (int i = 0; i < weights.length; i++) {
                newWeights[i] =
                        weights[i].doubleValue() + (
                                this.learningRate *
                                        deltas[this.hiddenLength.length][j].doubleValue() *
                                        this.timeLine.output(
                                                this.hiddenLength.length - 1
                                        )[i].doubleValue()
                        );
            }
            double bias = this.timeLine.bias(
                    this.hiddenLength.length, j
            ).doubleValue() +
                    (this.learningRate * deltas[this.hiddenLength.length][j].doubleValue());
            newEpochWeights.get(this.hiddenLength.length).add(newWeights);
            newEpochBias[this.hiddenLength.length][j] = bias;
        }
        this.timeLine.newEpoch(newEpochBias, newEpochWeights);
    }
}
