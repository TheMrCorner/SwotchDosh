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

        initText();

    }

    /**
     * Initialize all font sprites. Create the Text array
     *
     */
    void initText(){

        // Create text array
        _text = new Text [6];

        int x = (int)_x;
        for (int i = 0; i < _text.length; i++) {
            _text[i] = new Text(x - (64 * i), (int)_y, _sprite);
        }

        _text[0].setCharacter('S');
        _text[1].setCharacter('T');
        _text[2].setCharacter('N');
        _text[3].setCharacter('I');
        _text[4].setCharacter('O');
        _text[5].setCharacter('P');

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

        for(int i = 0; i < _text.length; i++){
            _text[i].render(g);
        }

    }

    // Internal variables
    // Text array
    Text[] _text;
}
