/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZombieCommie;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kelly
 */
public class OverlapTesterTest {
    
    /**
     *
     */
    public OverlapTesterTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of overlapRectangles method, of class OverlapTester.
     */
    @Test
    public void testOverlapRectangles() {
        System.out.println("overlapRectangles");
        Rectangle r1 = null;
        Rectangle r2 = null;
        boolean expResult = false;
        boolean result = OverlapTester.overlapRectangles(r1, r2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pointInRectangle method, of class OverlapTester.
     */
    @Test
    public void testPointInRectangle_Rectangle_Vector2() {
        System.out.println("pointInRectangle");
        Rectangle r = null;
        Vector2 p = null;
        boolean expResult = false;
        boolean result = OverlapTester.pointInRectangle(r, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pointInRectangle method, of class OverlapTester.
     */
    @Test
    public void testPointInRectangle_3args() {
        System.out.println("pointInRectangle");
        Rectangle r = null;
        float x = 0.0F;
        float y = 0.0F;
        boolean expResult = false;
        boolean result = OverlapTester.pointInRectangle(r, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}