package ucm.dv.vdm.pclauncher;

import ucm.dv.vdm.pcengine.Game;
import ucm.dv.vdm.logic.Logic;

/**
 * Main class for Java. Initialize the desktop app and set it work correctly.
 */
public class Main {

    /**
     * Main method called when aplication starts. Creates Game and Logic and puts them to work
     * together correctly.
     *
     * @param args Arguments
     */
    public static void main (String[] args){
        // Create Game and Logic
        Game _game = new Game();
        Logic _logic = new Logic(_game);

        // Make them work together
        _game.setLogic(_logic);

        // Run game
        _game.run();

    } // main()

} // Main class
