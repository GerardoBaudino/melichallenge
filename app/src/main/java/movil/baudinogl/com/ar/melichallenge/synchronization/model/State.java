package movil.baudinogl.com.ar.melichallenge.synchronization.model;

import java.io.Serializable;

public class State implements Serializable {

    private String id;

    private String name;

    public State() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
