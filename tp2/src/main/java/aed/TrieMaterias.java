package aed;

public class TrieMaterias {

    NodoMateria raiz;

    public TrieMaterias() {
        raiz = new NodoMateria();
    }

    public NodoMateria insertarEnTrie(String word) {
        int n = word.length();

        NodoMateria head = raiz;

        for (int i = 0; i < n; i++) {

            int index = word.charAt(i);// - 'a'; <- esto se bugea, resolver despues

            if (head.hijo[index] == null) {
                head.hijo[index] = new NodoMateria();
            }

            head = head.hijo[index];
            head.letra = String.valueOf(word.charAt(i));
        }

        head.esFinalPalabra = true;
        return head;
    }

    public NodoMateria devolverHojaMateria(String word) {

        int n = word.length();

        NodoMateria head = raiz;
        for (int i = 0; i < n; i++) {
            int index = word.charAt(i);// - 'a'; <- esto se bugea, resolver despues

            if (head.hijo[index] == null) return null;
            head = head.hijo[index];
        }

        return head;
    }

}
