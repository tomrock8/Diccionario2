public class Program {
    public static void main(String[] args) {
        Diccionario diccio=new DiccLisJava();
        if(args.length>=1){
          diccio.leeDiccionario(args[0]);
          diccio.visualiza();
        }
        else
         System.out.println("Forma uso: java p03 p03.dic");
        
    }
}
