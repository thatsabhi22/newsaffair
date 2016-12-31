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
import com.theleafapps.pro.newsaffair.models.Article;
import com.theleafapps.pro.newsaffair.ui.NewsListCardsActivity;
import com.theleafapps.pro.newsaffair.utils.MySingleton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aviator on 26/04/16.
 */
public class NewsListViewRecyclerAdapter extends
        RecyclerView.Adapter<NewsListViewRecyclerAdapter.NewsListViewHolder>{

    Context mContext;
    List<Article.ArticlesBean> newsArticlesList;
    private ImageLoader mImageLoader;

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

                Intent intent = new Intent(mContext, NewsListCardsActivity.class);
                intent.putExtra("newsArticleUrl",newsUrlTv.getText());

                mContext.startActivity(intent);

            }
        });

        NewsListViewHolder rcv = new NewsListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {

        mImageLoader = MySingleton.getInstance(mContext).getImageLoader();
        holder.newsArticleImage.setImageUrl(newsArticlesList.get(position).getUrlToImage(),mImageLoader);
        holder.newsArticleUrl.setText(String.valueOf(newsArticlesList.get(position).getUrl()));
        holder.newheadlineTv.setText(String.valueOf(newsArticlesList.get(position).getTitle()));

    }

    @Override
    public int getItemCount() {
        return this.newsArticlesList.size();
    }


    public class NewsListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.news_article_image_view)
        NetworkImageView newsArticleImage;

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
