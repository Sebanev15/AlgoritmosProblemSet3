package ucu.edu.aed.ej11;

import java.util.function.Consumer;

import ucu.edu.aed.ej11.clases.ContadorPalabras;
import ucu.edu.aed.ej11.clases.Normalizador;
import ucu.edu.aed.utils.FileUtils;

public class Main {
    public static void main (String[] args){
        ContadorPalabras normalizador = new ContadorPalabras();
        Consumer<String> cons = x -> {
            normalizador.agregar(x);
        };
        FileUtils.leerLineas("src\\main\\resources\\ut03\\libro.txt", cons);
        normalizador.graficar();
    }

}
