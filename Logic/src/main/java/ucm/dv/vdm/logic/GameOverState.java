package ucm.dv.vdm.logic;

import java.util.List;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Input;
import ucm.dv.vdm.engine.Sprite;

public class GameOverState extends GameState {
    /**
     * Constructor of GameState. Receives an instance of Logic to call it when needed. Receives
     * the player's score when needed (coming from GamRun to GameOver to show punctuation).
     *
     * @param l     Logic instance.
     * @param score Player's actual score.
     */
    public GameOverState(Logic l, int score) {
        super(l, score);
    } // GameOverState

    /**
     * Initializes the GameState with all the sprites needed, provided by the ResourceManager
     *
     * @param r ResourceManager instance.
     */
    @Override
    public void initState(ResourceManager r){ // This one needs GameOverText, SoundButton, HelpMenuButton, PlayAgainText, TextPoints, TextNumber
        // Init _go
        _go = new GameObject[6]; // Player, BallPool, Text for punctuation

        Sprite[] buttons = Sprite.spriteMaker(r.getInterface("Buttons"), 10, 1);

        // Init each GO
        _go[0] = new GameObject (((_l._canvas.getWidth()/2) - (r.getText("GameOver").getWidth()/2)), 364, Sprite.spriteMaker(r.getText("GameOver"), 1, 1)); // GameOver text
        _go[1] = new Blinker (((_l._canvas.getWidth()/2) - (r.getText("PlayAgain").getWidth()/2)), 1396, Sprite.spriteMaker(r.getText("PlayAgain"), 1, 1)); // PLayAgainText


        Sprite[] soundButtons = new Sprite[2];
        soundButtons[0] = buttons[2];
        soundButtons[1] = buttons[3];

        Sprite[] helpButton = new Sprite[1];
        helpButton[0] = buttons[0];

        _go[2] = new Button (Button.Position.LEFT, _l._canvas.getWidth(), soundButtons, 30, 90); // SoundButton
        _go[3] = new Button (Button.Position.RIGHT, _l._canvas.getWidth(), helpButton, 30, 90); // HelpMenuButton

        Sprite[] font = Sprite.spriteMaker(r.getText("Font"), 15, 7);

        int first = (_l.getCanvasSize().getWidth()/2);

        int i = 0;
        int div = _pts;

        // Set the number and it's position
        while(div > 0){
            div = div / 10;

            i++;
        } // while

        Points p = new Points (first, 780, font, this, 3);
        p.setActive();
        _go[4] = p;

        //Position of the text 'POINTS'. Set under number points
        int x = first + (font[0].get_rect().getWidth() - 32);
        int y = (int)_go[4].getPosY() + font[0].get_rect().getHeight();

        _go[5] = new TextContainer (x, y, font);

        // Initialize alpha value to 1
        _alpha = 1.0f;

        _inv = true;
    } // initState

    /**
     * Updates all GameObjects in this State with the time passed since the las update.
     *
     * @param t Time elapsed since the last frame.
     */
    @Override
    public void update (double t){
        // Update
        for(int i = 0; i < _go.length; i++){
            _go[i].update(t);
        } // for
    } // update

    /**
     * Renders all GameObjects in their specific locations. Receives an instance of Graphics
     * to call the drawing methods.
     *
     * @param g Instance of Graphics
     */
    @Override
    public void render(Graphics g){
        for(int i = 0; i < _go.length; i++){
            _go[i].render(g);
        } // for
    } // render

    /**
     * Process the Input received from the Logic. If mouse is clicked in a random place of the screen
     * change state to GameRun. if it is clicked over sound options button, mute sound. If it is
     * clicked over the help button, change to HelpMenuState.
     *
     * @param g Game instance to get the Input.
     */
    @Override
    public void processInput(Game g){
        List<Input.TouchEvent> e = g.getInput().getTouchEvent(); // Get the list of TouchEvents

        int ptr = e.size() - 1; // Pointer to roam the list

        while(!e.isEmpty() && ptr >= -1){ // While list is not empty

            Input.TouchEvent te = e.get(ptr); // Get the TouchEvent at the pointer position

            switch(te.getType()){ // Process the type of the TouchEvent
                case CLICKED:
                    if(((Button)_go[2]).isPressed(te.getX(), te.getY())){ // Sound Button
                        ((Button)_go[2]).changeButton();
                    } // if
                    else if(((Button)_go[3]).isPressed(te.getX(), te.getY())){ // Help Button
                        _l.changeState(new HelpMenuState(_l, 0), false);
                    } // else if
                    else{
                        _l.changeState(new GameRunState(_l, 0), true);
                    } // else
                    break;
                default:
                    //Anything else, do nothing.
                    break;
            } // switch

            e.remove(ptr); // Remove that TouchEvent from the list
            ptr--;
        } // while
    } // processInput

    // Alpha value for the PlayAgain text
    float _alpha;
    boolean _inv; // stores if the text is appearing or disappearing

} // GameOverState
