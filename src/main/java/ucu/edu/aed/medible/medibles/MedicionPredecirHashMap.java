package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.Map;

public class MedicionPredecirHashMap extends Medible<String> {

    private final Map<String, String> map;

    public MedicionPredecirHashMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            for (String clave : map.keySet()) {
                clave.startsWith(prefijo);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.map;
    }
}