package ucu.edu.aed.tda.trie;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTTrieTest {

    protected abstract TTrie<String> crearTrie();

    @Test
    public void testInsertarNuevaPalabraRetornaTrue() {
        TTrie<String> trie = crearTrie();
        assertTrue(trie.insertar("casa", "casa"));
    }

    @Test
    public void testInsertarPalabraRepetidaRetornaFalse() {
        TTrie<String> trie = crearTrie();
        trie.insertar("casa", "casa");
        assertFalse(trie.insertar("casa", "casa"));
    }

    @Test
    public void testBuscarPalabraExistente() {
        TTrie<String> trie = crearTrie();
        trie.insertar("casa", "casa");
        Entry<String> entry = trie.buscar("casa");
        assertTrue(entry.esPalabra());
        assertEquals("casa", entry.getPalabra());
    }

    @Test
    public void testBuscarPalabraNoExistente() {
        TTrie<String> trie = crearTrie();
        trie.insertar("casa", "casa");
        Entry<String> entry = trie.buscar("perro");
        assertFalse(entry.esPalabra());
    }

    @Test
    public void testBuscarPrefijoNoEsPalabra() {
        TTrie<String> trie = crearTrie();
        trie.insertar("casa", "casa");
        Entry<String> entry = trie.buscar("cas");
        assertFalse(entry.esPalabra());
    }

    @Test
    public void testPredecirDevuelvePalabrasConPrefijo() {
        TTrie<String> trie = crearTrie();
        trie.insertar("casa", "casa");
        trie.insertar("casita", "casita");
        trie.insertar("castillo", "castillo");
        trie.insertar("perro", "perro");

        List<Entry<String>> resultado = trie.predecir("cas");

        assertEquals(3, resultado.size());
        assertTrue(contiene(resultado, "casa"));
        assertTrue(contiene(resultado, "casita"));
        assertTrue(contiene(resultado, "castillo"));
        assertFalse(contiene(resultado, "perro"));
    }

    @Test
    public void testPredecirPrefijoInexistenteDevuelveListaVacia() {
        TTrie<String> trie = crearTrie();
        trie.insertar("casa", "casa");
        List<Entry<String>> resultado = trie.predecir("xyz");
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testPredecirPalabraExactaTambienSeIncluye() {
        TTrie<String> trie = crearTrie();
        trie.insertar("cas", "cas");
        trie.insertar("casa", "casa");

        List<Entry<String>> resultado = trie.predecir("cas");

        assertEquals(2, resultado.size());
        assertTrue(contiene(resultado, "cas"));
        assertTrue(contiene(resultado, "casa"));
    }

    @Test
    public void testRecorrerVisitaTodasLasPalabras() {
        TTrie<String> trie = crearTrie();
        trie.insertar("casa", "casa");
        trie.insertar("perro", "perro");
        trie.insertar("gato", "gato");

        List<String> visitadas = new ArrayList<>();
        trie.recorrer(entry -> visitadas.add(entry.getPalabra()));

        assertEquals(3, visitadas.size());
        assertTrue(visitadas.contains("casa"));
        assertTrue(visitadas.contains("perro"));
        assertTrue(visitadas.contains("gato"));
    }

    @Test
    public void testRecorrerTrieVacioNoVisitaNada() {
        TTrie<String> trie = crearTrie();
        List<String> visitadas = new ArrayList<>();
        trie.recorrer(entry -> visitadas.add(entry.getPalabra()));
        assertTrue(visitadas.isEmpty());
    }

    private boolean contiene(List<Entry<String>> lista, String palabra) {
        for (Entry<String> entry : lista) {
            if (palabra.equals(entry.getPalabra())) return true;
        }
        return false;
    }
}