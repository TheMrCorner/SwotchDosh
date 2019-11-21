package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class TextContainer extends GameObject {
    /**
     * Initializes GameObject. (Create _sprite,
     *
     * @param x
     * @param y
     * @param s
     */
    public TextContainer(int x, int y, Sprite[] s) {
        super(x, y, s);
    }

    @Override
    public void update(double t){

    }

    @Override
    public void render(Graphics g){
        for(int i = 0; i < _sprite.length; i++){
            _sprite[i].draw(g, (int)_x, (int)_y);
        }
    }


    Text[] _text;
}
