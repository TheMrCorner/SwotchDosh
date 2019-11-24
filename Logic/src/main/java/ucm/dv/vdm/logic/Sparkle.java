package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

/**
 * Sparkle that happens when the GameState changes.
 */
public class Sparkle {

    /**
     * Sparkle constructor, Receives how much space it will use and the White Sprite.
     *
     * @param width Width of the Sparkle
     * @param height Height of the Sparkle
     * @param s White sprite.
     */
    public Sparkle (int width, int height, Sprite s){
        _white = s;

        setSize(width, height);

        _size.setPosition(0, 0);

        _alpha = 1.0f; // set alpha value to 1.0

        _active = false;
    } // Sparkle

    /**
     * Update is called once per frame while the Sparkle is active. Once it disappears, it's not
     * called again.
     */
    public void update(){
        if(_active) {
            _alpha -= 0.1f; // diminish alpha value periodically

            if(_alpha <= 0.0f){
                setActive(false);
            } // if
        } // if
        else{
            _alpha = 1.0f; // Reset Alpha value to use it again
        } // else
    } // update

    /**
     * Render the Sparkle with the new Alpha value.
     *
     * @param g Graphics' instance
     */
    public void render(Graphics g){
        if(_active){
            _white.draw(g, _size, _alpha);
        } // if
    } // render

    /**
     * Set size of the Sparkle depending on the Size of the Canvas.
     *
     * @param width New Width of the Sparkle
     * @param height New height of the Sparkle
     */
    public void setSize(int width, int height){
        _size = new Rect(width, 0, 0, height);
    } // setSize

    /**
     * Change active state. Receives the new active state.
     *
     * @param b boolean new Active State
     */
    public void setActive(boolean b){
            _active = b;
    } // setActive

    // Internal values
    float _alpha;

    boolean _active;

    Sprite _white;
    Rect _size;
}
