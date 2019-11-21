package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class Text extends GameObject{

    /**
     * Initializes Text.
     *
     * @param x
     * @param y
     * @param s
     */
    public Text(int x, int y, Sprite[] s) {
        super(x, y, s);

        _character = 0;
    }

    public void setCharacter (char c){

        switch (c){
            case 'a':
            case 'A':
                _character = 0;
                break;

            case 'b':
            case 'B':
                _character = 1;
                break;

            case 'c':
            case 'C':
                _character = 2;
                break;

            case 'd':
            case 'D':
                _character = 3;
                break;

            case 'e':
            case 'E':
                _character = 4;
                break;

            case 'f':
            case 'F':
                _character = 5;
                break;

            case 'g':
            case 'G':
                _character = 6;
                break;

            case 'h':
            case 'H':
                _character = 7;
                break;

            case 'i':
            case 'I':
                _character = 8;
                break;

            case 'j':
            case 'J':
                _character = 9;
                break;

            case 'k':
            case 'K':
                _character = 10;
                break;

            case 'l':
            case 'L':
                _character = 11;
                break;

            case 'm':
            case 'M':
                _character = 12;
                break;

            case 'n':
            case 'N':
                _character = 13;
                break;

            case 'o':
            case 'O':
                _character = 14;
                break;

            case 'p':
            case 'P':
                _character = 15;
                break;

            case 'q':
            case 'Q':
                _character = 16;
                break;

            case 'r':
            case 'R':
                _character = 17;
                break;

            case 's':
            case 'S':
                _character = 18;
                break;

            case 't':
            case 'T':
                _character = 19;
                break;
                //-20-
            case 'u':
            case 'U':
                _character = 20;
                break;

            case 'v':
            case 'V':
                _character = 21;
                break;

            case 'w':
            case 'W':
                _character = 22;
                break;

            case 'x':
            case 'X':
                _character = 23;
                break;

            case 'y':
            case 'Y':
                _character = 24;
                break;

            case 'z':
            case 'Z':
                _character = 25;
                break;
            //NUMBERS
            case '0':
                _character = 26;
                break;

            case '1':
                _character = 27;
                break;

            case '2':
                _character = 28;
                break;

            case '3':
                _character = 29;
                break;

            case '4':
                _character = 30;
                break;

            case '5':
                _character = 31;
                break;

            case '6':
                _character = 32;
                break;

            case '7':
                _character = 33;
                break;

            case '8':
                _character = 34;
                break;

            case '9':
                _character = 35;
                break;

        }

    }

    @Override
    public void render(Graphics g) {
       _sprite[_character].draw(g, (int)_x, (int)_y);
    }

    int _character; //Iterator to a specific letter or number
}
