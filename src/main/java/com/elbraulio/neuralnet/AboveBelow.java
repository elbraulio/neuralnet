package com.elbraulio.neuralnet;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class AboveBelow implements Desired {
    @Override
    public Number output(Number... input) {
        return input[1].doubleValue() - input[0].doubleValue() <= 0 ? 0 : 1;
    }
}
