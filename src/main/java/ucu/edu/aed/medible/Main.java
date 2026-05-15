package ucu.edu.aed.medible;


import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.medible.lib.Medicion;
import ucu.edu.aed.medible.medibles.MedicionBuscarArrayList;
import ucu.edu.aed.medible.medibles.MedicionBuscarHashMap;
import ucu.edu.aed.medible.medibles.MedicionBuscarLinkedList;
import ucu.edu.aed.medible.medibles.MedicionBuscarTreeMap;
import ucu.edu.aed.medible.medibles.MedicionPredecirHashMap;
import ucu.edu.aed.medible.medibles.MedicionPredecirLinkedList;
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
        medibles.add(new MedicionBuscarArrayList(arrayList));
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

        //PARTE 5

        List<Medible<String>> mediblesPredecir = new LinkedList<>();
        // TODO mediblesPredecir.add(new MedicionPredecirTrie(trie));
        mediblesPredecir.add(new MedicionPredecirLinkedList(linkedList));
        mediblesPredecir.add(new MedicionPredecirHashMap(hashMap));

        StringBuilder sb2 = new StringBuilder();
        sb2.append("algoritmo,tiempo,memoria\n");

        for (Medible<String> m : mediblesPredecir) {
            Medicion mi = m.medir(20, "cas");
            mi.print();
            sb2.append(mi.toCSV()).append("\n");
        }

        FileUtils.escribirLineas("./salida-predecir.csv", sb2.toString());
    }
    
}