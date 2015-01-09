package crazy8s.deck;

/**
 *
 * @author brendon
 */

import java.util.ArrayList;

public class Pile<T> extends ArrayList<T> {
    
    public T top() {
        if(!this.isEmpty())
            return this.get(this.size()-1);
        else
            throw new IndexOutOfBoundsException();
    }
    
    public T bottom() {
        if(!this.isEmpty())
            return this.get(0);
        else
            throw new IndexOutOfBoundsException();
    }
}
