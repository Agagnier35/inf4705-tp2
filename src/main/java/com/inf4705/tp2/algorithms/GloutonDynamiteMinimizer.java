package com.inf4705.tp2.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.inf4705.tp2.model.Dynamite;

public class GloutonDynamiteMinimizer extends BaseDynamiteMinimizer {

    @Override
    public List<Dynamite> minimizeDynamiteUsage(List<Dynamite> dynamites, int goal) {
        dynamites.sort(Comparator.comparing(Dynamite::getPower).reversed());
        List<Dynamite> solution =  new ArrayList<>();
        int sum = 0;
        while (sum < goal){
            Dynamite max = max(dynamites, goal - sum);
            if(max.getId() == -1){//no dynamites fits, abort
                break;
            }
            solution.add(max);
            sum += max.getPower();
        }
        return solution;
    }

    @Override
    public double getFX(int goal) {
        return 1;
    }

    private Dynamite max(List<Dynamite> dynamites, int max) {
        Dynamite maxDyn = new Dynamite(-1, 0);
        for (Dynamite dyn : dynamites) {
            if (dyn.getPower() < max && dyn.getPower() > maxDyn.getPower()) {
                maxDyn = dyn;
            }
        }
        dynamites.remove(maxDyn);
        return maxDyn;
    }

}
