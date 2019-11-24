package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Blinker extends GameObject { //TODO: comentar
    /**
     * Initializes GameObject. (Create _sprite,
     *
     * @param x
     * @param y
     * @param s
     */
    public Blinker(int x, int y, Sprite[] s) {
        super(x, y, s);

        _alpha = 1.0f;

        _inv = true;

        _paRect = new Rect(x + _sprite[0].get_rect().getWidth(), x, y, y + _sprite[0].get_rect().getHeight());

        _paRect.setPosition(x, y);
    }

    @Override
    public void update(double t){
        // Change alpha value of PlayAgain
        if(_inv == true) {
            _alpha -= 0.001;
        }
        else{
            _alpha += 0.001;
        }

        if(_alpha == 1.0){
            _inv = true;
        }
        else if(_alpha <= 0.0){
            _inv = false;
        }
    }

    @Override
    public void render(Graphics g){
        for(int i = 0; i < _sprite.length; i++){
            _sprite[i].draw(g, _paRect, _alpha);
        }
    }


    // Rectangle to draw the TapToPlay text
    Rect _paRect;

    // Alpha value for the TapToPlay text
    float _alpha;
    boolean _inv; // stores if the text is appearing or disappearing
}
