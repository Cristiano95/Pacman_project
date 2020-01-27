package pacman_actor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

/**
 * Tile rappresenta ogni blocco che andrà a formare i muri del livello
 * @author cris1
 *
 */
public class Tile extends Rectangle {

	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore e inizializzazione del blocco alle coordinate x e y richieste
	 * Ogni blocco ha larghezza e altezza pari a 32 
	 * @param x coordinata x
	 * @param y coordinata y
	 */
	public Tile(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	/**
	 * Disegna il blocco di colore blu (personalizzato)
	 * @param g nuovo oggetto grafico g costruito
	 */
	public void render(Graphics g) {
		g.setColor(new Color(24,24,189));
		g.fillRect(x, y, width, height);
	}
	
}
