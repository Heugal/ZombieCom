/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
public class CentralControlPointTest {
    
    /**
     *
     */
    public CentralControlPointTest() {
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
     * Test of getName method, of class CentralControlPoint.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        CentralControlPoint instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHealth method, of class CentralControlPoint.
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        CentralControlPoint instance = null;
        int expResult = 0;
        int result = instance.getHealth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFunds method, of class CentralControlPoint.
     */
    @Test
    public void testGetFunds() {
        System.out.println("getFunds");
        CentralControlPoint instance = null;
        int expResult = 0;
        int result = instance.getFunds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class CentralControlPoint.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        CentralControlPoint instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHealth method, of class CentralControlPoint.
     */
    @Test
    public void testSetHealth() {
        System.out.println("setHealth");
        int health = 0;
        CentralControlPoint instance = null;
        instance.setHealth(health);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFunds method, of class CentralControlPoint.
     */
    @Test
    public void testSetFunds() {
        System.out.println("setFunds");
        int funds = 0;
        CentralControlPoint instance = null;
        instance.setFunds(funds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upgradeUnit method, of class CentralControlPoint.
     */
    @Test
    public void testUpgradeUnit() {
        System.out.println("upgradeUnit");
        CentralControlPoint instance = null;
        instance.upgradeUnit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upgradeTower method, of class CentralControlPoint.
     */
    @Test
    public void testUpgradeTower() {
        System.out.println("upgradeTower");
        CentralControlPoint instance = null;
        instance.upgradeTower();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyUnit method, of class CentralControlPoint.
     */
    @Test
    public void testBuyUnit() {
        System.out.println("buyUnit");
        CentralControlPoint instance = null;
        instance.buyUnit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyTower method, of class CentralControlPoint.
     */
    @Test
    public void testBuyTower() {
        System.out.println("buyTower");
        CentralControlPoint instance = null;
        instance.buyTower();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of die method, of class CentralControlPoint.
     */
    @Test
    public void testDie() {
        System.out.println("die");
        CentralControlPoint instance = null;
        instance.die();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class CentralControlPoint.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        SpriteBatch spriteBatch = null;
        float alphaModulation = 0.0F;
        CentralControlPoint instance = null;
        instance.draw(spriteBatch, alphaModulation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}