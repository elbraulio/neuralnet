package com.elbraulio.neuralnet.desired;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class Equals implements Check {
    @Override
    public boolean isOnRange(Number desired, Number output) {
        return desired.doubleValue() == output.doubleValue();
    }
}
