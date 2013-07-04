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
					System.out.println("Please Wait...");
					DeleteAllBackgrounds delbg = new DeleteAllBackgrounds();
					delbg = null;
					System.out.println("All backgounds have been stripped from folders.");
					break;
				case "rbgs":
					System.out.println("Please Wait...");
					RestoreAllBackgrounds rbgs = new RestoreAllBackgrounds();
					rbgs = null;
					System.out.println("Backgrounds Have Been Restored");
					break;
				case "ripall":
					System.out.println("Please Wait...");
					ripSongs ripper = new ripSongs();
					ripper=null;
					System.out.println("Done Ripping");
					break;
				default:
					System.out.println("ERROR: Cannot interpret command.");
					break;
			}
			
		}
	}
	

}
