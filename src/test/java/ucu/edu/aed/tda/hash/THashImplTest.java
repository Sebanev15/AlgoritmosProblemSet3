package ucu.edu.aed.tda.hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class THashImplTest {

    @Test
    public void testInsertarYBuscar() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        boolean resultado = hash.insertar(1, "uno", new Report());
        assertTrue(resultado);

        String valor = hash.buscar(1, new Report());
        assertEquals("uno", valor);
    }

    @Test
    public void testBuscarInexistente() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        String valor = hash.buscar(99, new Report());
        assertNull(valor);
    }

    @Test
    public void testDelete() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        hash.insertar(5, "cinco", new Report());

        boolean eliminado = hash.delete(5, new Report());
        assertTrue(eliminado);

        assertNull(hash.buscar(5, new Report()));
    }

    @Test
    public void testEsVacio() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        assertTrue(hash.esVacio());

        hash.insertar(1, "uno", new Report());
        assertFalse(hash.esVacio());
    }

    @Test
    public void testVaciar() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        hash.insertar(1, "uno", new Report());
        hash.vaciar();

        assertTrue(hash.esVacio());
    }

    @Test
    public void testKeys() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        hash.insertar(1, "uno", new Report());
        hash.insertar(2, "dos", new Report());

        int cantidad = 0;
        for (Integer k : hash.keys()) {
            cantidad++;
        }

        assertEquals(2, cantidad);
    }

    @Test
    public void testValues() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        hash.insertar(1, "uno", new Report());
        hash.insertar(2, "dos", new Report());

        int cantidad = 0;
        for (String v : hash.values()) {
            cantidad++;
        }

        assertEquals(2, cantidad);
    }

    @Test
    public void testEntries() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        hash.insertar(1, "uno", new Report());

        int cantidad = 0;
        for (Entry<Integer, String> e : hash.entries()) {
            cantidad++;
        }

        assertEquals(1, cantidad);
    }

    // ---------------- EXTRA TESTS PARA SUBIR COVERAGE ----------------

    @Test
    public void testColisionInsertar() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        hash.insertar(1, "uno", new Report());
        hash.insertar(11, "once", new Report());

        assertEquals("uno", hash.buscar(1, new Report()));
        assertEquals("once", hash.buscar(11, new Report()));
    }

    @Test
    public void testDeleteInexistente() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        assertFalse(hash.delete(999, new Report()));
    }

    @Test
    public void testBuscarEnVacio() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        assertNull(hash.buscar(1, new Report()));
    }

   

    @Test
    public void testVaciarYBuscar() {
        THashimpl<Integer, String> hash = new THashimpl<>(10);

        hash.insertar(1, "uno", new Report());
        hash.vaciar();

        assertNull(hash.buscar(1, new Report()));
        assertTrue(hash.esVacio());
    }
}