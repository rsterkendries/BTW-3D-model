import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

class LogAxis2DChart extends ApplicationFrame {
	
	private static final long serialVersionUID = 1L;

	public LogAxis2DChart(String title) {
		super(title);
		final XYSeries serie1 = new XYSeries("V = 16 x 16 x 16");
		
		BTW_3D btw1 = new BTW_3D(16);
		btw1.startAvalanche(1000);
		for(Entry<Double,Integer> e : btw1.getRelativeDistribution().entrySet()) {
			serie1.add(e.getKey(),e.getValue());
		}
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie1);
		
		final JFreeChart chart = ChartFactory.createXYLineChart("3D Bak-Tang-Wiesenfeld Model",
																"Size","Frequency",
																dataset,
																PlotOrientation.VERTICAL,
																true,
																true,
																false);
		
		final XYPlot plot = chart.getXYPlot();
        final NumberAxis domainAxis = new LogarithmicAxis("Log(Size Ratio)");
        final NumberAxis rangeAxis = new LogarithmicAxis("Log(Frequency)");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 470));
        setContentPane(chartPanel);
		
	}
}

public class TestClass {

	public static void main(String[] args) {
		
		final LogAxis2DChart demo = new LogAxis2DChart("BTW");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
		
	}

}
