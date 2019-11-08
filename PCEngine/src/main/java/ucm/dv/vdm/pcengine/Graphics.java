package ucm.dv.vdm.pcengine;

import java.awt.*;
import ucm.dv.vdm.engine.Rect;

public class Graphics implements ucm.dv.vdm.engine.Graphics{

    public Graphics (Window w){
        win = w;
    }

    @Override
    public void setCanvasSize(int x, int y) {

    }

    //Hacer dos métodos que inicien y acaben el frame
    // Esto es para abstraer todo el sistema de pintado de la Lógica


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
        win.getJGraphics().setColor(Color.getColor("White", color));
        win.getJGraphics().fillRect(0, 0, win.getWidth(), win.getHeight());
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, int x, int y) {
        try {
            if(_image != null){
                win.getJGraphics().drawImage(((ucm.dv.vdm.pcengine.Image)image).getImage(), x, y, source.getWidth(), source.getHeight(), null);
            }
        }
        finally{
            win.getJGraphics().dispose();
        }
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest) {

    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest, int alpha) {

    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, int x, int y) {
        try {
            if(_image != null){
                win.getJGraphics().drawImage(_image.getImage(), 0, 100, null);
            }
        }
        finally{
            win.getJGraphics().dispose();
        }
    }

    @Override
    public int getWidth() {
        return _imageWidth;
    }

    @Override
    public int getHeight() {
        return _imageHeight;
    }


    Window win;
    Image _image;
    int _imageWidth, _imageHeight;

}
