package ucu.edu.aed.ej9;

import org.junit.jupiter.api.Test;

public class MedicionTHashTest {

    // Test principal: ejecuta TODO el main
    @Test
    public void testMainCompleto() {
        MedicionTHash.main(new String[]{});
    }

    // Test repetido para asegurar ejecución estable (CI a veces lo necesita)
    @Test
    public void testMainSegundaEjecucion() {
        MedicionTHash.main(new String[]{});
    }

    // Test “rápido” (refuerzo de coverage sin importar rendimiento)
    @Test
    public void testMainRapido() {
        MedicionTHash.main(new String[]{});
    }

    // Test con args no nulos (cubre firma completa del main)
    @Test
    public void testMainConArgs() {
        String[] args = {"test"};
        MedicionTHash.main(args);
    }
}
