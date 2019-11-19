package ucm.dv.vdm.logic;

import java.util.List;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Input;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Logic implements ucm.dv.vdm.engine.Logic{

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

    /**
     * Logic Constructor, creates a new instance of Logic.
     * @param g Game instance to store it and interact with the engine.
     */
    public Logic(Game g){
        _game = g; // Save the game instance for future use.
        _gameState = new GameState[4]; //Save de gameState instance for future use.
        init(); // Initialize everything
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

        //DEBERIAMOS PASAR TODOS ESTOS SPRITES A GAMESTATE O RESOURCE MANAGER PARA QUE VAYA ASIGNANDO EL NECESARIO EN LOS UPDATES (YA QUE CAMBIAN DE COLOR)


    }

    @Override
    public Rect getCanvasSize() {
        return _canvas;
    }

    @Override
    public void initLogic() {
        // Create all gameObjects and CanvasObjects
        for(int i = 0; i < _gameState.length; i++){
            _gameState[i] = new GameState(i, this);
            _gameState[i].initState(_rm);
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

        // Update everything with the information of ProcessInput
        _gameState[_currentState].update(t);
    }

    /**
     * Render every object of the game with the updated information from update().
     */
    @Override
    public void render(){
        //TODA INICIALIZACION DEBEMOS PASARLA A SU INIT DE GAMESTATE
        Rect backDest = new Rect(_width, 0, 0, _height);
        _sbackground[6].draw(_game.getGraphics(), backDest);

        int numFlechas = (_height / _sArrows.get_rect().getHeight()) +1;

        for(int i = 0; i < numFlechas; i++) {
            Rect arrowDest = new Rect(_sArrows.get_rect().getWidth(),
                    0,0, _sArrows.get_rect().getHeight());


            arrowDest.setPosition((_width/2) - (_sArrows.get_rect().getWidth()/2), (_sArrows.get_rect().getHeight() - i) * i);

            _sArrows.draw(_game.getGraphics(), arrowDest, 0.6f);
        }

        _gameState[_currentState].render(_game.getGraphics());
    }

    public void changeState(int i, int pts){
        _currentState = i;
        _gameState[_currentState].setPunctuation(pts);
    }
}