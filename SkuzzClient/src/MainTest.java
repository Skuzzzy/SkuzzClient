import java.io.File;
public class MainTest {
	public static void main(String[] args){
		File config = new File("config.txt");
		if(!config.exists()){ //If config file for Client does not exist make a new config and have user input data into it.
			setConfig firststart = new setConfig();
			firststart.doConfig(1);
		}
		
		
		
	}
	
}
