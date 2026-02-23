package pkgDungeonDiver;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class MusicPlayer{
	//track if panel has been created 
	private static boolean fxExists = false;
	
	//panel creation 
	private static void initFX() {
		//create panel if does not exist
		if (!fxExists) {
			new JFXPanel();	
		}
	}
	
	//sound player
    public static void playSound(String sfx) {
    	//check that panel exists
    	initFX();
    	
        //establish path
        File file = new File("Sounds/" + sfx);
        //check that path is correct to prevent crashes
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }
        //continue path establishment
        Media media = new Media(file.toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        //set volume and play
        player.setVolume(Settings.volume/100.0);
        player.play();
    }
}
