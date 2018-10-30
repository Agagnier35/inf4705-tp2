package com.inf4705.tp2;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.inf4705.tp2.model.Dynamite;

public class Logger {
	public static boolean p = false;
	public static boolean t = false;

	public static void logLine(String text) {
		if (canLog()) {
			System.out.println(text);
		}
	}

	public static void logTime(long time) {
		if (t) {
			System.out.println(time);
		}
	}

	public static void logResult(List<Dynamite> res) {
		if (p) {
			StringBuilder builder = new StringBuilder();
			AtomicReference<String> separator = new AtomicReference<>("");
			res.forEach(dynamite -> {
				builder.append(separator.get()).append(dynamite.getId());
				separator.set(" ");
			});
			System.out.println(builder.toString());
		}
	}

	private static boolean canLog() {
		return !p && !t;
	}
}
