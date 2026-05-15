package ucu.edu.aed;

import java.util.*;
import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.TTrieHashMap;

public class BusquedaPatrones {
    private TTrieHashMap<Integer> trie;

    public BusquedaPatrones(String texto) {
        trie = new TTrieHashMap<>();
        // Insertamos todos los sufijos del texto en el Trie
        for (int i = 0; i < texto.length(); i++) {
            String sufijo = texto.substring(i);
            trie.insertar(sufijo, i); // Guardamos la posición inicial del sufijo
        }
    }

    // Busca un patrón y devuelve las posiciones donde ocurre
    public List<Integer> buscarPatron(String patron) {
        List<Integer> posiciones = new ArrayList<>();
        List<Entry<Integer>> resultados = trie.predecir(patron);
        for (Entry<Integer> entry : resultados) {
            posiciones.add(entry.getDato()); // El dato es la posición en el texto
        }
        Collections.sort(posiciones); // Ordena
        return posiciones;
    }

}
