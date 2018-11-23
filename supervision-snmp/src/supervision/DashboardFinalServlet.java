package supervision;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Servlet implementation class DashboardFinalServlet
 */
@WebServlet("/DashboardFinalServlet")
public class DashboardFinalServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DashboardFinalServlet( ) {
		super();
	}
    /**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        genGraph(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        genGraph(req, resp);
    }

    public void genGraph(HttpServletRequest req, HttpServletResponse resp) {

        try {
            OutputStream out = resp.getOutputStream();

            // Create a simple Bar chart
            System.out.println("Setting dataset values");

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(30, "Girls","SCIENCE CLASS");
            dataset.setValue(30,  "Boys","SCIENCE CLASS");
            dataset.setValue(10,  "Girls","ECONOMICS CLASS");
            dataset.setValue(50, "Boys","ECONOMICS CLASS");
            dataset.setValue(5, "Girls","LANGUAGE CLASS");
            dataset.setValue(55, "Boys","LANGUAGE CLASS");

            JFreeChart chart = ChartFactory.createBarChart(
                "Comparison between Girls and Boys in Science," + "Economics and Language classes",
                "Students Comparisons", "No of Students",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

            chart.setBackgroundPaint(Color.WHITE);

            // Set the background colour of the chart
            chart.getTitle().setPaint(Color.blue);

            // Adjust the colour of the title
            CategoryPlot plot = chart.getCategoryPlot();

            // Get the Plot object for a bar graph
            plot.setBackgroundPaint(ChartColor.white);
            plot.setRangeGridlinePaint(ChartColor.red);

            CategoryItemRenderer renderer = plot.getRenderer();
            renderer.setSeriesPaint(0, ChartColor.red);
            renderer.setSeriesPaint(1, ChartColor.green);
            renderer.setDefaultItemURLGenerator(
                new StandardCategoryURLGenerator(
                    "index1.html",
                    "series",
                    "section"));
            renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());

            resp.setContentType("image/png");

            ChartUtils.writeChartAsPNG(out, chart, 625, 500);

        } catch (Exception e) {
            System.err.println("Problem occurred creating chart." + e.getMessage());
        }
    }}
