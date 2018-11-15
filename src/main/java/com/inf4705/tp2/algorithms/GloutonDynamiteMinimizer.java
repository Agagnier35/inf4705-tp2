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
        int index = 0;
        while (sum < goal){
            index = max(dynamites, goal - sum, index);
            if(index == -1){//no dynamites fits, abort
                break;
            }
            Dynamite max = dynamites.get(index);
            index++;
            solution.add(max);
            sum += max.getPower();
        }
        return solution;
    }

    @Override
    public double getFX(int goal) {
        return 1;
    }

    private int max(List<Dynamite> dynamites, int max, Integer index) {
        while (index < dynamites.size()){
            Dynamite dyn = dynamites.get(index);
            if (dyn.getPower() < max) {
                return index;
            }
            index++;
        }
        return -1;
    }

}
