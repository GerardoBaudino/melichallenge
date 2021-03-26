package movil.baudinogl.com.ar.melichallenge.synchronization;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImageRequester {
    private final Context context;
    private final RequestQueue requestQueue;
    private final int maxByteSize;

    private ImageLoader imageLoader;

    @Inject
    public ImageRequester(Context context, RequestQueue requestQueue) {
        this.context = context;
        this.requestQueue = requestQueue;
        this.requestQueue.start();
        this.maxByteSize = calculateMaxByteSize();
        setImageLoader();
    }

    public void setImageLoader() {
        this.imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> lruCache =
                    new LruCache<String, Bitmap>(maxByteSize) {
                        @Override
                        protected int sizeOf(String url, Bitmap bitmap) {
                            return bitmap.getByteCount();
                        }
                    };

            @Override
            public synchronized Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public synchronized void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url, bitmap);
            }
        });
    }

    public void setImageFromUrl(NetworkImageView networkImageView, String url) {
        networkImageView.setImageUrl(url, imageLoader);
    }

    private int calculateMaxByteSize() {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int screenBytes = displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
        return screenBytes * 3;
    }
}
