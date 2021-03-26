package movil.baudinogl.com.ar.melichallenge.ui.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int largePadding;
    private int smallPadding;

    @Inject
    public GridItemDecoration() {
    }

    public void setLargePadding(int largePadding) {
        this.largePadding = largePadding;
    }

    public void setSmallPadding(int smallPadding) {
        this.smallPadding = smallPadding;
    }

    @Override
    public void getItemOffsets(Rect rect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        rect.left = smallPadding;
        rect.right = smallPadding;
        rect.top = largePadding;
        rect.bottom = largePadding;
    }
}
