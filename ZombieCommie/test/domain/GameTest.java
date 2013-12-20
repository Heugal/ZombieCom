/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
public class GameTest {
    
    /**
     *
     */
    public GameTest() {
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
     * Test of canAfford method, of class Game.
     */
    @Test
    public void testCanAfford() {
        System.out.println("canAfford");
        Defender item = null;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.canAfford(item.getPrice());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buy method, of class Game.
     */
    @Test
    public void testBuy() {
        System.out.println("buy");
        Defender item = null;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.buy(item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}