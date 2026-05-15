package ucu.edu.aed.ej11.clases;

import java.util.HashMap;
import java.util.Map;

public class ContadorPalabras {
    private Map<String,Integer> diccionario = new HashMap<>();
    private Normalizador normalizador = new Normalizador();

    public void agregar(String cadena){
        cadena = normalizador.sanitizar(cadena);
        String[] listaPalabras = cadena.split("_");
        for (String palabra : listaPalabras) {
            diccionario.put(palabra, diccionario.getOrDefault(palabra,0)+1);
        }
    }

    public void graficar(){
        diccionario.entrySet()
            .stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .limit(10)
            .forEach(System.out::println);
    }
}
