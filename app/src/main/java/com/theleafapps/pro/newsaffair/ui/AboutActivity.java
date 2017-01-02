package com.theleafapps.pro.newsaffair.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.theleafapps.pro.newsaffair.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.mipmap.ic_launcher)
                .setDescription("News Affair\nNews Affair is the one stop destination to popular news channels on the globe.\n" +
                        "\n" +
                        "Catch with the latest stories and happenings around the various news categories that includes world, business, blog, photography, technology, sport, film, comics, science, and local, national and international news.\n")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("Connect with us")
                .addEmail("getintouch@theleafapps.com")
                .addWebsite("http://theleafapps.com/")
                .addFacebook("theleafapps")
                .addTwitter("theleafapps")
                .addPlayStore("com.theleafapps.pro")
                .addInstagram("theleafapps")
                .create();

        setContentView(aboutPage);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
