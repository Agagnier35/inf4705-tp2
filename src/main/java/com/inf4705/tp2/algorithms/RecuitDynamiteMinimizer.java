package com.inf4705.tp2.algorithms;

import com.inf4705.tp2.model.Dynamite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RecuitDynamiteMinimizer extends BaseDynamiteMinimizer {
	private final static int K_MAX = 10;
	private final static double ALPHA = 0.99;

	@Override
	public List<Dynamite> minimizeDynamiteUsage(List<Dynamite> dynamites, int goal) {
		GloutonDynamiteMinimizer previousMinimizer = new GloutonDynamiteMinimizer();
		List<Dynamite> previousSolution = previousMinimizer.minimizeDynamiteUsage(dynamites, goal);

		List<Dynamite> solution = new ArrayList<>(previousSolution);
		List<Dynamite> bestSolution = new ArrayList<>(solution);
		double theta = sum(previousSolution);

		for (int k = 1; k < K_MAX; k++) {
			for (int j = 1; j < goal; j++) {
				List<Dynamite> neighborSolution = neighbor(solution, dynamites, goal);
				int delta = sum(neighborSolution) - sum(solution);

				if(delta >= 0 || Math.exp((delta/theta)) >= Math.random()){
					solution = new ArrayList<>(neighborSolution);
					if(sum(solution) > sum(bestSolution)){
						bestSolution = new ArrayList<>(solution);
					}
				}
			}
			theta *= ALPHA;
		}
		return bestSolution;
	}

    @Override
    public double getFX(int goal) {
        return 1;
    }

    private List<Dynamite> neighbor(List<Dynamite> solution, List<Dynamite> dynamites, int goal) {
		Dynamite randomDyn;
		List<Dynamite> neighbor = new ArrayList<>(solution);
		do {
			int randomIndex = new Random().nextInt(dynamites.size());
			randomDyn = dynamites.get(randomIndex);
		} while (neighbor.contains(randomDyn));

		neighbor.add(randomDyn);

		while(sum(neighbor) > goal){
			int randomIndex = new Random().nextInt(neighbor.size());
			neighbor.remove(randomIndex);
		}
		return neighbor;
	}
}
