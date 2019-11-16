package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Sprite;

public class Player extends GameObject{

    Player(int x, int y, Image image){
        super(x, y, Sprite.spriteMaker(image, 1, 2));

        setPosX(x - (_sprite[0].get_rect().getWidth()/2));
    }


    @Override
    public void update(double t) {

    }

    @Override
    public void render(Graphics g) {

    }
}
