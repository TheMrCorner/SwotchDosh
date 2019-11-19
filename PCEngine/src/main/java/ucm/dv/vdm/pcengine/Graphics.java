package ucm.dv.vdm.pcengine;

import java.awt.*;
import java.awt.image.BufferStrategy;

import ucm.dv.vdm.engine.Rect;

public class Graphics implements ucm.dv.vdm.engine.Graphics {

    public void testCanvas(Window w){ // TODO: Lo dejamos(?)
        _win.getJGraphics().setColor(Color.pink);
        _win.getJGraphics().fillRect(_can.getX(), _can.getY(), _can.getWidth(), _can.getHeight());
    }

    /**
     * Graphics constructor. Saves an instance of the window.
     * @param w Window instance
     */
    public Graphics(Window w) {
        _win = w;
        _can = new Rect(0, 0, 0, 0);
        _refCan = new Rect(0, 0, 0, 0);
    }

    /**
     * Set a size for the canvas to place objects in the menus and UI
     * @param c Size of canvas
     */
    @Override
    public void setCanvasSize(Rect c, Rect dim) { // TODO: Recomentar
        Rect temp; // Temporal rectangle for calculations

        int width = c.getWidth(); //
        int height = c.getHeight();

        if(width > dim.getWidth()){
            width = dim.getWidth();

            height = (width * c.getHeight()) / c.getWidth();
        }

        if(height > dim.getHeight()){
            height = dim.getHeight();

            width = (height * c.getWidth()) / c.getHeight();
        }

        temp = new Rect (width, 0, 0, height);

        _can = temp;
    }

    @Override
    public Rect getCanvas() {
        return _can;
    }

    @Override
    public void setReferenceCanvas(Rect c) {
        _refCan = c;
    }

    //Hacer dos métodos que inicien y acaben el frame
    // Esto es para abstraer t odo el sistema de pintado de la Lógica

    /**
     * Creates an Image (type Image from pcengine) and returns it.
     * @param name The path to the image
     * @return The image created
     */
    @Override
    public Image newImage(String name) {
        Image _image = null; // Temporal variable to store the Image

        try { // Try to create the image and if it fails handle the exception
            _image = new Image((java.awt.Image) javax.imageio.ImageIO.read(new java.io.File(name)));

        } catch (Exception e) {
            System.err.println(e);
        }

        // Return the image created
        return _image;
    }

    /**
     * This function receives a color and paints the hole screen with that color (white recommended)
     * to clean it from the last painting.
     * @param color Flat color received to paint the screen
     */
    @Override
    public void clear(int color) {
        // Set color to paint in the Swing Graphics.
        _win.getJGraphics().setColor(Color.BLACK);
        // Paint the hole screen with it.
        _win.getJGraphics().fillRect(0, 0, _win.getWidth(), _win.getHeight());
    }

    /**
     * Draws an image in a specific location.
     * @param image Image that wil be painted
     * @param x Position x of the image
     * @param y Position y of the image
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, int x, int y) {
        try {
            if (image != null) { // If the image exists, try to draw it
                _win.getJGraphics().drawImage(((Image) image).getImage(), 0, 100, null);
            }
        } catch (Exception e) { // Handle Exception

        }
    }

    /**
     * Draws an image (or a part of it (Sprite)) in a specific location, counting with the values of
     * the image.
     * @param image Image to paint
     * @param source Image dimensions (Rectangle)
     * @param x Position X to place the image (top left corner)
     * @param y Position Y to place the image (top left corner)
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, int x, int y) { // TODO: Comentar
        try {
            Rect temp = new Rect (repositionX(source.getWidth()), 0, 0, repositionY(source.getHeight()));
            //temp = dimensions(source, temp);

            x = _can.getX() + repositionX(x);
            y = _can.getY() + repositionY(y);

            temp.setPosition(x, y);

            if (image != null) { // If the image exists, try to draw it
                _win.getJGraphics().drawImage(((Image) image).getImage(), x, y, temp.getX() + temp.getWidth(), temp.getY() + temp.getHeight(),
                        source.getLeft(), source.getTop(), source.getRight(), source.getBottom(), null);
            }
        } catch (Exception e) { // Handle Exception

        }
    }

    /**
     * Draw an image (or a part of it) in a specific rectangle location.
     * @param image Image to paint
     * @param source Image dimensions
     * @param dest Rectangle in which we will draw
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest) { // TODO: Comentar
        try {
            dest = dimensions(dest, _can);

            int x = repositionX(dest.getX());
            int y = repositionY(dest.getY());

            dest.setPosition(x + _can.getX(), y + _can.getY());

            if (image != null) {
                _win.getJGraphics().drawImage(((ucm.dv.vdm.pcengine.Image) image).getImage(), dest.getX(), dest.getY(),
                        dest.getX() + dest.getWidth(), dest.getY() + dest.getHeight(),
                        source.getLeft(), source.getTop(), source.getRight(), source.getBottom(), null);
            }
        } catch (Exception e) {

        }
    }

    /**
     * Draws an image (or a part of it (Sprite)) in a specific rectangle with an specific alpha
     * value (transparency).
     * TODO: Preguntar como se haría esta wea
     * @param image Image to paint
     * @param source Rectangle of the image.
     * @param dest Rectangle destination to paint the image.
     * @param alpha Alpha value for the color.
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest, float alpha) { // TODO: comentar
        try {
            // Set alpha value received from the function call
            ((Graphics2D)_win.getJGraphics()).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            dest = dimensions(dest, _can);

            int x = repositionX(dest.getX());
            int y = repositionY(dest.getY());

            dest.setPosition(x + _can.getX(), y + _can.getY());

            Color c = new Color(0.0f, 0.0f, 0.0f, alpha);

            if (image != null) {
                _win.getJGraphics().setColor(c);

                _win.getJGraphics().drawImage(((ucm.dv.vdm.pcengine.Image) image).getImage(), dest.getX(), dest.getY(),
                        dest.getX() + dest.getWidth(), dest.getY() + dest.getHeight(),
                        source.getLeft(), source.getTop(), source.getRight(), source.getBottom(), null);
            }

            // Reset alpha value to 1.0f
            ((Graphics2D)_win.getJGraphics()).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        } catch (Exception e) {

        }


    }

    /**
     * Return the width value of the window.
     * @return Window Width
     */
    @Override
    public int getWidth() {
        return _win.getWidth();
    }

    /**
     * Return the height value of the window.
     *
     * @return Window height
     */
    @Override
    public int getHeight() {
        return _win.getHeight();
    }

    /**
     * Change the size of a Rectangle using another rectangle as a reference. Maintains the aspect
     * ratio of the Rectangle (proportions) and returns the new rectangle.
     * @param src Rectangle to be resized
     * @param dim Rectangle to use as a reference.
     * @return Resized src rectangle maintaining the aspect ratio of it.
     */
    @Override
    public Rect dimensions(Rect src, Rect dim){
        Rect temp; // Temporal rectangle for calculations

        int width = src.getWidth(); // Save the src width
        int height = src.getHeight(); // Save the src height

        // If the src width is higher than the reference width
        if(width > dim.getWidth()){
            // Set the new width but resized proportionally
           width = repositionX(width);
            // Change height keeping proportions
           height = (width * src.getHeight()) / src.getWidth();
        }

        // If the src height (or the changed height) is bigger than the reference one
        if(height > dim.getHeight()){
            // Set the new height but resized proportionally
            height = repositionY(height);
            // Change width proportionally
            width = (height * src.getWidth()) / src.getHeight();
        }

        // Save the changes to the new Rectangle
        temp = new Rect (width, 0, 0, height);

        // Set the original position in canvas of the source Rectangle
        temp.setPosition(src.getX(), src.getY());

        // Return result
        return temp;
    }

    // TODO: COmentar de aquí para abajo.
    @Override
    public void setCanvasPos(int x, int y) {
        _can.setPosition(x, y);
    }

    @Override
    public int repositionX(int x) {
        return (x * _can.getWidth()) / _refCan.getWidth();
    }

    @Override
    public int repositionY(int y) {
        return (y * _can.getHeight()) / _refCan.getHeight();
    }

    //---------------------------------------------------------------
    //----------------------Pivate Atributes-------------------------
    //---------------------------------------------------------------

    /**
     * Atribute that saves an instance of the window to get the Swing Graphics
     */
    Window _win;

    // Canvas
    Rect _can;

    Rect _refCan;

}
