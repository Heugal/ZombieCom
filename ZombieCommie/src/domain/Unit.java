package domain;

import ZombieCommie.Assets;
import ZombieCommie.Play;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import java.sql.Time;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Class that represents a unit in the game- used to defend the central control
 * point against enemies. Unlike Towers, they are movable 
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class Unit extends Sprite
        implements Defender, InputProcessor{
    
    
    private static int damage = 1;
    public static int price = 30;
    private int health;
    private int armor;
    private static int level = 1;
    private int attackSpeed;
    private float moveSpeed;
    private int worth;
    public static int upgradeCost = 200;
    private int posX;
    private int posY;
    private Location loc;
    // Gives access to the play class
    private Play access;
    public boolean killed = false;
    // Prevents multiple Enemies from killing a single unit
    public static boolean kill_mut = false;
    Location endpoint;
    // Unsed to test if the endpoint of an enemy has been updated
    boolean endpointfail = false;
    public static int maxHealth = 25; //full health not current
    /**
     *
     */
    public Enemy enemy = null;
    
    public Game player = null;
    /**
     *
     */
    public boolean signaled = false;
    
    private Vector2 velocity = new Vector2();
    
    private Sprite sprite;
    

    public static int x_offset = 400;
    
    public static int y_offset = Play.UI_OFFSET+450;
    
    /**
     * Constructor for class Unit
     * @param name Name of unit
     * @param damage Amount of damage to unit
     * @param health Health status of unit
     * @param armor Amount of armor unit has
     * @param attackSpeed of unit
     * @param sprite Sprite of unit (2D image of the unit)
     * @param moveSpeed Move speed of unit
     */
    public Unit(
            
            int damage,
            int health,
            int armor,
            int attackSpeed,
            float moveSpeed, Sprite sprite, Play access, Game player){
        super(sprite);
        
        this.health = Unit.maxHealth;
        this.armor = 0;
        this.attackSpeed = attackSpeed;
        this.worth = 0;
        this.moveSpeed = 60*2;
        this.sprite = sprite;
        this.access = access;
        this.player = player;
    }
    /**
     *Resets the velocity back to 0
     */
    public void reset_Velocity()
    {
        this.velocity.x = 0;
        this.velocity.y = 0;
    }

    // Gest the damage of this Unit
    @Override
    public int getDamage(){
        return damage;
    }
    
    // Gets the price of this Unit
    @Override
    public int getPrice(){
        return price;
    }
    
    // Gets the health of this Unit
    @Override
    public int getHealth(){
        return health;
    }
    
    // Gets the armor of this Unit
    @Override
    public int getArmor(){
        return armor;
    }
    
    // Gets the level of this Unit
    @Override
    public int getLevel(){
        return level;
    }
    
    // Gets the attackspeed of this Unit
    @Override
    public int getAttackSpeed(){
        return attackSpeed;
    }
    
    // Gets the movespeed of this Unit
    @Override
    public float getMoveSpeed(){
        return moveSpeed;
    }
    
    // Gets the worth of this Unit
    @Override
    public int getWorth(){
        return worth;
    }
    
    // Gets the upgradeCost of this Unit
    public int getUpgradeCost() {
        return upgradeCost;
    }
    
    // Gets the positionX of this Unit
    @Override
    public int getPosX(){
        return posX;
    }
    
    // Gets the positionY of this Unit
    @Override
    public int getPosY(){
        return posY;
    }
    
    // Sets the Health of this Unit
    @Override
    public void setHealth(int health){
        this.health = health;
    }
    
    // Sets the damage of this Unit
    @Override
    public void setDamage(int damage){
        this.damage = damage;
    }
    
    // Sets the armor of this Unit
    @Override
    public void setArmor(int armor){
        this.armor = armor;
    }
    
    // Sets the level of this Unit
    @Override
    public void setLevel(int level){
        this.level = level;
    }
    
    // Sets the attackspeed of this Unit
    @Override
    public void setAttackSpeed(int attackSpeed){
        this.attackSpeed = attackSpeed;
    }
    
    // Sets the movespeed of this Unit
    @Override
    public void setMoveSpeed(float moveSpeed){
        this.moveSpeed = moveSpeed;
    }
    
    // Sets the worth of this Unit
    @Override
    public void setWorth(int worth){
        this.worth = worth;
    }
    
    // Sets the upgradeCost of this Unit
    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }
    
    // Sets the positionX of this Unit
    @Override
    public void setPosX(int posX){
        this.posX = posX;
    }
    
    // Sets the positionY of this Unit
    @Override
    public void setPosY(int posY){
        this.posY = posY;
    }
    /**
     * Gets the starting health of unit
     * @return max health of unit
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * Sets the starting health of unit
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    
    /**
     * Upgrades a unit
     */
    public static int upgrade(){
        //Can add a if or case statement for types.
        if (Unit.level < 3) {
            Game.setFunds(Game.getFunds() - Unit.upgradeCost);
            Unit.level = Unit.level + 1;
            Unit.damage = Unit.damage + 1;
            Unit.maxHealth = Unit.maxHealth + 25;
            Unit.upgradeCost = Unit.upgradeCost * 2;
            Unit.price = price + 20;
            return 1;
        }
        return 0;
    }
    
    /**
     * Gets the current location of the unit
     * @return the locations of the unit
     */
    public Location getLocation()
    {
        return loc;
    }
    
    /**
     *Calculates the sell price of the unit
     * @param price the current price of the unit
     * @return
     */
    public long getSellPrice(int price){
        // currently selling a unit will return 70% of its initial worth
        return Math.round(0.7*price);
    }
    
    /**
     * Creates a path for the unit based on the end point
     * @param end The endpoint in which the unit has to go
     */
    protected void create_path(Location end)
    {
        double start = System.currentTimeMillis();
        double deltaTime = System.currentTimeMillis() - start;
        while(((Math.abs(end.positionX-x_offset - this.getX()) > 10) || 
                (Math.abs(-end.positionY+y_offset - this.getY()) > 10))
                 && deltaTime < 5000)
        {
            try 
            {
                Thread.sleep(0,1);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            }
            deltaTime = System.currentTimeMillis() - start;
            if(this.getX() > 770)
            {
                this.setX(769);
                velocity.x = 0; 
                velocity.y = 0;
                break;
            }
            else if(this.getX() < 0)
            {
                this.setX(1);
                velocity.x = 0; 
                velocity.y = 0;
                break;
            }
            else if(this.getY() > 600)
            {
                this.setY(599);
                velocity.x = 0; 
                velocity.y = 0;
                break;
            }
            else if(this.getY() < 0)
            {
                this.setY(1);
                velocity.x = 0; 
                velocity.y = 0;
                break;
            }
        }
        
        this.velocity.x = 0;
        this.velocity.y = 0;
        if(this.enemy != null)
        {
            this.battle();
        }
        return;
    }
    
    /**
     * Creates future path of unit
     * @param end coordinates of endpoint
     */
    private void create_future_path(Location end)
    {
        create_path(end);
        if(this.enemy == null)
        {
            velocity.x = 0;
            velocity.y = 0;
        }
        return;
    }
    
     /**
     *Draws the unit 
     * @param spriteBatch It used to draw 2D rectangles that reference a texture
     * (region) for the unit. It will batch the drawing commands and optimize 
     * them for processing by the GPU
     * @param alphaModulation Change of brightness of unit
     */
    @Override
    public void draw(SpriteBatch spriteBatch, float alphaModulation) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch, alphaModulation);
    }

    /**
     * Updates a unit
     * @param deltaTime Time span between the current frame and the last frame in seconds
     */
    public void update(float deltaTime) {
        if(this.enemy != null && this.signaled == false)
        {
            this.signaled = true;
            if(enemy.unit == null)
            {
                enemy.unit = this;
            }
            this.touchDown(1,1,1,1);
        }

        this.setX(getX() + velocity.x * deltaTime);
        this.setY(getY() + velocity.y * deltaTime);
        x_offset = (int)(.51329*Gdx.graphics.getWidth() - 411.4286);
    }

    /**
     * Represents the battle between a unit and enemy
     */
    private void battle()
    {
        this.reset_Velocity();
        while(this.enemy != null){
            if(this.enemy != null){
                while(this.enemy.health_mut == true)
                {
                    if(this.enemy.killed)
                    {
                        this.enemy = null;
                        this.signaled = false;
                        
                        return;
                    }
                    //System.out.println("STUCK HERE!!!!!!!");
                }
                this.enemy.health_mut = true;
                this.enemy.setHealth(this.enemy.getHealth()-Unit.damage);
                this.enemy.checkHealth();
                //System.out.println("    ENEMY" + this.enemy.getHealth());
                if(this.enemy != null)
                {
                    this.enemy.health_mut = false;
                }
                else
                {
                    return;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(this.killed == true)
            {
                this.enemy = null;
                return;
            }
            else if(this.enemy != null && this.enemy.killed == true)
            {
                this.enemy = null;
                this.signaled = false;
                return;
            }
            if(this.enemy != null && this.enemy.getHealth() < 1 && this.enemy.killed == false)
            {
                this.enemy.killed = true;
                Game.setFunds(Game.getFunds() + this.enemy.reward);
                this.enemy.attacking_base = false;
                this.signaled = false;
                this.enemy.unit = null;
                while(Enemy.kill_mut == true)
                {
                }
                if(access.enemy.contains(this.enemy)){
                    this.access.enemy.remove(this.enemy);
                }
                this.enemy = null;
                Random one = new Random();
                int x = Math.abs(one.nextInt()%770);

                this.signaled = false;
                access.score += 100;
                return;
            }
            if(this.enemy == null)
            {
                this.enemy = null;
                return;
            }
            if(!access.enemy.contains(this.enemy))
            {
                this.enemy = null;
                return;
            }
            if(this.enemy != null && this.enemy.unit == null)
            {
                this.enemy.unit = this;
            }
        }
        return;
    }
    /**
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode)
        {
            case Input.Keys.W:
                velocity.y = moveSpeed;
                break;
            case Input.Keys.A:
                velocity.x = -moveSpeed;
                break;
            case Input.Keys.S:
                velocity.y = -moveSpeed;
                break;
            case Input.Keys.D:
                velocity.x = moveSpeed;
                break;
        }
        
        return true;
    }

    /**
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        switch(keycode)
        {
            case Input.Keys.W:
            case Input.Keys.S:
                velocity.y = 0;
                break;
            case Input.Keys.D:
            case Input.Keys.A:
                velocity.x = 0;
                break;
        }
        return true;
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean keyTyped(char c) {
        return true;
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */
    // This method gets the endpoint this unit is trying to get to and the angle 
    //      then it setsthe velocity in those directons based on that.
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
            Play.unit_processor = false;
            Play.stage_processor = true;
        }
        
        if (Gdx.input.isButtonPressed(Buttons.RIGHT) || this.enemy != null) {
            if(enemy==null)
            {
                endpoint = new Location(screenX,screenY);
            }
            else if(enemy != null && enemy.attacking_base == false)
            {
                if(this.enemy.unit == this)
                {
                    endpoint = new Location((enemy.getX()+this.getX())/2 ,-(enemy.getY()+this.getY())/2);
                }
                else
                {  
                    if(enemy.endpoint.positionX == Play.base.getPosX()+15 && enemy.endpoint.positionY == Play.base.getPosY()+20 && enemy.attacking_base == false)
                    {
                        endpoint = new Location((enemy.getX()+this.getX())/2 ,-(enemy.getY()+this.getY())/2);
                    }
                    else
                    {
                        endpoint = new Location((enemy.endpoint.positionX),-(enemy.endpoint.positionY));
                    }
                }
            }
            else
            {
                if(this.enemy == null)
                {
                    return false;
                }
                endpoint = new Location(enemy.getX(),-enemy.getY());
            }
            // This thread is used to move the unit and engage in battle when the 
            //      Unit has been signaled
            Thread move = new Thread(new Runnable() {
                @Override
                public void run(){
                    if(enemy == null || enemy.attacking_base == true)
                    {
                        create_path(endpoint);
                    }
                    else
                    {   
                        create_future_path(endpoint);
                    }
                    return;
                }
            });
            
            if(!move.isAlive()){
                double angle;
                if(this.enemy != null && signaled){
                    angle = Math.atan(Math.abs((this.getX() - endpoint.positionX))/Math.abs((this.getY() + endpoint.positionY)));

                    screenX = (int)(endpoint.positionX + x_offset);
                    screenY = (int)(endpoint.positionY + y_offset);
                    endpoint.positionY += y_offset;
                }
                else{
                    angle = Math.atan(Math.abs((this.getX() - (screenX-(x_offset))))/Math.abs((this.getY() - (-screenY + y_offset))));
                }
                if(this.getX() > screenX-x_offset && this.getY() > -screenY+y_offset){
                    velocity.y = (float)(Math.cos(angle))* -moveSpeed;
                    velocity.x = (float)(Math.sin(angle))* -moveSpeed;
                }
                if(this.getX() < screenX-x_offset && this.getY() > -screenY+y_offset){
                    velocity.y = (float)(Math.cos(angle))* -moveSpeed;
                    velocity.x = (float)(Math.sin(angle))* moveSpeed;
                }
                if(this.getX() > screenX-x_offset && this.getY() < -screenY+y_offset){
                    velocity.y = (float)(Math.cos(angle))* moveSpeed;
                    velocity.x = (float)(Math.sin(angle))* -moveSpeed;
                }
                if(this.getX() < screenX-x_offset && this.getY() < -screenY+y_offset){
                    velocity.y = (float)(Math.cos(angle))* moveSpeed;
                    velocity.x = (float)(Math.sin(angle))* moveSpeed;
                }

                if(this.killed == false){
                    move.start();
                }
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @param i
     * @param i1
     * @param i2
     * @param i3
     * @return
     */
    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return true;
    }

    /**
     *
     * @param i
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return true;
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public boolean mouseMoved(int i, int i1) {
        return true;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public boolean scrolled(int i) {
        return true;
    }

    
}
