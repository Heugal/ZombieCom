/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZombieCommie;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
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
public class AssetsTest {
    
    /**
     *
     */
    public AssetsTest() {
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
     * Test of loadTexture method, of class Assets.
     */
    @Test
    public void testLoadTexture() {
        System.out.println("loadTexture");
        String file = "";
        Texture expResult = null;
        Texture result = Assets.loadTexture(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class Assets.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        Assets.load();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playSound method, of class Assets.
     */
    @Test
    public void testPlaySound() {
        System.out.println("playSound");
        Sound sound = null;
        Assets.playSound(sound);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}