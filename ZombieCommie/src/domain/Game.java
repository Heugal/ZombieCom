package domain;

//import com.badlogic.gdx.Gdx;

/**
 * Class that represents the game
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class Game {

    public static int funds;
    private Tower tower;
    private Unit unit;

    /**
     * Constructor for Game. Sets up the initial funds.
     */
    public Game() {
        funds = 10000000;
    }
    
     /**
     * Gets the amount of funds the player currently has.
     */
    public static int getFunds(){
        return funds;
    }
    
     /**
     * Sets the amount of funds the player currently has.
     */
    public static void setFunds(int fundz){
        funds = fundz;
    }

    /**
     * Determines, based on the funds the user has, if he/she can afford the
     * wanted item
     * @param item Type of defender
     * @return
     */
    public boolean canAfford(
            int price) {
        if (funds >= price) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Buys the specified item 
     * @param item The type of defender user wants to buy
     * @return
     */
    public boolean buy(
            Defender item) {
        if (canAfford(item.getPrice())) {
            //Gdx.app.log( ZombieCommie.LOG, "Buying item: " + item );
            if (item instanceof Tower) {
                // I have to figure out how to be able to buy shit
            } else if (item instanceof Unit) {
                // I have to figure out how to be able to buy shit
            } else {
                throw new IllegalArgumentException("Unknown defender: " + item);
            }
        }
        return false;
    }
}
