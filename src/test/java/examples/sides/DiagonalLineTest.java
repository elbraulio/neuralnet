package examples.sides;

import com.elbraulio.neuralnet.DiagonalLine;
import com.elbraulio.neuralnet.Supervise;
import com.elbraulio.neuralnet.NeuralUnit;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class DiagonalLineTest {
    @Test
    public void noLearning(){
        final Supervise wisdom = new DiagonalLine(0);
        final NeuralUnit initial = wisdom.perceptron();
        for (int i = 0; i < 100; i++) {
            wisdom.perceptron();
        }
        assertThat(
                wisdom.perceptron().feed(1,1).doubleValue(),
                is(initial.feed(1,1).doubleValue())
        );
    }
}