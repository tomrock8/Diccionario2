public class Program {
    public static void main(String[] args) {
        String lexeme = new String("soul");
        char[] idiomas = { 'F', 'I', 'E', 'C', 'H' };
        Palabra2 word = new Palabra2(lexeme, idiomas);
        String[] meaning = new String[5];
        char[] language = new char[5];
        meaning[0] = new String("coeur");
        language[0] = 'F';
        meaning[1] = new String("alma");
        language[1] = 'E';
        meaning[2] = new String("ziel");
        language[2] = 'H';
        meaning[3] = new String("conciencia");
        language[3] = 'E';
        meaning[4] = new String("anima");
        language[4] = 'I';
        int pos = -1;
        for (int i = 0; i < language.length; i++) {
            pos = word.setTrad(meaning[i], language[i]);
            System.out.println("traduccion a " + language[i] + "; acepcion " + meaning[i] + " -> " + pos);
        }
        String trad = null;
        for (int i = 0; i < idiomas.length; i++) {
            trad = word.getTraduccion(idiomas[i]);
            if (trad != null)
                System.out.println("Traduccion a " + idiomas[i] + " -> " + trad);
            else
                System.out.println("No tiene traduccion a " + idiomas[i]);
        }
        word.escribeInfo();

    }

}
