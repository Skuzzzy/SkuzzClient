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
			// TODO 
			e.printStackTrace();
		}
		return null; //NOTE: IF STRING OSUPATH RETURNS NULL STOP TRYING TO PASS IT INTO WHATEVER FUNCTION
	}
}
