package ucm.dv.vdm.logic;

import java.util.List;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Input;
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

    public GameState(int s, Logic l){
        _l = l;

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
    public void initState(ResourceManager r){
        switch(_state) {
            case MainMenu:
                initMenu(r);
                break;
            case Pause:
                initPause(r);
                break;
            case GameOver:
                initOver(r);
                break;
            case GameRun:
                initGame(r);
                break;
        }

    }

    void initMenu (ResourceManager r){
        _go = new GameObject[4]; // Title, 2 buttons, Tap to Play

        _sprites = new Sprite[4][10];
    }

    void initPause (ResourceManager r){
        _go = new GameObject[4]; // How to Play, Instructions, Button, Tap to Play
    }

    void initOver (ResourceManager r){
        _go = new GameObject[4]; // 2 buttons, Game Over, Play Again?, Points, Punctuation

        _go[0] = new GameObject(_l.getCanvasSize().getWidth()/2, 364, Sprite.spriteMaker(r.getText("GameOver"), 1, 1));
        _go[1] = new GameObject(_l.getCanvasSize().getWidth()/2, 1396, Sprite.spriteMaker(r.getText("PlayAgain"), 1, 1));
        _go[2] = new GameObject(0, 30, Sprite.spriteMaker(r.getInterface("Buttons"), 10, 2));
        _go[3] = new GameObject(0, 0,  Sprite.spriteMaker(r.getText("Font"), 15, 7));
    }

    void initGame (ResourceManager r){
        _go = new GameObject[2]; // Player, BallPool (Object Pool)
        _pts = 0;

        _go[0] = new Player(_l.getCanvasSize().getWidth()/2, 1200, r.getGameObject("Player"));
        _go[0].setColor(GameObject.Color.BLACK);
        _go[1] = new BallPool(_l.getCanvasSize().getWidth()/2, 0, r.getGameObject("Balls"));
    }

    public void setPunctuation (int p){
        _pts = p;
    }

    /**
     * Gets the TouchEvent list from the Input created in the game and processes it.
     */
    public void processInput(Game g){
        List<Input.TouchEvent> e = g.getInput().getTouchEvent(); // Get the list of TouchEvents

        int ptr = e.size() - 1; // Pointer to roam the list

        while(!e.isEmpty() && ptr >= -1){ // While list is not empty

            Input.TouchEvent te = e.get(ptr); // Get the TouchEvent at the pointer position

            switch(te.getType()){ // Process the type of the TouchEvent
                case CLICKED:
                    if(_state == State.GameRun) {
                        if(_go[0].getColor() == GameObject.Color.BLACK) {
                            _go[0].setColor(GameObject.Color.WHITE);
                        }
                        else{
                            _go[0].setColor(GameObject.Color.BLACK);
                        }
                    }

                    if(_state == State.MainMenu){
                        _l.changeState(1, 0);
                    }

                    if(_state == State.Pause){
                        _l.changeState(3, 0);
                    }

                    if(_state == State.GameOver) {
                        _l.changeState(3, 0);
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

    public void update(double t) {
        for (int i = 0; i < _go.length; i++) {
            _go[i].update(t);
        }

        if(_state == State.GameRun){
            colisions();
        }
    }

    void colisions(){
        //int i = 1;
        boolean colision = false;

        Ball n = ((BallPool)_go[1]).colision();

        if(_go[0].getPosY() <= n.getPosY()){
            if(n.getColor() == _go[0].getColor()){
                _pts++;

                ((BallPool)_go[1]).destroy();
            }
            else{
                _l.changeState(2, _pts);
            }

            colision = true;
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < _go.length; i++){
            _go[i].render(g);
        }
    }

    //The state that is running in this moment
    State _state;

    Logic _l;

    int _pts;

    //GameObjects
    GameObject[] _go;
    Sprite[][] _sprites;

    Sprite _sballs[];
    Sprite _sbuttons[];
    Sprite _sFont[];
    Sprite _sPlayers[];

}
