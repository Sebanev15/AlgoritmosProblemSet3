package ucu.edu.aed.tda.hash;

import java.util.ArrayList;
import java.util.List;

public class THashimpl <K,V> extends THash<K,V> {
    public THashimpl(int elementosEsperados) {
        super(elementosEsperados);
    }

    @Override
    public V buscar(K clave, Report report) {
        int tabla = functionHashing(clave);
        for(int j =0; j < hashTable.length; j++){
           report.setCantidadComparaciones(report.getCantidadComparaciones() + 1);
           int posicion= (tabla + (j*j)) % hashTable.length;
            if(hashTable[posicion] == null){
                return null;
            }
            if (hashTable[posicion].getClave().equals(clave) && !hashTable[posicion].isLoteLibre()){
                return hashTable[posicion].getValor();
            }
            
        }
        return null;
    }

    @Override
    public boolean delete(K clave, Report report) {
     int tabla=functionHashing(clave);
        for(int j =0; j < hashTable.length; j++){
              report.setCantidadComparaciones(report.getCantidadComparaciones() + 1);
              int posicion= (tabla + (j*j)) % hashTable.length;
            if(hashTable[posicion] == null){
                return false;
            }
            if (hashTable[posicion].getClave().equals(clave) && !hashTable[posicion].isLoteLibre()){
                hashTable[posicion].setLoteLibre(true);
                return true;
            }
        } 
        return false; 
    }

    @Override
    public boolean insertar(K clave, V valor, Report report) {
        int tabla = functionHashing(clave);
        for (int i = 0; i < hashTable.length; i++){
            int posicion = (tabla + (i*i)) % hashTable.length;
            report.setCantidadComparaciones(report.getCantidadComparaciones() + 1);

            if (hashTable[posicion] == null || hashTable[posicion].isLoteLibre()){
                hashTable[posicion] = new TNodoHash<>(clave, valor);
                hashTable[posicion].setLoteLibre(false);
                return true;
            }
        }
        return false;
        
    }

    @Override
    protected int functionHashing(K valor) {
        return valor.hashCode() % hashTable.length;
    }

    @Override
    public boolean esVacio() {
        for (TNodoHash<K, V> nodoHash : hashTable) {
            if (nodoHash != null) {
                return false;
            }
        }
        return true;
    }
    

    @Override
    public void vaciar() {
        for (int i = 0; i < hashTable.length; i++){
            hashTable[i] = null;
        }
    }

    @Override
    protected int calcularCapacidadOptima(int elementosEsperados) {
        if(elementosEsperados > 0) {
            int tamaño = (int) (elementosEsperados / 0.5);
            return tamaño;
        }
        return 0;
    }

    @Override
    protected boolean redimensionar() {
        int elementosEsperados = 0;
        for (TNodoHash nodoHash : hashTable){
            if (nodoHash != null){
                elementosEsperados++;
            }
        }

        double factorDeCarga = (double)elementosEsperados / hashTable.length;
        if (factorDeCarga <= 0.5) {
            return false;
        }
        TNodoHash<K, V>[] tabla = new TNodoHash[calcularCapacidadOptima(elementosEsperados)];

        int posicion = 0;
        for (TNodoHash nodoHash : hashTable){
            posicion++;
            if (nodoHash != null){
                tabla[posicion] = nodoHash;
            }
        }
        hashTable = tabla;
        return true;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
         List<Entry<K, V>> lista = new ArrayList<>();
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                lista.add(new Entry<K, V>(hashTable[i].getClave(), hashTable[i].getValor()));
            }
        }
        return lista;
    }

    @Override
    public Iterable<K> keys() {
           List<K> lista = new ArrayList<>();
        for (int i = 0; i < hashTable.length; i++) {
            if(hashTable[i] != null){
                lista.add(hashTable[i].getClave());
            }
        }
        return lista;
    }

    @Override
    public Iterable<V> values() {
        List<V> lista = new ArrayList<>();
        for (int i = 0; i < hashTable.length; i++) {
            if(hashTable[i] != null){
                lista.add(hashTable[i].getValor());
            }
        }
        return lista;
    }
}
