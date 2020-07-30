package com.datnt.tikihometest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datnt.tikihometest.R;
import com.datnt.tikihometest.model.QuickLink;
import com.datnt.tikihometest.utilities.LoadImageByUrl;

import java.util.List;

public class QuickLinkAdapter extends RecyclerView.Adapter<QuickLinkAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private QuickLink mQuickLink;

    // data is passed into the constructor
    public QuickLinkAdapter(Context context, QuickLink quickLink) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mQuickLink = quickLink;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_rv_quicklink_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = mQuickLink.getData().get(0).get(position).getImageUrl();
        if(url!=null && !url.isEmpty()){
            LoadImageByUrl load = new LoadImageByUrl(holder.imageView);
            load.execute(url);
        }
        holder.tvTitle.setText(mQuickLink.getData().get(0).get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mQuickLink.getData().get(0).size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView tvTitle;
        ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.rv_item_image);
            tvTitle = itemView.findViewById(R.id.rv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
