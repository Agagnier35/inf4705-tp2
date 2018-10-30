package com.inf4705.tp2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.inf4705.tp2.algorithms.*;
import com.inf4705.tp2.exception.InvalidArgumentException;
import com.inf4705.tp2.model.Dynamite;

public class ApplicationStartup {
	public static void main(String[] args) throws Exception {
		String algo = args[0];
		String filePath = args[1];
		setUpOptionalArguments(args, 2);

		DecryptedFile file = new FileReader(filePath).readFile();

		BaseDynamiteMinimizer minimizer;
		switch (algo) {
			case "glouton":
				minimizer = new GloutonDynamiteMinimizer();
				break;
			case "progdyn1":
				minimizer = new ProgDyn1DynamiteMinimizer();
				break;
			case "progdyn2":
				minimizer = new ProgDyn2DynamiteMinimizer();
				break;
			case "recuit":
				minimizer = new RecuitDynamiteMinimizer();
				break;
			default:
				throw new InvalidArgumentException("Invalid algo argument, expected glouton, progdyn1, progdyn2 or recuit");
		}

		LocalDateTime start = LocalDateTime.now();
		List<Dynamite> resultSet = minimizer.minimizeDynamiteUsage(file.getDynamites(), file.getGoal());
		LocalDateTime end = LocalDateTime.now();

		long timeElapsed = Duration.between(start, end).toMillis();
		Logger.logTime(timeElapsed);
		Logger.logResult(resultSet);
	}

	private static void setUpOptionalArguments(String[] args, int index) throws Exception {
		if (args.length > index) {
			if ("-p".equals(args[index])) {
				Logger.p = true;
			} else if ("-t".equals(args[index])) {
				Logger.t = true;
			} else {
				throw new InvalidArgumentException("Invalid " + (index + 1) + " argument, expected -t or -p");
			}
			setUpOptionalArguments(args, index + 1);
		}
	}
}
