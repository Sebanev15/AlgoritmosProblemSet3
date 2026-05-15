package ucu.edu.aed.ej16;

import java.util.ArrayList;
import java.util.List;
import ucu.edu.aed.tda.generic_trie.TNodoGenerico;
import ucu.edu.aed.tda.generic_trie.ImplArbolGenerico;

public class ArbolGenealogico{
    public ImplArbolGenerico<Persona> arbol;

    public ArbolGenealogico(Persona raiz) {
        arbol = new ImplArbolGenerico<>(raiz);
    }

    // 1.
    public List<Persona> listarDescendientes(Persona persona) {
        List<Persona> descendientes = new ArrayList<>();
        TNodoGenerico<Persona> nodo = arbol.raiz.buscar(persona);
        if (nodo != null) {
            nodo.preOrden(n -> {
                if (!n.getDato().equals(persona)) {
                    descendientes.add(n.getDato());
                }
            });
        }
        return descendientes;
    }

    // 2.
    public int altura() {
        return arbol.raiz.altura();
    }

    // 3.
    public int contarPersonas() {
        final int[] contador = {0};
        arbol.preOrden(p -> contador[0]++);
        return contador[0];
    }

    // 4.
    public List<Persona> obtenerGeneracion(int nivel) {
        List<Persona> resultado = new ArrayList<>();
        obtenerGeneracionRec(arbol.raiz, 0, nivel, resultado);
        return resultado;
    }

    private void obtenerGeneracionRec(TNodoGenerico<Persona> nodo, int actual, int nivel, List<Persona> lista) {
        if (nodo == null) return;
        if (actual == nivel) {
            lista.add(nodo.getDato());
        } else {
            for (Persona hijo : nodo.obtenerHijos()) {
                TNodoGenerico<Persona> nodoHijo = nodo.buscar(hijo);
                obtenerGeneracionRec(nodoHijo, actual + 1, nivel, lista);
            }
        }
    }

    // 5.
    public Persona ancestroComun(Persona p1, Persona p2) {
        return ancestroComunRec(arbol.raiz, p1, p2);
    }

    private Persona ancestroComunRec(TNodoGenerico<Persona> nodo, Persona p1, Persona p2) {
        if (nodo == null) return null;
        if (nodo.getDato().equals(p1) || nodo.getDato().equals(p2)) return nodo.getDato();

        int count = 0;
        Persona temp = null;
        for (Persona hijo : nodo.obtenerHijos()) {
            TNodoGenerico<Persona> nodoHijo = nodo.buscar(hijo);
            Persona res = ancestroComunRec(nodoHijo, p1, p2);
            if (res != null) {
                count++;
                temp = res;
            }
        }
        if (count == 2) return nodo.getDato();
        return temp;
    }

    // 6.
    public boolean esDescendiente(Persona ancestro, Persona posibleDesc) {
        TNodoGenerico<Persona> nodoAncestro = arbol.raiz.buscar(ancestro);
        if (nodoAncestro == null) return false;
        return nodoAncestro.buscar(posibleDesc) != null;
    }
}
