package ZombieCommie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *Class that represents the method in which to detect if a collision occurs 
 * between objects on grid
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class OverlapTester {
	/**
     *
     * @param r1
     * @param r2
     * @return
     */
    public static boolean overlapRectangles (Rectangle r1, Rectangle r2) {
		if (r1.x < r2.x + r2.width && r1.x + r1.width > r2.x && r1.y < r2.y + r2.height && r1.y + r1.height > r2.y)
			return true;
		else
			return false;
	}

	/**
     *
     * @param r
     * @param p
     * @return
     */
    public static boolean pointInRectangle (Rectangle r, Vector2 p) {
		return r.x <= p.x && r.x + r.width >= p.x && r.y <= p.y && r.y + r.height >= p.y;
	}

	/**
     *
     * @param r
     * @param x
     * @param y
     * @return
     */
    public static boolean pointInRectangle (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
}
