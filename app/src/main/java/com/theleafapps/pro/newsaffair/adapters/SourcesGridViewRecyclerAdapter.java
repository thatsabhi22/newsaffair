package com.theleafapps.pro.newsaffair.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.theleafapps.pro.newsaffair.R;
import com.theleafapps.pro.newsaffair.models.Source;
import com.theleafapps.pro.newsaffair.ui.NewsCardsActivity;
import com.theleafapps.pro.newsaffair.utils.MySingleton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aviator on 26/04/16.
 */
public class SourcesGridViewRecyclerAdapter extends
        RecyclerView.Adapter<SourcesGridViewRecyclerAdapter.SourcesGridViewHolder>{

    Context mContext;
    List<Source.SourcesBean> sourceList;
    private ImageLoader mImageLoader;

    public SourcesGridViewRecyclerAdapter(Context context, List<Source.SourcesBean> sourceList) {
        this.sourceList        =   sourceList;
        this.mContext          =   context;
    }

    @Override
    public SourcesGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list_cards, null);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//              TextView name       =   (TextView) v.findViewById(R.id.product_name);
                TextView sourceIdTv  =   (TextView) v.findViewById(R.id.source_id_tv);

//                Toast.makeText(mContext,"Card Clicked -> Product Name : " + name.getText() + " | Product Id ->"
//                        + productId.getText() , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, NewsCardsActivity.class);
                intent.putExtra("sourceId",sourceIdTv.getText());
                mContext.startActivity(intent);

            }
        });

        SourcesGridViewHolder rcv = new SourcesGridViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(SourcesGridViewHolder holder, int position) {

        mImageLoader = MySingleton.getInstance(mContext).getImageLoader();
        holder.sourceImage.setImageUrl(sourceList.get(position).getUrlsToLogos().getSmall(),mImageLoader);
        holder.sourceId.setText(String.valueOf(sourceList.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return this.sourceList.size();
    }

    public class SourcesGridViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.source_image)
        public NetworkImageView sourceImage;

        @BindView(R.id.source_id_tv)
        public TextView sourceId;

        public SourcesGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
//            itemView.setOnClickListener(this);
        }
    }
}
