package movil.baudinogl.com.ar.melichallenge.synchronization.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SellerReputation implements Serializable {

    private Transactions transactions;

    @SerializedName("power_seller_status")
    private String powerSellerStatus;

    public SellerReputation() {
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public String getPowerSellerStatus() {
        String result = powerSellerStatus;
        if (powerSellerStatus != null && !powerSellerStatus.isEmpty()) {
            result = powerSellerStatus.substring(0, 1).toUpperCase() + powerSellerStatus.substring(1);
        }
        return result;
    }
}
