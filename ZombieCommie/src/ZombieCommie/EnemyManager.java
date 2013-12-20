/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZombieCommie;

import com.badlogic.gdx.graphics.g2d.Sprite;
import domain.Enemy;
import domain.Unit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class EnemyManager manages the rate and the direction of the enemies entering
 * the play area
 * @author Matt
 */
public class EnemyManager implements Runnable {

    /**
     * Gives access to Play area
     */
    private Play access;
    /**
     * Time the game started
     */
    private long start_time;
    /**
     * Rate of enemies
     */
    private int rate;
    private int health = 5;
    private int damage = 1;
    private int reward = 10;
    public EnemyManager(Play play)
    {
        rate = 5000;
        start_time = System.currentTimeMillis();
        access = play;
    }
    
    // This is the main thread that is creating the enemies throughout the game.
    //      It begins by setting the start time when it begins and then goes into
    //      a forever while loop or atleast untill the game ends.
    //      It also creates the new enemies in random spots around the outside of
    //      the map as well as updates the speed at which they are created.
    @Override
    public void run() {
        start_time = System.currentTimeMillis();
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(EnemyManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true)
        {
            // Increases the score every second by 1.
            if(Math.abs(System.currentTimeMillis() - start_time) % (1000) == 0)
            {
                access.score += 1;
            }
            // Increases the rate every 50 seconds by 3/5
            if(Math.abs(System.currentTimeMillis() - start_time) % (5000*10) == 0)
            {
                rate = rate*3/5 ;
            }
            // Creates a new Enemy every the amound of time rate goes by.
            if(Math.abs(System.currentTimeMillis() - start_time) % rate == 0)
            {
                Random one = new Random();
                int x = Math.abs(one.nextInt()%770);
                int y = Math.abs(one.nextInt()%590);
                Random two = new Random();
                int z = Math.abs(two.nextInt()%4);
                if(z == 0)
                {
                    access.enemy.add(new Enemy(health, damage, reward, 10, 10, 10, new Sprite(Assets.enemy),x , 590,access));
                }
                else if(z == 1)
                {
                    access.enemy.add(new Enemy(health, damage,reward, 10, 10, 10, new Sprite(Assets.enemy),x , 0,access));
                }
                else if(z == 2)
                {
                    access.enemy.add(new Enemy(health, damage, reward, 10, 10, 10, new Sprite(Assets.enemy),770 , y,access));
                }
                else
                {
                    access.enemy.add(new Enemy(health, damage, reward, 10, 10, 10, new Sprite(Assets.enemy),0 ,y,access));
                }
                for(int i = 0; i < 5; i++){
                
                }
            }
            // Makes new enemies created stronger that they were before and increases the rate they are spawned at.
            if(Math.abs(System.currentTimeMillis() - start_time) % (5000*50) == 0)
            {
                health = health + 5;
                reward = reward + 5;
                damage = damage + 1;
                rate = rate*2;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(EnemyManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
