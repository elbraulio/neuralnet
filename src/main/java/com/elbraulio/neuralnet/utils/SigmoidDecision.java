package com.elbraulio.neuralnet.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class SigmoidDecision extends Number {
    private final Number dotProduct;
    private final Number bias;

    public SigmoidDecision(Number dotProduct, Number bias) {
        this.dotProduct = dotProduct;
        this.bias = bias;
    }

    @Override
    public int intValue() {
        return 1 / (
                1 + Math.exp(-dotProduct.doubleValue() - bias.doubleValue())
        ) >= 0.5 ? 1 : 0;
    }

    @Override
    public long longValue() {
        return 1 / (
                1 + Math.exp(-dotProduct.doubleValue() - bias.doubleValue())
        ) >= 0.5 ? 1 : 0;
    }

    @Override
    public float floatValue() {
        return 1 / (
                1 + Math.exp(-dotProduct.doubleValue() - bias.doubleValue())
        ) >= 0.5 ? 1 : 0;
    }

    @Override
    public double doubleValue() {
        return 1 / (
                1 + Math.exp(-dotProduct.doubleValue() - bias.doubleValue())
        );
    }
}
