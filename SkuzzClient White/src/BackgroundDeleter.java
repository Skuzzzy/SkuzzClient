import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


public class BackgroundDeleter {
	
	public void deleteAllBackgrounds(){
		String[] songDir = songList();
		for(int z = 0; z<songDir.length;z++){

			Stack<String> popcorn= new Stack();
			Stack<File> osus=locateOSU(songDir[z]);
			while(!osus.isEmpty()){
				System.out.println(osus.peek().getAbsolutePath());
				Stack<String> temp = (scanOSUFile(osus.pop().getAbsolutePath()));
				while(!temp.isEmpty()){
					popcorn.push(temp.pop());
					
				}
			}

			while(!popcorn.isEmpty()){
				File toDelete = new File(Data.osuPath()+"//Songs//"+songDir[z]+"//"+popcorn.pop());
				if(toDelete.delete()){
	    			System.out.println(toDelete.getName() + " DELETED!");
	    		}else{
	    			//System.out.println("Did not delete "+toDelete.getName());
	    		}
			}

			
		}
	}
	
	public String[] songList(){
			File songFolder = new File(Data.osuPath()+"\\Songs");
			String[] directories = songFolder.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File dir, String name) {
			    return new File(dir, name).isDirectory();
			  }
			});
			System.out.println(Arrays.toString(directories));
			return directories;
	}
	
	public Stack<File> locateOSU(String foldername){
		Stack<File> result = new Stack();
		File targetSongFolder = new File(Data.osuPath()+"\\Songs\\"+foldername);
		
		File[] listOfFiles = targetSongFolder.listFiles(); 
		
		String files; 
		for (int i = 0; i < listOfFiles.length; i++) { 
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				if (files.endsWith(".osu")){
						result.push(new File(listOfFiles[i].getAbsolutePath()));
				}
			}
		}
		return result;
	}
	
	public Stack<String> scanOSUFile(String absPath){
		Stack<String> result = new Stack();
		try {
			File osuFile = new File(absPath);
			Scanner reader = new Scanner(new FileReader(osuFile));
			while(reader.hasNextLine()){
				String data=reader.nextLine();
				if(data.contains("0,0,\"")){
					int startIndex = data.indexOf("\""); int endIndex = data.lastIndexOf("\"");
					String thefile =data.substring(startIndex+1, endIndex);
					result.push(thefile);
					//System.out.println(thefile);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
