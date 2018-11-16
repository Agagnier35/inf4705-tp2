package com.inf4705.tp2.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.inf4705.tp2.model.Dynamite;

public class ProgDyn2DynamiteMinimizer extends BaseDynamiteMinimizer {
    @Override
    public List<Dynamite> minimizeDynamiteUsage(List<Dynamite> dynamites, int goal) {
        // Step 1 : Define the matrix
        // matrix[1...n][0...N]
        // n : number of dynamite types
        // N : goal
        double[][] countMatrix = new double[dynamites.size() + 1][goal + 1];

        // Sort dynamites by power
        // * * (Worsen performances but more accurate implementation of algorithm)
        // ex: for [6,5,1,10] and goal = 11
        // sorted answer = 10 + 1
        // unsorted answer = 6 + 5
        dynamites.sort(Comparator.comparing(Dynamite::getPower));

        // Step 2 : Set infinity and default pivots
        for (int i = 1; i < countMatrix.length; i++) {
            for (int j = 0; j < countMatrix[i].length; j++) {
                Double value = Double.POSITIVE_INFINITY;
                if (j == 0) {
                    value = 0.0;
                }
                countMatrix[i][j] = value;
            }
        }


        // Step 3 : Main loop
        for (int i = 1; i < countMatrix.length; i++) {
            for (int j = 1; j < countMatrix[i].length; j++) {
                Integer dynamitePower = dynamites.get(i - 1).getPower();

                Double left;
                if (dynamitePower > j) {
                    left = Double.POSITIVE_INFINITY;
                } else {
                    left = 1 + countMatrix[i][j - dynamitePower];
                }

                Double right;
                if (i == 1) {
                    right = Double.POSITIVE_INFINITY;
                } else {
                    right = countMatrix[i - 1][j];
                }

                Double minValue = Math.min(left, right);

                countMatrix[i][j] = minValue;
            }
        }

        // Step 4 : Recombination
        // Get back the combination from the table
        List<Dynamite> usedDynamites = new ArrayList<Dynamite>();
        int i = dynamites.size();
        int j = goal;
        while (countMatrix[i][j] > 0) {
            Dynamite dynamiteUsed = dynamites.get(i - 1);

            Double left = Double.POSITIVE_INFINITY;
            if (j >= dynamiteUsed.getPower()) {
                left = 1 + countMatrix[i][j - dynamiteUsed.getPower()];
            }

            Double right = Double.POSITIVE_INFINITY;
            if (i > 1) {
                right = countMatrix[i - 1][j];
            }

            // Add to used every time we move from right to left
            if (left <= right && j - dynamiteUsed.getPower() >= 0) {
                j -= dynamiteUsed.getPower();
                Dynamite leftDynamite = dynamites.get(i - 1);
                usedDynamites.add(leftDynamite);
            } else {
                i -= 1;
            }
        }

        return usedDynamites;
    }

    @Override
    public double getFX(int goal, int nDynamites, int mSolutionSize) {
        return goal * nDynamites;
    }

}
