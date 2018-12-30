package com.elbraulio.neuralnet.utils;

import com.elbraulio.neuralnet.timeline.DefaultTimeLine;
import com.elbraulio.neuralnet.unit.NeuralUnit;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class NetworkBuilderTest {
    @Test
    public void notNullNeuronUbits(){
        NetworkBuilder net = new NetworkBuilder(
                new DefaultTimeLine(3,3,new int[]{5,4,3}),
                3, 5,4,3
        );
        for(NeuralUnit n : net.array()){
            MatcherAssert.assertThat(n, Matchers.notNullValue());
        }
    }
}