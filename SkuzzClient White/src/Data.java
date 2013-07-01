import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Data {
	public static String osuPath(){
		try {
			File config = new File("config.txt");
			Scanner reader = new Scanner(new FileReader(config));
			return reader.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null; 
	}
}
