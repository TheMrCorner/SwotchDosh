package ucm.dv.vdm.swotchdosh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ucm.dv.vdm.androidengine.Game;
import ucm.dv.vdm.logic.Logic;

public class MainActivity extends AppCompatActivity { // TODO: Comentar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _game = new Game(this);

        _logic = new Logic(_game);

        _game.setLogic(_logic);

        _game.run();
    }

    @Override
    protected void onResume(){
        super.onResume();
        _game.onResume();
    }

    @Override
    protected void  onPause(){
        super.onPause();
        _game.onPause();
    }

    // Internal variables
    Game _game;

    Logic _logic;

}
