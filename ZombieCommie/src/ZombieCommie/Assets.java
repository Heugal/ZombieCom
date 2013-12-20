package ZombieCommie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Assets initiates the files that accompany the game 
 * but doesn't provide any logic (background, buttons, sounds, etc.)
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class Assets {
    /**
     * The background image
     */
    public static Texture background;
    /**
     *   Defines the rectangular area for the background
     */
    public static TextureRegion backgroundRegion;
    /**
     * The sprite map that contains the images of the logo, buttons, units, etc.
     */
    public static Texture items;
     /**
     * Defines the rectangular area for the Main Menu
     */
    public static TextureRegion mainMenu;
     /**
     *  Defines the rectangular area for the pause button
     */
    public static TextureRegion pauseMenu;
     /**
     *  Defines the rectangular area for the Ready text
     */
    public static TextureRegion ready;
     /**
     * Defines the rectangular area for the Game Over text
     */
    public static TextureRegion gameOver;
     /**
     * Defines the rectangular area for the high scores
     */
    public static TextureRegion highScoresRegion;
     /**
     * Defines the rectangular area for the logo
     */
    public static TextureRegion logo;
    /**
     * Defines the rectangular area for the sound on button
     */
    public static TextureRegion soundOn;
     /**
     * Defines the rectangular area for the sound off button
     */
    public static TextureRegion soundOff;
     /**
     * Defines the rectangular area for an arrow
     */
    public static TextureRegion arrow;
    /** 
     * Defines the rectangular area for the pause button
    */
    public static TextureRegion pause;
    /**
     * Bit map used for font
     */
    public static BitmapFont font;
    /**
     * Defines the rectangular area for a unit
     */
    public static TextureRegion unit;
    /**
     * Defines the rectangular area for an enemy1
     */
    public static TextureRegion enemy;
    /**
     * Defines the rectangular area for an enemy2
     */
    public static TextureRegion enemy2;
    /**
     * Defines the rectangular area for an enemy3
     */
    public static TextureRegion enemy3;
    /**
     * Defines the rectangular area for a tower
     */
    public static TextureRegion tower;
     /**
     * Defines the rectangular area for the central control point
     */
    public static TextureRegion ccp;
    
    public static TextureRegion musicBtn1;
    public static TextureRegion musicBtn2;
    public static TextureRegion musicBtn3;
    public static TextureRegion musicBtn4;
    
    public static TextureRegion optionLogo;
    
    /**
     *The background music for the game
     */
    public static Music music;
    /**
     * The sound of a click
     */
    public static Sound clickSound;

    
    private static String song = "data/MYTH_-_Deus_Ex_installer.mp3";
    
    public static void setSong(String song){
        music.stop();
        music.dispose();
        Assets.song = song;
        music = Gdx.audio.newMusic(Gdx.files.internal(song));
        playMusic(music);
    }
    
    /**
     *Loads the file necessary and returns it as a texture 
     * @param file File to be loaded as texture 
     * @return texture loaded from file
     */
    public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

     /**
     * Loads all necessary files
     */
    public static void load () {
		background = loadTexture("data/background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
		items = loadTexture("data/items.png");
		mainMenu = new TextureRegion(items, 224, 2, 700, 150);
                optionLogo = new TextureRegion(items, 250, 60, 700, 45);
                musicBtn1 = new TextureRegion(items, 300, 240, 60, 75);
                musicBtn2 = new TextureRegion(items, 380, 245, 60, 75);
                musicBtn3 = new TextureRegion(items, 300, 350, 60, 65);
                musicBtn4 = new TextureRegion(items, 380, 345, 60, 70);
		pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
		ready = new TextureRegion(items, 320, 224, 192, 32);
		gameOver = new TextureRegion(items, 352, 256, 160, 96);
		highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
		logo = new TextureRegion(items, 0, 330, 225, 181);
		soundOff = new TextureRegion(items, 0, 0, 64, 64);
		soundOn = new TextureRegion(items, 64, 0, 64, 64);
		arrow = new TextureRegion(items, 0, 64, 64, 64);
		pause = new TextureRegion(items, 64, 64, 64, 64);

                unit = new TextureRegion(items, 1,127,32,32);
                tower = new TextureRegion(items, 127,32,32,32);
                ccp = new TextureRegion(items, 130,67,64,64);
                enemy = new TextureRegion(items, 96, 127, 32, 32);
                enemy2 = new TextureRegion(items, 132, 127, 32, 32);
                enemy3 = new TextureRegion(items, 162, 130, 32, 32);
                
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);

		music = Gdx.audio.newMusic(Gdx.files.internal(song));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		clickSound = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
	}

        /**
         * Plays the specified sound
         * @param sound The sound to be played 
         */
	public static void playSound (Sound sound) {
            sound.play(1);
	}
        
        public static void playMusic (Music music) {
            music.play();
        }
        
        public static void stopMusic (Music music) {
            music.stop();
        }
}
