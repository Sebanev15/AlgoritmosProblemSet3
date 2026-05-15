package ucu.edu.aed.tda.generic_trie;

import java.util.function.Consumer;

public class ImplArbolGenerico<T extends Comparable<T>> implements TArbolGenerico<T> {
    public ImplNodoGenerico<T> raiz;

    public ImplArbolGenerico(T datoRaiz) {
        this.raiz = new ImplNodoGenerico<>(datoRaiz);
    }

    @Override
    public boolean agregarHijo(Comparable<T> padre, T hijo) {
        return raiz.agregarHijo((T) padre, hijo);
    }

    @Override
    public void eliminar(Comparable<T> criterio) {
        if (criterio.compareTo(raiz.getDato()) == 0) {
            raiz = null;
        } else if (raiz != null) {
            raiz.eliminar(criterio);
        }
    }

    @Override
    public T obtenerPadre(Comparable<T> criterio) {
        TNodoGenerico<T> padre = raiz.obtenerPadre(criterio);
        return padre != null ? padre.getDato() : null;
    }

    @Override
    public T buscar(Comparable<T> criterio) {
        TNodoGenerico<T> nodo = raiz.buscar(criterio);
        return nodo != null ? nodo.getDato() : null;
    }

    @Override
    public void preOrden(Consumer<T> consumidor) {
        if (raiz != null) raiz.preOrden(n -> consumidor.accept(n.getDato()));
    }

    @Override
    public void inOrden(Consumer<T> consumidor) {
        if (raiz != null) raiz.inOrden(n -> consumidor.accept(n.getDato()));
    }

    @Override
    public void postOrden(Consumer<T> consumidor) {
        if (raiz != null) raiz.postOrden(n -> consumidor.accept(n.getDato()));
    }

    @Override
    public void vaciar() {
        raiz = null;
    }

    @Override
    public int grado(Comparable<T> nodo) {
        TNodoGenerico<T> encontrado = raiz.buscar(nodo);
        return encontrado != null ? encontrado.grado() : -1;
    }

    @Override
    public int altura(Comparable<T> nodo) {
        TNodoGenerico<T> encontrado = raiz.buscar(nodo);
        return encontrado != null ? encontrado.altura() : -1;
    }
}