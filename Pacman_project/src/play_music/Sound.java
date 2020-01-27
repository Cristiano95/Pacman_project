package play_music;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
	
/**
 * Gestisce i suoni presenti nel gioco
 * @author cris1
 *
 */
public class Sound {
	
	public static AudioClip soundClip, winClip, loseClip, overClip, completeClip, menuClip, dashClip;
	private static URL url;

	/**
	 * Costruttore per la gestione dei suoni
	 * @param path percorso del file audio
	 * @param number numero suono
	 */
	public Sound(String path, int number) {
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		switch(number) {
			case 0: soundClip 	 = Applet.newAudioClip(url); break;
			case 1: winClip 	 = Applet.newAudioClip(url); break;
			case 2: loseClip 	 = Applet.newAudioClip(url); break;
			case 3: overClip 	 = Applet.newAudioClip(url); break;
			case 4: completeClip = Applet.newAudioClip(url); break;
			case 5: menuClip 	 = Applet.newAudioClip(url); break;
			case 6: dashClip 	 = Applet.newAudioClip(url); break;			
		}
	}
	
	/**
	 * Play file audio
	 * @param clip file audio
	 */
	public void playSound(AudioClip clip) {
		clip.play();
	}
	
	/**
	 * Stop file audio
	 * @param clip file audio
	 */
	public void stopSound(AudioClip clip) {
		clip.stop();
	}
	
	/**
	 * Loop file audio
	 * @param clip file audio
	 */
	public void loopSound(AudioClip clip) {
		clip.loop();
	}
}