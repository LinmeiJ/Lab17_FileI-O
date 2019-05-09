package co.grandcircus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;



public class CountryTextFile {
	Scanner sc = new Scanner(System.in);
	Country c = new Country();
	
	ArrayList<Country> countryList = new ArrayList<>();
	public void creatAFile(String fileName) {
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
		String fileName = "src/countries.txt";
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
		String fileName = "src/countries.txt";
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
	
	
	//trying to delete the old file by moving all countries(except the one that needs to be deleted) into a new file
	//then rename the new file with the old file name.
	public void deleteCountry() {
		String fileName = "src/countries.txt";
		String tempFile = "src/temp.txt";
		String deleteC = Validator.getString(sc, "Enter the country you wish to delete:");
		File f = new File(fileName);
		File t = new File(tempFile);
		try {
			//connect both files
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner sc = new Scanner(new File(fileName));
			sc.useDelimiter("[,\n]"); //seperate fileds by new line or a comma.
			while(sc.hasNext()) {
				c.setName(sc.next());
				System.out.println(sc.next());
				//broken line!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
				c.setPopulation(Long.parseLong(sc.next()));//why doesn't pass this one?
				
				if(c.getName().equals(deleteC)) {
					pw.println(c.getName() + "," + c.getPopulation());
				}
			}
			sc.close();
			pw.flush();
			pw.close();
			f.delete();//delete the original file
			File oldFile = new File(fileName);
			t.renameTo(oldFile);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong, cann't help!");
		}
		
	}

}
