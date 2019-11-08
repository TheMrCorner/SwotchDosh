package ucm.dv.vdm.pclauncher;

import ucm.dv.vdm.pcengine.Game;
import ucm.dv.vdm.logic.Logic;

public class Main {

    public static void main (String[] args){

        Game _game = new Game();
        Logic _logic = new Logic(_game);
        _game.setLogic(_logic);
        _game.run();

    }

}
