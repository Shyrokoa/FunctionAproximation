import java.util.Comparator;

public class FitnessComparator implements Comparator<Gene> {

    /**
     * Override comparing method for Gene needs
     */
    public int compare(Gene o1, Gene o2) {
        return Double.compare(o1.getFitness(), o2.getFitness());
    }
}

