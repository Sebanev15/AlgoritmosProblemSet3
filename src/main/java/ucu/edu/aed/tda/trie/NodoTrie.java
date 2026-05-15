package ucu.edu.aed.tda.trie;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NodoTrie<T> implements TNodoTrie<T> {
    char caracter;
    boolean esPalabra;
    T dato;
    List<NodoTrie<T>> hijos;

    public NodoTrie(char caracter) {
        this.caracter = caracter;
        this.esPalabra = false;
        this.dato = null;
        this.hijos = new ArrayList<>();
    }

    private NodoTrie<T> getHijo(char c) {
        for (NodoTrie<T> hijo : hijos) {
            if (hijo.caracter == c) return hijo;
        }
        return null;
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        recorrerRec("", consumer);
    }

    private void recorrerRec(String prefijo, Consumer<Entry<T>> consumer) {
        if (esPalabra) {
            consumer.accept(new Entry<>(dato, true, prefijo));
        }
        for (NodoTrie<T> hijo : hijos) {
            hijo.recorrerRec(prefijo + hijo.caracter, consumer);
        }
    }

    @Override
    public Entry<T> buscar(String palabra) {
        NodoTrie<T> nodo = this;
        for (char c : palabra.toCharArray()) {
            nodo = nodo.getHijo(c);
            if (nodo == null) return new Entry<>(null, false, palabra);
        }
        return new Entry<>(nodo.dato, nodo.esPalabra, palabra);
    }

    @Override
    public boolean insertar(String palabra, T dato) {
        NodoTrie<T> nodo = this;
        for (char c : palabra.toCharArray()) {
            NodoTrie<T> hijo = nodo.getHijo(c);
            if (hijo == null) {
                hijo = new NodoTrie<>(c);
                nodo.hijos.add(hijo);
            }
            nodo = hijo;
        }
        boolean agregado = !nodo.esPalabra;
        nodo.esPalabra = true;
        nodo.dato = dato;
        return agregado;
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        List<Entry<T>> resultados = new ArrayList<>();
        NodoTrie<T> nodo = this;
        for (char c : prefijo.toCharArray()) {
            nodo = nodo.getHijo(c);
            if (nodo == null) return resultados;
        }
        nodo.recorrerRec(prefijo, resultados::add);
        return resultados;
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public boolean esPalabra() {
        return esPalabra;
    }
}