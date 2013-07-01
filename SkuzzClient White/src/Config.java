import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Config {
	File config = new File("config.txt");
	public Config(){
		try {
			if(config.createNewFile()){
				if(attemptDefaultPath()){
					System.out.println("Default OSU path found, writing to config");
					PrintWriter configWriter = new PrintWriter(new FileWriter(config), true);
					configWriter.write("C:\\Program Files (x86)\\osu!");
					configWriter.close();
				}else{
					System.out.println("Default Path to OSU not found. Please input path to osu directory");
					System.out.println("Example C:\\Program Files (x86)\\osu!");
					Scanner input = new Scanner(System.in);
					
					File testPath;
					do{
						System.out.print(">");
						testPath = new File(input.nextLine());
					}while(!pathIsCorrect(testPath));
					PrintWriter configWriter = new PrintWriter(new FileWriter(config), true);
					configWriter.write(testPath.getPath());
					configWriter.close();
				}
				//Set up Initial Stuff
			}	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//End of Config Constructor
	}
	
	private boolean attemptDefaultPath(){
		File osuDefaultPath = new File("C:\\Program Files (x86)\\osu!\\osu!.exe");
		if(osuDefaultPath.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean pathIsCorrect(File pathToTest){
		System.out.println(pathToTest.getPath());
		File test = new File(pathToTest.getPath()+"\\osu!.exe");
		if(test.exists()){
			System.out.println("Inputted Path Is Correct");
			return true;
		}else{
			System.out.println("Inputted Path is INCORRECT");
			return false;
		}
	}
}
