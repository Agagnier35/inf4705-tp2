package com.inf4705.tp2.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.inf4705.tp2.model.Dynamite;

public class ProgDyn2DynamiteMinimizer extends BaseDynamiteMinimizer {
	@Override
	public List<Dynamite> minimizeDynamiteUsage(List<Dynamite> dynamites, int goal) {
		// Step 1 : Define the matrix
		// matrix[1...n][0...N]
		// n : number of dynamite types
		// N : goal
		double [][] countMatrix = new double [dynamites.size() + 1][goal + 1];

		// Sort
		// Collections.sort(dynamites, (Dynamite d1, Dynamite d2) -> d1.getPower() - d2.getPower());

		// Step 2 : Set infinity and default pivots
		for (int i = 1; i < countMatrix.length; i++) {
			for (int j = 0; j < countMatrix[i].length; j++) {
				Double value = Double.POSITIVE_INFINITY;
				if(j == 0) {
					value = 0.0;
				}
				countMatrix[i][j] = value;
			}
		}


		// Main loop
		for (int i = 1; i < countMatrix.length; i++) {
			for (int j = 1; j < countMatrix[i].length; j++) {
				Integer dynamitePower = dynamites.get(i-1).getPower();

				Double left;
				if(dynamitePower > j) {
					left = Double.POSITIVE_INFINITY;
				} else {
					left = 1 + countMatrix[i][j - dynamitePower];
				}

				Double right;
				if(i == 1) {
					right = Double.POSITIVE_INFINITY;
				} else {
					right = countMatrix[i - 1][j];
				}

				Double minValue = Math.min(left, right);

				countMatrix[i][j] = minValue;
			}
		}

		System.out.printf("\n");
		System.out.printf("Minimum : \n");
		System.out.print(countMatrix[dynamites.size()-1][goal]);
		System.out.printf("\n");

		return new ArrayList<>();
	}

}
