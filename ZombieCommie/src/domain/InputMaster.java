/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import ZombieCommie.Play;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;
import ZombieCommie.ZombieCommieDesktop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Class that represents the input processor
 * @author tud55469
 */
public class InputMaster implements InputProcessor {

    Play play;
    /**
     * Constructor for input master
     * @param play the Play area
     */
    public InputMaster(Play play)
    {
        this.play = play;
        this.handle(play.stage);
    }
    
    public void handle(InputProcessor in)
    {
        this.play.changeInputProcessor(in);
    }

    @Override
    public boolean keyDown(int i) {
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        if(Input.Keys.ESCAPE == c)
        {
            play.unit_processor = false;
            play.stage_processor = false;
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        
        return true;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return true;
        
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return true;
        
    }

    @Override
    public boolean scrolled(int i) {
        return true;
        
    }
    
}
