package ucm.dv.vdm.androidengine;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.io.InputStream;

import ucm.dv.vdm.engine.AbstractGraphics;
import ucm.dv.vdm.engine.Rect;

public class  Graphics extends AbstractGraphics { // TODO: Comentar

    public Graphics (SurfaceView sv, AssetManager am){
        _surfaceView = sv;
        _assetManager = am;
    }

    @Override
    public Image newImage(String name) {

        Image _image = null;
        InputStream _ip = null;

        try{
            _ip = _assetManager.open(name);
            Bitmap bm = BitmapFactory.decodeStream(_ip);
            _image = new Image(bm);
        } catch (Exception e){
            System.err.println(e);
        }
        finally{
            if(_ip != null){
                try{
                    _ip.close();
                }
                catch(Exception e){
                    System.err.println(e);
                }
            }
        }

        return _image;
    }

    @Override
    public void clear(int color) {
        _canvas.drawColor(color);
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, int x, int y) {
        if(image != null){
            Image im = (Image) image;
            Bitmap bm = im.getBitmap();
            _canvas.drawBitmap(bm, x, y, null);
        }
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, int x, int y) {
        try{
            x = _can.getX() + repositionX(x);
            y = _can.getY() + repositionY(y);

            android.graphics.Rect dest = new android.graphics.Rect(x, y, repositionX(source.getWidth()), repositionY(source.getHeight()));
            //int left, int top, int right, int bottom
            android.graphics.Rect src = new android.graphics.Rect(source.getLeft(), source.getTop(), source.getRight(), source.getBottom());

            Image im = (Image) image;
            Bitmap bm = im.getBitmap();
            if(bm != null) {
                _canvas.drawBitmap(bm, src, dest, null);
            }
        }catch(Exception e){

        }
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest) {
        try {
            dest = dimensions(dest, _can);

            int x = repositionX(dest.getX());
            int y = repositionY(dest.getY());

            dest.setPosition(x + _can.getX(), y + _can.getY());

            android.graphics.Rect dst = new android.graphics.Rect(dest.getLeft(), dest.getTop(), dest.getRight(), dest.getBottom());
            //int left, int top, int right, int bottom
            android.graphics.Rect src = new android.graphics.Rect(source.getLeft(), source.getTop(), source.getRight(), source.getBottom());

            Image im = (Image) image;
            Bitmap bm = im.getBitmap();

            if(bm != null) {
                _canvas.drawBitmap(bm, src, dst, null);
            }

        }catch(Exception e){

        }
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest, float alpha) {
        try {
            Rect temp =  new Rect (repositionX(dest.getWidth()), 0, 0, repositionY(dest.getHeight()));

            int x = repositionX(dest.getX());
            int y = repositionY(dest.getY());

            temp.setPosition(x + _can.getX(), y + _can.getY());

            android.graphics.Rect dst = new android.graphics.Rect(temp.getX(), temp.getY(), temp.getX() + temp.getWidth(),temp.getY() + temp.getHeight());
            //int left, int top, int right, int bottom
            android.graphics.Rect src = new android.graphics.Rect(source.getLeft(), source.getTop(), source.getRight(), source.getBottom());

            Image im = (Image) image;
            Bitmap bm = im.getBitmap();

            Paint _al = new Paint();

            _al.setAlpha((int)(alpha * 100));

            if(bm != null) {
                _canvas.drawBitmap(bm, src, dst, _al);
            }

        }catch(Exception e){

        }
    }

    @Override
    public int getWidth() {
        return _surfaceView.getWidth();
    }

    @Override
    public int getHeight() {
        return _surfaceView.getHeight();
    }

    // Private Atributes

    private SurfaceView _surfaceView;
    private AssetManager _assetManager;
    private Canvas _canvas;

}
