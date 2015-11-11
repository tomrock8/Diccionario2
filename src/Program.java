public class Program {

    public static void main(String[] args) {
        String lexeme = new String("quiet");
        Palabra2 word = new Palabra2(lexeme, null);
        String[] meaning = new String[4];
        char[] language = new char[4];
        meaning[0] = new String("tranquilo");
        language[0] = 'E';
        meaning[1] = new String("calme");
        language[1] = 'F';
        meaning[2] = new String("quieto");
        language[2] = 'P';
        meaning[3] = new String("ancora");
        language[3] = 'I';
        int pos = -1;
        for (int i = 0; i < language.length; i++) {
            pos = word.setTrad(meaning[i], language[i]);
            System.out.println("traduccion a " + language[i] + "; acepcion " + meaning[i] + " -> " + pos);
        }
        word.escribeInfo();
        meaning[0] = new String("tranquilo");
        language[0] = 'P';
        meaning[1] = new String("tranquillo");
        language[1] = 'I';
        meaning[2] = new String("calme");
        language[2] = 'F';
        meaning[3] = new String("callado");
        language[3] = 'E';
        for (int i = 0; i < language.length; i++) {
            pos = word.setTrad(meaning[i], language[i]);
            System.out.println("traduccion a " + language[i] + "; acepcion " + meaning[i] + " -> " + pos);
        }
        word.escribeInfo();
    }
}