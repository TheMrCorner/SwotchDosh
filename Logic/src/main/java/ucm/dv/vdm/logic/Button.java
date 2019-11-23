package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class Button extends GameObject {

    /**
     * Position near the PlayRectangle.
     */
    public enum Position{
        RIGHT,
        LEFT
    }

    /**
     * Initializes GameObject. Initialize the buttons.
     *
     * @param p position in Canvas
     * @param canWidth Y position
     * @param s Sprite array with the different sprites of the Button.
     */
    public Button(Position p, int canWidth, Sprite[] s, int x, int y) {
        super(x, y, s);

        if(p == Position.RIGHT){ // Check button position
            setPosX(canWidth - (30 + _sprite[0].get_rect().getWidth()));
        }
    }

    /**
     * Render is called once per frame
     *
     * @param g Graphics Instance
     */
    @Override
    public void render(Graphics g) {
        // Render current sprite
        _sprite[_spr].draw(g, (int)_x, (int)_y);
    }

    /**
     * Method that changes the sprite that will be rendered. Adds 1 to the _spr variable, if it
     * is equal to the _sprite length, then change it to 0. Generic.
     */
    public void changeButton(){
        // Changes the sprite only if the Button has more than 1 different sprite.
        if(_sprite.length > 1){
            if(_spr == (_sprite.length - 1)){
                _spr = 0; // Reset button sprite
            }
            else {
                _spr++;
            }
        }
    }

    /**
     * Function that checks if a button is pressed. Returns true when that happens, false if not.
     *
     * @param x X position of the pointer
     * @param y Y position of the pointer
     * @return Returns true if button is pressed, false if not
     */
    public boolean isPressed(int x, int y){
        // If the cursor is inside the rectangle of the sprite.
        if( ((x >= _x) && (x < (_x +_sprite[_spr].get_rect().getWidth())))
        && ((y >= _y) && (y < (_y + _sprite[_spr].get_rect().getHeight())))) {
            return true;
        }
        else{ // If not, return Button not Pressed
            return false;
        }
    }

    // Atributes
    // Stores the value of the actual sprite (if we need to change it)
    int _spr;
}
