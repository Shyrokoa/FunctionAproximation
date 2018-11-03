import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.util.LinkedList;
import java.util.Random;

public class BinaryGeneTree {

    private static LinkedList<String> xSet;                 //set of variables
    private static LinkedList<String> operationSet;         //set of operation

    private static String[] binaryGeneTree = new String[15];

    /**
     * Binary tree gene default constructor, which create a pool of variables and operations
     */
    BinaryGeneTree() {
        createXSet();
        createOperandSet();
    }

    /**
     * Binary tree gene parametrized constructor, which create a tree from giving []string equation
     */
    BinaryGeneTree(String[] stringEquation) {
        createXSet();
        createOperandSet();
        binaryGeneTree = stringEquation;

    }

    /**
     * Binary tree gene parametrized constructor, which create a tree from giving string equation
     */
    BinaryGeneTree(String stringEquation) {
        createXSet();
        createOperandSet();
        binaryGeneTree = getEquationArray(stringEquation);

    }

    /**
     * This method creates a set of variables
     */
    private void createXSet() {
        LinkedList<String> myXSet = new LinkedList<>();
        myXSet.add("x");
        myXSet.add("x^2");
        myXSet.add("sin(x)");
        myXSet.add("cos(x)");
        for (int i = 0; i < 10; i++) {
            myXSet.add(Integer.toString(i));
        }
        xSet = myXSet;
    }

    /**
     * This method creates a set of operations
     */
    private void createOperandSet() {
        LinkedList<String> myOperandSet = new LinkedList<>();
        myOperandSet.add("+");
        myOperandSet.add("-");
        myOperandSet.add("*");
        myOperandSet.add("/");
        operationSet = myOperandSet;
    }

    /**
     * The method generates first entity as a root
     */
    private void generateRootLevel() {
        binaryGeneTree[0] = xSet.get(new Random().nextInt(xSet.size()));
    }

    /**
     * The method generates a random binary tree
     */
    void generateRandomTree() {
        generateRootLevel();
        createChild(0, true, true);
        createChild(1, true, true);
        createChild(2, true, true);
    }


    /**
     * The method transforms tree equation to the String prototype
     */

    String treeToStringEquation() {
        String a, b, c, d, e, f, finish;
        System.out.println();
        if (binaryGeneTree[7] != null && binaryGeneTree[7] != null) {
            a = "( " + binaryGeneTree[7] + " " + binaryGeneTree[3] + " " + binaryGeneTree[8] + " )";
        } else {
            a = binaryGeneTree[3];
        }

        if (binaryGeneTree[9] != null && binaryGeneTree[10] != null) {
            b = "( " + binaryGeneTree[9] + " " + binaryGeneTree[4] + " " + binaryGeneTree[10] + " )";
        } else {
            b = binaryGeneTree[4];
        }

        if (binaryGeneTree[11] != null && binaryGeneTree[12] != null) {
            c = "( " + binaryGeneTree[11] + " " + binaryGeneTree[5] + " " + binaryGeneTree[12] + " )";
        } else {
            c = binaryGeneTree[5];
        }

        if (binaryGeneTree[13] != null && binaryGeneTree[14] != null) {
            d = "( " + binaryGeneTree[13] + " " + binaryGeneTree[6] + " " + binaryGeneTree[14] + " )";
        } else {
            d = binaryGeneTree[6];
        }

        if (binaryGeneTree[3] != null && binaryGeneTree[4] != null) {
            e = "( " + a + " " + binaryGeneTree[1] + " " + b + " )";
        } else {
            e = binaryGeneTree[1];
        }

        if (binaryGeneTree[5] != null && binaryGeneTree[6] != null) {
            f = "( " + c + " " + binaryGeneTree[1] + " " + d + " )";
        } else {
            f = binaryGeneTree[2];
        }

        if (binaryGeneTree[1] != null && binaryGeneTree[2] != null) {
            finish = "( " + e + " " + binaryGeneTree[0] + " " + f + " )";
        } else {
            finish = binaryGeneTree[0];
        }
        return finish;
    }

    /**
     * This method creates right and left sons of the particular root
     */
    private void createChild(int rootIndex, boolean left, boolean right) {
        if (binaryGeneTree[rootIndex] != null) {
            binaryGeneTree[rootIndex] = operationSet.get(new Random().nextInt(operationSet.size()));
            if (left) {
                setLeftChild(xSet.get(new Random().nextInt(xSet.size())), rootIndex);
                if (!right) {
                    binaryGeneTree[rootIndex] = binaryGeneTree[getLeftChildIndex(rootIndex)];
                    binaryGeneTree[getLeftChildIndex(rootIndex)] = null;
                    binaryGeneTree[getRightChildIndex(rootIndex)] = null;
                }
            }
            if (right) {
                setRightChild(xSet.get(new Random().nextInt(xSet.size())), rootIndex);
                if (!left) {
                    binaryGeneTree[rootIndex] = binaryGeneTree[getRightChildIndex(rootIndex)];
                    binaryGeneTree[getRightChildIndex(rootIndex)] = null;
                    binaryGeneTree[getLeftChildIndex(rootIndex)] = null;
                }
            }
            if (binaryGeneTree[getLeftChildIndex(rootIndex)] != null || binaryGeneTree[getRightChildIndex(rootIndex)] != null)
                binaryGeneTree[rootIndex] = operationSet.get(new Random().nextInt(operationSet.size()));
            else
                binaryGeneTree[(getLeftChildIndex(rootIndex) - 1) / 2] = xSet.get(new Random().nextInt(xSet.size()));
        }
    }

    /**
     * This method returns the right child of the root
     */
    private int getRightChildIndex(int rootIndex) {
        return 2 * rootIndex + 2;
    }

    /**
     * This method returns the left child of the root
     */
    private int getLeftChildIndex(int rootIndex) {
        return 2 * rootIndex + 1;
    }

    /**
     * This method set entity at the left child for the root
     */
    private void setLeftChild(String entity, int rootIndex) {
        int leftChildIndex = (rootIndex * 2) + 1;                           // left child index calculation
        if (binaryGeneTree[rootIndex] == null) {
            System.out.printf("Can't set left child for %d, no parent found\n", leftChildIndex);
        } else {
            binaryGeneTree[leftChildIndex] = entity;
        }
    }

    /**
     * This method set entity at the right child for the root
     */
    private void setRightChild(String entity, int rootIndex) {
        int rightChildIndex = (rootIndex * 2) + 2;                          // right child index calculation
        if (binaryGeneTree[rootIndex] == null) {
            System.out.printf("Can't set right child for %d, no parent found\n", rightChildIndex);
        } else {
            binaryGeneTree[rightChildIndex] = entity;
        }
    }

    String[] getBinaryGeneTree() {
        return binaryGeneTree;
    }

    /**
     * This method mutates an original giving binary gene tree
     */
    void mutateTree(String[] strings) {
        int x = (int) (Math.random() * getSizeOfNotNullElement());
        if (xSet.contains(strings[x])) {
            strings[x] = xSet.get(new Random().nextInt(xSet.size()));
        } else if (operationSet.contains(strings[x])) {
            strings[x] = operationSet.get(new Random().nextInt(operationSet.size()));
        }
        binaryGeneTree = strings;
    }

    /**
     * This method gets function from string equivalent using the mXparser
     * library (is an open-source mathematical expressions parser/evaluator
     * providing abilities to calculate various expressions at a runtime )
     */
    Gene getFunction(String string) {
        Function function = new Function("f(x)=" + string);
        double[] x = new double[61];
        double[] y = new double[61];
        int de = 0;
        for (double d = 0; d < 61; d++) {
            Expression expression = new Expression("f(" + d + ")", function);
            //mXparser.consolePrintln(expression.getExpressionString() + "=" + expression.calculate());     --> show result
            y[de] = expression.calculate();
            x[de] = d;
            de++;
        }

        return new Gene(x, y);
    }


    /**
     * The method returns not null elements of the string array
     */
    private int getSizeOfNotNullElement() {
        int initialSize = 0;
        for (String aBinaryGeneTree : binaryGeneTree) {
            if (aBinaryGeneTree != null) {
                initialSize++;
            }
        }
        return initialSize;
    }

    /**
     * Recombination method, which combines the genetic information
     * of two subtrees of the current binary tree, to generate a new
     * binary gene tree
     */
    void crossoverProcess() {
        int sizeOfNotNullElement = getSizeOfNotNullElement();
        if (sizeOfNotNullElement == 3) {
            String temp = binaryGeneTree[2];
            binaryGeneTree[2] = binaryGeneTree[1];
            binaryGeneTree[1] = temp;
        }
        if (sizeOfNotNullElement == 7) {
            String tempRoot = binaryGeneTree[1];
            binaryGeneTree[1] = binaryGeneTree[2];
            binaryGeneTree[2] = tempRoot;

            String tempLeft = binaryGeneTree[3];
            binaryGeneTree[3] = binaryGeneTree[4];
            binaryGeneTree[4] = tempLeft;

            String tempRight = binaryGeneTree[5];
            binaryGeneTree[5] = binaryGeneTree[6];
            binaryGeneTree[6] = tempRight;
        } else if (sizeOfNotNullElement == 15) {
            String tempRootLevel2left = binaryGeneTree[3];
            binaryGeneTree[3] = binaryGeneTree[4];
            binaryGeneTree[4] = tempRootLevel2left;

            String tempLeftLevel3L = binaryGeneTree[7];
            binaryGeneTree[7] = binaryGeneTree[8];
            binaryGeneTree[8] = tempLeftLevel3L;

            String tempRightLevel3L = binaryGeneTree[9];
            binaryGeneTree[9] = binaryGeneTree[10];
            binaryGeneTree[10] = tempRightLevel3L;

            String tempRootLevel2right = binaryGeneTree[5];
            binaryGeneTree[5] = binaryGeneTree[6];
            binaryGeneTree[6] = tempRootLevel2right;

            String tempLeftLevel3R = binaryGeneTree[11];
            binaryGeneTree[11] = binaryGeneTree[12];
            binaryGeneTree[12] = tempLeftLevel3R;

            String tempRightLevel3R = binaryGeneTree[13];
            binaryGeneTree[13] = binaryGeneTree[14];
            binaryGeneTree[14] = tempRightLevel3R;
        }
    }

    /**
     * The method returns the math equation as a binary array representation
     */
    private static String[] getEquationArray(String givenString) {
        char[] tempChar = new char[givenString.length()];
        for (int i = 0; i < givenString.length(); i++) {
            tempChar[i] = givenString.charAt(i);
        }
        int indexOfWhitespaces = 0;
        for (char aTempChar : tempChar) {

            if (Character.isWhitespace(aTempChar)) {
                indexOfWhitespaces++;
            }
        }
        String[] temp = new String[15];
        String[] binTree = new String[15];

        switch (indexOfWhitespaces) {
            case 2:
                for (int i = 0; i < indexOfWhitespaces + 1; i++) {
                    temp = givenString.split(" ");
                }
                binTree[0] = temp[0];
                break;

            case 4:
                for (int i = 0; i < indexOfWhitespaces + 1; i++) {
                    temp = givenString.split(" ");
                }
                binTree[0] = temp[2];
                binTree[1] = temp[1];
                binTree[2] = temp[3];
                break;

            case 12:
                for (int i = 0; i < indexOfWhitespaces + 1; i++) {
                    temp = givenString.split(" ");
                }
                binTree[0] = temp[6];
                binTree[1] = temp[3];
                binTree[2] = temp[9];
                binTree[3] = temp[2];
                binTree[4] = temp[4];
                binTree[5] = temp[8];
                binTree[6] = temp[10];
        }
        return binTree;
    }
}
