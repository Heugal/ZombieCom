package domain;

/**
 *The interface for a Defender(units and towers) that are used as defense of the
 * player against the enemies
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public interface Defender {
    /**
     * Gets the name of the defender
     * @return A string containing the name of the defender
     */

    public int getDamage();
    
    /**
     * Gets the price of the current defender
     * @return The price of the current defender
     */
    public int getPrice();
    
    /**
     * Returns the current health of the defender
     * @return The amount of health the current defender has
     */
    public int getHealth();
    
    /**
     * Gets the amount of armor the current defender has
     * @return the amount of armor the current defender has
     */
    public int getArmor();
    
    /**
     * Gets the current level of the defender
     * @return the current level of the defender
     */
    public int getLevel();
    
    /**
     * Gets the current attack speed of the defender
     * @return the current attack speed of the defender
     */
    public int getAttackSpeed();
    
    /**
     * Gets the current move speed of the defender
     * @return the current move speed of the defender
     */
    public float getMoveSpeed();
    
    /**
     * Gets the total accrued worth of the current defender.
     * @return the total accrued worth of the current defender.
     */
    public int getWorth();
    
    /**
     * Gets the X-coordinate of the current defenders position
     * @return the X-coordinate of the current defenders position
     */
    public int getPosX();
    
    /**
     * Gets the Y-coordinate of the current defenders position
     * @return the Y-coordinate of the current defenders position
     */
    public int getPosY();
    
    /**
     * Sets the defenders name
     * @param name A String containing the defenders new name
     */
    public void setHealth(int health);
    
    /**
     * Set the defenders damage
     * @param damage The amount of damage the defender will have
     */
    public void setDamage(int damage);
    
    /**
     * Set the defenders armor
     * @param armor The amount of armor the defender will have
     */
    public void setArmor(int armor);
    
    /**
     * Set the defenders level
     * @param level The level the defender will be
     */
    public void setLevel(int level);
    
    /**
     * Set the defenders attack speed
     * @param attackSpeed The amount of attack speed the defender will have
     */
    public void setAttackSpeed(int attackSpeed);
    
    /**
     * Set the defenders move speed
     * @param moveSpeed The amount of move speed the defender will have
     */
    public void setMoveSpeed(float moveSpeed);
    
    /**
     * Set the defenders total accrued worth
     * @param worth The total amount that the defender will be worth
     */
    public void setWorth(int worth);
    
    /**
     * Sets the defenders X-coordinate position
     * @param posX The defenders new X-coordinate
     */
    public void setPosX(int posX);
    
    /**
     * Sets the defenders Y-coordinate position
     * @param posY The defenders new Y-coordinate
     */
    public void setPosY(int posY);
}
