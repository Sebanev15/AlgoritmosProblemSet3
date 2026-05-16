package ucu.edu.aed.ej15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {
    Libro libro1 = new Libro("12345", "El gran libro", "Pedro", 1605);
    Libro libro2 = new Libro("12345", "El gran libro. Segunda edicion", "Pedro", 1605);
    Libro libro3 = new Libro("54321", "Cien Años de Soledad", "Gabriel García Márquez", 1967);

    @Test
    public void EqualsInSameLibrosTest(){
        assertEquals(libro1, libro2);
    }

    @Test
    public void EqualsWithSameObjectTest(){
        assertEquals(libro1, libro1);
    }

    @Test
    public void EqualsWithEmptyObjectTest(){
        assertNotEquals(libro1, null);
    }

    @Test
    public void EqualsWithDifferentClassTest(){
        assertNotEquals(libro1, "No soy un libro");
    }

    @Test
    public void EqualsInDifferentLibrosTest(){
        assertNotEquals(libro1, libro3);
    }

    @Test
    public void HashCodeInSameLibrosTest(){
        assertEquals(libro1.hashCode(), libro2.hashCode());
    }

    @Test
    public void HashCodeInDifferentLibrosTest(){
        assertNotEquals(libro1.hashCode(), libro3.hashCode());
    }
}
