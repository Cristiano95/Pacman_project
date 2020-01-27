package pacman_test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;

import pacman_actor.Tile;

public class TestTile {
	
	Tile tl;
	int x = 6, y = 3;
	
	@Before
	public void runBefore() {
		tl = new Tile(x*32, y*32);
	}
	
	@Test
	public void testLarghezzaAltezza() {
		assertEquals(32, (int)tl.getWidth());
		assertEquals(32, (int)tl.getHeight());
	}
	
	@Test
	public void testPosition() {
		Point p = new Point(x*32, y*32);
		assertEquals(p, tl.getLocation());
	} 
	
	@Test
	public void testDimension() {
		Dimension d = new Dimension(32, 32);
		assertEquals(d, tl.getSize());
	}
}
