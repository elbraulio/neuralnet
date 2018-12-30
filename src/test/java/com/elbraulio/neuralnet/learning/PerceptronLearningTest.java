package com.elbraulio.neuralnet.learning;

import com.elbraulio.neuralnet.args.DefaultArgs;
import com.elbraulio.neuralnet.args.NeuralArgs;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class PerceptronLearningTest {
    @Test
    public void noLearningRate(){
        NeuralArgs newArgs = new PerceptronLearning(0).newArgs(
                0, new DefaultArgs(1d,1,1),1,1
        );
        assertThat(newArgs.bias(), is(1d));
        assertThat(newArgs.weights(), is(new Number[]{1d,1d}));
    }

    @Test
    public void learnFromCorrectOnes(){
        NeuralArgs newArgs = new PerceptronLearning(1d).newArgs(
                1, new DefaultArgs(1d,1d,1d),1,1
        );
        assertThat(newArgs.bias(), is(1d));
        assertThat(newArgs.weights(), is(new Number[]{1d,1d}));
    }
}