package ucm.dv.vdm.pcengine;

import java.awt.*;
import java.awt.image.BufferStrategy;

import ucm.dv.vdm.engine.Rect;

public class Graphics implements ucm.dv.vdm.engine.Graphics{

    public Graphics (Window w){
        _win = w;
    }

    @Override
    public void setCanvasSize(int x, int y) {

    }

    //Hacer dos métodos que inicien y acaben el frame
    // Esto es para abstraer t odo el sistema de pintado de la Lógica


    @Override
    public Image newImage(String name) {

        try{

            _image = new Image((java.awt.Image) javax.imageio.ImageIO.read(new java.io.File(name)));
            _imageWidth = _image.getWidth();
            _imageHeight = _image.getHeight();

        }
        catch (Exception e){
            System.err.println(e);
        }

        return _image;
    }

    @Override
    public void clear(int color) {
        _win.getJGraphics().setColor(Color.getColor("White", color));
        _win.getJGraphics().fillRect(0, 0, _win.getWidth(), _win.getHeight());
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, int x, int y) {
        try {
            if(_image != null){
                _win.getJGraphics().drawImage(((Image)image).getImage(), x, y, source.getWidth(), source.getHeight(),
                        source.getLeft(), source.getTop(), source.getRight(), source.getBottom(), null);
            }
        }
        catch(Exception e){

        }
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest) {
        try {
            if(_image != null){
                _win.getJGraphics().drawImage(((ucm.dv.vdm.pcengine.Image)image).getImage(), dest.getLeft(), dest.getTop(), dest.getRight(), dest.getBottom(),
                        source.getLeft(), source.getTop(), source.getRight(), source.getBottom(), null);
            }
        }
        catch(Exception e){

        }
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest, int alpha) {

    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, int x, int y) {
        try {
            if(_image != null){
                _win.getJGraphics().drawImage(_image.getImage(), 0, 100, null);
            }
        }
        catch(Exception e){

        }
    }

    /**
     * Return the width value of the image.
     * @return
     */
    @Override
    public int getWidth() {
        return _imageWidth;
    }

    /**
     * Return the height value of the image.
     * @return Image height
     */
    @Override
    public int getHeight() {
        return _imageHeight;
    }


    //---------------------------------------------------------------
    //----------------------Pivate Atributes-------------------------
    //---------------------------------------------------------------

    /**
     * Atribute that saves an instance of the window to get the Swing Graphics
     */
    Window _win;

    /**
     * Saves an image to paint it later
     */
    Image _image;

    /**
     *
     */
    int _imageWidth, _imageHeight;

}
