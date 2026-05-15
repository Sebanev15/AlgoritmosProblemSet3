package ucu.edu.aed.medible;


import ucu.edu.aed.ej16.ArbolGenealogico;
import ucu.edu.aed.ej16.Persona;
import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.medible.lib.Medicion;
import ucu.edu.aed.medible.medibles.MedicionBuscarArrayList;
import ucu.edu.aed.medible.medibles.MedicionBuscarHashMap;
import ucu.edu.aed.medible.medibles.MedicionBuscarLinkedList;
import ucu.edu.aed.medible.medibles.MedicionBuscarTreeMap;
import ucu.edu.aed.tda.generic_trie.ImplArbolGenerico;
import ucu.edu.aed.tda.generic_trie.ImplNodoGenerico;
import ucu.edu.aed.tda.trie.TTrie;
//import ucu.edu.aed.tda.trie.impl.Trie;
import ucu.edu.aed.utils.FileUtils;
import java.util.*;

public class Main {

    private static final int REPETICIONES = 20;

    public static void main(String[] args) {
        //TTrie<String> trie = new Trie<>();
        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        List<String> palabrasParaAgregar = new LinkedList<>();
        List<String> palabrasParaBuscar = new LinkedList<>();
        FileUtils.leerLineas("./ut03/listado-general-desordenado.txt", palabrasParaAgregar::add);
        FileUtils.leerLineas("./ut03/listado-general-palabrasBuscar.txt", palabrasParaBuscar::add);

        for (String p : palabrasParaAgregar) {
            // insertar la palabra p en el trie
            // TODO 
            // insertar la palabra p en el linkedList
            linkedList.add(p);
            // insertar la palabra p en el arrayList
            arrayList.add(p);
            // insertar la palabra p en el hashMap
            hashMap.put(p, p);
            // insertar la palabra p en el treeMap
            treeMap.put(p, p);
        }

        List<Medible<List<String>>> medibles = new LinkedList<>();
        medibles.add(new MedicionBuscarLinkedList(linkedList));

        // TODO implementar MedicionBuscarArrayList
        //medibles.add(new MedicionBuscarArrayList(arrayList));

        // TODO implementar MedicionBuscarTrie
        // medibles.add(new MedicionBuscarTrie(trie));
        medibles.add(new MedicionBuscarHashMap(hashMap));
        medibles.add(new MedicionBuscarTreeMap(treeMap));

        StringBuilder sb = new StringBuilder();
        sb.append("algoritmo,tiempo,memoria\n");

        for (Medible<List<String>> m : medibles) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            mi.print();
            sb
                    .append(mi.toCSV())
                    .append("\n");
        }

        FileUtils.escribirLineas("./salida.csv", sb.toString());

        
        // Ejemplo de uso del Ejercicio 16
        Persona raiz = new Persona("Abuelo", 1960);
        ArbolGenealogico arbolGenealogico = (new ArbolGenealogico(raiz));
        
        // Hijos
        Persona hijo1 = new Persona("Primer Hijo", 1983);
        arbolGenealogico.arbol.agregarHijo(raiz, hijo1);
        Persona hijo2 = new Persona("Segundo Hijo", 1986);
        arbolGenealogico.arbol.agregarHijo(raiz, hijo2);
        Persona hijo3 = new Persona("Tercer Hijo", 1986);
        arbolGenealogico.arbol.agregarHijo(raiz, hijo3);

        // Nietos
        Persona nieto1 = new Persona("Primer Nieto", 2005);
        arbolGenealogico.arbol.agregarHijo(hijo1, nieto1);
        Persona nieto2 = new Persona("Segundo Nieto", 2005);
        arbolGenealogico.arbol.agregarHijo(hijo1, nieto2);
        Persona nieto3 = new Persona("Tercer Nieto", 2007);
        arbolGenealogico.arbol.agregarHijo(hijo2, nieto3);
        Persona nieto4 = new Persona("Cuarto Nieto", 2009);
        arbolGenealogico.arbol.agregarHijo(hijo2, nieto4);
        Persona nieto5 = new Persona("Quinto Nieto", 2011);
        arbolGenealogico.arbol.agregarHijo(hijo3, nieto5);
        Persona nieto6 = new Persona("Sexto Nieto", 2013);
        arbolGenealogico.arbol.agregarHijo(hijo3, nieto6);

        System.out.println("Descendientes desde la raíz: " + arbolGenealogico.listarDescendientes(raiz).toString());
        System.out.println("Altura del árbol: " + arbolGenealogico.altura());
        System.out.println("Personas en generación 2: " + arbolGenealogico.obtenerGeneracion(2).toString());
        System.out.println("Ancestro común: " + arbolGenealogico.ancestroComun(hijo1, nieto5).toString());
        System.out.println("El Cuarto Nieto es descendiente del Abuelo (raíz)? " + arbolGenealogico.esDescendiente(raiz, nieto4));
    }
}