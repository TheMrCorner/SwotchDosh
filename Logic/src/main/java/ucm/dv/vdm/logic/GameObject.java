package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

/**
 * GameObject class that stores all the information an object in the Game needs.
 */
public class GameObject {

    /**
     * Color of the GameObject (Not all of them have a color value.
     */
    public enum Color {
        WHITE,
        BLACK
    }

    /**
     * Initializes GameObject. (Create _sprite,
     */
    public GameObject(int x, int y, Sprite[] s){
        setPosX(x);
        setPosY(y);

        setSprite(s);

        _c = null;
    } // GAmeObject

    /**
     * Get position X of the GameObject.
     *
     * @return X coordinate of the GameObject
     */
    public double getPosX() { return _x; } // getPosX

    /**
     * Get position Y of the GameObject.
     *
     * @return Y coordinate of the GameObject
     */
    public double getPosY() {
        return _y;
    } // getPosY

    /**
     * Set the sprite of the GameObject.
     *
     * @param s Sprite[] to set the GameObject Sprite
     */
    public void setSprite(Sprite[] s) {_sprite = s;} // setSprite

    /**
     * Set the X coordinate of the GameObject
     *
     * @param x X coordinate
     */
    void setPosX (int x) {_x = x;} // setPosX

    /**
     * Set the Y coordinate of the GameObject
     *
     * @param y Y coordinate
     */
    void setPosY (int y) {_y = y;} // setPosY

    /**
     * Set the color of the GameObject. BLACK or WHITE
     *
     * @param c GameObject.Color
     */
    void setColor(Color c){
        _c = c;
    } // setColor

    /**
     * Get the actual color of the GameObject
     *
     * @return GameObject.Color actual color
     */
    public Color getColor(){
        return _c;
    } // getColor

    /**
     * Update. Called once per frame. Each GameObject has it's own update.
     *
     * @param t Time elapsed since last frame.
     */
    public void update(double t){} // update

    /**
     * Render. Generic render that renders the GameObject with the color it is set.
     *
     * @param g Graphics instance
     */
    public void render(Graphics g){
        for(int i = 0; i < _sprite.length; i++){
            _sprite[i].draw(g, (int)_x, (int)_y);
        } // for
    } // render

    //---------------------------------------------
    //----------------Atributes--------------------
    //---------------------------------------------
    Sprite[] _sprite;

    // Color of the sprite
    Color _c;

    // First point position
    double _x;
    double _y;

}
