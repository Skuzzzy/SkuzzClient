import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Scanner;

public class ripSongs {
	public ripSongs(){
		
	File[] allSongs = SongFolders();
	
	File songRip = new File(Data.osuPath()+"\\OSU! Songs");
	songRip.mkdirs();
	
		for(File chosenSong : allSongs){
			File osuFile = FindOsu(chosenSong);
			
			String[] infoStuff = new String[3];
			if(!(osuFile==null)){
				infoStuff = getSongInfo(osuFile);
			}else{
				break;
			}
			
			if(!(infoStuff[0].isEmpty())&!(infoStuff[1].isEmpty())&!(infoStuff[2].isEmpty())){
				
				int index=infoStuff[0].lastIndexOf(".");
				String extension = infoStuff[0].substring(index);
	
				infoStuff[2]=infoStuff[2].replace("*", ".").replace("?", ".").replace("\\", ".").replace("<", ".").replace(">", ".").replace("/", ".").replace("|", ".").replace("\"", ".");
				infoStuff[1]=infoStuff[1].replace("*", ".").replace("?", ".").replace("\\", ".").replace("<", ".").replace(">", ".").replace("/", ".").replace("|", ".").replace("\"", ".");
				
				File origMpthree = new File(chosenSong.getAbsolutePath()+"\\"+infoStuff[0]);//FROM
				
				File songFile = new File(songRip.getAbsolutePath()+"\\"+infoStuff[2]+"-"+infoStuff[1]+extension);//TO
				
				try {
					Files.copy(origMpthree.toPath(), songFile.toPath() );
				} catch (IOException e1) {
					//UH I SHOULD REALLY HANDLE THESE. 
					//THIS ERROR WILL THROW IF THE SONG FILE ALREADY EXISTS
				}	
			}
		}
	}
	
	private File[] SongFolders(){
		File songFolder = new File(Data.osuPath()+"\\Songs");
		File[] result = songFolder.listFiles();
		return result;
	}
	
	private File FindOsu(File folderPath){
		if(folderPath.isDirectory()){
			File[] listOfFiles = folderPath.listFiles(); 
			for(File innerFiles : listOfFiles){
				if (innerFiles.getName().endsWith(".osu")){
					return innerFiles;
				}
			}
		}
		return null;
	}
	
	private String[] getSongInfo(File osuFile){
		String[] result = new String[3];
		//INDEX 0 FOR THE MP3
		//INDEX 1 FOR TITLE
		//INDEX 2 FOR ARTIST
		try {
			
			Scanner reader;
			reader = new Scanner(new FileReader(osuFile));
			while(reader.hasNextLine()){
				String data=reader.nextLine();	
				if(data.contains("AudioFilename:")){
					String[] wData = data.split(":");
					result[0]=wData[1].trim();
				}
				if(data.contains("Title:")){
					String[] wData = data.split(":");
					result[1]=wData[1];
				}
				if(data.contains("Artist:")){
					String[] wData = data.split(":");
					result[2]=wData[1];
				}

			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		return result;
	}
	
}
