package movil.baudinogl.com.ar.melichallenge.synchronization.model;

import java.io.Serializable;

public class Paging implements Serializable {

    private int total;

    private int offset;

    public Paging() {
    }

    public int getTotal() {
        return total;
    }

    public int getOffset() {
        return offset;
    }
}
