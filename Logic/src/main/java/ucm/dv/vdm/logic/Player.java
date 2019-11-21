package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Sprite;

public class Player extends GameObject{ // TODO: Comentar toda esta wea

    Player(int x, int y, Image image){
        super(x, y, Sprite.spriteMaker(image, 1, 2));

        setPosX(x - (_sprite[0].get_rect().getWidth()/2));
    }

    @Override
    public void render(Graphics g) {
        if(_c == Color.BLACK){
            _sprite[1].draw(g, (int)_x, (int)_y);
        }
        else{
            _sprite[0].draw(g, (int)_x, (int)_y);
        }
    }
}
