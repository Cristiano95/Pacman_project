package pacman_actor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Texture viene utilizzata per gestire le immagini
 * @author cris1
 *
 */
public class Texture {
	private BufferedImage[] frames;

	public static BufferedImage[] player;
	public static BufferedImage[] player1;
	
	public static BufferedImage[] ghostRedRight;
	public static BufferedImage[] ghostRedLeft;
	public static BufferedImage[] ghostRedUp;
	public static BufferedImage[] ghostRedDown;
	
	public static BufferedImage[] ghostPurpleRight;
	public static BufferedImage[] ghostPurpleLeft;
	public static BufferedImage[] ghostPurpleUp;
	public static BufferedImage[] ghostPurpleDown;
	
	public static BufferedImage[] ghostBlueRight;
	public static BufferedImage[] ghostBlueLeft;
	public static BufferedImage[] ghostBlueUp;
	public static BufferedImage[] ghostBlueDown;
	
	public static BufferedImage[] ghostOrangeRight;
	public static BufferedImage[] ghostOrangeLeft;
	public static BufferedImage[] ghostOrangeUp;
	public static BufferedImage[] ghostOrangeDown;
	
	public static BufferedImage[] power;
	
	public static BufferedImage[] winTitle;
	
	public static BufferedImage   pacman_died;
	public static BufferedImage   black;
	public static BufferedImage   title;
	public static BufferedImage   overTitle;
	
	/**
	 * Costruttore per lettura e caricamento di tutte le immagini
	 * @param framesRes insieme di stringhe (stringhe passate da game)
	 */
	public Texture(String ... framesRes) {
		readImages(framesRes);	// Lettura di tutte le stringhe e inserimento delle immagini in frames
		loading();				// Caricamento delle immagini
	}	

	/**
	 * Metodo per la lettura di ogni stringa e inserimento in un vettore
	 * @param framesRes insieme di stringhe
	 */
	private void readImages(String ... framesRes) {
        try {
            frames = new BufferedImage[framesRes.length];
            for (int i = 0; i < framesRes.length; i++) {
                String frameRes = framesRes[i];
                frames[i] = ImageIO.read(getClass().getResourceAsStream(frameRes));
            }
        } catch (IOException ex) {
            System.exit(-1);
        }
    } 
	
	/**
	 * Metodo per il caricamento delle immagini nei vettori di BufferedImage
	 */
	private void loading() {
		//Pacman
		player = new BufferedImage[2]; // Right & Left
		player[0] = frames[1];
		player[1] = frames[2];
		
		player1 = new BufferedImage[2]; // Up & Down
		player1[0] = frames[13];
		player1[1] = frames[14];
		
		//Rosso
		ghostRedRight = new BufferedImage[2];
		ghostRedRight[0] = frames[16];
		ghostRedRight[1] = frames[17];
		
		ghostRedDown = new BufferedImage[2];
		ghostRedDown[0] = frames[18];
		ghostRedDown[1] = frames[19];
		
		ghostRedLeft = new BufferedImage[2];
		ghostRedLeft[0] = frames[20];
		ghostRedLeft[1] = frames[21];
		
		ghostRedUp = new BufferedImage[2];
		ghostRedUp[0] = frames[22];
		ghostRedUp[1] = frames[23];
		
		//Purple
		ghostPurpleRight = new BufferedImage[2];
		ghostPurpleRight[0] = frames[24];
		ghostPurpleRight[1] = frames[25];
		
		ghostPurpleDown = new BufferedImage[2];
		ghostPurpleDown[0] = frames[26];
		ghostPurpleDown[1] = frames[27];
		
		ghostPurpleLeft = new BufferedImage[2];
		ghostPurpleLeft[0] = frames[28];
		ghostPurpleLeft[1] = frames[29];
		
		ghostPurpleUp = new BufferedImage[2];
		ghostPurpleUp[0] = frames[30];
		ghostPurpleUp[1] = frames[31];
		
		//Blue
		ghostBlueRight = new BufferedImage[2];
		ghostBlueRight[0] = frames[32];
		ghostBlueRight[1] = frames[33];
		
		ghostBlueDown = new BufferedImage[2];
		ghostBlueDown[0] = frames[34];
		ghostBlueDown[1] = frames[35];
		
		ghostBlueLeft = new BufferedImage[2];
		ghostBlueLeft[0] = frames[36];
		ghostBlueLeft[1] = frames[37];
		
		ghostBlueUp = new BufferedImage[2];
		ghostBlueUp[0] = frames[38];
		ghostBlueUp[1] = frames[39];
		
		//Orange
		ghostOrangeRight = new BufferedImage[2];
		ghostOrangeRight[0] = frames[40];
		ghostOrangeRight[1] = frames[41];
		
		ghostOrangeDown = new BufferedImage[2];
		ghostOrangeDown[0] = frames[42];
		ghostOrangeDown[1] = frames[43];
		
		ghostOrangeLeft = new BufferedImage[2];
		ghostOrangeLeft[0] = frames[44];
		ghostOrangeLeft[1] = frames[45];
		
		ghostOrangeUp = new BufferedImage[2];
		ghostOrangeUp[0] = frames[46];
		ghostOrangeUp[1] = frames[47];
		
		//Power
		power = new BufferedImage[2];
		power[0] = frames[48];
		power[1] = frames[49];
		
		winTitle = new BufferedImage[46];
		for(int i = 0; i < 46; i++)
			winTitle[i] = frames[50 + i]; // da 50 a 95
		
		pacman_died = frames[96];
		title	    = frames[97];
		overTitle   = frames[98];
		black 	    = frames[99];
	}
	
}