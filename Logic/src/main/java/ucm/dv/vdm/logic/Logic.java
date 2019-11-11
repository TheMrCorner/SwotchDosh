package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Logic implements ucm.dv.vdm.engine.Logic{

    Game _game;
    ResourceManager _rm;

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
            _rm = ResourceManager.getResourceMan(_game);
            loadResources();
        }
        catch (Exception e){
            _game.HandleException(e);
        }

        initRenderPosition();
    }

    void loadResources(){


        // Load sprites
        _sbackground = Sprite.spriteMaker(_rm.getInterface("Background"), 9, 1);
        _sArrows =  Sprite.spriteMaker(_rm.getInterface("Arrows"), 1, 5)[0];
        _sballs = Sprite.spriteMaker(_rm.getGameObject("Balls"), 10, 2);

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