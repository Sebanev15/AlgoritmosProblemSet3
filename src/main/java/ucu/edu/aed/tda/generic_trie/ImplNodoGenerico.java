package ucu.edu.aed.tda.generic_trie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class ImplNodoGenerico<T extends Comparable<T>> implements TNodoGenerico<T> {
    private T dato;
    private List<ImplNodoGenerico<T>> hijos;

    public ImplNodoGenerico(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public boolean agregarHijo(T padre, T hijo) {
        if (dato.compareTo(padre) == 0) {
            hijos.add(new ImplNodoGenerico<>(hijo));
            return true;
        }
        for (ImplNodoGenerico<T> h : hijos) {
            if (h.agregarHijo(padre, hijo)) return true;
        }
        return false;
    }

    @Override
    public TNodoGenerico<T> eliminar(Comparable<T> criterio) {
        hijos.removeIf(h -> criterio.compareTo(h.getDato()) == 0);
        for (ImplNodoGenerico<T> h : hijos) {
            h.eliminar(criterio);
        }
        return this;
    }

    @Override
    public TNodoGenerico<T> buscar(Comparable<T> criterio) {
        if (criterio.compareTo(dato) == 0) return this;
        for (ImplNodoGenerico<T> h : hijos) {
            TNodoGenerico<T> encontrado = h.buscar(criterio);
            if (encontrado != null) return encontrado;
        }
        return null;
    }

    @Override
    public TNodoGenerico<T> obtenerPadre(Comparable<T> criterio) {
        for (ImplNodoGenerico<T> h : hijos) {
            if (criterio.compareTo(h.getDato()) == 0) return this;
            TNodoGenerico<T> padre = h.obtenerPadre(criterio);
            if (padre != null) return padre;
        }
        return null;
    }

    @Override
    public void preOrden(Consumer<TNodoGenerico<T>> consumidor) {
        consumidor.accept(this);
        for (ImplNodoGenerico<T> nodo : hijos) nodo.preOrden(consumidor);
    }

    @Override
    public void inOrden(Consumer<TNodoGenerico<T>> consumidor) {
        if (!hijos.isEmpty()) hijos.get(0).inOrden(consumidor);
        consumidor.accept(this);
        for (int i = 1; i < hijos.size(); i++) hijos.get(i).inOrden(consumidor);
    }

    @Override
    public void postOrden(Consumer<TNodoGenerico<T>> consumidor) {
        for (ImplNodoGenerico<T> nodo : hijos) nodo.postOrden(consumidor);
        consumidor.accept(this);
    }

    @Override
    public int altura() {
        int maxAltura = 0;
        for (ImplNodoGenerico<T> nodo : hijos) {
            maxAltura = Math.max(maxAltura, nodo.altura());
        }
        return 1 + maxAltura;
    }

    @Override
    public int grado() {
        return hijos.size();
    }

    @Override
    public void vaciar() {
        hijos.clear();
    }

    @Override
    public List<T> obtenerHijos() {
        List<T> lista = new ArrayList<>();
        for (ImplNodoGenerico<T> h : hijos) lista.add(h.getDato());
        return lista;
    }
}