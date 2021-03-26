package movil.baudinogl.com.ar.melichallenge.synchronization;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import movil.baudinogl.com.ar.melichallenge.R;
import movil.baudinogl.com.ar.melichallenge.synchronization.model.ResponseSearch;

import static com.android.volley.Request.Method.GET;

@Singleton
public class ApiMELI {

    private final Context context;
    private final Gson gson;
    private final MutableLiveData<ResponseSearch> searchMutableLiveData;
    private final RequestQueue requestQueue;

    @Inject
    public ApiMELI(Context context,
                   RequestQueue requestQueue,
                   Gson gson,
                   MutableLiveData<ResponseSearch> searchMutableLiveData) {
        this.context = context;
        this.requestQueue = requestQueue;
        this.gson = gson;
        this.searchMutableLiveData = searchMutableLiveData;
    }

    public void search(String request, int offset) {
        String url = String.format(context.getString(R.string.urlML), request, offset);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(GET, url, null,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response.toString());
                        ResponseSearch responseSearch = gson
                                .fromJson(jsonResponse.toString(), ResponseSearch.class);
                        searchMutableLiveData.postValue(responseSearch);
                    } catch (JSONException ignored) {
                        error();
                    }
                },
                error -> error()
        );
        requestQueue.add(jsonObjectRequest);
    }

    public MutableLiveData<ResponseSearch> getSearchMutableLiveData() {
        return searchMutableLiveData;
    }

    private void error() {
        Toast.makeText(context, context.getString(R.string.searchError), Toast.LENGTH_LONG).show();
    }
}
