package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Sprite;

/**
 * State of the game. Defines the actual state of the game. (Main Menú, Pause Menú, Game Over, Game Run)
 */
public class GameState {

    //The different states of the game
    enum State {
        MainMenu, Pause, GameOver, GameRun
    }

    //The state that is running in this moment
    State _actualState;

    //GameObjects
    Ball _goball;
    Player _goplayer;

    //GETTER PROVISIONAL PUES EL ENUMERADO NO ESTARÁ EN LAS DEMÁS CLASES
    public State getActualGameState(){ return _actualState;}

    /**
     * Calls the construction of all the gameObjects.
     */
    public void createGameObjects(){

        _goball = new Ball ();
        _goplayer = new Player ();

    }

    //-------STATE INITS-------------------------------------------------

    //Background and arrows are always renderer

    //Calls the different initiation when the actual game state changes
    public void initState(){
        switch(_actualState) {

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

    //-----------TICKS---------------------

    //Switch between the different tick of each game state
    public void tick(double elapsedtime){
        switch(_actualState) {

            case MainMenu:
                tickMenu(elapsedtime);
                break;
            case Pause:
                tickPause(elapsedtime);
                break;
            case GameOver:
                tickOver(elapsedtime);
                break;
            case GameRun:
                tickGame(elapsedtime);
                break;
        }
    }

    //CADA UNO DE ESTOS TICKS LLAMARÁ AL UPDATE DE CADA UNO DE LOS OBJETOS Y A SU RENDER
    void tickMenu(double elapsedtime){

    }

    void tickPause(double elapsedtime){

    }

    void tickOver(double elapsedtime){

    }

    void tickGame(double elapsedtime){

    }



}
