package com.elbraulio.neuralnet.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class MatrixByLength {
    private final int outputLength;
    private final int[] hiddenLength;

    public MatrixByLength(int outputLength, int[] hiddenLength) {
        this.outputLength = outputLength;
        this.hiddenLength = hiddenLength;
    }

    public Number[][] matrix() {
        Number[][] matrix = new Number[this.hiddenLength.length + 1][];
        for (int i = 0; i < this.hiddenLength.length; i++) {
            matrix[i] = new Number[this.hiddenLength[i]];
        }
        matrix[this.hiddenLength.length] = new Number[this.outputLength];
        return matrix;
    }
}
