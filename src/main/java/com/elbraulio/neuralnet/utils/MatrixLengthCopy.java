package com.elbraulio.neuralnet.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class MatrixLengthCopy {
    private final Number[][] toCopy;

    public MatrixLengthCopy(Number[][] toCopy) {
        this.toCopy = toCopy;
    }

    public Number[][] matrix() {
        Number[][] copy = new Number[this.toCopy.length][];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = new Number[this.toCopy[i].length];
            for (int j = 0; j < copy[i].length; j++) {
                copy[i][j] = 0d;
            }
        }
        return copy;
    }
}
