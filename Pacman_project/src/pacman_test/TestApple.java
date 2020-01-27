package pacman_test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import pacman_actor.Apple;

public class TestApple {

	Apple ap;
	int x = 4, y = 5;
	
	@Before
	public void runBefore() {
		ap = new Apple(x*32, y*32);
	}
	
	@Test
	public void testAltezzaLarghezza() {
		assertEquals(32, (int)ap.getWidth());
		assertEquals(32, (int)ap.getHeight());
	}
	
	@Test
	public void testPosition() {
		Point p = new Point(x*32, y*32);
		assertEquals(p, ap.getLocation());
	} 
	
	@Test
	public void testDimension() {
		Dimension d = new Dimension(32, 32);
		assertEquals(d, ap.getSize());
	}
	
}
