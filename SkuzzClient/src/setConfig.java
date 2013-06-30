import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;


public class setConfig {
	setConfig(){
	}
	public void doConfig(int type){
		switch(type){ //calling a setConfig will be used with the type
		case 1: //First Start (NO CONFIG FILE PRESENT)
			try {
				System.out.println("No Config File Found: Creating New config.txt");
				File config = new File("config.txt");
			
				config.createNewFile();
				
				System.out.println("Please Input Path to the Folder containing OSU!");
				System.out.println("Example: C:\\Program Files (x86)\\osu!");
				Scanner input = new Scanner(System.in);
				PrintWriter configWriter = new PrintWriter(new FileWriter(config), true);
		
				String pathToOsu = input.nextLine();
				if(pathToOsuIsCorrect(pathToOsu)){
					configWriter.write("[OSUPATH]"+pathToOsu+"\n");
					
				}
				configWriter.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		default:
			break;
			
		}
	}	
	boolean pathToOsuIsCorrect(String path){
			
		File osuExe = new File(path+"\\osu!.exe");
		if(osuExe.exists()){
			return true;
		}else{
			System.out.println("ERROR: NO OSU!.EXE DETECTED");
			return false;
		}
	}
		
	

}
