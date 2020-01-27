package pacman_test;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import pacman_actor.Player;
import pacman_interface.Game;

public class TestPlayerPacman {

	Player pl;
	
	@Before
	public void runBefore() {
		pl = new Player(Game.WIDTH/2, Game.HEIGHT/2, false);
	}
	
	@Test
	public void testLarghezzaAltezza() {
		assertEquals(32, (int)pl.getWidth());
		assertEquals(32, (int)pl.getHeight());
	}
	
	@Test
	public void testInitialPosition() {
		Point p = new Point(Game.WIDTH/2, Game.HEIGHT/2);
		assertEquals(p, pl.getLocation());
	} 
	
	@Test
	public void testDimension() {
		Dimension d = new Dimension(32, 32);
		assertEquals(d, pl.getSize());
	}
	
	@Test
	public void testSpeed() {
		assertEquals(4, Player.speed);
	}
	
}
