package com.elbraulio.neuralnet.utils;


import com.elbraulio.neuralnet.utils.DotProduct;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DotProductTest {

    @Test
    public void intValue() {
        assertThat(
                new DotProduct(
                        new Number[]{1, 1, 1}, new Number[]{2, 2, 2}
                ).intValue(),
                is(6)
        );
    }

    @Test
    public void longValue() {
        assertThat(
                new DotProduct(
                        new Number[]{-9223372036L, -9223372036L, 9223372036L},
                        new Number[]{1L, 1L, 1L}
                ).longValue(),
                is(-9223372036L)
        );
    }

    @Test
    public void floatValue() {
        assertThat(
                new DotProduct(
                        new Number[]{-1.2f, -1.2f, 1.2f},
                        new Number[]{1f, 1f, 1f}
                ).floatValue(),
                is(-1.2f)
        );
    }

    @Test
    public void doubleValue() {
        assertThat(
                new DotProduct(
                        new Number[]{-1.234d, -1.234d, 1.234d},
                        new Number[]{1d, 1d, 1d}
                ).doubleValue(),
                is(-1.234d)
        );
    }
}