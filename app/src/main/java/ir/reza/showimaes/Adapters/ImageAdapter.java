package ir.reza.showimaes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.reza.showimaes.Constant.Constant;
import ir.reza.showimaes.Model.Image;
import ir.reza.showimaes.R;


public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Image> image;
    private Context context;
    private final int VIEW_POST_ITEM = 0;
    private final int VIEW_LOADING = 1;

    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount, firstVisibleItem, l;
    boolean isEndReq = true;


    public ImageAdapter( Context context, ArrayList<Image> image, RecyclerView mRecyclerView) {
        this.context = context;
        this.image = image;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }


            }


        });

    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    @Override
    public int getItemViewType(int position) {
        if (image.get(position) != null)
            return VIEW_POST_ITEM;
        else
            return VIEW_LOADING;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == VIEW_POST_ITEM) {
            View view = inflater.inflate(R.layout.layout_item, parent, false);
            return new postViewHolder(view);
        } else if (viewType == VIEW_LOADING) {
            View view = inflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        if (holder instanceof postViewHolder) {
            postViewHolder mPostViewHolder = (postViewHolder) holder;
            mPostViewHolder.txtTitle.setText(image.get(listPosition).getTitle());


            Glide.with(context).load(Constant.BASE_URL + image.get(listPosition).getUrl()).into(mPostViewHolder.img);


        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }


    }


    private String toNewPrice(double zarib, String price) {

        return String.valueOf(Math.round(Integer.valueOf(price) * (float) zarib));

    }


    @Override
    public int getItemCount() {
        if (image == null)
            return 0;
        return image.size();
    }

    static class postViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public ImageView img;

        public postViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);

        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

        }
    }


}