import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;

public class Test {

    private final static String PATH = "C:\\Users\\Andrii\\FunctionAproximation\\src\\main\\resources\\coordinate.txt";

    public static void main(String[] args) {

        /**
         * Create Gene class instance, then create a set of genes
         * size depends from quantity parameter) and insert them
         * into LinkedList<> structure. Then walking through the list
         * I am creating a series of genes. Finally, add series into the dataSet.
         * */

        Gene g = new Gene();

        ArrayList<Gene> listOfGenes = g.generateNGenes(5);

        XYSeriesCollection dataSet = new XYSeriesCollection();
        for (int i = 0; i < listOfGenes.size(); i++) {
            XYSeries series = listOfGenes.get(i).createGeneSeries(listOfGenes.get(i), "gene" + i);
            dataSet.addSeries(series);
        }

        Draw draw = new Draw("Genetic Algorithm", dataSet);
        draw.setSize(800, 400);
        draw.setLocationRelativeTo(null);
        draw.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        draw.setVisible(true);

        /**
         * Read original points from the file, and add them into the dataSet
         * */
        TextReader textReader = new TextReader(PATH);
        double[] xx = textReader.getXFromFile();
        double[] yy = textReader.getYFromFile();
        Gene originalPoints = new Gene(xx, yy);
        dataSet.addSeries(originalPoints.createGeneSeries(originalPoints, "original function"));


        Chromosome chromosome = new Chromosome(listOfGenes);
        for (int i = 0; i < listOfGenes.size(); i++) {
            double fitness = chromosome.fitnessSelection(listOfGenes.get(i), textReader);
            System.out.println("PARENTS: ");
            System.out.println(i + " gene --> " + listOfGenes.get(i).stringEquation + "; Fitness --> " + fitness);
        }
        System.out.println("\n\n\n");

        long startTime = System.currentTimeMillis();
        GeneticAlgorithm m = new GeneticAlgorithm(chromosome, listOfGenes, dataSet, textReader);
        m.evolution();
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("Running Time of Algorithms: " + timeSpent + " milliseconds.");

    }
}
