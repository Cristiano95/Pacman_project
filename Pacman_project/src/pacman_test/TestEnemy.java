package pacman_test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;

import pacman_actor.Enemy;

public class TestEnemy {
	
	Enemy en;
	int type = 0, x = 3, y = 2;
	
	@Before
	public void runBefore() {
		en = new Enemy(x*32, y*32, type);
	}
	
	@Test
	public void testLarghezzaAltezza() {
		assertEquals(32, (int)en.getWidth());
		assertEquals(32, (int)en.getHeight());
	}
	
	@Test
	public void testInitialPosition() {
		Point p = new Point(x*32, y*32);
		assertEquals(p, en.getLocation());
	} 
	
	@Test
	public void testDimension() {
		Dimension d = new Dimension(32, 32);
		assertEquals(d, en.getSize());
	}
	
	@Test
	public void testSpeed() {
		assertEquals(1, Enemy.spd);
	}
	
}
