package co.grandcircus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;



public class CountryTextFile {
	Scanner sc = new Scanner(System.in);
	String fileName = "src/countries.txt";
	Country c = new Country();
	ArrayList<Country> countryList = new ArrayList<>();

	public void creatAFile() {
		String cd = "src";
		Path filePath = Paths.get(fileName);

		File f = filePath.toFile();
		BufferedReader br = null;
		try {
			// FileReader fr = new FileReader(f);
			br = new BufferedReader(new FileReader(f));
			String line = br.readLine();

			while (line != null) {
//				System.out.println(line);
				line = br.readLine();
			}

			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("The file was not found...");

		} catch (IOException e) {
			System.out.println("file exists.");

		}
	}

	public void readFromAFile() {

		Path filePath = Paths.get(fileName);
		File f = filePath.toFile();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(f);

			br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			while (line != null) {
				String[] words = line.split(",");
				c.setName(words[0]);
				c.setPopulation(Long.parseLong(words[1]));
				
				countryList.add(c);
				System.out.println(c);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found...");
		} catch (IOException e) {
			System.out.println("Something crazy happened -- call someone who can help!");
		}

	}

	public void writeToAFile() {
		Path path = Paths.get(fileName);

		File file = path.toFile();
		PrintWriter output = null;

		try {
			output = new PrintWriter(new FileOutputStream(file, true));
			String countinue = "y";
			while (countinue.equalsIgnoreCase("y")) {
				c = new Country(Validator.getString(sc, "Enter the country name: "),
						Validator.getInt(sc, "Enter the population: "));
				output.println(c.getName() + "," + c.getPopulation());
				countinue = Validator.getStringMatchingRegex(sc, "Add more?(y/n)", "[yYnN ]+");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Yoooo, I don't know what's going on -- contact someone!");
		} finally {

			output.close();
		}
	}

}
