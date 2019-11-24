package ucm.dv.vdm.engine;

/**
 * Sprite class to divide an image an use it in the game.
 */
public class Sprite {

    /**
     * Constructor. Receives an Image and the rectangle the sprite needs.
     *
     * @param i Image that sprite will use.
     * @param r Rectangle the sprite needs.
     */
    public Sprite(Image i, Rect r){
        _image = i;
        _rect = r;
    } // Sprite

    /**
     * Draw the Sprite in a specific location of the screen and canvas.
     *
     * @param g Graphics instance
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public void draw(Graphics g, int x, int y){
        g.drawImage(_image, _rect, x, y);
    } // draw

    /**
     * Draw the Sprite in the destination rectangle.
     *
     * @param g Graphics instance
     * @param dest Destination Rectangle
     */
    public void draw(Graphics g, Rect dest){
        g.drawImage(_image, _rect, dest);
    } // draw

    /**
     * Draw the sprite in a specific Rectangle with an specific alpha value.
     *
     * @param g Graphics instance
     * @param dest Destination rectangle
     * @param alpha alpha value
     */
    public void draw(Graphics g, Rect dest, float alpha){
        g.drawImage(_image, _rect, dest, alpha);
    } // draw


    /**
     * Create the sprites from an Image. Receives an image and makes the Sprite array of it with the
     * dimensions given.
     *
     * @param sImage Image to subdivide in Sprites
     * @param width How many sprites are widely
     * @param height How many lines of sprites there are
     * @return A Sprite Array with all sprites
     */
    public static Sprite[] spriteMaker (Image sImage, int width, int height){ // Width es el nº de sprites que hay a lo ancho y height a lo alto

        // Sprite Array
        Sprite[] sprites = new Sprite[width * height]; // The number of sprites is fixed and determined by the sprite grid

        // Image atributes
        int w = sImage.getWidth(); // Pixel width of the image
        int h = sImage.getHeight(); // Pixel height of the image

        // Calculate the sprites pixels
        int sw = w/width;
        int sh = h/height;

        // Temporary atributes for the sprite
        int right = 0;
        int left = 0;
        int top = 0;
        int bottom = 0;

        int i = 0;
        int k = 0;

        // Create sprites and put them in the Array
        for(int j = 0; j < width*height; j++){

            left = k * sw;
            right = (k+1) *sw;
            top = i * sh;
            bottom = (i+1) * sh;

            Rect r = new Rect (right, left, top, bottom);
            sprites[j] = new Sprite(sImage, r);

            if(k < width) {
                k++;
            } // if

            if(height != 1 && k == width){
                i++;
                k = 0;
            } // if
        } // for

        return sprites; // Returns all sprites separated in different images

    } // spriteMaker

    /**
     * Return the Rectangle of this Sprite.
     *
     * @return Rectangle of Sprite
     */
    public Rect get_rect() {
        return _rect;
    } // get_rect

    // Private Atributes

    private Image _image;

    private Rect _rect;

}
