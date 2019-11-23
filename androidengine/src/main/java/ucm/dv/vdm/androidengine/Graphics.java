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

public class  Graphics extends AbstractGraphics { // TODO: Comentar

    public Graphics (SurfaceView sv, AssetManager am){
        _surfaceView = sv;
        _assetManager = am;
        _paint = new Paint();
    }

    public void startFrame(Canvas c){
        _canvas = c;
    }

    public void drawRect(){
        _paint.setColor(Color.BLUE);

        _canvas.drawRect((float)_can.getX(), (float)_can.getY(), (float)(_can.getX() + _can.getWidth()), (float)(_can.getY() + _can.getHeight()), _paint);
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
        _paint.setColor(color);

        _canvas.drawRect((float)_can.getX(), (float)_can.getY(), (float)(_can.getX() + _can.getWidth()), (float)(_can.getY() + _can.getHeight()), _paint);

        _paint.reset();
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, int x, int y) {
        if(image != null){
            Image im = (Image) image;
            Bitmap bm = im.getBitmap();
            _canvas.drawBitmap(bm, x, y, _paint);
        }
    }

    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, int x, int y) {
        try{
            Rect dst = new Rect(repositionX(source.getWidth()), 0, 0, repositionY(source.getHeight()));

            x = _can.getX() + repositionX(x);
            y = _can.getY() + repositionY(y);

            dst.setPosition(x, y);

            android.graphics.Rect dest = new android.graphics.Rect(dst.getX(), dst.getY(), dst.getX() + dst.getWidth(), dst.getY() + dst.getHeight());
            //int left, int top, int right, int bottom
            android.graphics.Rect src = new android.graphics.Rect(source.getLeft(), source.getTop(), source.getRight(), source.getBottom());

            Image im = (Image) image;
            Bitmap bm = im.getBitmap();
            if(bm != null) {
                _canvas.drawBitmap(bm, src, dest, _paint);
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

            android.graphics.Rect dst = new android.graphics.Rect(x, y, x + dest.getWidth(), y + dest.getHeight());
            //int left, int top, int right, int bottom
            android.graphics.Rect src = new android.graphics.Rect(source.getLeft(), source.getTop(), source.getRight(), source.getBottom());

            Image im = (Image) image;
            Bitmap bm = im.getBitmap();

            if(bm != null) {
                _canvas.drawBitmap(bm, src, dst, _paint);
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

            _paint.setAlpha((int)(alpha * 100));

            System.out.println(_paint.getAlpha());

            if(bm != null) {
                _canvas.drawBitmap(bm, src, dst, _paint);
            }

        }catch(Exception e){

        }

        _paint.reset();
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
    private Paint _paint;

}
