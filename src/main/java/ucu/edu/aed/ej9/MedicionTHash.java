package ucu.edu.aed.ej9;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.tda.hash.Report;
import ucu.edu.aed.tda.hash.THashimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MedicionTHash {
    public static void main(String[] args){
         // Factores de carga pedidos
        double[] factores = {
                0.70, 0.75, 0.80, 0.85,
                0.90, 0.91, 0.92, 0.93,
                0.94, 0.95, 0.96, 0.97,
                0.98, 0.99
        };

        // Tamaño de la tabla
        int tamañoTabla = 1000;

        // Recorrer cada factor
        for (double factor : factores) {

            // Crear nueva tabla hash
            THashimpl<Integer, Integer> hash =
                    new THashimpl<>(tamañoTabla);

            Random random = new Random();

            // Lista para guardar claves insertadas
            List<Integer> clavesInsertadas =
                    new ArrayList<>();

            // Cantidad de elementos a insertar
            int elementosAInsertar =
                    (int) (tamañoTabla * factor);
                      // Variables para contar comparaciones
            int totalInsercion = 0;
            int totalBusquedaExitosa = 0;
            int totalBusquedaFallida = 0;
            // insertamos las claves  que se necsiten
            for (int i = 0; i < elementosAInsertar; i++) {

                int clave =random.nextInt(100000);

                clavesInsertadas.add(clave);

                Report reporte = new Report();

                hash.insertar(clave, clave, reporte);

                totalInsercion +=reporte.getCantidadComparaciones();
            }
            // busqueda exitosa
            for (Integer clave : clavesInsertadas) {

                Report reporte = new Report();

                hash.buscar(clave, reporte);

                totalBusquedaExitosa +=reporte.getCantidadComparaciones();
            }
            // busqueda fallida
            for (int i = 0; i < elementosAInsertar; i++) {
                 // Clave que no fue insertada
                int claveInexistente =random.nextInt(100000) + 200000;

                Report reporte = new Report();

                hash.buscar(claveInexistente, reporte);

                totalBusquedaFallida +=reporte.getCantidadComparaciones();
            }
            // promedios
            double promedioInsercion =(double) totalInsercion / elementosAInsertar;

            double promedioBusquedaExitosa =(double) totalBusquedaExitosa / elementosAInsertar;

            double promedioBusquedaFallida =(double) totalBusquedaFallida / elementosAInsertar;
            // Imprimir resultados
            System.out.println(
                    "Factor: " + factor +
                    " | Inserción: " + promedioInsercion +
                    " | Exitosa: " + promedioBusquedaExitosa +
                    " | Fallida: " + promedioBusquedaFallida
            );
        }
        
    }
}
