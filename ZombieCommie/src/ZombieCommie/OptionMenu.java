package ZombieCommie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class OptionMenu implements Screen{
    OrthographicCamera guiCam;
    SpriteBatch batcher;
    ZombieCommie game;
    private TiledMap map;
    Rectangle musicBtn1Bounds;
    Rectangle musicBtn2Bounds;
    Rectangle musicBtn3Bounds;
    Rectangle musicBtn4Bounds;
    Vector3 touchPoint;
    
    public OptionMenu(ZombieCommie game, TiledMapTileLayer towerLayer){
        this.game = game;
        guiCam = new OrthographicCamera(320, 480);
        batcher = new SpriteBatch();
        guiCam.position.set(320 / 2, 480 / 2, 0);
        musicBtn1Bounds = new Rectangle(10, 500, 60, 60);
        musicBtn2Bounds = new Rectangle(90, 500, 60, 60);
        musicBtn3Bounds = new Rectangle(170,500, 60, 60);
        musicBtn4Bounds = new Rectangle(250, 500, 60, 60);
        touchPoint = new Vector3();
    }
    
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
        batcher.draw(Assets.optionLogo, 0, 300, 300, 40);
        batcher.draw(Assets.musicBtn1, 0, 100, 60, 60);
        batcher.draw(Assets.musicBtn2, 70, 100, 60, 60);
        batcher.draw(Assets.musicBtn3, 140, 100, 60, 60);
        batcher.draw(Assets.musicBtn4, 210, 100, 60, 60);
        batcher.end();
    }
    
    public void update(float delta){
        if (Gdx.input.justTouched()) {
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (OverlapTester.pointInRectangle(musicBtn1Bounds, touchPoint.x, touchPoint.y)){
                Assets.playSound(Assets.clickSound);
                Assets.setSong("data/PSC_-_DelinvFile_3.03_Build_3.3.0.46crk.mp3");
            }
            if (OverlapTester.pointInRectangle(musicBtn2Bounds, touchPoint.x, touchPoint.y)){
                Assets.playSound(Assets.clickSound);
                Assets.setSong("data/MYTH_-_Deus_Ex_installer.mp3");
            }
            if (OverlapTester.pointInRectangle(musicBtn3Bounds, touchPoint.x, touchPoint.y)){
                Assets.playSound(Assets.clickSound);
                Assets.setSong("data/ismail_-_ProShow_Gold_3.x.x.xcrk.mp3");
            }
            if (OverlapTester.pointInRectangle(musicBtn4Bounds, touchPoint.x, touchPoint.y)){
                Assets.playSound(Assets.clickSound);
                Assets.setSong("data/music.mp3");
            } 
        }
    }
    
    @Override
    public void dispose(){
        
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    @Override
    public void resize(int i, int i1) {
        
    }

    @Override
    public void show() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }
}
