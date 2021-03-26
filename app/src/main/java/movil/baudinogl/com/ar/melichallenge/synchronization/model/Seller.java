package movil.baudinogl.com.ar.melichallenge.synchronization.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Seller implements Serializable {

    private int id;

    private String permalink;

    @SerializedName("seller_reputation")
    private SellerReputation sellerReputation;

    public Seller() {
    }

    public int getId() {
        return id;
    }

    public SellerReputation getSellerReputation() {
        return sellerReputation;
    }

    public String getPermalink() {
        return permalink;
    }
}
