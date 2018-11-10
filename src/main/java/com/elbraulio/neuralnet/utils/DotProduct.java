package com.elbraulio.neuralnet.utils;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DotProduct extends Number {
    private final Number[] vect1;
    private final Number[] vect2;

    public DotProduct(Number[] vect1, Number[] vect2) {
        this.vect1 = vect1;
        this.vect2 = vect2;
    }

    @Override
    public int intValue() {
        int sum = 0;
        for (int i = 0; i < this.vect1.length; i++) {
            sum += this.vect1[i].intValue() * this.vect2[i].intValue();
        }
        return sum;
    }

    @Override
    public long longValue() {
        long sum = 0;
        for (int i = 0; i < this.vect1.length; i++) {
            sum += this.vect1[i].longValue() * this.vect2[i].longValue();
        }
        return sum;
    }

    @Override
    public float floatValue() {
        float sum = 0;
        for (int i = 0; i < this.vect1.length; i++) {
            sum += this.vect1[i].floatValue() * this.vect2[i].floatValue();
        }
        return sum;
    }

    @Override
    public double doubleValue() {
        double sum = 0;
        for (int i = 0; i < this.vect1.length; i++) {
            sum += this.vect1[i].doubleValue() * this.vect2[i].doubleValue();
        }
        return sum;
    }
}
