package pacman_actor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Apple rappresenta ogni blocco che dovrà essere mangiato da Pacman al fine di completare il livello
 * @author cris1
 *
 */
public class Apple extends Rectangle{

	private static final long serialVersionUID = 1L; // Identificatore di versione universale per una classe Serializable 
	// Serializzazione = meccanismo che permette di salvare un oggetto su uno stream di byte che successivamente può essere
	// salvato su un file o inviato sulla rete

	/**
	 * Costruttore e inizializzazione del blocco alle coordinate x e y richieste
	 * Ogni blocco ha larghezza e altezza pari a 32 
	 * @param x coordinata x
	 * @param y coordinata y
	 */
	public Apple(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	/**
	 * Disegna il blocco di colore verde e dimensione ridotta
	 * @param g nuovo oggetto grafico g costruito
	 */
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x + 11, y + 11, 8, 8);
	}
}
