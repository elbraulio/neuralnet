package examples.sides;

import com.elbraulio.neuralnet.*;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class Launcher {
    private static final int n = 1000;
    private static final int[] x = new int[n];
    private static final int[] y = new int[n];
    private static final Supervise supervisor = new DiagonalLine(0.1);
    private static Desired desired = new AboveBelow();

    public static void main(String... args) {
        for (int i = 0; i < n; i++) {
            x[i] = ThreadLocalRandom.current().nextInt(-120, 120);
            y[i] = ThreadLocalRandom.current().nextInt(-120, 120);
        }
        XYChart chart = getPrecisionChart(
                new double[]{0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100},
                new double[]{
                        experimentAndShowResults(supervisor, 0),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10),
                        experimentAndShowResults(supervisor, 10)
                }
        );
        new SwingWrapper<>(chart).displayChart();
    }

    private static XYChart getPrecisionChart(double[] x, double[] y) {
        XYChart chart = new XYChartBuilder()
                .theme(Styler.ChartTheme.XChart)
                .width(800)
                .height(600)
                .build();
        chart.getStyler().setYAxisMin(0d);
        chart.getStyler().setYAxisMax(100d);
        XYSeries seriesLiability = chart.addSeries(
                "Precision", x, y
        );
        seriesLiability.setXYSeriesRenderStyle(
                XYSeries.XYSeriesRenderStyle.Line
        );
        return chart;
    }

    private static double experimentAndShowResults(
            Supervise trainer, int training
    ) {
        int[] outputs = new int[n];
        int[] corrects = new int[n];
        for (int j = 0; j < training; j++) {
            trainer.perceptron();
        }
        NeuralUnit perceptron = trainer.perceptron();
        for (int i = 0; i < n; i++) {
            outputs[i] = perceptron.feed(x[i], y[i]).intValue();
            corrects[i] = desired.output(x[i], y[i]).intValue();
        }
        XYChart chart = getChart(x, y, outputs);
        new SwingWrapper<>(chart).displayChart();
        int failCount = 0;
        for (int i = 0; i < n; i++) {
            if (outputs[i] != corrects[i]) {
                failCount++;
            }
        }
        return (n - failCount) * 100d / (double) n;
    }

    private static XYChart getChart(
            int[] xData, int[] yData, int[] outputs
    ) {
        XYChart chart = new XYChartBuilder()
                .theme(Styler.ChartTheme.Matlab)
                .width(800).height(600).build();
        chart.getStyler().setDefaultSeriesRenderStyle(
                XYSeries.XYSeriesRenderStyle.Scatter
        );
        List<Integer> xAbove = new LinkedList<>();
        List<Integer> yAbove = new LinkedList<>();
        List<Integer> xBelow = new LinkedList<>();
        List<Integer> yBelow = new LinkedList<>();
        for (int i = 0; i < xData.length; i++) {
            if (outputs[i] == 1) {
                xAbove.add(xData[i]);
                yAbove.add(yData[i]);
            } else {
                xBelow.add(xData[i]);
                yBelow.add(yData[i]);
            }
        }
        chart.addSeries("Above", xAbove, yAbove);
        XYSeries below = chart.addSeries("Below", xBelow, yBelow);
        below.setMarker(SeriesMarkers.CIRCLE);
        XYSeries seriesLiability = chart.addSeries("Divider",
                new double[]{-120d, 0d, 120d}, new double[]{-120d, 0d, 120d}
        );
        seriesLiability.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        seriesLiability.setMarker(SeriesMarkers.NONE);
        return chart;
    }
}

