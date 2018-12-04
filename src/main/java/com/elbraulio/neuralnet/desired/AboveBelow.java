package com.elbraulio.neuralnet.desired;

import com.elbraulio.neuralnet.desired.Desired;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class AboveBelow implements Desired {
    @Override
    public Number output(Number... input) {
        return input[1].intValue() - input[0].intValue() <= 0 ? 0 : 1;
    }
}
