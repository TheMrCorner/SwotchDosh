package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Sparkle { //TODO: comentar

    public Sparkle (int width, int height, Sprite s){
        _white = s;

        setSize(width, height);

        _size.setPosition(0, 0);

        _alpha = 1.0f;

        _active = false;
    }

    public void update(){
        if(_active) {
            _alpha -= 0.1f;

            if(_alpha <= 0.0f){
                setActive(false);
            }
        }
        else{
            _alpha = 1.0f;
        }
    }

    public void render(Graphics g){
        if(_active){
            _white.draw(g, _size, _alpha);
        }
    }

    public void setSize(int width, int height){
        _size = new Rect(width, 0, 0, height);
    }

    public void setActive(boolean b){
            _active = b;
    }

    // Internal values
    float _alpha;

    boolean _active;

    Sprite _white;
    Rect _size;
}
