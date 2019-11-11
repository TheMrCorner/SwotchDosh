package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Logic implements ucm.dv.vdm.engine.Logic{

    /**
     * Instance of Game. Used to render and create Images.
     */
    Game _game;

    /**
     * ResourceManager. Used to access it and load different Resources.
     */
    ResourceManager _rm;

    // Sprites
    Sprite _sbackground[];
    Sprite _sballs[];
    Sprite _sbuttons[];
    Sprite _sFont[];
    Sprite _sPlayers[];
    Sprite _sArrows;

    /**
     * Logic Constructor, creates a new instance of Logic.
     * @param g Game instance to store it and interact with the engine.
     */
    public Logic(Game g){
        _game = g; // Save the game instance for future use.
        init(); // Initialize everything
    }

    //---------------------------

    //-------Internal Logic------

    //---------------------------

    /**
     * Initialize all resources needed for the game and all gameobjects.
     */
    public void init(){
        try{ // Try to create the ResourceManager and load all resources.
            _rm = ResourceManager.getResourceMan(_game);
            createSprites();
        }
        catch (Exception e){ // Catch the exception if it fails.
            _game.HandleException(e);
        }

        // Create all gameObjects and CanvasObjects

        // First render
        initRenderPosition();
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
        _sballs = Sprite.spriteMaker(_rm.getGameObject("Balls"), 10, 2);

    }

    private void initRenderPosition(){

        Rect backDest = new Rect(_game.getWidth(), 0, 0, _game.getHeight());
        _sbackground[6].draw(_game.getGraphics(), backDest);

        int numFlechas = (_game.getHeight() / _sArrows.get_rect().getHeight()) +1;
        for(int i = 1; i <= numFlechas; i++) {
            Rect arrowDest = new Rect((_game.getWidth()/2) + (_sArrows.get_rect().getWidth()/2), (_game.getWidth()/2) - (_sArrows.get_rect().getWidth()/2),
                    0 + _sArrows.get_rect().getHeight()* (i -1), _sArrows.get_rect().getHeight() * i);
            _sArrows.draw(_game.getGraphics(), arrowDest);
        }
        //La bola está aquí de prueba pero lo normal es que se vaya generando una nueva en el render
        _sballs[0].draw(_game.getGraphics(), (_game.getWidth()/2) - (_sballs[0].get_rect().getWidth()/2), 200);

    }

    /**
     * Updates all positions and simulates the game. (Collisions, points added to the player, etc.)
     * @param t Current time.
     */
    @Override
    public void update(double t) {
        //Solo actualiza
    }

    /**
     * Render every object of the game with the updated information from update().
     */
    @Override
    public void render(){

    }
}