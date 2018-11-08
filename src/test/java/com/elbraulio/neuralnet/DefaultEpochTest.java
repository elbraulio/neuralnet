package com.elbraulio.neuralnet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DefaultEpochTest {
    @Test
    public void initialization(){
        final Number[][] bias = new Number[][]{
                {1,2,3},
                {4,5,6}
        };
        final List<List<Number[]>> weights = new ArrayList<>(2);
        final List<Number[]> layer1 = new ArrayList<>(3);
        layer1.add(new Number[]{7,8,9,0});
        layer1.add(new Number[]{1,2,3,4});
        layer1.add(new Number[]{5,6,7,8});
        final List<Number[]> layer2 = new ArrayList<>(3);
        layer2.add(new Number[]{9,8,7});
        layer2.add(new Number[]{6,5,4});
        layer2.add(new Number[]{3,2,1});
        weights.add(layer1);
        weights.add(layer2);
        final Epoch epoch = new DefaultEpoch(bias, weights);
        assertThat(epoch.bias(0, 0), is(1));
        assertThat(epoch.output(0)[0], is(0d));
        epoch.saveOutput(33, 0, 0);
        assertThat(epoch.output(0)[0], is(33d));
        assertThat(epoch.weight(1, 1)[1], is(5));
    }
}