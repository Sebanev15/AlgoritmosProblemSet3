package ucu.edu.aed.ej11.clases;

import java.util.List;

public class Normalizador {

public String sanitizar(String cadena){
    cadena = cadena.toLowerCase();
    cadena = cadena.replace("!", "");
    cadena = cadena.replace("?", "");
    cadena = cadena.replace("¡", "");
    cadena = cadena.replace("¿", "");
    cadena = cadena.replace("*", "");
    cadena = cadena.replace("@", "");
    cadena = cadena.replace("#", "");
    cadena = cadena.replace("$", "");
    cadena = cadena.replace("%", "");
    cadena = cadena.replace("&", "");
    cadena = cadena.replace("/", "");
    cadena = cadena.replace("(", "");
    cadena = cadena.replace(")", "");
    cadena = cadena.replace("=", "");
    cadena = cadena.replace("{", "");
    cadena = cadena.replace("}", "");
    cadena = cadena.replace("'", "");
    cadena = cadena.replace("-", "");
    cadena = cadena.replace("_", "");
    cadena = cadena.replace("[", "");
    cadena = cadena.replace("]", "");
    cadena = cadena.replace("+", "");
    cadena = cadena.replace(".", "");
    cadena = cadena.replace(",", "");
    cadena = cadena.replace(":", "");
    cadena = cadena.replace(";", "");
    cadena = cadena.replace("|", "");
    cadena = cadena.replace("°", "");
    cadena = cadena.replace("¬", "");
    cadena = cadena.replace("''", "");
    cadena = cadena.replace("”", "");
    cadena = cadena.replace("“", "");
    cadena = cadena.replace("’", "");
    cadena = cadena.replace("\n", "");
    cadena = cadena.replace("\r", "");
    cadena = cadena.replace(" ", "_");

    System.out.println(cadena);
    return cadena;
}
}
