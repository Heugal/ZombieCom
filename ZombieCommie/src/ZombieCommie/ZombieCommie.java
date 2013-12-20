package ZombieCommie;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import domain.Unit;

/**
 *
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class ZombieCommie extends Game {
 	boolean firstTimeCreate = true;
	FPSLogger fps;

        public static MainMenu mainMenuScreen;
        public Play playScreen;
        private TiledMap map;
        
        private Unit unit;

    /**
     * Creates the game. Loads everything and initializes the main menu and play screen
     */
    @Override
        public void create() {
                Assets.load();
                map = new TmxMapLoader().load("images/map.tmx");
                mainMenuScreen = new MainMenu(this, map);
                playScreen = new Play(this, (TiledMapTileLayer) map.getLayers().get(0));
                setScreen(mainMenuScreen);    
                fps = new FPSLogger();
        }
       
    /**
     *This method is called continuously by the rendering thread guaranteeing 
     * menu so to keep game up to date.
     */
    @Override
	public void render() {
		super.render();
	}
        
     /**
     * Disposes resource when no longer needed
     */
    @Override
	public void dispose () {
		super.dispose();
		getScreen().dispose();
	}
 }
