package pacman_interface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pacman_actor.Enemy;
import pacman_actor.Level;
import pacman_actor.Player;
import play_music.Sound;

public class Controller implements KeyListener{ // L'interfaccia listener per ricevere eventi da tastiera

	private Game view;
	
	public Controller(Game view){
		this.view = view;
		this.view.setListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(Game.STATE == Game.GAME) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				Game.player.right = true;
			if(e.getKeyCode() == KeyEvent.VK_DOWN)  
				Game.player.down = true;	
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
				Game.player.left = true; 
			if(e.getKeyCode() == KeyEvent.VK_UP)
				Game.player.up = true;   
						
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Game.music.stopSound(Sound.soundClip);
				Game.STATE = Game.PAUSE;
			}
		}
		else if(Game.STATE == Game.MENU) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Game.life = 3;
				Enemy.spd = 1;
				Game.music.loopSound(Sound.soundClip);
				Game.STATE = Game.GAME;
			}
		}
		else if(Game.STATE == Game.PAUSE) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Game.music.loopSound(Sound.soundClip);
				Game.STATE = Game.GAME;
			}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Game.levelnumber = 1;
				Game.life = 3;
				Game.musicmenu.loopSound(Sound.menuClip);
				
				Game.level = new Level("/resources/Map" + Game.levelnumber + ".png");
				Game.STATE = Game.MENU;
			}
		}
		else if(Game.STATE == Game.GAME_OVER) {
			Game.levelnumber = 1;
			Game.life = 3;
			Enemy.spd = 1;
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Game.music.loopSound(Sound.soundClip);
				Game.STATE = Game.GAME;	
			}
			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Game.music.stopSound(Sound.soundClip);
				Game.musicmenu.loopSound(Sound.menuClip);
				Game.STATE = Game.MENU;
			}
			Game.player = new Player(Game.WIDTH/2, Game.HEIGHT/2, false);
			Game.level = new Level("/resources/Map" + Game.levelnumber + ".png");		
		}
		else if(Game.STATE == Game.PACMAN_DIED) {
			Game.STATE = Game.LOSE;
		}
		else if(Game.STATE == Game.LOSE) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(Game.life == 0) {
					Game.music.stopSound(Sound.soundClip);
					Game.musicover.playSound(Sound.overClip);
					Game.STATE = Game.GAME_OVER;
				}
				else {
					Game.life--;
					Game.STATE = Game.GAME;
				}
				Enemy.spd = 1;

				Game.player = new Player(Game.WIDTH/2, Game.HEIGHT/2, false);
				Game.level = new Level("/resources/Map" + Game.levelnumber + ".png");		
			}
		}
		else if(Game.STATE == Game.WIN) {
			if(Game.levelnumber > 2 && e.getKeyCode() == KeyEvent.VK_ENTER) {
				Game.music.stopSound(Sound.soundClip);
				Game.musiccompl.playSound(Sound.completeClip);
				Game.STATE = Game.COMPLETE;
			}
			else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Game.levelnumber++;
				Game.STATE = Game.GAME;	
			}
			Game.player = new Player(Game.WIDTH/2, Game.HEIGHT/2, false);
			Game.level = new Level("/resources/Map" + Game.levelnumber + ".png");		
		}
		else if(Game.STATE == Game.COMPLETE) {
			Game.levelnumber = 1;
			Game.life = 3;
			Game.music.stopSound(Sound.soundClip);
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Game.music.loopSound(Sound.soundClip);
				Game.STATE = Game.GAME;	
			}
			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Game.music.stopSound(Sound.soundClip);
				Game.musicmenu.loopSound(Sound.menuClip);
				Game.STATE = Game.MENU;
			}
			Game.player = new Player(Game.WIDTH/2, Game.HEIGHT/2, false);
			Game.level = new Level("/resources/Map" + Game.levelnumber + ".png");		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) Game.player.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 	Game.player.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP) 	Game.player.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 	Game.player.down = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
