package com.elbraulio.neuralnet;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DotProduct extends Number {
    private final Number[] vector1;
    private final Number[] vector2;

    public DotProduct(Number[] vector1, Number[] vector2) {
        this.vector1 = vector1;
        this.vector2 = vector2;
    }

    @Override
    public int intValue() {
        int sum = 0;
        for (int i = 0; i < vector1.length; i++) {
            sum += vector1[i].intValue() * vector2[i].intValue();
        }
        return sum;
    }

    @Override
    public long longValue() {
        long sum = 0;
        for (int i = 0; i < vector1.length; i++) {
            sum += vector1[i].longValue() * vector2[i].longValue();
        }
        return sum;
    }

    @Override
    public float floatValue() {
        float sum = 0;
        for (int i = 0; i < vector1.length; i++) {
            sum += vector1[i].floatValue() * vector2[i].floatValue();
        }
        return sum;
    }

    @Override
    public double doubleValue() {
        double sum = 0;
        for (int i = 0; i < vector1.length; i++) {
            sum += vector1[i].doubleValue() * vector2[i].doubleValue();
        }
        return sum;
    }
}
