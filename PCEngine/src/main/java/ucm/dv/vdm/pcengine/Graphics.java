package ucm.dv.vdm.pcengine;

import java.awt.*;
import java.awt.image.BufferStrategy;

import ucm.dv.vdm.engine.AbstractGraphics;
import ucm.dv.vdm.engine.Rect;

public class Graphics extends AbstractGraphics {

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

        Color c = new Color(color);

        // Set color to paint in the Swing Graphics.
        _win.getJGraphics().setColor(c);
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
     *
     * @param image Image to paint
     * @param source Rectangle of the image.
     * @param dest Rectangle destination to paint the image.
     * @param alpha Alpha value for the color.
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest, float alpha) {
        try {
            // Set alpha value received from the function call
            ((Graphics2D)_win.getJGraphics()).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            Rect temp = new Rect (repositionX(dest.getWidth()), 0, 0, repositionY(dest.getHeight()));

            int x = repositionX(dest.getX());
            int y = repositionY(dest.getY());

            temp.setPosition(x + _can.getX(), y + _can.getY());

            Color c = new Color(0.0f, 0.0f, 0.0f, alpha);

            if (image != null) {
                _win.getJGraphics().setColor(c);

                _win.getJGraphics().drawImage(((ucm.dv.vdm.pcengine.Image) image).getImage(), temp.getX(), temp.getY(),
                        temp.getX() + temp.getWidth(), temp.getY() + temp.getHeight(),
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


    // TODO: COmentar de aquí para abajo.
    @Override
    public void setCanvasPos(int x, int y) {
        _can.setPosition(x, y);
    }

    //---------------------------------------------------------------
    //----------------------Pivate Atributes-------------------------
    //---------------------------------------------------------------

    /**
     * Atribute that saves an instance of the window to get the Swing Graphics
     */
    Window _win;


}
