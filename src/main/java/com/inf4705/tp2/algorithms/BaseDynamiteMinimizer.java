package com.inf4705.tp2.algorithms;

import java.util.List;

import com.inf4705.tp2.model.Dynamite;

public abstract class BaseDynamiteMinimizer {
	public abstract List<Dynamite> minimizeDynamiteUsage(List<Dynamite> dynamites, int goal);
}
