package co.grandcircus;

import java.util.Scanner;

public class CountriesApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CountryTextFile ctf = new CountryTextFile();
		String fileName = "src/countries.txt";

		new CountryTextFile().creatAFile(fileName);
		System.out.println("Welcome to the Countries Maintenance Application!");
		String c = "y";
		while(c.equalsIgnoreCase("y")){
			System.out.println("**********Menu**********");
			int choice = Validator.getInt(sc, "1. See the list of countries \n2. Add a country "
					+ "\n3. Delete Country \n4. Exit \nSelect what would you like to do: " ) ;
			
			switch(choice) {
			case 1:
				ctf.readFromAFile();
				break;
			case 2:
				ctf.writeToAFile();
				break;
			case 3:
//				ctf.deleteCountry();//it has broken line which I haven't figure it out
				break;
			case 4:
				c = "n";
				break;
			default:
				System.out.println("Entry is in valid");
				break;
			}
			if(choice != 4) {
				c = Validator.getStringMatchingRegex(sc, "Back to main menu?(y/n)", "[yYnN ]+");
			}
			System.out.println("Goodbye!");
		}
	}
}