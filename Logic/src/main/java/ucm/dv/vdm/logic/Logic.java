package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Logic implements ucm.dv.vdm.engine.Logic{

    Game _game;

    //IMAGES -------------------
    //Interface
    private Image _background;
    private Image _arrow;
    private Image _button;
    private Image _white;

    //GameObjects
    private Image _ball;
    private Image _player;

    //Text
    private Image _gameOver;
    private Image _howToPlay;
    private Image _instructions;
    private Image _playAgain;
    private Image _logo;
    private Image _tapToPlay;
    private Image _scoreFont;

    // Sprites
    Sprite _sbackground[];
    Sprite _sballs[];
    Sprite _sbuttons[];
    Sprite _sFont[];
    Sprite _sPlayers[];
    Sprite _sArrows;


    //---------------------------

    //-------Internal Logic------


    //---------------------------

    public Logic(Game g){
        _game = g;
        init();
    }


    public void init(){
        try{
            loadResources();
        }
        catch (Exception e){
            _game.HandleException(e);
        }

        initRenderPosition();
    }

    void loadResources(){

        //Interfaces
        _background = _game.getGraphics().newImage("./Sprites/backgrounds.png");
        _arrow = _game.getGraphics().newImage("./Sprites/arrowsBackground.png");
        _button = _game.getGraphics().newImage("./Sprites/buttons.png");
        _white = _game.getGraphics().newImage("./Sprites/white.png");

        //GameObjects
        _ball = _game.getGraphics().newImage("./Sprites/balls.png");
        _player = _game.getGraphics().newImage("./Sprites/players.png");

        //Text
        _gameOver = _game.getGraphics().newImage("./Sprites/gameOver.png");
        _howToPlay = _game.getGraphics().newImage("./Sprites/howToPlay.png");
        _instructions = _game.getGraphics().newImage("./Sprites/instructions.png");
        _playAgain = _game.getGraphics().newImage("./Sprites/playAgain.png");
        _logo = _game.getGraphics().newImage("./Sprites/switchDashLogo.png");
        _tapToPlay = _game.getGraphics().newImage("./Sprites/tapToPlay.png");
        _scoreFont = _game.getGraphics().newImage("./Sprites/scoreFont.png");

        // Load sprites
        _sbackground = Sprite.spriteMaker(_background, 9, 1);
        _sArrows =  Sprite.spriteMaker(_arrow, 1, 5)[0];
        _sballs = Sprite.spriteMaker(_ball, 10, 2);

    }

    private void initRenderPosition(){

        Rect backDest = new Rect(_game.getWidth(), 0, 0, _game.getHeight());
        _sbackground[6].draw(_game.getGraphics(), backDest);

        int numFlechas = (_game.getHeight() / _sArrows.get_rect().getHeight()) +1;
        for(int i = 1; i <= numFlechas; i++) {
            Rect arrowDest = new Rect((_game.getWidth()/2) + (_sArrows.get_rect().getWidth()/2), (_game.getWidth()/2) - (_sArrows.get_rect().getWidth()/2),
                    0 + _sArrows.get_rect().getHeight()* (i -1), _sArrows.get_rect().getHeight() * i);
            _sArrows.draw(_game.getGraphics(), arrowDest);
        }
        //La bola está aquí de prueba pero lo normal es que se vaya generando una nueva en el render
        _sballs[0].draw(_game.getGraphics(), (_game.getWidth()/2) - (_sballs[0].get_rect().getWidth()/2), 200);

    }

    @Override
    public void update(double t) {
        //Solo actualiza
    }

    @Override
    public void render(){

    }
}