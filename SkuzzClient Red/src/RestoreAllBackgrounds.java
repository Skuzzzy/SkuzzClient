import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;


public class RestoreAllBackgrounds {
	public RestoreAllBackgrounds(){
		File[] sFolders = SongFolders(); //Finds all Song Folders
		for(File targetSong: sFolders){
			Stack<File> osuFiles = FindOsu(targetSong);
			Stack<String> toRestore = scanOSUFile(osuFiles);
			RestoreBackground(toRestore,targetSong);
		}

		
		
	}
	
	private File[] SongFolders(){	
		File songFolder = new File(Data.osuPath()+"\\Songs");
		File[] result = songFolder.listFiles();
		return result;
	}
	
	private void RestoreBackground(Stack<String> backgroundName, File songFile){
		while(!backgroundName.isEmpty()){
			
			File elementPathFile = new File(songFile.getAbsolutePath()+"\\DeletedMapElements\\"+backgroundName.peek()); //File To be Moved
			File targetFolder = new File(songFile.getAbsolutePath()+"\\"+backgroundName.pop());
			elementPathFile.renameTo(targetFolder);
		}
	}
	
	private Stack<File> FindOsu(File folderPath){
		Stack<File> result = new Stack();
		if(folderPath.isDirectory()){
			File[] listOfFiles = folderPath.listFiles(); 
			for(File innerFiles : listOfFiles){
				if (innerFiles.getName().endsWith(".osu")){
					result.push(innerFiles);
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
						if(!result.contains(name)){
							result.push(name);
						}
					}
				}
			} catch (FileNotFoundException e) {e.printStackTrace();}
		}
		return result;
	}
	
	
	
}
