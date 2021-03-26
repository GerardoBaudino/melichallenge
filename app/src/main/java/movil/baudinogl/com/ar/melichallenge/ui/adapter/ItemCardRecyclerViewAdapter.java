package movil.baudinogl.com.ar.melichallenge.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import movil.baudinogl.com.ar.melichallenge.databinding.ItemCardBinding;
import movil.baudinogl.com.ar.melichallenge.synchronization.ImageRequester;
import movil.baudinogl.com.ar.melichallenge.synchronization.model.Result;

@Singleton
public class ItemCardRecyclerViewAdapter extends RecyclerView.Adapter<ItemCardViewHolder> {

    private final ImageRequester imageRequester;
    private final List<Result> items;

    private RecyclerItemClick itemClick;
    private ItemCardBinding binding;

    @Inject
    public ItemCardRecyclerViewAdapter(ImageRequester imageRequester) {
        this.imageRequester = imageRequester;
        this.items = new ArrayList<>();
    }

    public void setItems(List<Result> items) {
        clear();
        this.items.addAll(items);
    }

    public void clear() {
        this.items.clear();
    }

    public void setItemClick(RecyclerItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ItemCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCardBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ItemCardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCardViewHolder holder, int position) {
        final Result item = items.get(position);
        holder.itemView.setOnClickListener(v -> itemClick.itemClick(item));
        render(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void render(Result item) {
        binding.tvTitle.setText(item.getTitle());
        binding.tvPrice.setText(item.getPriceFormat());
        imageRequester.setImageFromUrl(binding.nivItem, item.getThumbnail());
    }
}