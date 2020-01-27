package pacman_actor;
import java.awt.Graphics;
import java.awt.Rectangle;

import pacman_interface.Game;
import play_music.Sound;

/**
 * Player rappresenta il personaggio di pacman gestito dall'utente
 * @author cris1
 *
 */
public class Player extends Rectangle{

	private static final long serialVersionUID = 1L;

	public boolean right, left, up, down;
	public static int speed = 4;
		
	private int time = 0, targetTime = 10;
	public int imageIndex = 0;
	private int lastDir;	
	
	private int count = 0;
		
	/**
	 * Costruttore e inizializzazione del player alle coordinate x e y richieste 
	 * (Dash è un booleano per gestire la posizione di inizializzazione del player in uscita dal teletrasporto)
	 * @param x coordinata x
	 * @param y coordinata y
	 * @param dash booleano dash per gestione corretta posizione
	 */
	public Player(int x, int y, boolean dash) {
		setBounds(x, y, 32, 32);
		
		if(dash)		  
			lastDir = -1; // Posizione sinistra (caso dash con comparsa a destra)
		else 
			lastDir = 1;  // Posizione destra
	}
	
	/**
	 * Metodo per verificare se pacman può muoversi in una determinata direzione
	 * @param nextx x della prossima posizione (che devo verificare)
	 * @param nexty y della prossima posizione (che devo verificare)
	 * @return
	 */
	private boolean canMove(int nextx, int nexty) {
		Rectangle bounds = new Rectangle(nextx, nexty, width, height);
		Level level = Game.level;
		
		for (int i = 0; i < level.tiles.length; i++) {
			for (int j = 0; j < level.tiles[0].length; j++) {
				if(level.tiles[i][j] != null) {
					if(bounds.intersects(level.tiles[i][j])) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Iterazione di un ciclo (come il ciclo logico del gioco principale)
	 * Le posizioni e operazioni sono aggiornate.
	 */
	public void tick() {	
		if(right && canMove(x + speed, y)) {x+=speed; lastDir = 1;}
		if(left  && canMove(x - speed, y)) {x-=speed; lastDir =-1;}
		if(up    && canMove(x, y - speed)) {y-=speed; lastDir = 2;}
		if(down  && canMove(x, y + speed)) {y+=speed; lastDir =-2;}
		
		Level level = Game.level;
				
		for (int i = 0; i < level.apples.size(); i++) {
			
			if(this.intersects(level.apples.get(i))) {
				Game.addScore(10);
				level.apples.remove(i);
				count++;
				if(count == 8)
					Enemy.spd = 1;
				break;
			}
		}

		if(level.apples.size() == 0) {
			//Level complete
			Game.STATE = Game.WIN;
			Game.musicwin.playSound(Sound.winClip);
		}
		
		for(int i = 0; i < level.firstShift.size(); i++){
			if(this.intersects(level.firstShift.get(i))) {						
				Game.player = new Player(0 + 32, Game.HEIGHT/2 - 16, false);
				Game.musicdash.playSound(Sound.dashClip);
			}																		
		}																			

		for(int i = 0; i < level.secondShift.size(); i++){
			if(this.intersects(level.secondShift.get(i))) {
				Game.player = new Player(Game.WIDTH - 64, Game.HEIGHT/2 - 16, true);
				Game.musicdash.playSound(Sound.dashClip);
			}
		}
		
		for (int i = 0; i < Game.level.enemies.size(); i++) {
			if(this.intersects(Game.level.enemies.get(i)) && Game.level.enemies.get(i).life)
				if(Enemy.spd == 0) {
					Game.level.enemies.get(i).life = false;				
				}
				else{
						Game.STATE = Game.PACMAN_DIED;
						Game.musiclose.playSound(Sound.loseClip);
				}
		}
		
		for (int i = 0; i < Game.level.power.size(); i++) {
			if(this.intersects(Game.level.power.get(i))) {
				Game.addScore(15);
				level.power.remove(i);
				count = 0;
				if(level.apples.size() > 8)
					Enemy.spd = 0;

			}
		}

		time++;
		
		if(time == targetTime) {
			time = 0;
			imageIndex++;
		}
		
	}

	/**
	 * Disegna il player.
	 * @param g nuovo oggetto grafico g costruito
	 */
	public void render(Graphics g) {
		if(lastDir == 1)		// Right
			g.drawImage(Texture.player[imageIndex % 2], x, y, 32, 32, null); 
		else if(lastDir == -1)  // Left 
			g.drawImage(Texture.player[imageIndex % 2], x + 32, y, -32, 32, null);
		else if(lastDir == 2)   // Up
			g.drawImage(Texture.player1[imageIndex % 2], x, y, 32, 32, null);
		else if(lastDir == -2)  // Down
			g.drawImage(Texture.player1[imageIndex % 2], x, y + 32, 32, -32, null);
		
		if(Game.STATE == Game.PACMAN_DIED) {
						if(Game.player.lastDir == 1) {
							g.drawImage(Texture.black, x - 4, y, 32, 32, null);
							g.drawImage(Texture.pacman_died, x, y, 32, 32, null);}
						else if(Game.player.lastDir == -1) {
							g.drawImage(Texture.black, x + 4, y, 32, 32, null);
							g.drawImage(Texture.pacman_died, x, y, 32, 32, null);}
						else if(Game.player.lastDir == 2) {
							g.drawImage(Texture.black, x, y + 4, 32, 32, null);
							g.drawImage(Texture.pacman_died, x, y, 32, 32, null);}
						else if(Game.player.lastDir == -2) {
							g.drawImage(Texture.black, x, y - 4, 32, 32, null);
							g.drawImage(Texture.pacman_died, x, y, 32, 32, null);}
		}
	}
	
}