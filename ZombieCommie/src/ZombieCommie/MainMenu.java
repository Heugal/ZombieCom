package ZombieCommie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Class that represents the main menu of the game
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class MainMenu implements Screen {

    OrthographicCamera guiCam;
    SpriteBatch batcher;
    ZombieCommie game;
    Rectangle playBounds;
    Rectangle exitBounds;
    Rectangle optionBounds;
    Vector3 touchPoint;
    private TiledMap map;

    /**
     *Constructor for the main menu
     * @param game 
     * @param map
     */
    public MainMenu(ZombieCommie game, TiledMap map) {
        this.game = game;
        this.map = map;

        guiCam = new OrthographicCamera(320, 480);
        batcher = new SpriteBatch();
        guiCam.position.set(320 / 2, 480 / 2, 0);
        playBounds = new Rectangle(220, 265, 500, 40);
        optionBounds = new Rectangle(230, 230, 500, 40);
        exitBounds = new Rectangle(260, 190, 500, 40);
        touchPoint = new Vector3();
    }

    /**
     * Updates the main menu
     * @param deltaTime Time span between the current frame and the last frame in seconds
     */
    public void update(float deltaTime) {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(playBounds, touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new Play(game, (TiledMapTileLayer) map.getLayers().get(0)));
            }
            
        }

        if (OverlapTester.pointInRectangle(optionBounds, touchPoint.x, touchPoint.y)){
                Assets.playSound(Assets.clickSound);
                game.setScreen(new OptionMenu(game, (TiledMapTileLayer) map.getLayers().get(0)));
        }
        if (OverlapTester.pointInRectangle(exitBounds, touchPoint.x, touchPoint.y)) {
            Assets.playSound(Assets.clickSound);
            game.dispose();
            System.exit(0);
            return;
        }
    }

    /**
     * Draws the assets of the main menu
     * @param deltaTime Time span between the current frame and the last frame in seconds
     */
    public void draw(float deltaTime) {
        GLCommon gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        batcher.setProjectionMatrix(guiCam.combined);

        batcher.disableBlending();
        batcher.begin();
        batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
        batcher.end();

        batcher.enableBlending();
        batcher.begin();
        batcher.draw(Assets.logo, 100, 480 - 10 - 142, 100, 142);
        batcher.draw(Assets.mainMenu, 200, 250 - 110 / 2, 300, 110);
        batcher.end();
    }

    /**
     *This method is called continuously by the rendering thread guaranteeing 
     * menu so to keep menu up to date. Calls update and draw. 
     * @param delta Time span between the current frame and the last frame in 
     * seconds
     */
    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    /**
     * Resizes the screen 
     * @param i
     * @param i1
     */
    @Override
    public void resize(int i, int i1) {
    }

    /**
     *
     */
    @Override
    public void show() {
    }

    /**
     *When called, disposes of main menu 
     */
    @Override
    public void hide() {
        dispose();
    }

    /**
     *
     */
    @Override
    public void pause() {
    }

    /**
     *
     */
    @Override
    public void resume() {
    }

    /**
     * Disposes resource when no longer needed
     */
    @Override
    public void dispose() {
    }
}
