package domain;

/**
 * Class that represents Sprite map
 * @author Kelly
 */
public class SpriteMap {
    // these are test variables, they should be changed
    // so that they aren't using absolute values later
    private int width;
    private int height;
    private final int BUFFER = 0;
    
    private int topRightX, topRightY;
    private int topLeftX, topLeftY;
    private int bottomRightX, bottomRightY;
    private int bottomLeftX, bottomLeftY;
    GameGrid gameGrid;
    
    /**
     * COnstructor for SpriteMap
     * @param height height of sprite
     * @param width width of sprite
     * @param gameGrid the current game grid
     */
    public SpriteMap(int height, int width, GameGrid gameGrid){
        this.width = width;
        this.height = height;
        this.gameGrid = gameGrid;
    }
    
    // test constructor, this should only be used to test the 32x40 pixel
    // sprites that we are currently using. In the final version this
    // should be removed.
    public SpriteMap(GameGrid gameGrid){
        this.width = 32;
        this.height = 32;
        this.gameGrid = gameGrid;
    }
    
    /**
     * 
     * @return 
     */
    public int getTopRightX(){
        return topRightX;
    }
    
    /**
     * 
     * @return 
     */
    public int getTopRightY(){
        return topRightY;
    }
    
    /**
     * 
     * @return 
     */
    public int getTopLeftX(){
        return topLeftX;
    }
    
    /**
     * 
     * @return 
     */
    public int getTopLeftY(){
        return topLeftY;
    }
    
    /**
     * 
     * @return 
     */
    public int getBottomRightX(){
        return bottomRightX;
    }
    
    /**
     * 
     * @return 
     */
    public int getBottomRightY(){
        return bottomRightY;
    }
    
    /**
     * 
     * @return 
     */
    public int getBottomLeftX(){
        return bottomLeftX;
    }
    
    /**
     * 
     * @return 
     */
    public int getBottomLeftY(){
        return bottomLeftY;
    }
    
    /**
     * 
     * @param topRightX 
     */
    public void setTopRightX(int topRightX){
        this.topRightX = topRightX;
    }
    
    /**
     * 
     * @param topRightY 
     */
    public void setTopRightY(int topRightY){
        this.topRightY = topRightY;
    }
    
    /**
     * 
     * @param topLeftX 
     */
    public void setTopLeftX(int topLeftX){
        this.topLeftX = topLeftX;
    }
    
    /**
     * 
     * @param topLeftY 
     */
    public void setTopLeftY(int topLeftY){
        this.topLeftY = topLeftY;
    }
    
    /**
     * 
     * @param bottomRightX 
     */
    public void setBottomRightX(int bottomRightX){
        this.bottomRightX = bottomRightX;
    }
    
    /**
     * 
     * @param bottomRightY 
     */
    public void setBottomRightY(int bottomRightY){
        this.bottomRightY = bottomRightY;
    }
    
    /**
     * 
     * @param bottomLeftX 
     */
    public void setBottomLeftX(int bottomLeftX){
        this.bottomLeftX = bottomLeftX;
    }
    
    /**
     * 
     * @param bottomLeftY 
     */
    public void setBottomLeftY(int bottomLeftY){
        this.bottomLeftY = bottomLeftY;
    }
    
    /**
     * 
     * @param posX
     * @param posY 
     */
    public void setAllCornersFromCoordinates(int posX, int posY){

        this.bottomLeftX = posX;
        this.bottomLeftY = posY;
        
        this.bottomRightX = posX+width;
        this.bottomRightY = posY;
        
        this.topLeftX = posX;
        this.topLeftY = posY+height;
        
        this.topRightX = posX+width;
        this.topRightY = posY+height;
    }
    
    /**
     * 
     * @return 
     */
    public boolean checkTopLeft(){
        if (gameGrid.getGameGridValueFromCoordinates(this.topLeftY+BUFFER, this.topLeftX+BUFFER) == 1)
            return true;
        return false;
    }
    
    /**
     * 
     * @return 
     */
    public boolean checkTopRight(){
        if (gameGrid.getGameGridValueFromCoordinates(this.topRightY+BUFFER, this.topRightX+BUFFER) == 1)
            return true;
        return false; 
    }
    
    /**
     * 
     * @return 
     */
    public boolean checkBottomLeft(){
        if (gameGrid.getGameGridValueFromCoordinates(this.bottomLeftY+BUFFER, this.bottomLeftX+BUFFER) == 1)
            return true;
        return false; 
    }
    
    /**
     * 
     * @return 
     */
    public boolean checkBottomRight(){
        if (gameGrid.getGameGridValueFromCoordinates(this.bottomRightY+BUFFER, this.bottomRightX+BUFFER) == 1)
            return true;
        return false; 
    }
    
    /**
     * 
     * @return 
     */
    public boolean checkTop(){
        if ((true == checkTopLeft()) || (true == checkTopRight()))
            return true;
        return false;
    }
    
    public boolean checkRight(){
        if ((true == checkTopRight()) || (true == checkBottomRight()))
            return true;
        return false;
    }
    
    public boolean checkBottom(){
        if ((true == checkBottomLeft()) || (true == checkBottomRight()))
            return true;
        return false;
    }
    
    public boolean checkLeft(){
        if ((true == checkTopLeft()) || (true == checkBottomLeft()))
            return true;
        return false;
    }
}
