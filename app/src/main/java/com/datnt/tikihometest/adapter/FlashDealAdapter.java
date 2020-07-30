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
import com.datnt.tikihometest.model.FlashDeal;
import com.datnt.tikihometest.utilities.LoadImageByUrl;

public class FlashDealAdapter extends RecyclerView.Adapter<FlashDealAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private FlashDeal mFlashDeal;

    public FlashDealAdapter(Context context, FlashDeal flashDeal) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mFlashDeal = flashDeal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_rv_flashdeal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = mFlashDeal.getData().get(position).getProduct().getThumbnailUrl();
        if(url!=null && !url.isEmpty()){
            LoadImageByUrl load = new LoadImageByUrl(holder.imageView);
            load.execute(url);
        }
        holder.tvPrice.setText(mFlashDeal.getData().get(position).getSpecialPrice() + " đ");
    }

    @Override
    public int getItemCount() {
        return mFlashDeal.getData().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView tvPrice;
        ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.rv_flash_deal_item_image);
            tvPrice = itemView.findViewById(R.id.rv_flash_deal_item_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
