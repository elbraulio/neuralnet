package examples;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class PrecisionChart {
    private final Number[] learnigCurve;

    public PrecisionChart(Number[] learnigCurve) {
        this.learnigCurve = learnigCurve;
    }


    public void show() {
        double[] x = new double[this.learnigCurve.length];
        double[] y = new double[this.learnigCurve.length];
        for (int i = 0; i < y.length; i++) {
            x[i] = i+1;
            y[i] = this.learnigCurve[i].doubleValue();
        }
        XYChart chart = new XYChartBuilder()
                .theme(Styler.ChartTheme.XChart)
                .width(800)
                .height(600)
                .build();
        chart.getStyler().setYAxisMin(0d);
        chart.getStyler().setYAxisMax(1d);
        XYSeries seriesLiability = chart.addSeries(
                "Precision", x, y
        );
        seriesLiability.setXYSeriesRenderStyle(
                XYSeries.XYSeriesRenderStyle.Line
        );
        new SwingWrapper<>(chart).displayChart();
    }
}
