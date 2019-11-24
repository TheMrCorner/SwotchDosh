package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Graphics;


/**
 * Abstract class for GameState. Defines the constructor, abstract methods and common atributes.
 */
public class GameState{

    /**
     * Constructor of GameState. Receives an instance of Logic to call it when needed. Receives
     * the player's score when needed (coming from GamRun to GameOver to show punctuation).
     * @param l Logic instance.
     * @param score Player's actual score.
     */
    public GameState (Logic l, int score){
        _l = l;

        _pts = score;
    } // GameState

    /**
     * Initializes the GameState with all the sprites needed, provided by the ResourceManager
     * @param r ResourceManager instance.
     */
    public void initState(ResourceManager r){} // initState

    /**
     * Updates all GameObjects in this State with the time passed since the las update.
     * @param t Time elapsed since the last frame.
     */
    public void update(double t){} // update

    /**
     * Renders all GameObjects in their specific locations. Receives an instance of Graphics
     * to call the drawing methods.
     * @param g Instance of Graphics
     */
    public void render(Graphics g){} // render

    /**
     * Method that processes the Input received from the Logic.
     * @param g Game instance to get the Input.
     */
    public void processInput (Game g){} // processInput

    /**
     * Function to get the points value.
     */
    public int get_pts(){
        return _pts;
    } // get_pts

    // Saves an instance of Logic in case it is necessary
    Logic _l;

    // Score points
    int _pts;

    // GameObjects
    // Array that contains all GameObjects in this State
    GameObject[] _go;
}