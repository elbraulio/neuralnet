package examples.ads;

import com.elbraulio.neuralnet.desired.DesiredOutput;
import com.elbraulio.neuralnet.network.DefaultNetwork;
import com.elbraulio.neuralnet.network.NeuralNetwork;
import com.elbraulio.neuralnet.utils.Add;
import com.elbraulio.neuralnet.utils.Normalize;
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

    /**
     * Normalize params
     */
    private static final Number NL = 0d;
    private static final Number NH = 1d;
    private static final Number DL = 0d;
    private static final Number DH = 640d;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            logln("Must provide data source path");
            return;
        }
        final NeuralNetwork network = new DefaultNetwork(
                0.3, 1557, 1,
                20, 20, 20, 20, 20
        );
        final String data = args[0];
        logln("Experiment started");
        final long ti = System.currentTimeMillis();
        logln("Initial and NO trained results");
        runTest(data, network, 3279);
        logln("Training");
        toTrain(100, data, network, 3279, true);
        logln("Final and trained results");
        runTest(data, network, 3279);
        logln(
                "Experiment finished after " +
                        (System.currentTimeMillis() - ti) / 1000 +
                        " seconds"
        );
    }

    /**
     * train a network nEpoches times.
     *
     * @param nEpoches       times to be trained
     * @param data    source
     * @param network network
     * @throws IOException when source is not found
     */
    private static void toTrain(
            int nEpoches, String data, NeuralNetwork network, int ndata,
            boolean shuffle
    ) throws IOException {
        final int epoches = nEpoches;
        final Add sqrerror = new Add();
        final Add abserror = new Add();
        final Number[] learnigCurve = new Number[nEpoches];
        final List<String> inputLines = readInput(data, ndata, shuffle);
        log("epoch (of " + nEpoches + "): ");
        while (nEpoches-- > 0) {
            log((epoches - nEpoches) + ", ");
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
            final int currentIndex = epoches - nEpoches - 1;
            learnigCurve[currentIndex] = hits.count() /
                    (hits.count() + wrong.count());
        }
        logln("");
        logln(
                "mean squared error: " +
                        (sqrerror.count()/(inputLines.size()*epoches))
        );
        logln(
                "mean abs error: " +
                        (abserror.count()/(inputLines.size()*epoches))
        );
        logln("learning curve: " + Arrays.toString(learnigCurve));
        new PrecisionChart(learnigCurve).show();
    }

    /**
     * Read the input from source and shuffle it if it's needed.
     *
     * @param source  data source path
     * @param ndata   number of lines to be read
     * @param shuffle if true, shuffle the data before return
     * @return list of lines from source
     * @throws IOException error reading source file
     */
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
            logln("tp :" + tp);
            logln("tn :" + tn);
            logln("fn :" + fn);
            logln("fp :" + fp);
            if ((tp + fp) != 0)
                logln("precision: " + tp / (tp + fp));
            else
                logln("precision: 0");
            if ((tp + fn) != 0)
                logln("recall: " + tp / (tp + fn));
            else
                logln("recall: 0");
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
            inputs[0] = new Normalize(x, DL, DH, NL, NH);
        } else {
            return new Number[]{};
        }
        if (!split[1].trim().equals("?")) {
            Number x = Double.parseDouble(split[1].trim());
            inputs[1] = new Normalize(x, DL, DH, NL, NH);
        } else {
            return new Number[]{};
        }
        if (!split[2].trim().equals("?")) {
            Number x = Double.parseDouble(split[2].trim());
            inputs[2] = new Normalize(x, DL, DH, NL, NH);
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

    /**
     * Log the message and print new line
     *
     * @param msg message
     */
    private static void logln(String msg) {
        log(msg + "\n");
    }

    /**
     * Log the message
     *
     * @param msg message
     */
    private static void log(String msg) {
        System.out.print(msg);
    }
}
