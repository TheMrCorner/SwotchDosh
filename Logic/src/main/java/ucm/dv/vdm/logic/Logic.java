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

    public enum BackColor {
        GREEN,
        TURQUOISE,
        LIGHTBLUE,
        BLUE,
        PURPLE,
        DARKBLUE,
        YELLOW,
        ORANGE,
        BROWN
    }

    /**
     * Logic Constructor, creates a new instance of Logic.
     * @param g Game instance to store it and interact with the engine.
     */
    public Logic(Game g){
        _game = g; // Save the game instance for future use.
        _gameState = new GameState[4]; //Save de gameState instance for future use.
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

        _currentState = 3;
    }

    /**
     * Calls the spriteMaker function of the different sprites to create them. Then save that info
     * in arrays of Sprites.
     */
    void createSprites(){
        // Load sprites
        // This arrays will save them. After that, the sprites will be assigned to a GO or a CO
        _sbackground = Sprite.spriteMaker(_rm.getInterface("Background"), 9, 1);
        _sArrows =  Sprite.spriteMaker(_rm.getInterface("Arrows"), 1, 5)[0];


    }

    @Override
    public Rect getCanvasSize() {
        return _canvas;
    }

    @Override
    public void initLogic() {

        backDest = new Rect(_sArrows.get_rect().getWidth() - 2, 0, 0, _height);
        backDest.setPosition((_width/2) - (_sArrows.get_rect().getWidth()/2), 0);
        _color = randomBackColor();

        initArrows();

        // Create all gameObjects and CanvasObjects
        for(int i = 0; i < _gameState.length; i++){
            _gameState[i] = new GameState(i, this);
            _gameState[i].initState(_rm);
        }
    }

    void initArrows () {

        int numArrows = (_height / _sArrows.get_rect().getHeight()) +2; //Number of sprites of arrows that we need at a specific canvas

        _arrows = new ArrayDeque<>();

        Arrow a = new Arrow((_width/2) - (_sArrows.get_rect().getWidth()/2),
                _canvas.getHeight() - _sArrows.get_rect().getHeight(), _sArrows);
        _arrows.add(a);

        //Keeps all the arrows in an array to update them later and draw the first position
        for(int i = 1; i < numArrows; i++) {

            a = new Arrow((_width/2) - (_sArrows.get_rect().getWidth()/2),
                    _arrows.getLast().getY() - _sArrows.get_rect().getHeight(), _sArrows);

            System.out.println(a.getY());

            _arrows.add(a);
        }

    }

    /**
     * Updates all positions and simulates the game. (Collisions, points added to the player, etc.)
     * @param t Current time.
     */
    @Override
    public void update(double t) {
        // Get the Input
        _gameState[_currentState].processInput(_game);

        updateArrows(t);

        // Update everything with the information of ProcessInput
        _gameState[_currentState].update(t);
    }

    void updateArrows (double t){

        if (_arrows.peek().getY() >= _canvas.getHeight()){
            // Reposition arrow
            Arrow a = _arrows.poll();
            a.setY(_arrows.getLast().getY() - _sArrows.get_rect().getHeight());
            // Add that element to the end of the queue
            _arrows.add(a);

        }

        for (Arrow a : _arrows){
            a.update(t);
        }
    }

    /**
     * Render every object of the game with the updated information from update().
     */
    @Override
    public void render(){

        _sbackground[_color].draw(_game.getGraphics(), backDest);

        for (Arrow a : _arrows){
            a.render(_game.getGraphics());
        }

        _gameState[_currentState].render(_game.getGraphics());
    }

    public void changeState(int i, int pts){
        _currentState = i;
        _gameState[_currentState].setPunctuation(pts);
    }

    int randomBackColor(){

        return rnd.nextInt(9);

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
    GameState[] _gameState;
    int _currentState;

    Sprite _sbackground[];
    Sprite _sArrows;

    // Pool
    ArrayDeque<Arrow> _arrows;

    //Rect of the background
    Rect backDest;

    private Random rnd;
    BackColor _currentColor;
    int _color;

}