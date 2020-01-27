package pacman_actor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import pacman_interface.Game;

/**
 * Enemy rappresenta ogni nemico che inseguirà Pacman
 * @author cris1
 *
 */
public class Enemy extends Rectangle{

	private static final long serialVersionUID = 1L;
	public int type;
	
	public boolean life = true;
	
	private int random = 0, smart = 1, find_path = 2;
	private int state = random;
	private int right = 0, left = 1, up = 2, down = 3;
	private int dir = -1;
	
	private int time = 0;
	private int targetTime = 60 * 4;
	public static int spd = 1;
	private int lastDir = -1;
	
	public Random randomGen;
	
	private int count = 0, target = 10;
	public int imageIndex = 0;

	/**
	 * Costruttore e inizializzazione del nemico del tipo richiesto e alle coordinate x e y richieste
	 * @param x coordinata x 
	 * @param y coordinata y
	 * @param type tipo nemico
	 */
	public Enemy(int x, int y, int type) {
		state = smart;
		randomGen = new Random();
		setBounds(x, y, 32, 32);
		
		dir = randomGen.nextInt(4);
		
		this.type = type;
	}
	
	/**
	 * Iterazione di un ciclo (come il ciclo logico del gioco principale)
	 * Le posizioni e operazioni sono aggiornate.
	 */
	public void tick() {
		if(state == random) {
			
			if(dir == right) {
		
				if(canMove(x + spd, y)) 
					x += spd;
				else
					dir = randomGen.nextInt(4);
			}
			else if(dir == left) {
				
				if(canMove(x - spd, y)) 
					x -= spd;
				else
					dir = randomGen.nextInt(4);
			}
			else if(dir == up) {
				
				if(canMove(x, y - spd)) 
					y -= spd;
				else
					dir = randomGen.nextInt(4);
			}
			else if(dir == down) {
				
				if(canMove(x, y + spd)) 
					y += spd;
				else
					dir = randomGen.nextInt(4);
			}
		}
		time++;
		
		if(time == targetTime) {
			state = smart;
			time = 0;
		}
		
		else if(state == smart) {
			boolean move = false;
			
			if(x < Game.player.x) {
				if(canMove(x + spd, y)) {
					x += spd;
					move = true;
					lastDir = right;
				}
			}
			
			if(x > Game.player.x) {
				if(canMove(x - spd, y)) {
					x -= spd;
					move = true;
					lastDir = left;
				}
			}
			
			if(y < Game.player.y) {
				if(canMove(x, y + spd)) {
					y += spd;
					move = true;
					lastDir = down;
				}
			}
			
			if(y > Game.player.y) {
				if(canMove(x, y - spd)) {
					y -= spd;
					move = true;
					lastDir = up;
				}
			}
			
			if(x == Game.player.x && y == Game.player.y)
				move = true;
			
			if(!move)
				state = find_path;
		}
		else if(state == find_path) {
			
			if(lastDir == right) {
				
				if(y < Game.player.y && canMove(x, y + spd))
					y += spd;
				else if (canMove(x, y - spd))
					y -= spd;
				if(canMove(x + spd, y))
					x += spd;
			}	
				
			else if(lastDir == left) {
				
				if(y < Game.player.y && canMove(x, y + spd))
					y += spd;
				else if (canMove(x, y - spd))
					y -= spd;
				if(canMove(x - spd, y))
					x -= spd;
			}	
				
			else if(lastDir == up) {
				
				if(x < Game.player.x && canMove(x + spd, y))
					x += spd;
				else if (canMove(x - spd, y))
					x -= spd;
				if(canMove(x, y - spd))
					y -= spd;
			}
				
			else if(lastDir == down) {
				
				if(x < Game.player.x && canMove(x + spd, y))
					x += spd;
				else if (canMove(x - spd, y))
					x -= spd;
				if(canMove(x, y + spd))
					y += spd;
			}
		}
		
		count++;
		
		if(count == target) {
			count = 0;
			imageIndex++;
		}
	}
	
	/**
	 * Metodo per verificare se il nemico in questione può muoversi in una determinata direzione
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
	 * Disegna il nemico di colore e dimensione personalizzata (in relazione a tipo e ultima direzione)
	 * @param g nuovo oggetto grafico g costruito
	 */
	public void render(Graphics g) {
		if(!life) return;
			
		switch(type) {
			case 0:
				if(lastDir == right)
					g.drawImage(Texture.ghostRedRight[imageIndex % 2], x, y, 32, 32, null); // Destra rosso
				else if(lastDir == left)
					g.drawImage(Texture.ghostRedLeft[imageIndex % 2], x, y, 32, 32, null); // Sinistra rosso
				else if(lastDir == down)
					g.drawImage(Texture.ghostRedDown[imageIndex % 2], x, y, 32, 32, null); // Giù rosso
				else if(lastDir == up)
					g.drawImage(Texture.ghostRedUp[imageIndex % 2], x, y, 32, 32, null); // Su rosso
				break;
			
			case 1:
				if(lastDir == right)
					g.drawImage(Texture.ghostPurpleRight[imageIndex % 2], x, y, 32, 32, null); // Destra viola
				else if(lastDir == left)
					g.drawImage(Texture.ghostPurpleLeft[imageIndex % 2], x, y, 32, 32, null); // Sinistra viola
				else if(lastDir == down)
					g.drawImage(Texture.ghostPurpleDown[imageIndex % 2], x, y, 32, 32, null); // Giù viola
				else if(lastDir == up)
					g.drawImage(Texture.ghostPurpleUp[imageIndex % 2], x, y, 32, 32, null); // Su viola
				break;
				
			case 2:
				if(lastDir == right)
					g.drawImage(Texture.ghostBlueRight[imageIndex % 2], x, y, 32, 32, null); // Destra azzurro
				else if(lastDir == left) 
					g.drawImage(Texture.ghostBlueLeft[imageIndex % 2], x, y, 32, 32, null); // Sinistra azzurro
				else if(lastDir == down)
					g.drawImage(Texture.ghostBlueDown[imageIndex % 2], x, y, 32, 32, null); // Giù azzurro
				else if(lastDir == up)
					g.drawImage(Texture.ghostBlueUp[imageIndex % 2], x, y, 32, 32, null); //	Su azzurro
				break;
				
			case 3:
				if(lastDir == right)
					g.drawImage(Texture.ghostOrangeRight[imageIndex % 2], x, y, 32, 32, null); // Destra arancione
				else if(lastDir == left)
					g.drawImage(Texture.ghostOrangeLeft[imageIndex % 2], x, y, 32, 32, null); // Sinistra arancione
				else if(lastDir == down)
					g.drawImage(Texture.ghostOrangeDown[imageIndex % 2], x, y, 32, 32, null); // Giù arancione
				else if(lastDir == up)
					g.drawImage(Texture.ghostOrangeUp[imageIndex % 2], x, y, 32, 32, null); // Su arancione	
				break;	
		}
	}
}
