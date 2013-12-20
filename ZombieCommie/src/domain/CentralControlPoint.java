package domain;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class that represents the central control point
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class CentralControlPoint extends Sprite {

    private String name;
    private int health;
    private int maxHealth;
    private int funds;
    private int posX;
    private int posY;
    private Sprite sprite;

    /**
     * Constructor for central control point
     * @param name Name of central control point
     * @param health Health status of central control point
     * @param sprite Sprite of unit (2D image of the central control point) 
     */
    public CentralControlPoint(
            String name,
            int health,
            Sprite sprite) {
        super(sprite);
        this.name = name;
        this.health = this.maxHealth = health;
        this.funds = 100;
    }

    /**
     * Gets the name of the central control point
     * @return A string containing the name of the central control point
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current health of the central control point
     * @return The amount of health the current central control point has
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the funds available to the central control point
     * @return The amount of funds of the central control point
     */
    public int getFunds() {
        return funds;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }

    /**
     * Sets the defenders name
     * @param name A String containing the central control point new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the central control points health
     * @param health The amount of health the central control point will have
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /** 
     * Sets the central control points funds
     * @param funds The amount of funds the cental control point will have
     */
    public void setFunds(int funds) {
        this.funds = funds;
    }
    
    public void setPosX(int posX){
        this.posX = posX;
    }
    
    public void setPosY(int posY){
        this.posY = posY;
    }
    
    /**
     * Gets the health the started out with
     * @return the max health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     *Draws the central control point 
     * @param spriteBatchIt used to draw 2D rectangles that reference a texture 
     * (region) for the central control point. It will batch the drawing commands and optimize them for processing by the GPU
     * @param alphaModulation Change of brightness of central control point
     */
    public void draw(SpriteBatch spriteBatch, float alphaModulation) {
        //update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch, alphaModulation);
    }
}
