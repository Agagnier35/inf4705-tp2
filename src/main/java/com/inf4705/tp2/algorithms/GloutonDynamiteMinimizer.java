package com.inf4705.tp2.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.inf4705.tp2.model.Dynamite;

public class GloutonDynamiteMinimizer extends BaseDynamiteMinimizer {

    @Override
    public List<Dynamite> minimizeDynamiteUsage(List<Dynamite> dynamites, int goal) {
        dynamites.sort(Comparator.comparing(Dynamite::getPower).reversed());
        List<Dynamite> solution = new ArrayList<>();
        int sum = 0;
        for (Dynamite d : dynamites) {
            if (sum == goal) {
                return solution;
            }
            if (sum + d.getPower() <= goal) {
                solution.add(d);
                sum += d.getPower();
            }
        }
        return solution;
    }

    @Override
    public double getFX(int goal, int nDynamites, int mSolutionSize) {
        return nDynamites * Math.log(nDynamites);
    }

}
