import java.util.Vector;

//DNI 15418068 GONZALEZ COBO, ANGEL

public class Palabra2 {

    private char[] lenguas; // I - Ingles, E - Espanol, P - portugues, F -
                            // Frances
    private String origen;
    private Vector<String> trad;

    /**
     * Constructor de la clase. En caso que no haya alguno de los parametros,
     * estos se iniciaran con su valor por defecto.
     * 
     * @param p
     *            String con la palabra de origen.
     * @param lenguas
     *            Char[] con la lenguas a traducir. Si es null el valor sera
     *            {'E', 'F', 'P'}.
     */
    public Palabra2(String p, char[] lenguas) {
        if (p == null)
            p = "";
        origen = p;

        if (lenguas == null) {
            this.lenguas = new char[3];
            this.lenguas[0] = 'E';
            this.lenguas[1] = 'F';
            this.lenguas[2] = 'P';
            this.trad = new Vector<String>(3);
            this.trad.setSize(3);
        } else {
            this.lenguas = lenguas;
            this.trad = new Vector<String>(lenguas.length);
            this.trad.setSize(lenguas.length);
        }

    }

    public int setTrad(String t, char l) {
        if (lenguas == null || t == null)
            return -1;

        int pos = posicionTrad(l);

        if (pos != -1) {
            try {
                //if (trad.get(pos) != null) {
                    trad.setElementAt(t, pos);
                //}
            } catch (IndexOutOfBoundsException e) {
                System.out.println("CATCH SETTRAD");
                trad.add(pos, t);
            }

        }

        return pos;
    }

    /**
     * For para obtener la posicion de la traduccion en el array
     * 
     * @param l
     *            Lengua a buscar
     * @return int con la posicion en el array. -1 Si no esta en el array
     */
    private int posicionTrad(char l) {
        for (int i = 0; i < lenguas.length; i++) {
            if (lenguas[i] == l) {
                return i;
            }
        }
        return -1;
    }

    public String getOrigen() {
        return origen;
    }

    public String getTraduccion(char l) {
        int pos = posicionTrad(l);

        try {
            if (pos != -1) {
                return trad.get(pos);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("CATCH GETTRADUCCION");
            return null;
        }

        return null;
    }

    public void escribeInfo() {
        String res = origen + ":";
        for (String s : trad) {
            if(s != null)
                res += s + ":";
        }
        res = res.replace("::", ":");
        res = res.substring(0, res.length() - 1);
        System.out.println(res);
    }

}
