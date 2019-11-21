package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class Points extends TextContainer {
    /**
     * Initializes GameObject. (Create _sprite,
     *
     * @param x
     * @param y
     * @param s
     */
    public Points(int x, int y, Sprite[] s, int canWidth, GameState g) {
        super(x, y, s);

        _text = new Text [3];

        _gs = g;

        initPoints(canWidth);
    }

    public void initPoints(int canWidth){
        for (int i = 0; i < _text.length; i++) {
            _text[i] = new Text(canWidth - (((int)_x + (_sprite[i].get_rect().getWidth() - 60))*i), (int)_y, _sprite);
        }
    }


    @Override
    public void update(double t){ //TODO: Mirar cómo recolocarlo para que no se solape con el juego!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int i = 0;
        int div = _gs.get_pts();

        while(div > 0){
            _text[i].setNumber(div % 10);
            div = div / 10;

            i++;
        }
    }

    @Override
    public void render(Graphics g){
        for(int i = 0; i < _text.length; i++){
            _text[i].render(g);
        }
    }

    int _points;

    GameState _gs;
}
