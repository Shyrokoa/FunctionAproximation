import java.util.ArrayList;

import static java.lang.Math.abs;

public class Chromosome {
    static ArrayList<Gene> chromosome;

    /**
     * Parameterized constructor
     */
    Chromosome(ArrayList<Gene> geneList) {
        chromosome = geneList;
    }

    /**
     * The method calculates fitness function for the certain gene.
     * For solving the function approximation task, the evaluation
     * function checks how far the generated function deviates from
     * the base points. So, we will calculate the approximation error
     * as the sum of the absolute values of the difference between the
     * certain gene and original function at the given points.
     */
    double fitnessSelection(Gene gene, TextReader textReader) {
        double fitness = 0;
        for (int i = 0; i < textReader.getYFromFile().length; i++) {
            fitness += abs(gene.getY()[i] - textReader.getYFromFile()[i]);
        }
        gene.setFitness(fitness);
        return fitness;
    }
}
