package ucm.dv.vdm.logic;

import java.util.List;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Input;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class HelpMenuState extends GameState {
    /**
     * Constructor of GameState. Receives an instance of Logic to call it when needed. Receives
     * the player's score when needed (coming from GamRun to GameOver to show punctuation).
     *
     * @param l     Logic instance.
     * @param score Player's actual score.
     */
    public HelpMenuState(Logic l, int score) {
        super(l, score);
    }

    /**
     * Initializes the GameState with all the sprites needed, provided by the ResourceManager
     *
     * @param r ResourceManager instance.
     */
    @Override
    public void initState(ResourceManager r){
        // Init _go
        _go = new GameObject[4]; // HowToPlay, Instructions, TapToPlay, Button

        // Create the buttons sprite
        Sprite[] buttons = Sprite.spriteMaker(r.getInterface("Buttons"), 10, 1);

        // Init each GO
        _go[0] = new GameObject (((_l._canvas.getWidth()/2) - (r.getText("HowToPlay").getWidth()/2)), 290, Sprite.spriteMaker(r.getText("HowToPlay"), 1, 1)); // HowToPlay Text
        _go[1] = new GameObject (((_l._canvas.getWidth()/2) - (r.getText("Instructions").getWidth()/2)), 768, Sprite.spriteMaker(r.getText("Instructions"), 1, 1)); // Instructions Image
        _go[2] = new Blinker (((_l._canvas.getWidth()/2) - (r.getText("TapToPlay").getWidth()/2)), 1464, Sprite.spriteMaker(r.getText("TapToPlay"), 1, 1)); // TapToPlayText

        // Initialize all buttons
        Sprite[] crossButton = new Sprite[1];
        crossButton[0] = buttons[1];

        _go[3] = new Button (Button.Position.RIGHT, _l._canvas.getWidth(), crossButton, 30, 90); // CrossButton
    }

    /**
     * Updates all GameObjects in this State with the time passed since the las update.
     *
     * @param t Time elapsed since the last frame.
     */
    @Override
    public void update (double t){
        // Update
        for(int i = 0; i < _go.length; i++) {
            _go[i].update(t);
        }
    }

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
        }
    }

    /**
     * Process the Input received from the Logic. If mouse is clicked, change Player color.
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
                    if(((Button)_go[3]).isPressed(te.getX(), te.getY())){ // Help Button
                        _l.changeState(new MainMenuState(_l, _pts));
                    }
                    else{
                        _l.changeState(new GameRunState(_l, _pts));
                    }
                    break;
                default:
                    //Anything else, do nothing.
                    break;
            }

            e.remove(ptr); // Remove that TouchEvent from the list
            ptr--;
        }
    }



    // Rectangle to draw the TapToPlay text
    Rect _paRect;

    // Alpha value for the TapToPlay text
    float _alpha;
    boolean _inv; // stores if the text is appearing or disappearing

}
