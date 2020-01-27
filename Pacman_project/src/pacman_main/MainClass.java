package pacman_main;

import javax.swing.JFrame;

import pacman_interface.Controller;
import pacman_interface.Game;

public class MainClass {

	public static void main(String[] args) {
		Game game = new Game();
		new Controller(game);
		
        JFrame frame = new JFrame();    // JFrame utilizzato per l'interfaccia grafica
        
        frame.setTitle(Game.TITLE);
        frame.add(game);									  // Aggiunta di "game" al contentPane
        frame.setResizable(false);							  // Per evitare che l'utente ridimensioni il frame
        frame.pack();										  // Racchiude i componenti all'interno della finestra in base alle dimensioni.
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiusura processo. Consente di uscire dall'applicazione.
        frame.setVisible(true);

        game.start(); 					// Metodo start() della classe Game per l'attivazione del Thread
	}
	
}