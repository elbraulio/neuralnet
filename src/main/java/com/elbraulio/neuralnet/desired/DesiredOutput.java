package com.elbraulio.neuralnet.desired;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public interface DesiredOutput {
    boolean isDesired(Number[] input, Number[] output);

    Number[] desired(Number[] input);

}
