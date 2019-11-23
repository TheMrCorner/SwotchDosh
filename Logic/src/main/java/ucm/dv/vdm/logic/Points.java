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
     * @param canWidth Width of the canvas to position it correctly
     * @param g GameState instance to access points value
     */
    public Points(int x, int y, Sprite[] s, int canWidth, GameState g) {
        super(x, y, s); // Parent Constructor

        // Create text array
        _text = new Text [3];

        // Save GameState instance
        _gs = g;

        // Init the points
        initPoints(canWidth);
    }

    /**
     * Initialize the text array width the corresponding coordinates and calculate the space between
     * numbers.
     *
     * @param canWidth Canvas width reference
     */
    public void initPoints(int canWidth){
        for (int i = 0; i < _text.length; i++) {
            _text[i] = new Text(canWidth - (((int)_x + (_sprite[i].get_rect().getWidth() - 60))*i), (int)_y, _sprite);
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
            _text[i].render(g);
        }
    }

    // Internal values
    // Points for calculations
    int _points;

    // GameState instance
    GameState _gs;
}
