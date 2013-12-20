package ZombieCommie;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Class the contains the main method that initializes a game of Zombie Commie
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class ZombieCommieDesktop {
    /**
     * Main method that creates a new game of Zombie Commie
     *
     * @param args the command line arguments
     */
    public static void main (String[] argv) {
            LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
            cfg.title = "Zombie Commie";
            cfg.useGL20 = true;
            cfg.width = 800;
            cfg.height = 800;  
            
            new LwjglApplication(new ZombieCommie(), cfg);
	}
}