package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Sprite;

/**
 * State of the game. Defines the actual state of the game. (Main Menú, Pause Menú, Game Over, Game Run)
 */
public class GameState {

    //The different states of the game
    public enum State {
        MainMenu, Pause, GameOver, GameRun
    }

    //GETTER PROVISIONAL PUES EL ENUMERADO NO ESTARÁ EN LAS DEMÁS CLASES
    public State getActualGameState() {
        return _state;
    }

    public GameState(int s){
        switch (s){
            case 0:
                _state = State.MainMenu;
                break;
            case 1:
                _state = State.Pause;
                break;
            case 2:
                _state = State.GameOver;
                break;
            case 3:
                _state = State.GameRun;
                break;
        }
    }

    //-------STATE INITS-------------------------------------------------

    //Background and arrows are always renderer

    //Calls the different initiation when the actual game state changes
    public void initState(){
        switch(_state) {

            case MainMenu:
                initMenu();
                break;
            case Pause:
                initPause();
                break;
            case GameOver:
                initOver();
                break;
            case GameRun:
                initGame();
                break;
        }

    }

    void initMenu (){

    }

    void initPause (){

    }

    void initOver (){

    }

    void initGame (){

    }


    public void update(){
        for(int i = 0; i < _go.length; i++){
            _go[i].update();
        }
    }

    //The state that is running in this moment
    State _state;

    //GameObjects
    GameObject[] _go;

}
