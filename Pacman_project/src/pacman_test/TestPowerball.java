package pacman_test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;

import pacman_actor.Powerball;

public class TestPowerball {

	Powerball pw;
	int x = 3, y = 4;
	
	@Before
	public void runBefore() {
		pw = new Powerball(x*32, y*32);
	}
	
	@Test
	public void testLarghezzaAltezza() {
		assertEquals(32, (int)pw.getWidth());
		assertEquals(32, (int)pw.getHeight());
	}
	
	@Test
	public void testPosition() {
		Point p = new Point(x*32, y*32);
		assertEquals(p, pw.getLocation());
	} 
	
	@Test
	public void testDimension() {
		Dimension d = new Dimension(32, 32);
		assertEquals(d, pw.getSize());
	}
	
}
