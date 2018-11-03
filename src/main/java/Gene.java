import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;

public class Gene {
    private double[] x;                         //an x-components array of gene
    private double[] y;                         //an y-components array of gene
    private double fitness = 0;                 //the current value of the fitness function
    String stringEquation;
    BinaryGeneTree binaryTree;

    /**
     * Parameterized constructor which separates binary tree components
     */
    Gene(BinaryGeneTree binaryTree) {
        x = binaryTree.getFunction(binaryTree.treeToStringEquation()).getX();
        y = binaryTree.getFunction(binaryTree.treeToStringEquation()).getY();
        this.binaryTree = binaryTree;
        stringEquation = binaryTree.treeToStringEquation();
    }

    /**
     * Parameterized constructor
     */
    Gene(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Default constructor
     */
    Gene() {
    }

    /**
     * The method returns an array of x-components
     */
    private double[] getX() {
        return x;
    }

    /**
     * The method returns an array of y-components
     */
    private double[] getY() {
        return y;
    }


    /**
     * The method creates a series of genes
     */
    XYSeries createGeneSeries(Gene gene, String geneTitle) {
        XYSeries series = new XYSeries(geneTitle);
        for (int i = 0; i < gene.x.length; i++) {
            series.add(Double.valueOf(gene.x[i]), Double.valueOf(gene.y[i]));
        }
        return series;
    }

    /**
     * The method sets the fitness value of the gene
     */
    void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * The method returns the fitness value of the gene
     */
    double getFitness() {
        return fitness;
    }

    /**
     * The method generates a set of n-genes
     */
    ArrayList<Gene> generateNGenes(int quantity) {
        ArrayList<Gene> geneList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            BinaryGeneTree binaryGeneTree = new BinaryGeneTree();
            binaryGeneTree.generateRandomTree();
            geneList.add(new Gene(binaryGeneTree));
        }
        return geneList;
    }
}
