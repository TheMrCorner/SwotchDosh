package ucm.dv.vdm.pcengine;

public class Image implements ucm.dv.vdm.engine.Image{

    public Image (java.awt.Image img){
        _image = img;
    }

    @Override
    public int getWidth() {
        return _image.getWidth(null);
    }

    @Override
    public int getHeight() {
        return _image.getHeight(null);
    }

    public java.awt.Image getImage() {
        return _image;
    }

    private java.awt.Image _image;

}
