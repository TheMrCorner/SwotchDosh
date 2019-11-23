package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class TextContainer extends GameObject {
    /**
     * Constructor for TextContainer.
     *
     * @param x Position X in canvas
     * @param y Position Y in canvas
     * @param s Sprite array
     */
    public TextContainer(int x, int y, Sprite[] s) {
        super(x, y, s);
    }

    /**
     * Update is called once per frame.
     *
     * @param t Time elapsed since last frame
     */
    @Override
    public void update(double t){
    }

    /**
     * Render is called once per frame.
     *
     * @param g Graphics instance
     */
    @Override
    public void render(Graphics g){
    }

    // Internal variables
    // Text array
    Text[] _text;
}
