package domain;

import ZombieCommie.Play;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *Class that represents a tower
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class Tower extends Sprite
        implements Defender, InputProcessor {

    private int damage;
    public static final int price = 50;
    private int health;
    private int armor;
    private int level;
    private int attackSpeed;
    private float moveSpeed = 0;
    private int worth;
    public static int upgradeCost;
    private int posX;
    private int posY;
    private int range;
    private long lastAttack;
    private Sprite sprite;
    public boolean signaled = false;
    private Play access;

    /**
     * Constructor for class Tower
     *
     * @param name Name of tower
     * @param damage Amount of damage to tower
     * @param health Health status of tower
     * @param armor Amount of armor tower has
     * @param attackSpeed Attack speed of the tower
     * @param sprite Sprite of tower (2D image of tower)
     */
    public Tower(
            int damage,
            int posX,
            int posY,
            int attackSpeed,
            int range,
            Sprite sprite,Play play) {
        super(sprite);
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.attackSpeed = attackSpeed;
        this.level = 1;
        this.range = range;
        this.upgradeCost = 70;
        this.lastAttack = System.nanoTime() - attackSpeed;
        access = play;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public float getMoveSpeed() {
        return 0;
    }

    @Override
    public int getWorth() {
        return worth;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    public int getRange() {
        return range;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    @Override
    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void setWorth(int worth) {
        this.worth = worth;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(int posY) {
        this.posY = posY;
    }
/**
 * Sets the range in which it would attack an enemy
 * @param range - range of tower
 */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Initializes the tower to attack
     */
    public int attack(Enemy enemy, Game player) {
        if (System.nanoTime() - lastAttack > (attackSpeed*1000000000)) {
            if (enemy.getHealth() > 0) {
                //LOCK
                if (enemy.health_mut = true) {
                    if (enemy.killed) {
                        return 0;
                    }
                }
                enemy.health_mut = true;
                enemy.setHealth(enemy.getHealth() - this.damage);
                enemy.checkHealth();
                this.lastAttack = System.nanoTime();
                if (enemy.getHealth() < 1) {
                    enemy.killed = true;
                    Game.setFunds(Game.getFunds() + 10);
                    enemy.attacking_base = false;
                    enemy.health_mut = false;
                    while(Enemy.kill_mut == true)
                    {
                    }
                    if(access.enemy.contains(enemy))
                    {
                        access.enemy.remove(enemy);
                    }
                    enemy = null;
                    access.score += 100;
                    return 1;
                }
                enemy.health_mut = false;
            }
            return 0;
        } else {
            return 0;
        }
    }

    /**
     * Upgrades a tower
     */
    public int upgrade() {
        if (this.level < 3) {
            Game.setFunds(Game.getFunds() - this.getUpgradeCost());
            setLevel(this.level + 1);
            setDamage(this.damage + 1);
            setUpgradeCost(this.upgradeCost + 10);
            return 1;
        }
        return 0;
    }

    /**
     * Sells a tower
     */
    public void sell(GameGrid gameGrid) {
        gameGrid.setGameGridValueFromCoordinates(posY, posX, 0);
        Game.setFunds(Game.getFunds() + (int) (Tower.price / 2));
    }

    /**
     *
     * @param spriteBatch
     * @param alphaModulation
     */
    @Override
    public void draw(SpriteBatch spriteBatch, float alphaModulation) {
        //update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch, alphaModulation);
    }

    /**
     * Updates the location coordinates of tower
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void update(int x, int y) {
        this.setX(x - 240);
        this.setY(y - 60);
    }

    /**
     * Calculates the sell price of the tower
     *
     * @param price the current price of the tower
     * @return
     */
    public long getSellPrice(int price) {
        // currently selling a tower will return 70% of its initial worth
        return Math.round(0.7 * price);
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public boolean keyDown(int i) {
        return true;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public boolean keyUp(int i) {
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
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.update(screenX, screenY);
        return true;
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
