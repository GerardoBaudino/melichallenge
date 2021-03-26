package movil.baudinogl.com.ar.melichallenge.synchronization.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transactions implements Serializable {

    private int completed;

    public Transactions() {
    }

    public int getCompleted() {
        return completed;
    }
}
