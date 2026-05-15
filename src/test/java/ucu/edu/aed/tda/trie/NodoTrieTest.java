package ucu.edu.aed.tda.trie;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class NodoTrieTest {

    @Test
    public void testInsertarYBuscarPalabraExistente() {
        NodoTrie<String> raiz = new NodoTrie<>('\0');
        raiz.insertar("casa", "casa");
        Entry<String> entry = raiz.buscar("casa");
        assertTrue(entry.esPalabra());
        assertEquals("casa", entry.getPalabra());
    }

    @Test
    public void testInsertarPalabraRepetidaRetornaFalse() {
        NodoTrie<String> raiz = new NodoTrie<>('\0');
        raiz.insertar("casa", "casa");
        assertFalse(raiz.insertar("casa", "casa"));
    }

    @Test
    public void testBuscarPalabraNoExistente() {
        NodoTrie<String> raiz = new NodoTrie<>('\0');
        raiz.insertar("casa", "casa");
        Entry<String> entry = raiz.buscar("perro");
        assertFalse(entry.esPalabra());
    }

    @Test
    public void testBuscarPrefijoNoEsPalabra() {
        NodoTrie<String> raiz = new NodoTrie<>('\0');
        raiz.insertar("casa", "casa");
        Entry<String> entry = raiz.buscar("cas");
        assertFalse(entry.esPalabra());
    }

    @Test
    public void testPredecirDesdeNodo() {
        NodoTrie<String> raiz = new NodoTrie<>('\0');
        raiz.insertar("casa", "casa");
        raiz.insertar("casita", "casita");
        raiz.insertar("perro", "perro");

        List<Entry<String>> resultado = raiz.predecir("cas");

        assertEquals(2, resultado.size());
    }

    @Test
    public void testPredecirPrefijoInexistenteDevuelveVacio() {
        NodoTrie<String> raiz = new NodoTrie<>('\0');
        raiz.insertar("casa", "casa");
        List<Entry<String>> resultado = raiz.predecir("xyz");
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testRecorrerVisitaTodas() {
        NodoTrie<String> raiz = new NodoTrie<>('\0');
        raiz.insertar("casa", "casa");
        raiz.insertar("perro", "perro");

        List<String> visitadas = new java.util.ArrayList<>();
        raiz.recorrer(entry -> visitadas.add(entry.getPalabra()));

        assertEquals(2, visitadas.size());
        assertTrue(visitadas.contains("casa"));
        assertTrue(visitadas.contains("perro"));
    }

    @Test
    public void testEsPalabraYGetDato() {
        NodoTrie<String> raiz = new NodoTrie<>('\0');
        assertFalse(raiz.esPalabra());
        assertNull(raiz.getDato());
    }
}