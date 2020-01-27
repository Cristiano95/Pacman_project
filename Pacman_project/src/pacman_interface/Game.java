package pacman_interface;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import pacman_actor.Level;
import pacman_actor.Player;
import pacman_actor.Texture;
import play_music.Sound;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final Color overColor = new Color(46, 211, 244);
	public static final Color pauseColor = new Color(30, 0, 0, 2); // 100 rosso, 30 nero

	public static final int WIDTH = 768, HEIGHT = 480;
	
	public static final String TITLE = "Pacman";
	
	private static int score;
    private static int highscore;
	
	private Thread thread;
	private boolean isRunning = false;
		
	public static Player player;				
	public static Level level;
	public static Texture texture;
	public static int life = 3;
	public static int levelnumber = 1;
	
	public static final int MENU = 0, GAME = 1, PAUSE = 2, PACMAN_DIED = 3, GAME_OVER = 4, WIN = 5, LOSE = 6, COMPLETE = 7;
	public static int STATE = -1;

	private int time = 0;
	private int targetframes = 45;
	private boolean showText = true;	

	public static Sound music, musicwin, musiclose, musicover, musiccompl, musicmenu, musicdash;
	
	public Game() {
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
				
		player = new Player(Game.WIDTH/2, Game.HEIGHT/2, false);
		level = new Level("/resources/Map1.png");	
				
		music 	   = new Sound("file:/C:/Users/cris1/eclipse-workspace/Pacman_project/src/music/Theme.wav", 0);
		musicwin   = new Sound("file:/C:/Users/cris1/eclipse-workspace/Pacman_project/src/music/Win.wav",   1);
		musiclose  = new Sound("file:/C:/Users/cris1/eclipse-workspace/Pacman_project/src/music/Lose.wav",  2);
		musicover  = new Sound("file:/C:/Users/cris1/eclipse-workspace/Pacman_project/src/music/Over.wav",  3);
		musiccompl = new Sound("file:/C:/Users/cris1/eclipse-workspace/Pacman_project/src/music/Compl.wav", 4);
		musicmenu  = new Sound("file:/C:/Users/cris1/eclipse-workspace/Pacman_project/src/music/Menu.wav",  5);
		musicdash  = new Sound("file:/C:/Users/cris1/eclipse-workspace/Pacman_project/src/music/Dash.wav",  6);
		
		STATE = MENU;
		
		texture = new Texture(initImages());
		musicmenu.loopSound(Sound.menuClip);
	}
	
	public synchronized void start() {
		if(isRunning) return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void tick() {
		if(STATE == GAME) {
			player.tick();
			level.tick();		
		}
		else if(STATE == MENU || STATE == GAME_OVER || STATE == COMPLETE) {
			time++;
			if(time == targetframes) {
				time = 0;
				if(showText)
					showText = false;
				else
					showText = true;
			}
		}
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();	// Crea un contesto grafico per il buffer di disegno
		
		if(STATE == GAME) {
			musicmenu.stopSound(Sound.menuClip);
			
			g.setColor(Color.black);
			g.fillRect(0,  0,  Game.WIDTH, Game.HEIGHT);
			
			player.render(g);
			level.render(g);
									
			g.setColor(Color.white);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
			
			g.drawString("Vite: " + life,           Game.WIDTH/3 - 220, Game.HEIGHT - 7);
			g.drawString("Punteggio: " + score,     Game.WIDTH/12 - 28, Game.HEIGHT/12 - 15);
			g.drawString("Record: " + highscore,    Game.WIDTH - 200,   Game.HEIGHT/12 - 15);
			g.drawString("Livello: " + levelnumber, Game.WIDTH - 150,   Game.HEIGHT - 7);
		}	
		else if(STATE == PAUSE) {
			g.setColor(pauseColor);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);	
			
			g.setColor(Color.white);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
			g.drawString("Pausa", Game.WIDTH/2 - 59, Game.HEIGHT/5 - 6);		
			
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
			g.drawString("premi il tasto Invio per continuare", Game.WIDTH/3 - 80,  Game.HEIGHT/2 - 40);		
			g.drawString("premi il tasto Esc per tornare al menù principale", Game.WIDTH/3 - 160, Game.HEIGHT/2 + 25);		
			
			g.setColor(pauseColor);
			g.fillRect(Game.WIDTH/3 - 80, Game.HEIGHT/3 + 12, Game.WIDTH/2 + 37, Game.HEIGHT/12);	
			g.fillRect(Game.WIDTH/3 - 160, Game.HEIGHT/2 - 3, Game.WIDTH - 171, Game.HEIGHT/12);				
		}
		else if(STATE == PACMAN_DIED) {
				player.render(g);
		}
		else if(STATE == LOSE) {
			g.setColor(Color.red);
			g.fillRect(0, Game.HEIGHT/2 - 25, Game.WIDTH, Game.HEIGHT/8 - 10);
			
			g.setColor(Color.white);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
			g.drawString("Vita persa", Game.WIDTH/2 - 60, Game.HEIGHT/2 + 10);
		}
		else if(STATE == MENU) {
			score = 0;
			
			g.setColor(Color.black);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);	
			
			g.setColor(Color.white);
			g.fillRect(0, Game.HEIGHT/3 - 20, Game.WIDTH, Game.HEIGHT/2 - 40);
			
			g.drawImage(Texture.title, Game.WIDTH/6 + 6, Game.HEIGHT/4 + 20, Game.WIDTH - 268, Game.HEIGHT/2 - 40, null);
			
			if(showText){
				g.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
				g.drawString("Premi Invio per iniziare la partita!", Game.WIDTH/4 - 16, Game.HEIGHT - 110);
			}
		}
		else if(STATE == GAME_OVER){
			score = 0;
			
			g.setColor(overColor);
			g.fillRect(32, 140, Game.WIDTH - 64, Game.HEIGHT/3 + 30);	
			
			g.setColor(Color.white);
			g.fillRect(32, 170, Game.WIDTH - 64, Game.HEIGHT/4 + 10);
			
			g.drawImage(Texture.overTitle, Game.WIDTH/6 + 6, Game.HEIGHT/3 + 40, Game.WIDTH - 268, Game.HEIGHT/6 - 10, null);	
			
			if(showText) {
				g.setColor(Color.black);
				g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
				g.drawString("Peccato, hai perso!  Premi Invio per giocare ancora!", Game.WIDTH/6 + 12, Game.HEIGHT/2 + 55);
			}
		}
		else if(STATE == WIN){
			g.setColor(overColor);
			g.fillRect(0, Game.HEIGHT/2 - 25, Game.WIDTH, Game.HEIGHT/8 - 10);
			
			g.setColor(Color.white);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
			g.drawString("Livello " + levelnumber + " completato!", Game.WIDTH/3, Game.HEIGHT/2 + 10);
		}
		else if(STATE == COMPLETE) {
			score = 0;
			
			g.setColor(Color.black);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
				
			g.drawImage(Texture.winTitle[time], Game.WIDTH/12 - 10, Game.HEIGHT/5 - 6, Game.WIDTH - 108, Game.HEIGHT/2 + 50, null);
			
			g.setColor(Color.white);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
			g.drawString("Complimenti! Hai completato il gioco!", Game.WIDTH/3 - 55, Game.HEIGHT - 40);
			
			if(showText) {
				g.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
				g.drawString("Premi Invio per giocare ancora!", Game.WIDTH/2 - 112, Game.HEIGHT - 10);
			}
		}
		
		g.dispose(); // Disinstalla questo contesto grafico e rilascia tutte le risorse di sistema che sta utilizzando
		bs.show();	 // Rende visibile il prossimo buffer disponibile 
	}
	
	public static void addScore(int point) {
        score += point;
        if (score > highscore)
            highscore = score;
    }

	public String[] initImages() {
        String img[] = new String[100];
        
        for (int d = 0; d < 4; d++) {
            for (int i = 0; i < 4; i++)
                img[i + 4 * d] = "/resources/pacman_" + d + "_" + i + ".png"; 	//Fino a posizione 15 (0 a 15)
        }
        
        int n = 16;
        for (int k = 0; k < 4; k++) {
	        for (int l = 0; l < 8; l++)
				img[n + l] = "/resources/ghost_" + k + "_" + l + ".png"; 		//Fino a posizione 23 (16 a 23) ... così fino a 47
	        n = n + 8;
        }
        
        for (int o = 0; o < 2; o++)
			img[48 + o] = "/resources/punto" + o + ".png"; 						//Fino a posizione 49 (48 a 49)
        
        for (int p = 0; p < 46; p++)
			img[50 + p] = "/gif/tmp-" + p + ".png";								//Fino a posizione 95 (50 a 95)	
        
        img[96] = "/resources/pacman_died.png";
        img[97] = "/resources/titlepac.png";
        img[98] = "/resources/gameover.png";
        img[99] = "/resources/black_screen.png";
        
        return img;
    }
	
	@Override
	public void run() {
		requestFocus();	// Richiede che il componente sia visibile
		int fps = 0;	// Frame per secondo
		double timer = System.currentTimeMillis(); 
		long lastTime = System.nanoTime();			// Ritorna il valore corrente di esecuzione della Java Virtual Machine
		double targetTick = 60.0;					// "Velocità" di gioco
		double delta = 0;
		double ns = 1000000000 / targetTick; 		// Intervallo tra i diversi tick()
		
		while(isRunning) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				render();
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println(fps);
				fps = 0;
				timer += 1000;
			}		
		}
		stop();
	}

	public void setListener(KeyListener k) {
		this.addKeyListener(k);	 // L'oggetto Listener creato da questa classe viene registrato con un componente 
	}							 // con l'addKeyListener del componente.
	
}
