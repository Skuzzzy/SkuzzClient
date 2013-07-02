import java.util.Scanner;

//THIS IS A TEMPORARY SOLUTION TO ALLOW TESTING OF FEATURES
//USE THIS FOR TESTING ONLY

public class TempCommandLineInput {
	Scanner tempScanner = new Scanner(System.in);
	boolean exitCommandExecuted=false;
	public TempCommandLineInput(){
		while(!exitCommandExecuted){
			System.out.print(">");
			String commandInput = tempScanner.nextLine();
			
			switch(commandInput){
				case "exit":
					System.out.println("Goodbye! Thanks for trying SkuzzClient!");
					exitCommandExecuted=true;
					break;
				case "delbgs":
					DeleteAllBackgrounds delbg = new DeleteAllBackgrounds();
					delbg = null;
					System.out.println("All backgounds have been stripped from folders.");
					break;
				case "restoreall":
					//RESTORE BACKGROUND CODE HERE
					break;
				default:
					System.out.println("ERROR: Cannot interpret command.");
					break;
			}
			
		}
	}
	

}
