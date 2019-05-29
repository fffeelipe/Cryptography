package Logic;

import java.util.Comparator;

/**
 *
 * @author gabriel
 */
public class BloqueComparadorPorLoops implements Comparator<Bloque> {
    @Override
    public int compare(Bloque b1, Bloque b2) {
        // for example - sort ascending by ID
        if(b1.cicloB+b1.cicloN == b2.cicloB+b2.cicloN) return 0;
        if (b1.cicloB+b1.cicloN < b2.cicloB+b2.cicloN){
            return 1;
        }
        return -1;
    }
}