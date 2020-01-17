package ucm.dv.vdm.logic;

import java.util.Random;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

/**
 * Logic of the whole Game.
 */
public class Logic implements ucm.dv.vdm.engine.Logic{

    /**
     * Logic Constructor, creates a new instance of Logic.
     *
     * @param g Game instance to store it and interact with the engine.
     */
    public Logic(Game g){
        _game = g; // Save the game instance for future use.
        init(); // Initialize everything

        rnd = new Random();
    } // Logic

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
        } // try
        catch (Exception e){ // Catch the exception if it fails.
            _game.HandleException(e);
        } // catch

        // Create canvas.
        _canvas = new Rect (1080, 0, 0, 1920);

        _width = _canvas.getWidth();
        _height = _canvas.getHeight();
    } // init

    /**
     * Calls the spriteMaker function of the different sprites to create them. Then save that info
     * in arrays of Sprites. Only for the sprites needed for the Background to work.
     */
    void createSprites(){
        // Load sprites
        // This arrays will save them. After that, the sprites will be assigned to a GO or a CO
        _sbackground = Sprite.spriteMaker(_rm.getInterface("Background"), 9, 1);
    } // createSprites

    /**
     * Get the size of the canvas of the Logic.
     *
     * @return Rect _canvas here stored
     */
    @Override
    public Rect getCanvasSize() {
        return _canvas;
    } // getCanvasSize

    /**
     * Initialize everything needed for the Logic to work correctly. Create the Sprites for the
     * arrows, set the playable part of the canvas, create the Arrows, create the Sparkle and the
     * first GameState with the Menu. Initialize that state.
     */
    @Override
    public void initLogic() {
        Sprite _sArrows =  Sprite.spriteMaker(_rm.getInterface("Arrows"), 1, 1)[0];

        // Set the atributes for the arrows.
        _backDest = new Rect(_sArrows.get_rect().getWidth(), 0, 0, _height);
        _backDest.setPosition((_width/2) - (_sArrows.get_rect().getWidth()/2), 0);

        // Create the Background arrows
        _arrow = new Arrow((_width/2) - (_sArrows.get_rect().getWidth()/2),
                0 - (_sArrows.get_rect().getHeight()/5), _sArrows);

        // Create the Sparkle that will be used whenever a GameState changes
        _spr = new Sparkle(_width, _height,
                Sprite.spriteMaker(_rm.getInterface("White"), 1, 1)[0]);

        // Create the MainMenuState
        _currentGameState = new MainMenuState(this, 0);

        // Initialize the GameState
        _currentGameState.initState(_rm);

        // Random color for the background
        _currentColor = randomBackColor();
    } // initLogic

    /**
     * Updates all positions and simulates the game. (Collisions, points added to the player, etc.)
     * Called once per frame.
     *
     * @param t Current time.
     */
    @Override
    public void update(double t) {
        // Call to process the input
        _currentGameState.processInput(_game);

        // Update Sparkle
        _spr.update();

        // Update everything with the information of ProcessInput
        _currentGameState.update(t);
        _arrow.update(t);
    } // update

    /**
     * Render every object of the game with the updated information from update().
     */
    @Override
    public void render(){
        // Clear the background
        clearBackground();

        // Draw the Background color
        _sbackground[_currentColor].draw(_game.getGraphics(), _backDest);

        // Render the arrows
        _arrow.render(_game.getGraphics());

        // Call for current GameState to render
        _currentGameState.render(_game.getGraphics());

        // render the sparkle (only if active)
        _spr.render(_game.getGraphics());
    } // render

    /**
     * Changes current state to the new state received as a parameter.
     *
     * @param gs GameState
     */
    public void changeState(GameState gs){
        _arrow.resetVel();
        _currentGameState = gs;
        gs.initState(_rm);
        _spr.setActive(true);
    } // changeState

    /**
     * Generates a random iterator to the background color
     */
    int randomBackColor(){

        return rnd.nextInt(9);

    } // randomBackColor

    /**
     * Change the iterator of the background color
     */
    public void changeBackgroundColor(){
        _currentColor = randomBackColor();
    } // changeBackgroundColor

    /**
     * Clears the whole canvas to draw over it.
     */
    void clearBackground(){

        _game.getGraphics().clear(_planeColor[_currentColor]);

    } // clearBackground

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

} // Logic