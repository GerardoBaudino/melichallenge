package movil.baudinogl.com.ar.melichallenge.synchronization.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseSearch implements Serializable {

    @SerializedName("site_id")
    private String siteId;

    private Paging paging;

    private List<Result> results;

    public ResponseSearch() {
    }

    public String getSiteId() {
        return siteId;
    }

    public Paging getPaging() {
        return paging;
    }

    public List<Result> getResults() {
        return results;
    }
}
