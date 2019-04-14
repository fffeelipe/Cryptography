/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Comparator;

/**
 *
 * @author gabriel
 */
public class BloqueComparadorPorFrecuencia implements Comparator<Bloque> {
    @Override
    public int compare(Bloque b1, Bloque b2) {
        // for example - sort ascending by ID
        if (b1.frecuencia != b2.frecuencia){
            return b1.frecuencia -b2.frecuencia;
        }
        return b1.id.compareTo(b2.id);
    }
}
