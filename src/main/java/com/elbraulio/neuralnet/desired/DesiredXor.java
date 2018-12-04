package com.elbraulio.neuralnet.desired;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DesiredXor implements Desired {
    @Override
    public Number output(Number... input) {
        boolean x = input[0].intValue() == 1;
        boolean y = input[1].intValue() == 1;
        return (x || y) && (!x || !y) ? 1 : 0;
    }
}
