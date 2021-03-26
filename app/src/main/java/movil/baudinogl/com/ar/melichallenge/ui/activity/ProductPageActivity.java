package movil.baudinogl.com.ar.melichallenge.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import movil.baudinogl.com.ar.melichallenge.R;
import movil.baudinogl.com.ar.melichallenge.core.App;
import movil.baudinogl.com.ar.melichallenge.databinding.ActivityProductPageBinding;
import movil.baudinogl.com.ar.melichallenge.synchronization.ImageRequester;
import movil.baudinogl.com.ar.melichallenge.synchronization.model.Result;

import static movil.baudinogl.com.ar.melichallenge.R.id.favorite;
import static movil.baudinogl.com.ar.melichallenge.R.id.search;
import static movil.baudinogl.com.ar.melichallenge.R.id.shopping;

public class ProductPageActivity extends AppCompatActivity {

    @Inject
    ImageRequester imageRequester;

    private ActivityProductPageBinding binding;
    private Result item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        initializeComponents();
        initializeListener();
        setDetail();
    }

    public void buy(View view) {
        showNotification(getString(R.string.buy));
    }

    private void initializeComponents() {
        binding = ActivityProductPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBar);
        binding.btnBuy.setBackgroundColor(getColor(R.color.colorAccent));

        ((App) getApplication()).getAppComponent().inject(this);

        item = (Result) getIntent().getExtras().getSerializable(ProductPageActivity.class.getSimpleName());
    }

    private void initializeListener() {
        binding.toolBar.setNavigationOnClickListener(v -> finish());
    }

    private void setDetail() {
        String condition = String.format(getString(R.string.condition),
                item.getCondition(),
                item.getSeller().getSellerReputation().getTransactions().getCompleted());
        binding.tvCondition.setText(condition);
        binding.tvProductTitle.setText(item.getTitle());
        imageRequester.setImageFromUrl(binding.nivProduct, item.getThumbnail());
        binding.tvProductPrice.setText(item.getPriceFormat());
        String acceptsMercadopago = item.isAcceptsMercadopago() ? getString(R.string.accepts) : getString(R.string.not_accepts);
        binding.tvAcceptsMercadopago.setText(String.format(getString(R.string.accepts_mercadopago), acceptsMercadopago));
        binding.tvUbicationDetail.setText(item.getSellerAddress().toString());
        String sellerStatus = item.getSeller().getSellerReputation().getPowerSellerStatus();
        binding.tvSellerStatus.setText(String.format(getString(R.string.seller_status), sellerStatus));
        String permalink = item.getSeller().getPermalink();
        binding.tvSellerDetail.setText(String.format(getString(R.string.seller_detail_url), permalink));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case favorite:
                showNotification(getString(R.string.favorite));
                return true;
            case search:
                showNotification(getString(R.string.search));
                return true;
            case shopping:
                showNotification(getString(R.string.shopping));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showNotification(String msj) {
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
    }
}