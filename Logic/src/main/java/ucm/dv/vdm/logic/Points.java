package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class Points extends TextContainer {
    /**
     * Initialize all Point sprites. Create the Text array and save an instance of GameState to get
     * the value of Player Points.
     *
     * @param x Position X
     * @param y Position Y
     * @param s Sprite array with the Font
     * @param g GameState instance to access points value
     */
    public Points(int x, int y, Sprite[] s, GameState g, int pts) {
        super(x, y, s); // Parent Constructor

        // Create text array
        _text = new Text [pts];

        // Save GameState instance
        _gs = g;

        // Init the points
        initPoints();
    }

    /**
     * Initialize the text array width the corresponding coordinates and calculate the space between
     * numbers.
     */
    public void initPoints(){
        int x = (int)_x;
        for (int i = 0; i < _text.length; i++) {
            _text[i] = new Text(x - (64 * i), (int)_y, _sprite);
            _text[i].setActive(false);
        }
    }

    /**
     * Update. Is called once per frame.
     *
     * @param t Time elapsed since last frame
     */
    @Override
    public void update(double t){
        // Give different values for each character
        int i = 0;
        int div = _gs.get_pts();

        // Set the number and it's position
        while(div > 0){
            _text[i].setNumber(div % 10);
            div = div / 10;
            _text[i].setActive(true);

            i++;
        }
    }

    /**
     * Render. Is called once per frame.
     *
     * @param g Graphics instance
     */
    @Override
    public void render(Graphics g){
        for(int i = 0; i < _text.length; i++){
            if(_text[i].getActive()) {
                _text[i].render(g);
            }
        }
    }

    // Internal values
    // Points for calculations
    int _points;

    // GameState instance
    GameState _gs;
}
