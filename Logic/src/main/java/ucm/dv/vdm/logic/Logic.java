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

public class Logic implements ucm.dv.vdm.engine.Logic{

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
     * Initialize all resources needed for the game and all gameobjects.
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
        _sArrows =  Sprite.spriteMaker(_rm.getInterface("Arrows"), 1, 1)[0];

        _sText = Sprite.spriteMaker(_rm.getText("Font"), 15, 7);


    }

    @Override
    public Rect getCanvasSize() {
        return _canvas;
    }

    @Override
    public void initLogic() {

        backDest = new Rect(_sArrows.get_rect().getWidth() - 2, 0, 0, _height);
        backDest.setPosition((_width/2) - (_sArrows.get_rect().getWidth()/2), 0);
        _currentColor = randomBackColor();

        initArrow();

        _currentGameState = new MainMenuState(this, 0);

        _currentGameState.initState(_rm);
    }

        //Para el init del game state
        _points = new Text[3];
        for (int i = 0; i < _points.length; i++) {
            _points[i] = new Text( _canvas.getWidth() - ((30 + _sText[i].get_rect().getWidth())*i), 90, _sText);
        }

    }

    void initArrow () {

        _arrow = new Arrow((_width/2) - (_sArrows.get_rect().getWidth()/2),
                _canvas.getHeight() - _sArrows.get_rect().getHeight(), _sArrows);

    }

    /**
     * Updates all positions and simulates the game. (Collisions, points added to the player, etc.)
     * @param t Current time.
     */
    @Override
    public void update(double t) {
        _currentGameState.processInput(_game);

        updateArrows(t);

        //para probar
        updatePoints(873);

        // Update everything with the information of ProcessInput
        _currentGameState.update(t);
    }

    void updateArrows (double t){

        if (_arrow.getY() >= _canvas.getHeight()){

            // Reposition arrow
            _arrow.setY(0);

        }

        _arrow.update(t);

    }

    //Pasara al game state necesario
    void updatePoints(int p){

        int i = 2;
        int div = p;
        while(div > 0){
            _points[i].setNumber(div % 10);
            div = div / 10;

            i--;
        }

        //_points[].setNumber(p % 10);

    }

    /**
     * Render every object of the game with the updated information from update().
     */
    @Override
    public void render(){

        clearBackground();

        _sbackground[_currentColor].draw(_game.getGraphics(), backDest);

        _arrow.render(_game.getGraphics());


        for (Text t : _points) {
            t.render(_game.getGraphics());
        }

        _currentGameState.render(_game.getGraphics());
    }

    public void changeState(GameState gs){
        _currentGameState = gs;
        gs.initState(_rm);
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
    Sprite _sArrows;
    Sprite _sText[];

    // Pool
    //ArrayDeque<Arrow> _arrows;
    Arrow _arrow;

    //Rect of the background
    Rect backDest;

    private Random rnd;

    int _currentColor; //Background sprites iterator
    //GREEN, TURQUOISE, LIGHTBLUE, BLUE, PURPLE, DARKBLUE, YELLOW, ORANGE, BROWN
    int _planeColor[] = {0x41a85f, 0x00a885, 0x3d8eb9, 0x2969b0, 0x553982, 0x28324e, 0xf37934,
            0xd14b41, 0x75706b};

    //Points font
    Text _points[];


}