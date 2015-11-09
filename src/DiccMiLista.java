//DNI 15418068 GONZALEZ COBO, ANGEL

public class DiccMiLista {

    private class NodoL{
        private Palabra2 pal;
        private NodoL next;
        
        public NodoL(){
            pal = null;
            next = null;
        }
        
        public NodoL(Palabra2 p){
            this.pal = p;
            next = null;
        }
        
        public void cambiaNext(NodoL n){
            next = n;
        }
        
        public void setParabra2(Palabra2 p){
            pal = p;
        }
        
        public NodoL getNext(){
            return next;
        }
        
        public Palabra2 getPalabra2(){
            return pal;
        }
    }

}
