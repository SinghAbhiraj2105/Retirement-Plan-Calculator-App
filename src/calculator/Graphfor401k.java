package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Neel Debnath
 * Last Updated: 5/11/2026
 */


import java.util.List;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

public class Graphfor401k {
	 public static XYChart createChart(String title) {
        XYChart chart = new XYChartBuilder().title(title).xAxisTitle("Age").yAxisTitle("Balance").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.addSeries("Savings with Employer Match", new double[]{0}, new double[]{0});
        chart.addSeries("Savings without Employer Match", new double[]{0}, new double[]{0});
        chart.addSeries("Total Employee Contributions", new double[]{0}, new double[]{0});
        return chart;
    }

	    public static void updateData(XYChart chart, List<Integer> ages, List<Double> savingsWithMatch, List<Double> savingsWithoutMatch, List<Double> employeeContributions) {
        chart.updateXYSeries("Savings with Employer Match", ages, savingsWithMatch, null);
        chart.updateXYSeries("Savings without Employer Match", ages, savingsWithoutMatch, null);
        chart.updateXYSeries("Total Employee Contributions", ages, employeeContributions, null);
    }
}
