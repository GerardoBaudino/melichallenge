package movil.baudinogl.com.ar.melichallenge.synchronization.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Result implements Serializable {

    private String id;

    private String title;

    private Seller seller;

    private double price;

    @SerializedName("currency_id")
    private String currencyId;

    private String condition;

    private String thumbnail;

    @SerializedName("accepts_mercadopago")
    private boolean acceptsMercadopago;

    @SerializedName("seller_address")
    private SellerAddress sellerAddress;

    public Result() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Seller getSeller() {
        return seller;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getCondition() {
        if (condition.equals("new")) {
            condition = "Nuevo";
        }
        return condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public boolean isAcceptsMercadopago() {
        return acceptsMercadopago;
    }

    @SerializedName("seller_address")
    public SellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public String getPriceFormat() {
        String currency = "¤";
        if (currencyId.equals("ARS")) {
            currency = "$";
        }
        DecimalFormat decimalFormat = new DecimalFormat(String.format("%s #,###", currency));
        return decimalFormat.format(price).replace(",", ".");
    }
}
