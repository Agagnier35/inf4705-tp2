package com.inf4705.tp2.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.inf4705.tp2.model.Dynamite;

public class ProgDyn1DynamiteMinimizer extends BaseDynamiteMinimizer {
	@Override
	public List<Dynamite> minimizeDynamiteUsage(List<Dynamite> dynamites, int goal) {

		dynamites.sort(Comparator.comparing(Dynamite::getPower));
		// Step 1: Define c[1...N] ArrayList -> [0, N + 1]
		// N : goal
		// c[j] : minimum amount of dynamite required for the quantity
		ArrayList<Double> countList = new ArrayList<>(goal + 1);

		// Step 2: Define I[1...N] index ArrayList -> [0, N + 1]
		ArrayList<Double> indexList = new ArrayList<>(goal + 1);

		// Step 3: Fill count list with Integer.MAX_VALUE (suppose to be infinity)
		for (int j = 0; j < goal + 1; j++) {
			countList.add(j, Double.POSITIVE_INFINITY);
			indexList.add(j, Double.POSITIVE_INFINITY);
		}

		// Step 4: Set boundaries values
		// c[di] = 1
		// I[di] = -i
		for (int i = 1; i < dynamites.size(); i++) {
			Integer dynamiteValue = dynamites.get(i).getPower();

			if (dynamiteValue <= goal) {
				countList.set(dynamiteValue, 1.0);
				indexList.set(dynamiteValue, (double)(-i));
			}
		}

		// Step 5: Main loop
		for (int j = 1; j < countList.size(); j++) {
			// Check if boundary
			if (countList.get(j) != 1) {
				for (int i = 1; i <= Math.floor(j / 2); i++) {
					if (countList.get(j) > countList.get(i) + countList.get(j - i)) {
						countList.set(j, countList.get(i) + countList.get(j - i));
						indexList.set(j, (double)(i));
					}
				}
			}
		}

        Integer index = goal;
        Integer value = goal;
        List<Dynamite> usedDynamites = new ArrayList<>();
        while(value > 0) {
            Integer currentValue = indexList.get(index).intValue();
            if(currentValue < 0) {
                Dynamite currentDynamite = dynamites.get(-1 * currentValue);
                usedDynamites.add(currentDynamite);
                value -= currentDynamite.getPower();
                index = value;
            }
            else if(currentValue == Integer.MAX_VALUE) {
                index -= 1;
                value -= 1;
            }
            else {
                index = currentValue;
            }
        }

        return usedDynamites;
	}

    @Override
    public double getFX(int goal) {
        return 1;
    }
}
