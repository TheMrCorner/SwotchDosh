package ucm.dv.vdm.swotchdosh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ucm.dv.vdm.androidengine.Game;
import ucm.dv.vdm.logic.Logic;

/**
 * Main Activity of the App SwitchDash. Manages all app life loop.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Method called when app is opened for the first time. Initialize everything needed for the
     * app to work. Creates a Game, a Logic and puts them together to work correctly. Then it
     * only manages if the app is paused or not.
     *
     * @param savedInstanceState Instance Saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _game = new Game(this, this);

        Logic _logic = new Logic(_game);

        // Set Logic in Game
        _game.setLogic(_logic);
    } // onCreate

    /**
     * Method called when the app is being used again (or the first time it is opened). Calls for
     * Game's onResume().
     */
    @Override
    protected void onResume(){
        super.onResume();
        _game.onResume();
    } // onResume

    /**
     * Method called when app loses focus. Pauses everything. Calls for Game's onPause()
     */
    @Override
    protected void  onPause(){
        super.onPause();
        _game.onPause();
    } // onPause

    // Internal variables
    Game _game;

}
