package ucm.dv.vdm.androidengine;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.io.InputStream;

import ucm.dv.vdm.engine.AbstractGraphics;
import ucm.dv.vdm.engine.Rect;

/**
 * Graphics class that implements the interface from the engine and uses android API to draw images
 * in the phone.
 */
public class  Graphics extends AbstractGraphics { // TODO: Comentar

    /**
     * Constructor. Receives and saves an instance of the SurfaceView to paint there later. Also
     * receives and saves an instance of the AssetManager to load new Images and  create them .
     * Initializes Paint to use it to show images on the screen.
     *
     * @param sv SurfaceView instance
     * @param am AssetManager instance
     */
    public Graphics (SurfaceView sv, AssetManager am){
        _surfaceView = sv;
        _assetManager = am;
        _paint = new Paint();
    }

    /**
     * Initialize the frame. Receives a new canvas and sets it as the canvas it will use to Paint
     * later. Called once per frame.
     *
     * @param c New canvas.
     */
    public void startFrame(Canvas c){
        _canvas = c; // Set internal canvas
    }

    /**
     * Create a new Image using the AssetManager to load it from a file and creating it's bitmap.
     *
     * @param name Name of the image to load.
     * @return New created image.
     */
    @Override
    public Image newImage(String name) {

        Image _image = null; // Initialize Image
        InputStream _ip = null; // Initialize InputStream to null for checking errors

        try{
            _ip = _assetManager.open(name);
            Bitmap bm = BitmapFactory.decodeStream(_ip);
            _image = new Image(bm);
        } // try
        catch (Exception e){
            System.err.println(e);
        } // catch
        finally{
            if(_ip != null){
                try{
                    _ip.close(); // it can give an error when closing
                } // try
                catch(Exception e){
                    System.err.println(e);
                } // catch
            } // if
        } // finally

        return _image;
    } // newImage

    /**
     * Clears the screen with a color given as a parameter. Draws the hole screen with that color.
     *
     * @param color Color to paint
     */
    @Override
    public void clear(int color) {
        // Set _paint color with received color
        _paint.setColor(color);

        _canvas.drawRect((float)_can.getX(), (float)_can.getY(),
                (float)(_can.getX() + _can.getWidth()), (float)(_can.getY() + _can.getHeight()),
                _paint);

        // Reset Paint to let it draw everything else correctly
        _paint.reset();
    } // clear

    /**
     * Draws an Image in a specific location without rescaling it or
     *
     * @param image Image to be drawn
     * @param x Position X
     * @param y Position Y
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, int x, int y) {
        if(image != null){ // Check if image is null
            Image im = (Image) image;
            Bitmap bm = im.getBitmap();
            _canvas.drawBitmap(bm, x, y, _paint);
        } // if
    } // drawImage

    /**
     * Draws an image in a specific location with a specific size.
     *
     * @param image Image to be drawn
     * @param source Size of the image
     * @param x Position X
     * @param y Position Y
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, int x, int y) {
        try{
            // Create a destination rectangle to place the image
            // Scale the source image
            Rect dst = new Rect(repositionX(source.getWidth()), 0,
                    0, repositionY(source.getHeight()));

            // Reposition coordinates to fit the physical canvas
            x = _can.getX() + repositionX(x);
            y = _can.getY() + repositionY(y);

            dst.setPosition(x, y);

            // Create Android rectangles to position the image
            android.graphics.Rect dest = new android.graphics.Rect(dst.getX(), dst.getY(),
                    dst.getX() + dst.getWidth(), dst.getY() + dst.getHeight());
            android.graphics.Rect src = new android.graphics.Rect(source.getLeft(), source.getTop(),
                    source.getRight(), source.getBottom());

            Image im = (Image) image;
            Bitmap bm = im.getBitmap();

            if(bm != null) { // check if bitmap is null (defensive)
                _canvas.drawBitmap(bm, src, dest, _paint);
            } // if
        } // try
        catch(Exception e){
            System.err.println(e);
        } // catch
    } // drawImage

    /**
     * Draws an image in a specific rectangle of the screen resizing it.
     *
     * @param image Image to be drawn
     * @param source Image size
     * @param dest Destination size
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest) {
        try {
            // Scale image to fit the aspect ratio
            dest = dimensions(dest, _can);

            // Reposition coordinates
            int x = repositionX(dest.getX());
            int y = repositionY(dest.getY());

            dest.setPosition(x + _can.getX(), y + _can.getY());

            // Create Android rectangles to draw the image with the values of the original ones
            android.graphics.Rect dst = new android.graphics.Rect(x, y, x + dest.getWidth(),
                    y + dest.getHeight());
            android.graphics.Rect src = new android.graphics.Rect(source.getLeft(), source.getTop(),
                    source.getRight(), source.getBottom());

            Image im = (Image) image;
            Bitmap bm = im.getBitmap();

            if(bm != null) { // Check if bitmap is null
                _canvas.drawBitmap(bm, src, dst, _paint);
            } // if
        } // try
        catch(Exception e){
            System.err.println(e);
        } // catch
    } // drawImage

    /**
     * Draws an image in a specific rectangle scaling it to fit the aspect ratio and changing it's
     * alpha value (transparency).
     *
     * @param image Image to be drawn
     * @param source Source Rectangle of the image
     * @param dest Destination rectangle to place the image
     * @param alpha Alpha value to set the image (Between 0.0 and 1.0)
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest, float alpha) {
        try {
            // Create a temporal rectangle to scale the destination one correctly
            Rect temp =  new Rect (repositionX(dest.getWidth()), 0,
                    0, repositionY(dest.getHeight()));

            // Reposition coordinates to fit the screen
            int x = repositionX(dest.getX());
            int y = repositionY(dest.getY());

            temp.setPosition(x + _can.getX(), y + _can.getY());

            // Create android rectangles in order to draw the images correctly
            android.graphics.Rect dst = new android.graphics.Rect(temp.getX(), temp.getY(),
                    temp.getX() + temp.getWidth(),temp.getY() + temp.getHeight());
            android.graphics.Rect src = new android.graphics.Rect(source.getLeft(), source.getTop(),
                    source.getRight(), source.getBottom());

            Image im = (Image) image;
            Bitmap bm = im.getBitmap();

            // Set the alpha value of Paint
            if(alpha > 1.0){ // Defensive, someone can use a different value for alpha
                _paint.setAlpha((int) alpha); // Somebody gave us an alpha value between 1 and 100
            }
            else { // If someone uses the correct value for alpha, do everything normally
                _paint.setAlpha((int) (alpha * 100)); // Multiply it by 100 because android needs it
            }

            if(bm != null) { // Check if Bitmap is null
                _canvas.drawBitmap(bm, src, dst, _paint);
            } // if
        } // try
        catch(Exception e){
            System.err.println(e);
        } // catch

        _paint.reset(); // Reset Paint to reset alpha value
    } // drawImage

    /**
     * Return width of the SurfaceView for calculations.
     *
     * @return _surfaceView Width.
     */
    @Override
    public int getWidth() {
        return _surfaceView.getWidth();
    }

    /**
     * Return Height of the SurfaceView for calculations.
     *
     * @return _surfaceView Height.
     */
    @Override
    public int getHeight() {
        return _surfaceView.getHeight();
    }

    // Private Atributes
    private SurfaceView _surfaceView;
    private AssetManager _assetManager;
    private Canvas _canvas;
    private Paint _paint;

} // Graphics Android
