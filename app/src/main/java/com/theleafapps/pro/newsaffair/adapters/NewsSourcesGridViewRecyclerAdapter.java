package com.theleafapps.pro.newsaffair.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theleafapps.pro.newsaffair.R;
import com.theleafapps.pro.newsaffair.models.Source;
import com.theleafapps.pro.newsaffair.ui.NewsListCardsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aviator on 26/04/16.
 */
public class NewsSourcesGridViewRecyclerAdapter extends
        RecyclerView.Adapter<NewsSourcesGridViewRecyclerAdapter.SourcesGridViewHolder> {

    Context mContext;
    List<Source.SourcesBean> sourceList;

    public NewsSourcesGridViewRecyclerAdapter(Context context, List<Source.SourcesBean> sourceList) {
        this.sourceList = sourceList;
        this.mContext = context;
    }

    @Override
    public SourcesGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list_cards, null);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView sourceNameTv = (TextView) v.findViewById(R.id.source_name_tv);
                TextView sourceIdTv = (TextView) v.findViewById(R.id.source_id_tv);

//                Toast.makeText(mContext,"Card Clicked -> Product Name : " + name.getText() + " | Product Id ->"
//                        + productId.getText() , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, NewsListCardsActivity.class);
                intent.putExtra("sourceId", sourceIdTv.getText());
                intent.putExtra("sourceName", sourceNameTv.getText());
                mContext.startActivity(intent);

            }
        });

        SourcesGridViewHolder rcv = new SourcesGridViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(SourcesGridViewHolder holder, int position) {

//        mImageLoader = MySingleton.getInstance(mContext).getImageLoader();
//        holder.sourceImage.setImageUrl(sourceList.get(position).getUrlsToLogos().getSmall(),mImageLoader);

        String url = sourceList.get(position).getUrlsToLogos().getSmall();
        if (!TextUtils.isEmpty(url)) {
            Picasso
                    .with(mContext)
                    .load(sourceList.get(position).getUrlsToLogos().getSmall())
                    .into(holder.sourceImage);
        } else {
            // make sure Glide doesn't load anything into this view until told otherwise
//            Glide.clear(holder.newsArticleImage);
            // remove the placeholder (optional); read comments below
            holder.sourceImage.setImageDrawable(null);
        }

        holder.sourceIdTv.setText(String.valueOf(sourceList.get(position).getId()));
        holder.sourceNameTv.setText(String.valueOf(sourceList.get(position).getName()));
        holder.sourceCaptionTv.setText(String.valueOf(sourceList.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return this.sourceList.size();
    }

    public class SourcesGridViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.source_image_view)
        ImageView sourceImage;

        @BindView(R.id.source_id_tv)
        TextView sourceIdTv;

        @BindView(R.id.source_name_tv)
        TextView sourceNameTv;

        @BindView(R.id.source_caption_tv)
        TextView sourceCaptionTv;

        public SourcesGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(this);
        }
    }
}
