import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;

public class GeneticAlgorithm {

    private Chromosome chromosome;
    private ArrayList<Gene> listOfGene;
    private XYSeriesCollection dataSet;
    private TextReader textReader;
    private static ArrayList<Gene> winnersGeneList = new ArrayList<>();
    private static int population;

    GeneticAlgorithm(Chromosome chromosome, ArrayList<Gene> listOfGene, XYSeriesCollection dataSet, TextReader textReader) {
        this.chromosome = chromosome;
        this.listOfGene = listOfGene;
        this.dataSet = dataSet;
        this.textReader = textReader;
        population = 0;
    }

    /**
     * The method sorts our incoming list of the gene using Fitness comparator
     * and write winner to the list of winner
     */
    private void sortList() {
        population++;
        listOfGene.sort(new FitnessComparator());
        winnersGeneList.add(listOfGene.get(0));
        System.out.println("POPULATION " + (population - 1) + " --> --> -->");
    }

    /**
     * This method helps us to transform the winner's gene for the losers
     * (mutate 0-index gene for 1-st and 2-nd index
     * in listOfGene)
     */
    private void losersTransformation() {
        for (int i = 1; i < listOfGene.size(); i++) {
            BinaryGeneTree binaryGeneTree = new BinaryGeneTree(listOfGene.get(0).stringEquation);
            binaryGeneTree.mutateTree(binaryGeneTree.getBinaryGeneTree());
            binaryGeneTree.crossoverProcess();
            listOfGene.set(i, new Gene(binaryGeneTree));
        }
    }

    /**
     * Calculates fitness function for the list of genes
     */
    private void calculateFitness() {
        for (int i = 0; i < listOfGene.size(); i++) {
            double fitness = chromosome.fitnessSelection(listOfGene.get(i), textReader);
            System.out.println(i + " th gene --> " + listOfGene.get(i).stringEquation + " | Fitness --> " + fitness);
        }
    }

    /**
     * This recursion method looking for a math equation
     * till the approximation error will be acceptable for us.
     */
    void evolution() {
        sortList();
        losersTransformation();
        calculateFitness();
        if (chromosome.fitnessSelection(listOfGene.get(0), textReader) == 0) {
            System.out.println("Finish: " + listOfGene.get(0).stringEquation + " Fitness: " + chromosome.fitnessSelection(listOfGene.get(0), textReader));
            for (int i = 0; i < listOfGene.size(); i++) {
                Gene gene = new Gene(listOfGene.get(i).binaryTree);
                dataSet.addSeries(gene.createGeneSeries(gene, "Gene " + i));
            }
        } else {
            if (winnersGeneList.size() > 2) {
                if (listOfGene.get(0).getFitness() == winnersGeneList.get(winnersGeneList.size() - 2).getFitness()) {
                    do {
                        System.out.println("CHANGE WIN_GENE  ");
                        mutateRedoundedGene();
                        population++;
                    }
                    while (
                            chromosome.fitnessSelection(listOfGene.get(0), textReader) > chromosome.
                                    fitnessSelection(winnersGeneList.get(winnersGeneList.size() - 2), textReader));
                }
            }
            evolution();
        }
    }

    /**
     * This method mutates winner gene if redundant takes place
     */
    private void mutateRedoundedGene() {
        BinaryGeneTree binaryGeneTree = new BinaryGeneTree(listOfGene.get(0).stringEquation);
        binaryGeneTree.mutateTree(binaryGeneTree.getBinaryGeneTree());
        binaryGeneTree.crossoverProcess();
        binaryGeneTree.mutateTree(binaryGeneTree.getBinaryGeneTree());
        binaryGeneTree.crossoverProcess();
        listOfGene.set(0, new Gene(binaryGeneTree));
    }
}
