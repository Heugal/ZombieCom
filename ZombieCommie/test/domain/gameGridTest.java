package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class gameGridTest {
    @Test
    public void constructorTest(){
        GameGrid grid = new GameGrid(100, 50, 10);
        assertEquals(10, grid.getCellX());
        assertEquals(5, grid.getCellY());
    }
    
    @Test
    public void pixelXtoCellXTest(){
        GameGrid grid = new GameGrid(100, 50, 10);
        int tempX = grid.getCellXFromPixel(40);
        assertEquals(4, tempX);
    }
    
    @Test
    public void pixelYtoCellYTest(){
        GameGrid grid = new GameGrid(100, 50, 10);
        int tempY = grid.getCellYFromPixel(20);
        assertEquals(2, tempY);
    }
    
    @Test
    public void setGameGridTest(){
        GameGrid grid = new GameGrid(100, 50, 10);
        int tempX = 40;
        int tempY = 20;
        grid.setGameGridValueFromCoordinates(tempY, tempX, 1);
        assertEquals(1, grid.getGameGridValueFromCoordinates(tempY, tempX));
    }
}
