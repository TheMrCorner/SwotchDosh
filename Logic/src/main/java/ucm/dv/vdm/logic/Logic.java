package ucm.dv.vdm.logic;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Input;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Logic implements ucm.dv.vdm.engine.Logic{ // TODO: Hacerse el sistema de Partículas, comentar todo lo comentable, hacer la memoria, hacer la build

    /**
     * Logic Constructor, creates a new instance of Logic.
     * @param g Game instance to store it and interact with the engine.
     */
    public Logic(Game g){
        _game = g; // Save the game instance for future use.
        init(); // Initialize everything

        rnd = new Random();
    }

    //---------------------------

    //-------Internal Logic------

    //---------------------------

    /**
     * Initialize all resources needed for the game and all GameObjects.
     */
    public void init(){

        try{ // Try to get the ResourceManager and load all resources.
            _rm = ResourceManager.getResourceMan(_game);
            createSprites();
        }
        catch (Exception e){ // Catch the exception if it fails.
            _game.HandleException(e);
        }

        _canvas = new Rect (1080, 0, 0, 1920);

        _width = _canvas.getWidth();
        _height = _canvas.getHeight();
    }

    /**
     * Calls the spriteMaker function of the different sprites to create them. Then save that info
     * in arrays of Sprites.
     */
    void createSprites(){
        // Load sprites
        // This arrays will save them. After that, the sprites will be assigned to a GO or a CO
        _sbackground = Sprite.spriteMaker(_rm.getInterface("Background"), 9, 1);
    }

    @Override
    public Rect getCanvasSize() {
        return _canvas;
    }

    @Override
    public void initLogic() {
        Sprite _sArrows =  Sprite.spriteMaker(_rm.getInterface("Arrows"), 1, 1)[0];

        _backDest = new Rect(_sArrows.get_rect().getWidth(), 0, 0, _height);
        _backDest.setPosition((_width/2) - (_sArrows.get_rect().getWidth()/2), 0);
        _currentColor = randomBackColor();

        _arrow = new Arrow((_width/2) - (_sArrows.get_rect().getWidth()/2),
                0 - (_sArrows.get_rect().getHeight()/5), _sArrows);

        _spr = new Sparkle(_width, _height, Sprite.spriteMaker(_rm.getInterface("White"), 1, 1)[0]);

        _currentGameState = new MainMenuState(this, 0);

        _currentGameState.initState(_rm);
    }

    /**
     * Updates all positions and simulates the game. (Collisions, points added to the player, etc.)
     * @param t Current time.
     */
    @Override
    public void update(double t) {
        _currentGameState.processInput(_game);

        _spr.update();

        // Update everything with the information of ProcessInput
        _currentGameState.update(t);
        _arrow.update(t);
    }

    /**
     * Render every object of the game with the updated information from update().
     */
    @Override
    public void render(){

        clearBackground();

        _sbackground[_currentColor].draw(_game.getGraphics(), _backDest);

        _arrow.render(_game.getGraphics());

        _currentGameState.render(_game.getGraphics());

        _spr.render(_game.getGraphics());
    }

    public void changeState(GameState gs, boolean runState){
        _arrow.resetVel();
        _currentGameState = gs;
        gs.initState(_rm);
        _spr.setActive(true);

        //If next state its run state, changed de background color
        if (runState){
            changeBackgroundColor();
        }
    }

    /**
     * Generates a random iterator to the background color
     */
    int randomBackColor(){

        return rnd.nextInt(9);

    }

    /**
     * Change the iterator of the background color
     */
    public void changeBackgroundColor(){
        _currentColor = randomBackColor();
    }

    void clearBackground(){

        _game.getGraphics().clear(_planeColor[_currentColor]);

    }

    /**
     * Instance of Game. Used to render and create Images.
     */
    Game _game;

    // Aspect ratio of the game
    int _width;
    int _height;
    Rect _canvas;

    /**
     * ResourceManager. Used to access it and load different Resources.
     */
    ResourceManager _rm;

    /**
     * GameState
     */
    GameState _currentGameState;

    Sprite _sbackground[];

    // Pool
    Arrow _arrow;

    //Rect of the background
    Rect _backDest;

    // SPARKLE
    Sparkle _spr;

    private Random rnd;

    int _currentColor; //Background sprites iterator
    //GREEN, TURQUOISE, LIGHTBLUE, BLUE, PURPLE, DARKBLUE, YELLOW, ORANGE, BROWN
    int _planeColor[] = {0xff41a85f, 0xff00a885, 0xff3d8eb9, 0xff2969b0, 0xff553982, 0xff28324e, 0xfff37934,
            0xffd14b41, 0xff75706b};

}