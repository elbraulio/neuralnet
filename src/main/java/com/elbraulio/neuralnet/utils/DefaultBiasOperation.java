package com.elbraulio.neuralnet.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DefaultBiasOperation extends Number {
    private final Number dotProduct;
    private final Number bias;

    public DefaultBiasOperation(Number dotProduct, Number bias) {
        this.dotProduct = dotProduct;
        this.bias = bias;
    }

    @Override
    public int intValue() {
        return this.dotProduct.intValue() + this.bias.doubleValue() > 0 ? 1 : 0;
    }

    @Override
    public long longValue() {
        return this.dotProduct.intValue() + this.bias.doubleValue() > 0 ? 1 : 0;
    }

    @Override
    public float floatValue() {
        return this.dotProduct.intValue() + this.bias.doubleValue() > 0 ? 1 : 0;
    }

    @Override
    public double doubleValue() {
        return this.dotProduct.intValue() + this.bias.doubleValue() > 0 ? 1 : 0;
    }
}
