package pacman_actor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import pacman_interface.Game;

/**
 * Level rappresenta il livello in cui si sta giocando
 * @author cris1
 *
 */
public class Level {

	public int width;
	public int height;
	public int val;
				
	public Tile[][] tiles;
	
	public List<Apple> apples;
	public List<Enemy> enemies;
	public List<Apple> firstShift = new ArrayList<>();
	public List<Apple> secondShift = new ArrayList<>();
	public List<Powerball> power;
	
	public BufferedImage map;
	public int[] pixels;
	
	public int type = 0;
	
	public static BufferedImage enemyText;
	
	/**
	 * Costruttore e inizializzazione livello (in relazione al path che corrisponde all'immagine interessata)
	 * @param path
	 */
	public Level(String path) {		
		apples  = new ArrayList<>();
		enemies = new ArrayList<>();		
		power   = new ArrayList<>();
		try {
			map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.height = map.getHeight();
			pixels = new int[width*height];
			tiles = new Tile[width][height];
			map.getRGB(0, 0, width, height, pixels, 0, width);
			
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					val = pixels[x +(y * width)];
					loadVal(val, x, y);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per impostare ogni blocco del livello (in relazione ai diversi elementi colorati della mappa)
	 * @param val valore di un blocco (da confrontare col colore)
	 * @param x posizione blocco coordinata x
	 * @param y posizione blocco coordinata y
	 */
	private void loadVal(int val, int x, int y) {
		if(val == 0xFF000000) {
			//tile
			tiles[x][y] = new Tile(x*32, y*32);						
		}
		else if(val == 0xFF0000FF) {
			//player
			Game.player.x = x * 32;
			Game.player.y = y * 32;
		}
		else if(val == 0xFFFF0000) {
			//enemy
			enemies.add(new Enemy(x*32, y*32, type++));
		}
		else if(val == 0xFF4CFF00) {
			apples.add(new Apple(x*32, y*32));
			firstShift.add(new Apple(x*32, y*32));
		}
		else if(val == 0xFFFFB368) {
			apples.add(new Apple(x*32, y*32));
			secondShift.add(new Apple(x*32, y*32));
		}
		else if(val == 0xFFFFD400) {
			power.add(new Powerball(x*32, y*32));
		}
		
		else apples.add(new Apple(x*32, y*32)); 
	}
	
	/**
	 * Iterazione di un ciclo (come il ciclo logico del gioco principale)
	 * Le posizioni e operazioni sono aggiornate.
	 */
	public void tick() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
		
		for (int j = 0; j < power.size(); j++) {
			power.get(j).tick();
		}
	}
	
	/**
	 * Disegna il livello.
	 * @param g nuovo oggetto grafico g costruito
	 */
	public void render(Graphics g) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(tiles[i][j] != null)
					tiles[i][j].render(g);
			}
		}
		
		for (int k = 0; k < apples.size(); k++) {
			apples.get(k).render(g);
		}
		
		for (int l = 0; l < power.size(); l++) {
			power.get(l).render(g);
		}
		
		for (int m = 0; m < enemies.size(); m++) {
			enemies.get(m).render(g);
		}
	}
	
}
