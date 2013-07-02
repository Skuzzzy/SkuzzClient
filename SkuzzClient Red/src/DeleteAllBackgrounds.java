import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class DeleteAllBackgrounds {
	public DeleteAllBackgrounds(){
		Stack<String> backgroundElements = scanOSUFile(FindOsu(SongFolders()));
		try {
			DeleteBackgroundElements(backgroundElements);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	private File[] SongFolders(){
		
		File songFolder = new File(Data.osuPath()+"\\Songs");
		File[] result = songFolder.listFiles();
		//for(int x=0;x<result.length;x++){
		//	System.out.println(result[x].getAbsolutePath());
		//}
		return result;

	}
	
	private Stack<File> FindOsu(File[] folderPath){
		Stack<File> result = new Stack();
		for(File songFile : folderPath){
			if(songFile.isDirectory()){
				File[] listOfFiles = songFile.listFiles(); 
				for(File innerFiles : listOfFiles){
					if (innerFiles.getName().endsWith(".osu")){
						//System.out.println("Got To Here "+innerFiles.getAbsolutePath()); REMOVE
						result.push(innerFiles);
					}
				}
			}
		}
		return result;	
	}
	
	private Stack<String> scanOSUFile(Stack<File> osuFiles){
		Stack<String> result = new Stack();
		for(File osuS : osuFiles){
			try {
				Scanner reader = new Scanner(new FileReader(osuS));
				while(reader.hasNextLine()){
					String data=reader.nextLine();
					if(data.contains("0,0,\"")){
						int startIndex = data.indexOf("\""); int endIndex = data.lastIndexOf("\"");
						String name =data.substring(startIndex+1, endIndex);
						if(!result.contains(osuS.getParent()+"\\"+name)){
							result.push(osuS.getParent()+"\\"+name);
						}
					}
				}
			} catch (FileNotFoundException e) {e.printStackTrace();}
		}
		return result;
	}
	
	private void DeleteBackgroundElements(Stack<String> backgroundElements) throws IOException{
		for(String elementPath : backgroundElements){
			
			
			File elementPathFile = new File(elementPath); //File To be Moved
			String filename = elementPathFile.getName(); //Name of File
			
			File targetFolder = new File(elementPathFile.getParent()+"\\DeletedMapElements");
			targetFolder.mkdirs();
			
			File target = new File(elementPathFile.getParent()+"\\DeletedMapElements"+"\\"+filename); //Location of Place
			
			elementPathFile.renameTo(target);

			//System.out.println(elementPath);
			
			//if(elementPathFile.delete()){
	    	//	System.out.println(elementPathFile.getName() + " DELETED!");
	    	//}else{
	    	//	//System.out.println("Did not delete "+toDelete.getName());
	    	//}
		}
	}
	
}
