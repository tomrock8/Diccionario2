//DNI 15418068 GONZALEZ COBO, ANGEL

import java.util.ArrayList;
import java.util.Vector;

public class DiccLisJava implements Diccionario {

    private int nlenguas;
    private Vector<Character> lenguas;
    private ArrayList<Palabra2> dicc;

    public DiccLisJava(){
        nlenguas = -1;
        lenguas = new Vector<Character>();
        dicc = new ArrayList<Palabra2>();
    }
    
    @Override
    public void leeDiccionario(String f) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean inserta(Palabra2 p) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean borra(String s) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int busca(String s) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String traduce(String s, char l) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void visualiza() {
        // TODO Auto-generated method stub

    }

    @Override
    public void visualiza(int j) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visualiza(int j, char l) {
        // TODO Auto-generated method stub

    }

}
