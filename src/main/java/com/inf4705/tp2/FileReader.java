package com.inf4705.tp2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.inf4705.tp2.exception.InvalidDataSetsException;
import com.inf4705.tp2.model.Dynamite;

public class FileReader {
	private Scanner scanner;

	public FileReader(String path) throws IOException {
		File file = new File(path);
		scanner = new Scanner(file);
	}

	public DecryptedFile readFile() throws InvalidDataSetsException {
		int nbDynamites = Integer.parseInt(scanner.nextLine());
		DecryptedFile file = new DecryptedFile();

		int dynamitesRead = 0;
		while (dynamitesRead < nbDynamites) {
			if (!scanner.hasNextLine()) {
				throw new InvalidDataSetsException("The number of dynamites read is smaller than the expected number");
			}
			String line = scanner.nextLine();
			String[] parts = line.split("\\s+", 2);//space regEx

			if(parts.length < 2){
				throw new InvalidDataSetsException("Expected a dynamite to have format: <ID> <POWER>");
			}
			int id = Integer.parseInt(parts[0].trim());
			int power = Integer.parseInt(parts[1].trim());

			Dynamite dynamite = new Dynamite(id, power);
			file.addDynamite(dynamite);
			dynamitesRead++;
		}
		if (!scanner.hasNextLine()) {
			throw new InvalidDataSetsException("Missing power target for dynamites");
		}

		file.setGoal(Integer.parseInt(scanner.nextLine().trim()));

		return file;
	}
}
