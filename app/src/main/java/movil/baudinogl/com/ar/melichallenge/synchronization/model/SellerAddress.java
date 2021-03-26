package movil.baudinogl.com.ar.melichallenge.synchronization.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.MessageFormat;

public class SellerAddress implements Serializable {

    private Country country;

    private State state;

    private City city;

    public SellerAddress() {
    }

    public Country getCountry() {
        return country;
    }

    public State getState() {
        return state;
    }

    public City getCity() {
        return city;
    }

    @NonNull
    @Override
    public String toString() {
        return MessageFormat.format("{0}, {1} {2}", city.getName(), state.getName(), country.getName());
    }
}
