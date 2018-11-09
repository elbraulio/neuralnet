package examples.ads;

import com.elbraulio.neuralnet.*;
import examples.PrecisionChart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class Launcher {
    static final Number nl = 0d;
    static final Number nh = 1d;
    static final Number dl = 0d;
    static final Number dh = 640d;

    public static void main(String[] args) throws IOException {

        final NeuralNetwork network = new DefaultNetwork(
                0.02, 1555, 1,
                50,50,50,50,50,50
        );
        final String data = "/Users/elbraulio/Downloads/ad-dataset/ad.data";
        System.out.println("Experiment started");
        final long ti = System.currentTimeMillis();
        runTest(data, network, 3279);
        toTrain(100, data, network, 3279, true);
        runTest(data, network, 3279);
        System.out.println(
                "Experiment finished after " +
                        (System.currentTimeMillis() - ti) / 1000 +
                        " seconds"
        );
    }

    /**
     * train a network n times.
     *
     * @param n       times to be trained
     * @param data    source
     * @param network network
     * @throws IOException when source is not found
     */
    private static void toTrain(
            int n, String data, NeuralNetwork network, int ndata,
            boolean shuffle
    ) throws IOException {
        final int epoches = n;
        final Add sqrerror = new Add();
        final Add abserror = new Add();
        final Number[] learnigCurve = new Number[n];
        final List<String> inputLines = readInput(data, ndata, shuffle);
        while (n-- > 0) {
            System.out.println("epoch: " + (epoches - n));
            final Add hits = new Add();
            final Add wrong = new Add();
            for (String inputLine : inputLines) {
                Number[] inputs = makeInputs(inputLine);
                if (inputs.length == 0) {
                    continue;
                }
                double desired = inputLine.split(",")[1558].trim().equals("ad.")
                        ? 1d : 0d;
                network.toTrain(
                        new DesiredOutput() {
                            @Override
                            public boolean isDesired(Number[] input, Number[] output) {
                                sqrerror.add(
                                        Math.pow(
                                                desired - output[0]
                                                        .doubleValue(), 2
                                        )
                                );
                                abserror.add(
                                        Math.abs(
                                                desired - output[0]
                                                        .doubleValue()
                                        )
                                );
                                if (output[0].doubleValue() > 0.5) {
                                    if (1 == desired) {
                                        hits.add(1);
                                        return true;
                                    } else {
                                        wrong.add(1);
                                        return false;
                                    }
                                } else {
                                    if (0 == desired) {
                                        hits.add(1);
                                        return true;
                                    } else {
                                        wrong.add(1);
                                        return false;
                                    }
                                }
                            }

                            @Override
                            public Number[] desired(Number[] input) {
                                return new Number[]{desired};
                            }
                        },
                        inputs
                );
            }
            final int currentIndex = epoches - n - 1;
            learnigCurve[currentIndex] = hits.count() /
                    (hits.count() + wrong.count());
        }
        System.out.println("mean squared error: " + sqrerror);
        System.out.println("mean abs error: " + abserror);
        System.out.println("learning curve: " + Arrays.toString(learnigCurve));
        new PrecisionChart(learnigCurve).show();
    }

    private static List<String> readInput(
            String source, int ndata, boolean shuffle
    ) throws IOException {
        List<String> data = new ArrayList<>(ndata);
        try (
                BufferedReader br = new BufferedReader(
                        new FileReader(source)
                )
        ) {
            String line;
            int set = ndata;
            while (set-- > 0) {
                if ((line = br.readLine()) == null) {
                    break;
                }
                data.add(line);
            }
            if (shuffle) Collections.shuffle(data);
        }
        return data;
    }

    /**
     * feeds the network with a given source.
     *
     * @param data    source
     * @param network network
     * @throws IOException source not found
     */
    private static void runTest(
            String data, NeuralNetwork network, int ndata
    ) throws IOException {
        try (
                BufferedReader br = new BufferedReader(
                        new FileReader(data)
                )
        ) {
            String line;
            double tp = 0d;
            double tn = 0d;
            double fn = 0d;
            double fp = 0d;
            int set = ndata;
            while (set-- > 0) {
                if ((line = br.readLine()) == null) {
                    break;
                }
                Number[] inputs = makeInputs(line);
                if (inputs.length == 0) {
                    continue;
                }
                int desired = line.split(",")[1558].trim().equals("ad.") ? 1
                        : 0;
                if (network.feed(inputs)[0].doubleValue() > 0.5) {
                    if (desired == 1) {
                        tp++;
                    } else {
                        fp++;
                    }
                } else {
                    if (desired == 0) {
                        tn++;
                    } else {
                        fn++;
                    }
                }
            }
            System.out.println("tp :" + tp);
            System.out.println("tn :" + tn);
            System.out.println("fn :" + fn);
            System.out.println("fp :" + fp);
            if ((tp + fp) != 0)
                System.out.println("precision: " + tp / (tp + fp));
            else
                System.out.println("precision: 0");
            if ((tp + fn) != 0)
                System.out.println("recall: " + tp / (tp + fn));
            else
                System.out.println("recall: 0");
        }
    }

    /**
     * @param line instance with inputs
     * @return array of normalized inputs or empty if ther are an unknown input
     */
    private static Number[] makeInputs(String line) {
        String[] split = line.split(",");
        Number[] inputs = new Number[1557];
        if (!split[0].trim().equals("?")) {
            Number x = Double.parseDouble(split[0].trim());
            inputs[0] = new Normalize(x, dl, dh, nl, nh);
        } else {
            return new Number[]{};
        }
        if (!split[1].trim().equals("?")) {
            Number x = Double.parseDouble(split[1].trim());
            inputs[1] = new Normalize(x, dl, dh, nl, nh);
        } else {
            return new Number[]{};
        }
        if (!split[2].trim().equals("?")) {
            Number x = Double.parseDouble(split[2].trim());
            inputs[2] = new Normalize(x, dl, dh, nl, nh);
        } else {
            return new Number[]{};
        }

        for (int j = 3; j < inputs.length; j++) {
            if (split[j].trim().equals("?")) {
                return new Number[]{};
            }
            inputs[j] = Integer.parseInt(split[j].trim());
        }
        return inputs;
    }
}
