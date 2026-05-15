package ucu.edu.aed.tda.trie;

import java.util.*;
import java.util.function.Consumer;

public class TNodoTrieHashMap<T> implements TNodoTrie<T> {
    Map<Character, TNodoTrieHashMap<T>> hijos;
    private boolean esPalabra;
    private T dato;

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
        dato = null;
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        recorrerRec("", consumer);
    }

    private void recorrerRec(String prefijo, Consumer<Entry<T>> consumer) {
        if (esPalabra) {
            consumer.accept(new Entry<>(dato, true, prefijo));
        }
        for (Map.Entry<Character, TNodoTrieHashMap<T>> entry : hijos.entrySet()) {
            entry.getValue().recorrerRec(prefijo + entry.getKey(), consumer);
        }
    }

    @Override
    public Entry<T> buscar(String palabra) {
        TNodoTrieHashMap<T> nodo = this;
        for (char c : palabra.toCharArray()) {
            nodo = nodo.hijos.get(c);
            if (nodo == null) {
                // No se encontró
                return new Entry<>(null, false, palabra);
            }
        }
        // Si existe, devolvemos con el dato y si es palabra completa
        return new Entry<>(nodo.dato, nodo.esPalabra, palabra);
    }

    @Override
    public boolean insertar(String palabra, T dato) {
        TNodoTrieHashMap<T> nodo = this;
        for (char c : palabra.toCharArray()) {
            nodo.hijos.putIfAbsent(c, new TNodoTrieHashMap<>());
            nodo = nodo.hijos.get(c);
        }
        boolean agregado = !nodo.esPalabra;
        nodo.esPalabra = true;
        nodo.dato = dato;
        return agregado;
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        List<Entry<T>> resultados = new ArrayList<>();
        TNodoTrieHashMap<T> nodo = this;
        for (char c : prefijo.toCharArray()) {
            nodo = nodo.hijos.get(c);
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
