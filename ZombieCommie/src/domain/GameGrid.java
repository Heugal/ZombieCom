package domain;

import java.util.HashMap;

/**
 * 
 * @author Stephan
 * A domain class to represent the physical grid
 * in order to place towers and introduce collision detection.
 * Features a snap-to ability, where the player will select
 * an area to place a tower and the tower will snap-to a pre calculated
 * spot closest to where the player clicked.
 */
public class GameGrid {
    private int[][] gameGrid;
    private int cellSize;
    // number of cells in the x and y coordinates
    private int cellX;
    private int cellY;
    
    /**
     * Constructor of game grid
     * @param width width of game grid
     * @param height height of game grid
     * @param cellSize size of the cells within the game grid
     */
    public GameGrid(
            int width,
            int height,
            int cellSize){
        if (cellSize != 0){
            this.cellSize = cellSize;
            this.cellX = Math.round(width/cellSize)-1;
            this.cellY = Math.round(height/cellSize)-1;
            this.gameGrid = new int[cellY+1][cellX+1];
        }
    }
    
    public int getCellX(){
        return cellX;
    }
    
    public int getCellY(){
        return cellY;
    }
    
    public void setCellX(int cellX){
        this.cellX = cellX;
    }
    
    public void setCellY(int cellY){
        this.cellY = cellY;
    }
    
    public int convertCoordinateToCenterOfGrid(int coord){
        return coord-(coord%cellSize)+(cellSize/2);
    }
    
    /** Checks the cell range of one cell size around the tower to see if there
        is currently space to place an object. Used to detect if a tower can
        * be placed within a certain grid. If the selected grid is already within
        * one space of an already placed tower, then the user may not place a 
        * tower there.
        * @param    posY the cell value of the y coordinate
        * @param    posX the cell value of the x coordinate
        * @return   false if there is nothing present in the gamegrid one cell
        *           size away from the coordinates
        *           true otherwise
    */
    public boolean checkRadiusFromCell(int tempCellY, int tempCellX){
        if (getGameGridValueFromCoordinates(tempCellY, tempCellX) == 0
                        && getGameGridValueFromCoordinates(tempCellY - this.cellSize, tempCellX) == 0
                        && getGameGridValueFromCoordinates(tempCellY, tempCellX - this.cellSize) == 0
                        && getGameGridValueFromCoordinates(tempCellY + this.cellSize, tempCellX) == 0
                        && getGameGridValueFromCoordinates(tempCellY, tempCellX + this.cellSize) == 0
                        && getGameGridValueFromCoordinates(tempCellY + this.cellSize, tempCellX +this.cellSize) == 0
                        && getGameGridValueFromCoordinates(tempCellY - this.cellSize, tempCellX + this.cellSize) == 0
                        && getGameGridValueFromCoordinates(tempCellY + this.cellSize, tempCellX - this.cellSize) == 0
                        && getGameGridValueFromCoordinates(tempCellY - this.cellSize, tempCellX - this.cellSize) == 0)
            return false;
        else
            return true;
    }
    
    /** Checks the pixel range of one cell size around the tower to see if there
        is currently space to place an object. Used to detect if a tower can
        * be placed within a certain grid. If the selected grid is already within
        * one space of an already placed tower, then the user may not place a 
        * tower there.
        * @param    posY the pixel value of the y coordinate
        * @param    posX the pixel value of the x coordinate
        * @return   false if there is nothing present in the gamegrid one cell
        *           size away from the coordinates
        *           true otherwise
    */
    public boolean checkRadiusFromPixel(int posY, int posX){
        if (getGameGridValueFromCoordinates(posY, posX) == 0
                        && getGameGridValueFromCoordinates(posY - this.cellSize, posX) == 0
                        && getGameGridValueFromCoordinates(posY, posX - this.cellSize) == 0
                        && getGameGridValueFromCoordinates(posY + this.cellSize, posX) == 0
                        && getGameGridValueFromCoordinates(posY, posX + this.cellSize) == 0
                        && getGameGridValueFromCoordinates(posY + this.cellSize, posX +this.cellSize) == 0
                        && getGameGridValueFromCoordinates(posY - this.cellSize, posX + this.cellSize) == 0
                        && getGameGridValueFromCoordinates(posY + this.cellSize, posX - this.cellSize) == 0
                        && getGameGridValueFromCoordinates(posY - this.cellSize, posX - this.cellSize) == 0)
            return false;
        else
            return true;
    }
    
    /**
     * Checks to see if enemy is in range of tower
     * @param enemy enemy on grid
     * @param tower tower on grid
     * @return true if specified enemy is in range of tower 
     */
    public boolean checkEnemyInRange(Enemy enemy, Tower tower){
        // basically everything has to be calculated from the center of a cell
        // so i'm going to have to use an offset to account for the difference
        int offset = (this.cellSize/2);
        int towerX = (int)tower.getX(), towerY = (int)tower.getY(), pixelRange = (tower.getRange()*this.cellSize)+offset;
        int enemyX,enemyY;
        if(enemy != null)
        {   
            enemyX = (int)enemy.getX();
            enemyY = (int)enemy.getY();
        }
        else
        {
            enemyX = 0;
            enemyY = 0;
            return false;
        }
        // setting the tower x and y pos to a psuedo (0,0) point, and then adding
        // the offsets to create the origin of the towers range at the middle of 
        // its square
        towerX = (towerX-(towerX%cellSize))+offset;
        towerY = (towerY-(towerY%cellSize))+offset;
        // distance formula between the tower's position and enemies position
        int diffX = enemyX - towerX, diffY = enemyY - towerY;
        if (Math.sqrt((diffX * diffX) + (diffY * diffY)) <= pixelRange)
            return true;
        return false;
    }
    
    /**
     * Gets the X cell from the game grid array based on the width of the grid
     * and the size of one cell.
     * @param posX The pixel X coordinate of the object
     * @return The X gamegrid cell
     */
    public int getCellXFromPixel(int posX){
        return (int)Math.floor(posX/cellSize);
    }
    
    /**
     * Gets the Y cell from the game grid array based on the height of the grid
     * and the size of one cell.
     * @param posY The pixel Y coordinate of the object
     * @return The Y gamegrid cell
     */
    public int getCellYFromPixel(int posY){
        return (int)Math.floor(posY/cellSize);
    }
    
    /**
     * Gets the value of the gamegrid cell based on the corresponding pixel 
     * coordinates of the object
     * @param posY The pixel Y coordinate of the object
     * @param posX The pixel X coordinate of the object
     * @return The value of the corresponding gamegrid cell
     */
    public int getGameGridValueFromCoordinates(int posY, int posX){
        int tempX = getCellXFromPixel(posX);
        int tempY = getCellYFromPixel(posY);
        return this.gameGrid[tempY][tempX];
    }
    
    /**
     * Sets the value of the gamegrid cell to 1 based on the corresponding
     * pixel coordinates of the object
     * @param posY The pixel Y coordinate of the object
     * @param posX The pixel X coordinate of the object
     */
    public void setGameGridValueFromCoordinates(int posY, int posX, int value){
        int tempX = getCellXFromPixel(posX);
        int tempY = getCellYFromPixel(posY);
        this.gameGrid[tempY][tempX] = value;
    }
    
    /**
     * Unsets the value of the gamegrid cell to 0 based on the corresponding
     * pixel coordinates of the object
     * @param posY The pixel Y coordinate of the object
     * @param posX The pixel X coordinate of the object
     */
    public void unsetGameGridValueFromCoordinates(int posY, int posX){
        int tempX = getCellXFromPixel(posX);
        int tempY = getCellYFromPixel(posY);
        this.gameGrid[tempY][tempX] = 0;
    }
    
    /**
     * Sets the central control point to the middle of the game grid and marks
     * the gamegrid array accordingly to notify that there is a building in that 
     * location.
     * @param   posY The pixel Y coordinate of the CCP
     * @param   posX The pixel X coordinate of the CCP
     */
    public void setupCentralControlPointGameGrid(int posY, int posX){
        int tempCellY = getCellYFromPixel(posY), tempCellX = getCellXFromPixel(posX);
        this.gameGrid[tempCellY][tempCellX] = 1;
        this.gameGrid[tempCellY][tempCellX+1] = 1;
        this.gameGrid[tempCellY+1][tempCellX] = 1;
        this.gameGrid[tempCellY+1][tempCellX+1] = 1;
    }
    
    /**
     * Checks to see whether the calling entity's pixel coordinates are within 
     * the game grids coordinates
     * @param posX The entity's pixel x coordinate
     * @param posY The entity's pixel y coordinate
     * @return true if the entity is located within the gamegrid, false if not
     */
    public boolean checkIfEntityIsWithinGameGridBounds(int posX, int posY){
        int tempCellX = posX/cellSize, tempCellY = posY/cellSize;
        if ((true == checkXRange(tempCellX)) && (true == checkYRange(tempCellY)))
            return true;
        return false;
    }
    
    /**
     * Checks to see whether the x position of the entity is located
     * within the bounds of the game grids coordinates
     * @param cellX The entity's X gamegrid cell position
     * @return true if the entitys is located within the gamegrid's x coordinate
     * false otherwise
     */
    private boolean checkXRange(int cellX){
        if ((cellX > 0) && (cellX < this.cellX))
            return true;
        return false;
    }
    
    /**
     * Checks to see whether the y position of the entity is located
     * within the bounds of the game grids coordinates
     * @param cellY The entity's Y gamegrid cell position
     * @return true if the entitys is located within the gamegrid's Y coordinate
     * false otherwise
     */
    private boolean checkYRange(int cellY){
        // that 19 is ugly but i dont know what
        // the fuck is wrong with the game grid's y axis so whatever
        if ((cellY > 0) && (cellY < 19))
            return true;
        return false;
    } 
}
