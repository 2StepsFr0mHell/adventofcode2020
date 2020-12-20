package com.advent;

public class FormulaSolver extends PuzzleSolver {

    public FormulaSolver(String name) {
        super(name);
    }

    @Override
    public void compute() {
        String formula = getNextInput();
        while (!formula.isEmpty()) {
            //System.err.println(formula);
            long v = computeFormula(formula);
            value += v;
            System.err.println(v);
            formula = getNextInput();
        }
    }

    private long computeFormula(String formula) {
        if (!formula.contains("(")) {
            return computeSimpleFormula2(formula);
        }
        String pattern = ".*?(\\([\\d\\s\\+\\*]+\\)).*";
        String simpleFormula = formula.replaceAll(pattern, "$1");
        String nextFormula = formula.replace(simpleFormula, String.valueOf(computeSimpleFormula2(simpleFormula.substring(1, simpleFormula.length()-1))));
        //System.err.println(nextFormula);
        return computeFormula(nextFormula);
    }

    private long computeSimpleFormula(String formula) {
        //System.err.println(formula);
        String[] parts = formula.split(" ");
        int i = 1;
        long n = Long.parseLong(parts[0]);
        while (i < parts.length) {
            long nb = Long.parseLong(parts[i+1]);
            if (parts[i].equals("+")) {
                n+= nb;
            }
            else {
                n*= nb;
            }
            i+=2;
        }
        return n;
    }

    private long computeSimpleFormula2(String formula) {
        //System.err.println(formula);
        if (!formula.contains("+")) {
            long n = 1;
            int i = 0;
            String[] parts = formula.split(" ");
            while (i < parts.length) {
                n = n * Long.parseLong(parts[i]);
                i += 2;
            }
            return n;
        }
        String pattern = ".*?(\\d+\\s\\+\\s\\d+).*";
        String addition = formula.replaceAll(pattern, "$1");
        formula = formula.replace(addition, String.valueOf(computeSimpleFormula(addition)));
        return computeSimpleFormula2(formula);
    }

}
