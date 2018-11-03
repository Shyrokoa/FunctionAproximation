import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class Draw extends JFrame {


    /**
     * Parameterized constructor
     */
    Draw(String title, XYDataset xyDataset) {
        super(title);

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Chromosome chart",
                "X-Axis", "Y-Axis", xyDataset);

        //Changes background color
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(11, 20, 10));

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
}
