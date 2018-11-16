package com.inf4705.tp2;

import java.util.ArrayList;
import java.util.List;

import com.inf4705.tp2.model.Dynamite;

public class DecryptedFile {
    private List<Dynamite> dynamites = new ArrayList<>();
    private int goal = 0;

    public void addDynamite(Dynamite d) {
        dynamites.add(d);
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public List<Dynamite> getDynamites() {
        return dynamites;
    }

    public int getGoal() {
        return goal;
    }
}
