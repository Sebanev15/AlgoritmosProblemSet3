package ucu.edu.aed.tda.trie;

import java.util.*;
import java.util.function.Consumer;

public class TTrieHashMap<T> implements TTrie<T> {
    private TNodoTrieHashMap<T> raiz;

    public TTrieHashMap() {
        raiz = new TNodoTrieHashMap<>();
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        raiz.recorrer(consumer);
    }

    @Override
    public Entry<T> buscar(String palabra) {
        return raiz.buscar(palabra);
    }

    @Override
    public boolean insertar(String palabra, T dato) {
        return raiz.insertar(palabra, dato);
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        return raiz.predecir(prefijo);
    }

    public List<Entry<T>> buscarPatron(String patron) {
        List<Entry<T>> resultados = new ArrayList<>();
        buscarPatronRec(raiz, "", patron, 0, resultados);
        return resultados;
    }

    private void buscarPatronRec(TNodoTrieHashMap<T> nodo, String actual, String patron, int i, List<Entry<T>> resultados) {
        if (i == patron.length()) {
            if (nodo.esPalabra()) {
                resultados.add(new Entry<>(nodo.getDato(), true, actual));
            }
            return;
        }
        char c = patron.charAt(i);
        if (c == '*') {
            for (Map.Entry<Character, TNodoTrieHashMap<T>> entry : nodo.hijos.entrySet()) {
                buscarPatronRec(entry.getValue(), actual + entry.getKey(), patron, i + 1, resultados);
            }
        } else {
            TNodoTrieHashMap<T> siguiente = nodo.hijos.get(c);
            if (siguiente != null) {
                buscarPatronRec(siguiente, actual + c, patron, i + 1, resultados);
            }
        }
    }
}
