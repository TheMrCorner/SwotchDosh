package ucm.dv.vdm.androidengine;

import android.graphics.Bitmap;

public class Image implements ucm.dv.vdm.engine.Image {

    /**
     * Constructor of Image. Stores an instance of a Bitmap.
     * @param b Instance of Bitmap.
     */
    Image (Bitmap b){
        _bitmap = b;
    }

    /**
     * Function that provides the width of the image.
     * @return Width of image (pixels)
     */
    @Override
    public int getWidth() {
        return _bitmap.getWidth();
    }

    /**
     * Function that provides the height of the Image.
     * @return Height of Image (pixels)
     */
    @Override
    public int getHeight() {

        return _bitmap.getHeight();
    }

    /**
     * Function that provides the instance of the Bitmap.
     * @return Instance of Bitmap.
     */
    public Bitmap getBitmap(){
        return _bitmap;
    }

    /**
     * Instance of Bitmap.
     */
    Bitmap _bitmap;

}
