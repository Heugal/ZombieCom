/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZombieCommie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import domain.CentralControlPoint;
import domain.Enemy;
import domain.InputMaster;
import domain.Tower;
import domain.Unit;
import domain.GameGrid;
import domain.Game;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents the play of the game
 *
 * @author Chad, Stephan, Matt, Kelly, Ian
 */
public class Play implements Screen {

    /**
     * Width of play area
     */
    public static final int WIDTH = 800;
    /**
     * Height of play area
     */
    public static final int HEIGHT = 800;
    /**
     * The set tile size
     */
    public static final int TILE_SIZE = 32;
    public static final int UI_OFFSET = 160;
    /**
     * State of game in progress
     */
    public static final int GAME_RUN = 0;
    /**
     * State of game paused
     */
    public static final int GAME_PAUSE = 1;
    /**
     * State of game over
     */
    public static final int GAME_OVER = 2;
    /**
     * The game state(running, paused, over)
     */
    public static int game_state;
    /**
     * Map
     */
    private TiledMap map;
    /**
     * Defines the variable that renders the map
     */
    private OrthogonalTiledMapRenderer renderer;
    /**
     * Camera used in Play area
     */
    private OrthographicCamera camera;
    public Stage stage;
    ZombieCommie game;
    Vector2 touchPoint; //can be Vector2 since not using z
    TiledMapTileLayer towerLayer;
    public ArrayList<Tower> towers;
    public ArrayList<Unit> unit;
    public static Unit primary_Unit;
    public static Tower primary_Tower;
    boolean displayTowerStat = false;
    public InputMaster iM = new InputMaster(this);
    public static CentralControlPoint base;
    public ArrayList<Enemy> enemy;
    public GameGrid gameGrid;
    public Game player;
    private Thread EM = new Thread(new EnemyManager(this));
    public static boolean unit_processor;
    public static boolean stage_processor;
    public static boolean tower_processor;
    private SpriteBatch batch;
    private boolean ran = false;
    public static int score;
    
    /*
     * GUI actors
     */
    Table table;
    Label nameLabel;
    Label healthLabel;
    Label xyLabel;
    Label damageArmorLabel;
    Label levelLabel;
    Label fundsLabel;
    Label notEnoughFundsLabel;
    Label unitCountLabel;
    Label scoreLabel;
    TextButton sellTowerButton;
    TextButton upgradeTowerButton;
    TextButton upgradeUnitButton;
    TextButton unitButton; 
    NinePatch patch;

    /**
     * Constructor for class Play
     *
     * @param game
     * @param towerLayer
     */
    public Play(ZombieCommie game, TiledMapTileLayer towerLayer) {

        /*
         Constructor variables
         */
        this.game = game;
        this.towerLayer = towerLayer;
        game_state = GAME_RUN;
        score = 0;
        
        /*
         Important game components
         */
        touchPoint = new Vector2();
        unit = new ArrayList<Unit>();
        enemy = new ArrayList<Enemy>();
        towers = new ArrayList<Tower>();
        gameGrid = new GameGrid(WIDTH, HEIGHT, TILE_SIZE);
        batch = new SpriteBatch();

        player = new Game();

        /*
         Current state booleans
         */
        unit_processor = false;
        stage_processor = true;
        tower_processor = false;

        /*
         *  Visual and UI initialization
         */
        map = new TmxMapLoader().load("images/map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        Skin skin = new Skin(Gdx.files.internal("data/ui/uiskin.json")); //test skin
        patch = new NinePatch(new Texture(Gdx.files.internal("data/health.png")), 0, 0, 0, 0);
        //skin.add("health", patch);
        stage = new Stage();

        /*
         *  Table for widgets
         */
        table = new Table(skin);

        /*
         *  Buttons
         */
        TextButton towerButton = new TextButton("Add Tower: " + Tower.price, skin);
        sellTowerButton = new TextButton("Sell Tower", skin);
        unitButton = new TextButton("Add Unit: " + Unit.price, skin);
        upgradeTowerButton = new TextButton("Upgrade Tower", skin);
        upgradeUnitButton = new TextButton("Upgrade Units", skin);
        TextButton pauseButton = new TextButton("Pause", skin);
        sellTowerButton.setVisible(false);
        upgradeTowerButton.setVisible(false);
        TextButton soundButton = new TextButton("Music On/Off", skin);

        /*
         * Button listeners: actions taken when clicked
         */
        soundButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(Assets.music.isPlaying()) {
                    Assets.stopMusic(Assets.music);
                } else {
                    Assets.playMusic(Assets.music);
                } 
            }            
        });     
        
        towerButton.addListener(new InputListener() {
            //test methods for UI button
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (tower_processor) {
                    tower_processor = false;
                    unit_processor = false;
                    stage_processor = true;
                } else {
                    if (Game.getFunds() < Tower.price) {
                        notEnoughFundsLabel.setText("Not enough funds!");
                    } else {
                        notEnoughFundsLabel.setText("");
                        tower_processor = true;
                        unit_processor = false;
                        stage_processor = false;
                    }
                }
            }
        });

        final Play p = this; // I'm not sure how to call Play inside of the listener w/o doing this line
        unitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (Game.getFunds() < Unit.price) {
                    notEnoughFundsLabel.setText("Not enough funds!");
                } else {
                    score += 10;
                    notEnoughFundsLabel.setText("");
                    Game.setFunds(Game.getFunds() - Unit.price);
                    Unit u = new Unit(10, 25, 10, 10, 10, new Sprite(Assets.unit), p, player);
                    Random one = new Random();
                    int xt = Math.abs(one.nextInt() % 90) + 360;
                    int yt = Math.abs(one.nextInt() % 90) + 260;
                    Random two = new Random();
                    int z = Math.abs(two.nextInt() % 4);
                    //System.out.println("Z = " + z);
                    if (z == 0) {
                        u.setPosition(xt, 350);
                    } else if (z == 1) {
                        u.setPosition(xt, 260);
                    } else if (z == 2) {
                        u.setPosition(450, yt);
                    } else {
                        u.setPosition(360, yt);
                    }
                    // need to fix this line
                    unit.add(u);
                }
            }
        });

        sellTowerButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                towers.remove(primary_Tower);
                primary_Tower.sell(gameGrid);
                primary_Tower = null;
                sellTowerButton.setVisible(false);
            }
        });

        upgradeTowerButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (Game.getFunds() < primary_Tower.getUpgradeCost()) {
                    notEnoughFundsLabel.setText("Not enough funds!");
                } else {
                    notEnoughFundsLabel.setText("");
                    int doesUpgrade = primary_Tower.upgrade();
                    if (doesUpgrade == 0) {
                        notEnoughFundsLabel.setText("Already Max Level!");
                    }
                }
            }
        });

        upgradeUnitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (Game.getFunds() < Unit.upgradeCost) {
                    notEnoughFundsLabel.setText("Not enough funds!");
                } else {
                    notEnoughFundsLabel.setText("");
                    int doesUpgrade = Unit.upgrade();
                    if (doesUpgrade == 0) {
                        notEnoughFundsLabel.setText("Already Max Level!");
                    }
                }
            }
        });

        pauseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Assets.playSound(Assets.clickSound);
                game_state = GAME_PAUSE;
                System.out.println("PAUSED!!!!!!!!!!!!!!!");
                update(0);
            }
        });


        /*
         *  Add Labels to Table
         */
        table = new Table(skin);
        Table table1 = new Table(skin);
        nameLabel = new Label("Name: ", skin);

        LabelStyle style = new LabelStyle(new BitmapFont(), new Color(1f, 1f, 1f, 1f));
        //style.background = skin.getDrawable("health");
        
        healthLabel = new Label("Health: ", style);
        levelLabel = new Label("Level: ", skin);
        damageArmorLabel = new Label("Damage: " + " Armor: ", skin);
        xyLabel = new Label("X: " + " Y: ", skin);
        fundsLabel = new Label("Funds: ", skin);
        notEnoughFundsLabel = new Label("", skin);
        unitCountLabel = new Label("Units: 0", skin);
        scoreLabel = new Label("Score: " + score, skin);        

        /*
         *  Table Layout
         */
        table.setWidth(WIDTH);
        table.setHeight(UI_OFFSET);

        table.add(nameLabel);
        table.add(fundsLabel).expandX();
        table.add(sellTowerButton);
        table.row();
        table.add(levelLabel);
        table.add(healthLabel).expandX();
        table.add(upgradeTowerButton);
        table.row();
        table.add(damageArmorLabel);
        table1.add(towerButton);
        table1.add(unitButton);
        table1.add(upgradeUnitButton);
        table.add(table1);
        table.add(notEnoughFundsLabel);
        table.row();
        
        //table.add(xyLabel);
        //table.add(unitButton).expandX();
        //table.add(pauseButton);
        table.add(unitCountLabel);
        table.add(scoreLabel).expandX();
        table.add(soundButton);

        /*
         *  Add table
         */
        stage.addActor(table);
        //table.debug();

        Gdx.input.setInputProcessor(stage);

        //the camera is set (increasing)
        //to X Left to Right
        //Y top to bottom
        camera = new OrthographicCamera(WIDTH, HEIGHT);

        //sets the Y axis increasing from bottom to top
        //camera.setToOrtho(true, WIDTH, HEIGHT);

        camera.position.set(WIDTH / 2, HEIGHT / 2 - UI_OFFSET, 0);
        //camera.position.set(Gdx.graphics.getWidth()-WIDTH,Gdx.graphics.getHeight()-HEIGHT, 0);
    }

    public void changeInputProcessor(InputProcessor in) {
        Gdx.input.setInputProcessor(in);
    }

    public static void remove_enemy(Enemy ene) {
        ene = null;
    }

    public static void remove_unit(Unit un) {
        un = null;
    }

    /**
     * Updates the play area
     *
     * @param delta Time span between the current frame and the last frame in
     * seconds
     */
    public void update(float delta) {
        switch (game_state) {
            case GAME_RUN:
                updateGame(delta);
                break;
            case GAME_PAUSE:
                updatePause();
                break;
            case GAME_OVER:
                System.out.println("GAME OVER");
                System.exit(1);
                break;
        }
    }
/**
 * 
 * @param delta Time span between the current frame and the last frame in
     * seconds
 */
    public void updateGame(float delta) {
        if (unit_processor) {
            //System.out.println("UNIT HAS IT!!");
            if (Gdx.input.justTouched()) {
                //empty
            }
        } else if (tower_processor) { // UI handle for when user is handling a tower
            //System.out.println("TO GET TOUCHED");
            if (Gdx.input.justTouched()) {
                int x = Gdx.input.getX(), y = Gdx.input.getY();
                if (this.player.canAfford(Tower.price)) {
                    addTower(y, x);
                    score += 10;
                }
                tower_processor = false;
                unit_processor = false;
                stage_processor = true;
            }
        } else if (stage_processor) { // UI handle for when user is handling the main stage
            if (Gdx.input.justTouched()) {
                //System.out.println("x: " + Gdx.input.getX() + " y: " + (-Gdx.input.getY() + Unit.y_offset));
                displayTowerStat = false;
                int x = Gdx.input.getX(), y = HEIGHT - Gdx.input.getY() - UI_OFFSET;
                if (!unit.isEmpty()) {
                    boolean foundUnit = false;
                    int index = -1;
                    for (int i = 0; i < unit.size(); i++) {
                        /*
                         * Get position of user mouse click
                         * Check each unit's position in the game
                         * if the user clicked in the region of the sprite
                         * select that unit and set the input processor to unit
                         */
                        Unit u = unit.get(i);
                        if (x < (u.getX() + TILE_SIZE) && y < (u.getY() + TILE_SIZE)
                                && x > u.getX() && y > u.getY()) {
                            foundUnit = true;
                            index = i;
                        }
                    }
                    if (foundUnit && index >= 0) {
                        primary_Unit = unit.get(index);
                        stage_processor = false;
                        tower_processor = false;
                        unit_processor = true;
                    }
                }
                if (!towers.isEmpty()) {
                    boolean foundTower = false;
                    int index = -1;
                    for (int i = 0; i < towers.size(); i++) {
                        Tower t = towers.get(i);
                        if (x < (t.getX() + TILE_SIZE) && y < (t.getY() + TILE_SIZE)
                                && x > t.getX() && y > t.getY()) {
                            foundTower = true;
                            index = i;
                        } else {
                            sellTowerButton.setVisible(false);
                            upgradeTowerButton.setVisible(false);
                            upgradeUnitButton.setVisible(true);
                        }
                    }
                    if (foundTower && index >= 0) {
                        primary_Tower = towers.get(index);
                        displayTowerStat = true;
                        ShapeRenderer circle = new ShapeRenderer();
                        circle.begin(ShapeRenderer.ShapeType.Line);
                        circle.setColor(com.badlogic.gdx.graphics.Color.CYAN);
                        circle.circle(primary_Tower.getPosX(), primary_Tower.getPosY(), 200);
                        sellTowerButton.setVisible(true);
                        upgradeTowerButton.setVisible(true);
                        upgradeUnitButton.setVisible(false);
                        circle.end();
                    }
                }
                if (x < (base.getX() + TILE_SIZE * 2) && y < (base.getY() + TILE_SIZE * 2)
                        && x < base.getX() && y > base.getY()) {
                    stage_processor = true;
                    tower_processor = false;
                    unit_processor = false;
                }
            }
        }
    }
/**
 * Checks if game was paused
 */
    private void updatePause() {
        if (Gdx.input.justTouched()) {
            //guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
    }
/**
 * Adds tower to game grid
 * @param y - y coordinate of the placing of the tower
 * @param x - x coordinate of the placing of the tower
 */
    public void addTower(int y, int x) {
        /**
         * logic: first the game checks to see if there is a tower in that spot
         * if it is free, the game creates a new tower object and sets its x and
         * y coordinates. Then the game marks the game grid as locked to
         * indicate that there is a tower in that position
         *
         */
        int offsetX = (Gdx.graphics.getWidth() - WIDTH) / 2, offsetY = (Gdx.graphics.getHeight() - UI_OFFSET) / 2;
        // the real x and y coordinates to account for the UI offsets
        int realX = x - offsetX, realY = -y + (offsetY * 2);
        int cellY = gameGrid.getCellYFromPixel(realY), cellX = gameGrid.getCellXFromPixel(realX);
        if (gameGrid.checkIfEntityIsWithinGameGridBounds(realX, realY)) {
            if (gameGrid.checkRadiusFromPixel(realY, realX) == false) {
                Tower temp = new Tower(1, realX, realY, 5, 2, new Sprite(Assets.tower), this);
                Game.setFunds(Game.getFunds() - Tower.price);
                if ((x >= offsetX) && (-y <= offsetY)) {
                    temp.setPosition(cellX * TILE_SIZE, cellY * TILE_SIZE);
                    temp.setPosX(cellX * TILE_SIZE);
                    temp.setPosY(cellY * TILE_SIZE);
                    towers.add(temp);
                }
                gameGrid.setGameGridValueFromCoordinates(realY, realX, 1);
            } else // temporary 'theres already a tower there' message
            {
                System.out.println("THERES ALREADY A TOWER THERE HOMIE");
            }
        }
    }

    /**
     *Render method is called continuously by the rendering thread guaranteeing 
     * menu so to keep play area up to date. 
     * @param delta Time span between the current frame and the last frame in
     * seconds
     */
    @Override
    public void render(float delta) {

        for(int i = 0; i < enemy.size();i++)
        {
            if(enemy.get(i) != null)
            {
                if(enemy.get(i).getHealth() < 1)
                {
                    enemy.remove(enemy.get(i));
                    break;
                }
            }
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        if (Play.unit_processor == true) { // What is shown when user is handling a unit
            Gdx.input.setInputProcessor(primary_Unit);
            nameLabel.setText("Name: Unit");
            healthLabel.setText("Health: " + primary_Unit.getHealth() + "/" + primary_Unit.getMaxHealth());
            xyLabel.setText("X: " + (int) primary_Unit.getX() + " Y: " + (int) primary_Unit.getY());
            levelLabel.setText("Level: " + primary_Unit.getLevel());
            damageArmorLabel.setText("Damage: " + primary_Unit.getDamage() + " Armor: " + primary_Unit.getArmor());
            float n = (float) primary_Unit.getHealth() / primary_Unit.getMaxHealth();
            patch.draw(batch, 182, HEIGHT - 700 - 19, n * 500f, 24);
        } else if (Play.stage_processor == true && displayTowerStat == true) { // What is shown when a user is handling a tower
            nameLabel.setText("Name: Tower");
            healthLabel.setText("Health: N/A");
            xyLabel.setText("X: " + (int) primary_Tower.getPosX() + " Y: " + (int) primary_Tower.getPosY());
            levelLabel.setText("Level: " + primary_Tower.getLevel());
            damageArmorLabel.setText("Damage: " + primary_Tower.getDamage() + " Armor: N/A");
            Gdx.input.setInputProcessor(stage);
        } else if (Play.stage_processor == true) { // What a user is shown when handling the main stage
            /* 
             * Made the default stats displayed of the Central
             * Control Point. I figured it was important enough
             * that you wouldn't want to have to click on it every
             * time you'd want to see the health of it. Especially
             * if it's nearing its last few health points and you 
             * are trying to click back and forth between enemies, units
             * , and towers. Regardless, the code is in to make it clickable.
             * I think it should be justified by something like:
             * upgradable CCP to add health and upgrades to units and/or towers.
             */
            nameLabel.setText("Name: CCP");
            healthLabel.setText("Health: " + base.getHealth() + "/" + base.getMaxHealth());
            xyLabel.setText("X: " + base.getX() + " Y: " + base.getY());
            levelLabel.setText("Level: 1");
            damageArmorLabel.setText("Damage: N/A" + " Armor: N/A");
            unitButton.setText("Add Unit: " + Unit.price);
            float n = (float) base.getHealth() / base.getMaxHealth();
            patch.draw(batch, 182, HEIGHT - 700 - 19, n * 500f, 24);

            Gdx.input.setInputProcessor(stage);
        } else if (Play.tower_processor == true) {
            /*
             * You are about to place a tower
             */
            float n = (float) base.getHealth() / base.getMaxHealth();
            patch.draw(batch, 182, HEIGHT - 700 - 19, n * 500f, 24);            
            Gdx.input.setInputProcessor(stage);
        } else {
            Gdx.input.setInputProcessor(iM);
        }


        batch.end();
        fundsLabel.setText("Funds: " + Game.getFunds());
        unitCountLabel.setText("Units: " + unit.size());
        scoreLabel.setText("Score: " + score);
        upgradeTowerButton.setText("Upgrade Towers: " + Tower.upgradeCost);
        upgradeUnitButton.setText("Upgrade Units: " + Unit.upgradeCost);
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Table.drawDebug(stage);

        renderer.setView(camera);
        renderer.render();
        renderer.getSpriteBatch().begin();

        //iM.handle();
        if (game_state != GAME_PAUSE) {
            if (!unit.isEmpty()) {
                for (int i = 0; i < unit.size(); i++) {
                    unit.get(i).draw(renderer.getSpriteBatch());
                }
            }
            if (!enemy.isEmpty()) {
                for (int i = 0; i < enemy.size(); i++) {
                    if (enemy.get(i) != null) {
                        enemy.get(i).draw(renderer.getSpriteBatch());
                    }
                }
            }
            int enemy_size = enemy.size();
            int unit_size = unit.size();
            
            if (!enemy.isEmpty() && !unit.isEmpty()) {
                Unit.kill_mut = true;
                unit_size = unit.size();
                for (int i = 0; i < unit_size; i++) {
                    Enemy temp = null;
                    Enemy.kill_mut = true;
                    enemy_size = enemy.size();
                    for (int j = 0; j < enemy_size; j++) {
                        if (Math.sqrt((enemy.get(j).getX() - unit.get(i).getX()) * (enemy.get(j).getX() - unit.get(i).getX()) + (enemy.get(j).getY() - unit.get(i).getY()) * (enemy.get(j).getY() - unit.get(i).getY())) < 100
                                && unit.get(i).signaled == false) {

                            if (this.unit.get(i) != null && enemy.get(j).unit == null) {
                                unit.get(i).enemy = enemy.get(j);
                                unit.get(i).reset_Velocity();
                                unit.get(i).update(delta);
                            } else if (this.unit.get(i) != null && enemy.get(j).endpoint.positionX != Play.base.getPosX()+15 || enemy.get(j).endpoint.positionY != Play.base.getPosY()+20) {
                                temp = enemy.get(j);
                            }
                            //enemy_size = enemy.size();
                        }  
                    }
                    Enemy.kill_mut = false;
                    if (unit.get(i).enemy == null && temp != null) {
                        unit.get(i).enemy = temp;
                        unit.get(i).reset_Velocity();
                        unit.get(i).update(delta);
                    } 
                }
                Unit.kill_mut = false;
            }


            if (!enemy.isEmpty() && !towers.isEmpty()) {
                for (int i = 0; i < towers.size(); i++) {
                    for (int j = 0; j < enemy.size(); j++) {
                        Tower temp_tower = towers.get(i);
                        Enemy temp_enemy = enemy.get(j);
                        if (gameGrid.checkEnemyInRange(temp_enemy, temp_tower) == true) {
                            int killed = temp_tower.attack(temp_enemy, player);
                            if (killed == 1) {
                                enemy.remove(temp_enemy);
                                Random one = new Random();
                                int x = Math.abs(one.nextInt() % 800);
                                //enemy.add(new Enemy("Enemy", 10, 10, 10, 10, 10, 10, new Sprite(Assets.enemy), x, 800, this));
                            }
                        }
                    }
                }
                //System.out.println("They both exist.");
            }
            //System.out.println("HERE IS THE VALUE");
            //System.out.println(Math.sqrt((enemy.getX()-unit.getX())*(enemy.getX()-unit.getX())+(enemy.getY()-unit.getY())*(enemy.getY()-unit.getY())));
            //Gdx.input.setInputProcessor(tower);
        }
        base.draw(renderer.getSpriteBatch());
        for (int i = 0; i < towers.size(); i++) {
            Tower temp = towers.get(i);
            temp.draw(renderer.getSpriteBatch());
        }
        renderer.getSpriteBatch().end();
        if (!unit.isEmpty()) {
            for (int i = 0; i < unit.size(); i++) {
                unit.get(i).update(delta);
            }
        }
        if (!enemy.isEmpty()) {
            for (int i = 0; i < enemy.size(); i++) {
                if (enemy.get(i) != null) {
                    enemy.get(i).update(delta);
                }
            }
        }
        update(delta);
    }

    /**
     * Resizes the camera to a new width and height
     *
     * @param width new width camera is being set to
     * @param height new height camera is being set to
     */
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        stage.setViewport(width, height, true);
        stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
    }

    /**
     *Sets positions of objects 
     */
    @Override
    public void show() {
        int offsetX = (Gdx.graphics.getWidth() / 2) - (TILE_SIZE / 2), offsetY = ((Gdx.graphics.getHeight() - UI_OFFSET) / 2) - TILE_SIZE;
        //unit.add(new Unit("Unit", 10, 25, 10, 10, 10, new Sprite(Assets.unit), this, player));
        //unit.add(new Unit("Unit", 10, 25, 10, 10, 10, new Sprite(Assets.unit), this, player));
        //unit.get(0).setPosition(400, 400);
        //unit.get(1).setPosition(450, 450);
        base = new CentralControlPoint("Base", 100, new Sprite(Assets.ccp));
        base.setPosition(offsetX, offsetY);
        base.setPosX(offsetX);
        base.setPosY(offsetY);
        gameGrid.setupCentralControlPointGameGrid(offsetY, offsetX);
        //enemy.add(new Enemy("Enemy", 10, 10, 10, 10, 10, 10, new Sprite(Assets.enemy), 800, 800, this));
        Random one = new Random();
        int x = Math.abs(one.nextInt() % 800);
        //new Thread(new EnemyManager(this)).start();
        if (!ran) {
            EM.start();
        }
        ran = true;

        //System.out.println(x);
        //this.enemy.add(new Enemy("Enemy", 10, 10, 10, 10, 10, 10, new Sprite(Assets.unit),x , 800,this));
    }

    /**
     *
     */
    @Override
    public void hide() {
    }

    /**
     * Pauses the game
     */
    @Override
    public void pause() {
    }

    /**
     * Resumes game in a situation in which it is paused
     */
    @Override
    public void resume() {
    }

    /**
     * Disposes resource when no longer needed
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
