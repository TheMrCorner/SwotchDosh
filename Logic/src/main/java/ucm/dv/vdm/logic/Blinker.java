package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

/**
 * Blinker GameObject. Changes it's own alpha value periodically.
 */
public class Blinker extends GameObject {
    /**
     * Create the Blinker GameObject. Receives it's position and the sprite it will use to draw.
     *
     * @param x int X coordinate of the Blinker
     * @param y int Y coordinate of the Blinker
     * @param s Sprite[] it will use to draw
     */
    public Blinker(int x, int y, Sprite[] s) {
        super(x, y, s);

        _alpha = 1.0f; // Set initial alpha value

        _inv = true; // _inv indicates if the image is appearing or disappearing.

        _paRect = new Rect(x + _sprite[0].get_rect().getWidth(), x, y, y + _sprite[0].get_rect().getHeight());

        _paRect.setPosition(x, y);
    } // Blinker

    /**
     * Update the alpha value every frame.
     *
     * @param t double Time elapsed since last frame
     */
    @Override
    public void update(double t){
        // Change alpha value
        if(_inv == true) {
            _alpha -= 0.001;
        } // if
        else{
            _alpha += 0.001;
        } // else

        if(_alpha == 1.0){
            _inv = true;
        } // if
        else if(_alpha <= 0.0){
            _inv = false;
        } // else if
    } // update

    /**
     * Render method that draws the Blinker with it's alpha value.
     *
     * @param g Graphics instance
     */
    @Override
    public void render(Graphics g){
        for(int i = 0; i < _sprite.length; i++){
            _sprite[i].draw(g, _paRect, _alpha);
        } // for
    } // render


    // Rectangle to draw the TapToPlay text
    Rect _paRect;

    // Alpha value for the TapToPlay text
    float _alpha;
    boolean _inv; // stores if the text is appearing or disappearing
}
