package ucm.dv.vdm.engine;

public class Sprite {

    public Sprite(Image i, Rect r){
        // Aquí pasan cosas
        // Tipo la constructora

        _image = i;
        _rect = r;
    }

    public void draw(Graphics g, int x, int y){
        g.drawImage(_image, _rect, x, y);
    }

    //Método que divide las imágenes que le pase la lógica y los guarda en un vector que devuelve
    //Como parámetros pasarán la imagen para coger medidas y actuar sobre ella y el numero de divisiones a realizar
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

        // Create sprites and put them in the Array
        for(int j = 0; j < width*height; j++){
            right = j * sw;
            left = (j+1) *sw;
            top = j * sh;
            bottom = (j+1) * sh;
            Rect r = new Rect (right, left, top, bottom); // Rectangle for Sprite

            sprites[j] = new Sprite(sImage, r);
        }

        return sprites; // Returns all sprites separated in different images

    } // tiene que devolver un Array de Sprites, de tamaño fijo

    private Image _image;

    private Rect _rect;

}
