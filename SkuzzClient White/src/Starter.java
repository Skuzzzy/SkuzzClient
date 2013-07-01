import java.util.Arrays;


public class Starter {
	public static void main(String[] args){
		Config testconfig = new Config();
		Data testData = new Data();
		System.out.println(Data.osuPath());
		BackgroundDeleter bgdel = new BackgroundDeleter();
		Arrays.toString(bgdel.songList());
		BackgroundDeleter asd = new BackgroundDeleter();
		asd.deleteAllBackgrounds();
	}
	
}
