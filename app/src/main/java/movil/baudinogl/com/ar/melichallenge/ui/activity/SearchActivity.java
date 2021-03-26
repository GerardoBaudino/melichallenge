package movil.baudinogl.com.ar.melichallenge.ui.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;

import javax.inject.Inject;

import movil.baudinogl.com.ar.melichallenge.R;
import movil.baudinogl.com.ar.melichallenge.core.App;
import movil.baudinogl.com.ar.melichallenge.databinding.ActivitySearchBinding;
import movil.baudinogl.com.ar.melichallenge.synchronization.ApiMELI;
import movil.baudinogl.com.ar.melichallenge.synchronization.model.Paging;
import movil.baudinogl.com.ar.melichallenge.synchronization.model.Result;
import movil.baudinogl.com.ar.melichallenge.ui.adapter.GridItemDecoration;
import movil.baudinogl.com.ar.melichallenge.ui.adapter.ItemCardRecyclerViewAdapter;
import movil.baudinogl.com.ar.melichallenge.ui.adapter.RecyclerItemClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class SearchActivity extends AppCompatActivity
        implements RecyclerItemClick, SearchView.OnQueryTextListener {

    @Inject
    ItemCardRecyclerViewAdapter adapter;
    @Inject
    GridItemDecoration decoration;
    @Inject
    ApiMELI apiMELI;

    private ActivitySearchBinding binding;
    private String query;
    private int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initializeComponents();
        initializeRecycleView();
        initializeListener();
        observerSearch();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void itemClick(Result item) {
        Intent intent = new Intent(this, ProductPageActivity.class);
        intent.putExtra(ProductPageActivity.class.getSimpleName(), item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.query = query;
        offset = 0;
        search();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        return false;
    }

    public void previous(View view) {
        offset--;
        if (offset >= 0) {
            search();
        } else {
            offset = 0;
        }
    }

    public void following(View view) {
        offset++;
        search();
    }

    private void initializeComponents() {
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((App) getApplication()).getAppComponent().inject(this);
    }

    private void initializeRecycleView() {
        binding.rvResult.setHasFixedSize(true);
        binding.rvResult.setLayoutManager(
                new GridLayoutManager(this, 2, VERTICAL, false)
        );
        adapter.setItemClick(this);
        binding.rvResult.setAdapter(adapter);
        decoration.setLargePadding(getResources().getDimensionPixelSize(R.dimen.dim_4dp));
        decoration.setSmallPadding(getResources().getDimensionPixelSize(R.dimen.dim_4dp));
        binding.rvResult.addItemDecoration(decoration);
    }

    private void initializeListener() {
        binding.svSearch.setOnQueryTextListener(this);
    }

    private void search() {
        adapter.clear();
        adapter.notifyDataSetChanged();
        binding.iStartMessage.rlMessage.setVisibility(GONE);
        if (isNetworkConnected()) {
            binding.iConnectionError.rlConnectionError.setVisibility(GONE);
            binding.pb.setVisibility(VISIBLE);
            apiMELI.search(query, offset);
        } else {
            binding.rlResultDetail.setVisibility(GONE);
            binding.iConnectionError.rlConnectionError.setVisibility(VISIBLE);
        }
    }

    private boolean isNetworkConnected() {
        boolean result = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            result = activeNetworkInfo != null
                    && activeNetworkInfo.isConnected()
                    && (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE
                    || activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI);
        }
        return result;
    }

    private void observerSearch() {
        apiMELI.getSearchMutableLiveData().observe(this, responseSearch -> {
            binding.pb.setVisibility(GONE);
            showDetail(responseSearch.getPaging());
            adapter.setItems(responseSearch.getResults());
            adapter.notifyDataSetChanged();
        });
    }

    private void showDetail(Paging paging) {
        binding.rlResultDetail.setVisibility(VISIBLE);
        binding.tvResult.setText(String.format(getString(R.string.searchSize), paging.getTotal()));
        int offset = paging.getOffset();
        if (offset > 0) {
            binding.tvPrevious.setVisibility(VISIBLE);
        } else {
            binding.tvPrevious.setVisibility(GONE);
        }
        binding.tvPag.setText(String.format(getString(R.string.pag), ++offset));
    }
}