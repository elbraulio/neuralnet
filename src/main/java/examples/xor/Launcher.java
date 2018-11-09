package examples.xor;

import com.elbraulio.neuralnet.DefaultNetwork;
import com.elbraulio.neuralnet.DesiredOutput;
import com.elbraulio.neuralnet.DesiredXor;
import com.elbraulio.neuralnet.NeuralNetwork;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class Launcher {
    public static void main(String... args){
        NeuralNetwork ntWork = new DefaultNetwork(1.5, 2, 1, 2,2);
        System.out.println("desired 0, 1 :" + new DesiredXor().output(0, 1));
        System.out.println("desired 1, 0 :" + new DesiredXor().output(1, 0));
        System.out.println("desired 1, 1 :" + new DesiredXor().output(1, 1));
        System.out.println("desired 0, 0 :" + new DesiredXor().output(0, 0));
        System.out.println("---------------------");
        System.out.println("0, 1 : " + ntWork.feed(0, 1)[0]);
        System.out.println("1, 0 : " + ntWork.feed(1, 0)[0]);
        System.out.println("1, 1 : " + ntWork.feed(1, 1)[0]);
        System.out.println("0, 0 : " + ntWork.feed(0, 0)[0]);
        int training = 5000;
        DesiredOutput desiredOutput = new DesiredOutput() {
            @Override
            public boolean isDesired(Number[] input, Number[] output) {
                double correct = new DesiredXor().output(input).intValue();

                return output[0].doubleValue() > 0.5 ? 1 == correct :
                        0 == correct;
            }

            @Override
            public Number[] desired(Number[] input) {
                return new Number[]{new DesiredXor().output(input)};
            }
        };
        while (training-- > 0) {
            ntWork.toTrain(
                    desiredOutput,
                    0, 1
            );
            ntWork.toTrain(
                    desiredOutput,
                    1, 0
            );
            ntWork.toTrain(
                    desiredOutput,
                    1, 1
            );
            ntWork.toTrain(
                    desiredOutput,
                    0, 0
            );
        }
        System.out.println("---------------------");
        System.out.println("0, 1 : " + ntWork.feed(0, 1)[0]);
        System.out.println("1, 0 : " + ntWork.feed(1, 0)[0]);
        System.out.println("1, 1 : " + ntWork.feed(1, 1)[0]);
        System.out.println("0, 0 : " + ntWork.feed(0, 0)[0]);
    }
}
