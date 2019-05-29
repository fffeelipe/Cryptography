
package Logic;

import java.util.Comparator;

/**
 *
 * @author gabriel
 */
public class BloqueComparadorPorTransiciones implements Comparator<Bloque> {
    @Override
    public int compare(Bloque b1, Bloque b2) {
        // for example - sort ascending by ID
        if (b1.transicionBN+b1.transicionNB == b2.transicionBN+b2.transicionNB)return 0;
        if(b1.transicionBN+b1.transicionNB < b2.transicionBN+b2.transicionNB)
            return 1;
        return -1;
    }
}