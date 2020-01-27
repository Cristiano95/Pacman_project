package pacman_actor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Powerball rappresenta ogni blocco utilizzato come bonus
 * @author cris1
 *
 */
public class Powerball extends Rectangle{

	private static final long serialVersionUID = 1L;
	public BufferedImage sheet;
	public BufferedImage[] texture;
	
	private int time = 0;
	private int targetframes = 35;
	private int imageIndex = 0;
	
	/**
	 * Costruttore e inizializzazione del blocco alle coordinate x e y richieste
	 * Ogni blocco ha larghezza e altezza pari a 32 
	 * @param x coordinata x
	 * @param y coordinata y
	 */
	public Powerball(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	/**
	 * Iterazione di un ciclo (come il ciclo logico del gioco principale)
	 * Le posizioni e operazioni sono aggiornate.
	 */
	public void tick() {
		time++;
		if(time == targetframes) {
			time = 0;
			imageIndex ++;
		}
	}
	
	/**
	 * Disegna il blocco in relazione alle immagini bonus create appositamente 
	 * @param g nuovo oggetto grafico g costruito
	 */
	public void render(Graphics g) {
		g.drawImage(Texture.power[imageIndex % 2], x, y, 33, 33, null);	
	}
	
}
