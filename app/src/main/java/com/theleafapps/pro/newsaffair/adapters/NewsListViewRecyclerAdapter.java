package com.theleafapps.pro.newsaffair.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theleafapps.pro.newsaffair.R;
import com.theleafapps.pro.newsaffair.models.Article;
import com.theleafapps.pro.newsaffair.ui.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aviator on 26/04/16.
 */
public class NewsListViewRecyclerAdapter extends
        RecyclerView.Adapter<NewsListViewRecyclerAdapter.NewsListViewHolder>{

    Context mContext;
    List<Article.ArticlesBean> newsArticlesList;

    public NewsListViewRecyclerAdapter(Context context, List<Article.ArticlesBean> newsArticlesList) {
        this.newsArticlesList   =   newsArticlesList;
        this.mContext           =   context;
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_cards, null);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView newsUrlTv      =   (TextView) v.findViewById(R.id.news_article_url);

//                Toast.makeText(mContext,"Card Clicked -> Product Name : " + name.getText() + " | Product Id ->"
//                        + productId.getText() , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("newsArticleUrl",newsUrlTv.getText());
                mContext.startActivity(intent);

            }
        });

        NewsListViewHolder rcv = new NewsListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {

//        mImageLoader = MySingleton.getInstance(mContext).getImageLoader();
//        holder.newsArticleImage.setImageUrl(newsArticlesList.get(position).getUrlToImage(),mImageLoader);

        String url = newsArticlesList.get(position).getUrlToImage();
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(mContext)
                    .load(newsArticlesList.get(position).getUrlToImage())
                    .into(holder.newsArticleImage);
        } else {
            // make sure Glide doesn't load anything into this view until told otherwise
//            Glide.clear(holder.newsArticleImage);
            // remove the placeholder (optional); read comments below
            holder.newsArticleImage.setImageDrawable(null);
        }


        holder.newsArticleUrl.setText(String.valueOf(newsArticlesList.get(position).getUrl()));
        holder.newheadlineTv.setText(Html.fromHtml(String.valueOf(newsArticlesList.get(position).getTitle())));

    }

    @Override
    public int getItemCount() {
        return this.newsArticlesList.size();
    }


    public class NewsListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.news_article_image_view)
        ImageView newsArticleImage;

        @BindView(R.id.news_article_url)
        TextView newsArticleUrl;

        @BindView(R.id.news_headlines_tv)
        TextView newheadlineTv;

        public NewsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
