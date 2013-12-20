package domain;

import ZombieCommie.Assets;
import ZombieCommie.Play;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Class that represents an enemy whose goal is to get to central control point. 
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class Enemy extends Sprite {
    private int startHealth;
    private int health;
    private int damage;
    public int reward;
    private int attackSpeed;
    private int moveSpeed;
    private int armor;
    private int posX;
    private int posY;
    private Play access;
    private boolean signaled_back = false;
    public boolean killed = false;
    public boolean health_mut = false;
    public boolean attacking_base = false;
    public Location endpoint = new Location(400,400);
    public static boolean kill_mut = false;
    public Unit unit = null;
    
    public Game player = null;
    
    public SpriteMap spriteMap;
    
    private Vector2 velocity = new Vector2();
    
    public int x_offset = (int)(.51329*Gdx.graphics.getWidth() - 411.4286);
    
    public int y_offset = Play.UI_OFFSET+360;
    
   /**
     * Constructor for class Enemy
     * @param name Name of enemy
     * @param health Health status of enemy
     * @param damage Amount of damage to enemy
     * @param health Health status of enemy
     * @param attackSpeed of enemy
     * @param armor Amount of armor enemy has
     * @param armor Amount of armor enemy has
     * @param sprite Sprite of enemy (2D image of the enemy)
     */
    public Enemy(
            int health,
            int damage,
            int reward,
            int attackSpeed,
            int moveSpeed,
            int armor,Sprite sprite,int startX,int startY,Play play){
        super(sprite);
        this.setX(startX);
        this.setY(startY);
        this.health = health;
        this.startHealth = health;
        this.damage = damage;
        this.reward = reward;
        this.attackSpeed = attackSpeed;
        this.moveSpeed = 60*2;
        this.armor = armor;
        this.access = play;
        move.start();
        spriteMap = new SpriteMap(this.access.gameGrid);
        this.spriteMap.setAllCornersFromCoordinates(startX, startY);
    }
    
    /**
     * Returns the current health of the enemy
     * @return The amount of health the current enemy has
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * Gets the damage of the enemy
     * @return The amount of damage the enemy currently does
     */
    public int getDamage(){
        return damage;
    }
    
    /**
     * Gets the rewards received for killing the enemy
     * @return the amount of the reward
     */
    public int getReward(){
        return reward;
    }
    
    /**
     * Gets the current attack speed of the enemy
     * @return the current attack speed of the enemy
     */
    public int getAttackSpeed(){
        return attackSpeed;
    }
    
    /**
     * Gets the current move speed of the enemy
     * @return the current move speed of the enemy
     */
    public int getMoveSpeed(){
        return moveSpeed;
    }
    
    /**
     * Gets the amount of armor the current enemy has
     * @return the amount of armor the current enemy has
     */
    public int getArmor(){
        return armor;
    }
    
    /**
     * Gets the X-coordinate of the current enemy's position
     * @return the X-coordinate of the current enemy's position
     */
    public int getPosX(){
        return posX;
    }
    
    /**
     * Gets the Y-coordinate of the current enemy's position
     * @return the Y-coordinate of the current enemy's position
     */
    public int getPosY(){
        return posY;
    }
    
    /**
     * Sets the enemy's health
     * @param health The amount of health the enemy will have
     */
    public void setHealth(int health){
        this.health = health;
    }
    
    /**
     * Set the enemy's damage
     * @param damage The amount of damage the enemy will have
     */
    public void setDamage(int damage){
        this.damage = damage;
    }
    
    /**
     * Set the enemy's reward
     * @param reward The amount of reward player receives for killing enemy
     */
    public void setReward(int reward){
        this.reward = reward;
    }
    
    /**
     * Set the enemy's attack speed
     * @param attackSpeed The amount of attack speed the enemy will have
     */
    public void setAttackSpeed(int attackSpeed){
        this.attackSpeed = attackSpeed;
    }
    
    /**
     * Set the enemy's move speed
     * @param moveSpeed The amount of move speed the enemy will have
     */
    public void setMoveSpeed(int moveSpeed){
        this.moveSpeed = moveSpeed;
    }
    
    /**
     * Set the enemy's armor
     * @param armor The amount of armor the enemy will have
     */
    public void setArmor(int armor){
        this.armor = armor;
    }
    
    /**
     * Sets the enemy's X-coordinate position
     * @param posX The enemy's new X-coordinate
     */
    public void setPosX(int posX){
        this.posX = posX;
    }
    
    /**
     * Sets the enemy's Y-coordinate position
     * @param posY The enemy's new Y-coordinate
     */
    public void setPosY(int posY){
        this.posY = posY;
    }
     
    /**
     * Checks the health of the enemy. It replaced the asset used for the enemy 
     * based on the health.  
     */
    public void checkHealth(){
        if(this.health < (this.startHealth * 2/3)){
            super.setRegion(Assets.enemy2);
        }
        if(this.health < (this.startHealth * 1/3)){
            super.setRegion(Assets.enemy3);
        }
    }
    
    /**
     * Draws the enemy 
     * @param spriteBatch It used to draw 2D rectangles that reference a texture
     * (region) for the enemy. It will batch the drawing commands and optimize 
     * them for processing by the GPU
     * @param alphaModulation Change of brightness of enemy
     */
    @Override
    public void draw(SpriteBatch spriteBatch, float alphaModulation) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch, alphaModulation);
    }

    /**
     * Updates a enemy
     * @param deltaTime Time span between the current frame and the last frame in seconds
     */
    public void update(float deltaTime) {
        this.setPosX(calculateRealXCoordinate(Math.round(getX())));
        this.setPosY(calculateRealYCoordinate(Math.round(getY())));
        this.setX(getX() + velocity.x * deltaTime);
        this.setY(getY() + velocity.y * deltaTime);

        this.spriteMap.setAllCornersFromCoordinates(this.getPosX(), this.getPosY());
        x_offset = (int)(.51329*Gdx.graphics.getWidth() - 411.4286);
    }
    // This thread is run when this enemy is created and is used to move the enemy
    //      towards the base and to reroute him to an unit and to have him battle.
    Thread move = new Thread(new Runnable() {
        @Override
        public void run(){
            while(true){
                if(unit == null)
                {
                    endpoint = new Location(Play.base.getPosX()+15,Play.base.getPosY()+20);
                    create_path(new Location(Play.base.getPosX()+15,Play.base.getPosY()+20));
                    
                }
                else
                {
                    while(unit != null)
                    {
                        create_future_path();
                        if(unit != null)
                        if(unit.killed){
                            unit = null;
                        }
                    }

                }
                if(killed == true){
                    access.enemy.remove(this);
                    return;
                }
            }  
        }
    });
    
    /**
     * Creates a path for the enemy based on the end point
     * @param end The endpoint in which the enemy has to go 
     */
    // This method sets the velocity x and y to the proper angles based on its 
    //      endpoint then it sends him into battle if necessary.
    protected void create_path(Location end)
    {
        double angle = Math.atan((Math.abs(this.getX() - end.positionX))/Math.abs(this.getY() - end.positionY));
        this.endpoint = end;
        if(this.getX() > end.positionX && this.getY() > end.positionY)
        {
            this.velocity.y = (float)(Math.cos(angle))* -moveSpeed;
            this.velocity.x = (float)(Math.sin(angle))* -moveSpeed;
        }
        // 
        if(this.getX() < end.positionX && this.getY() > end.positionY)
        {
            this.velocity.y = (float)(Math.cos(angle))* -moveSpeed;
            this.velocity.x = (float)(Math.sin(angle))* moveSpeed;
        }
        if(this.getX() > end.positionX && this.getY() < end.positionY)
        {
            this.velocity.y = (float)(Math.cos(angle))* moveSpeed;
            this.velocity.x = (float)(Math.sin(angle))* -moveSpeed;
        }
        if(this.getX() < end.positionX && this.getY() < end.positionY)
        {
            this.velocity.y = (float)(Math.cos(angle))* moveSpeed;
            this.velocity.x = (float)(Math.sin(angle))* moveSpeed;
        }
        
        int distance;
        double start = System.currentTimeMillis();
        double deltaTime = System.currentTimeMillis() - start;
        distance = (this.unit == null) ? 40 : 10;
        while(((Math.abs(end.positionX - this.getX()) > distance) || 
                (Math.abs(end.positionY - this.getY()) > distance))
                 && deltaTime < 5000)
        {
            if(this.unit != null && signaled_back == false){
                return;
            }
            try 
            {
                Thread.sleep(0,1);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            }
            deltaTime = System.currentTimeMillis() - start;
        }
        velocity.x = 0;
        velocity.y = 0;
        if(this.unit == null)
        {
            this.attacking_base = true;
            this.battle();
        }
        else
        {
            this.battle();
        }
        return;
    }
    /**
     * Creates a future path the enemy will take
     */
        // Creates a new endpoint between the this enemy and the unit it is engaged 
        //      with then creates a path to him. 
        protected void create_future_path()
        {
            this.endpoint = new Location((unit.getX()+ this.getX())/2,(unit.getY()+this.getY())/2);
            signaled_back = true;
            create_path(endpoint);
            signaled_back = false;
            return;
        }
        
        /**
         * Represents the battle between a defender or ccp against the enemy
         */
        private void battle()
        {
            if(attacking_base == true)
            {
                while(true)
                {
                    if(killed){
                        return;
                    }
                    if(attacking_base == true)
                    {
                        Play.base.setHealth(Play.base.getHealth()-1);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
                    if(Play.base.getHealth() < 1)
                    {
                        this.access.game_state = this.access.GAME_OVER;
                        access.update(0);
                    }
                    if(this.health < 1)
                    {
                        this.killed = true;
                        access.enemy.remove(this);
                    }
                    if(attacking_base == false)
                    {
                        return;
                    }
                    if(access.enemy.contains(this) == false)
                    {
                        this.attacking_base = false;
                        return;
                    }
                }
            }
            else
            {
                while(true)
                {
                    if (killed)
                        return;
                    if(this.unit != null){
                        this.unit.setHealth(this.unit.getHealth()-this.damage);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(this.unit == null)
                    {
                        return;
                    }
                    if(this.unit.getHealth() < 1)
                    {
                        this.unit.killed = true;
                        this.unit.signaled = false;
                        this.unit.enemy = null;
                        while(Unit.kill_mut == true)
                        {
                        }
                        this.access.unit.remove(this.unit);
                        this.unit = null;
                        this.signaled_back = false;
                        return;
                    }
                }
            }
        }
        
        /**
         * Calculates the x coordinate on the grid based on the offset
         * @param posX
         * @return 
         */
        private int calculateRealXCoordinate(int posX){
            int offsetX = (Gdx.graphics.getWidth() - Play.WIDTH)/2;
            return posX - offsetX + 25;
        }
        
        /**
         * Calculates the y coordinate on the grid based on the offset
         * @param posY
         * @return 
         */
        private int calculateRealYCoordinate(int posY){
            int offsetY = Gdx.graphics.getHeight() - Play.UI_OFFSET;
            return -posY + offsetY + 50;
        }
}
