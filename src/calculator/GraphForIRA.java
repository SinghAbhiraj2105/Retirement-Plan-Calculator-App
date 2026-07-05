package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Abhiraj Singh
 * Last Updated: 5/10/2026
 */


import java.util.List;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

public class GraphForIRA {
	 public static XYChart createIRAChart(String title) {
	        XYChart chart = new XYChartBuilder().title(title).xAxisTitle("Age").yAxisTitle("Balance").build();
	        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
	        chart.addSeries("Total Savings", new double[]{0}, new double[]{0});
	        chart.addSeries("Total Contributions", new double[]{0}, new double[]{0});
	        return chart;
	    }

	    public static void updateData(XYChart chart, List<Integer> ages, List<Double> balances, List<Double> contributions) {
	        chart.updateXYSeries("Total Savings", ages, balances, null);
	        chart.updateXYSeries("Total Contributions", ages, contributions, null);
	    }
	}


