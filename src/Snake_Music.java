import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Snake_Music {
	
	Clip clip;
	File file;
	String[] songList = {"Music_1.wav","Music_3.wav","Music_4.wav","Powerup_1.wav  "};
	
	//Songlist
	//Eric Skiff - Underclocked  
	//Eric Skiff - A Night Of Dizzy Spells  
	//Jeremy Blake - Powerup! 
	//Joshua McLean - Mountain Trials
	
	
	Snake_Music(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		this.file = new File(songList[i]);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		this.clip = AudioSystem.getClip();
		clip.open(audioStream);
	}
	
	void close() {
		this.clip.close();
	}
	
	void stop() {
		this.clip.stop();
	}
	
	void start() {
		this.clip.start();
	}
	
}
