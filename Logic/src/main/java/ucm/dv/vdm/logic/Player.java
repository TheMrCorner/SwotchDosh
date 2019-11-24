package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Sprite;

public class Player extends GameObject{

    /**
     * Player constructor. Receives the coordinates and the Image it will use to paint it self.
     *
     * @param x X coordinate of the Player
     * @param y Y coordinate of the Player
     * @param image Image image it will use to paint
     */
    Player(int x, int y, Image image){
        super(x, y, Sprite.spriteMaker(image, 1, 2));

        // Set initial position
        setPosX(x - (_sprite[0].get_rect().getWidth()/2));
    } // Player

    /**
     * Render the GameObject with the corresponding Color.
     *
     * @param g Graphics instance
     */
    @Override
    public void render(Graphics g) {
        if(_c == Color.BLACK){
            _sprite[1].draw(g, (int)_x, (int)_y);
        } // if
        else{
            _sprite[0].draw(g, (int)_x, (int)_y);
        } // else
    } // render
} // Player class
