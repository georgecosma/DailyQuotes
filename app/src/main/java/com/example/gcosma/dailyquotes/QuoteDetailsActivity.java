package com.example.gcosma.dailyquotes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.example.gcosma.dailyquotes.models.QuoteModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class QuoteDetailsActivity extends AppCompatActivity {

    private TextView tvQuote, tvAuthor, tvCategory, tvTags, tvTitle;
    private ImageView ivBackgroundImage;
    QuoteModel quoteModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_details);

        /* sets the actionBar */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarDarkColor)));
        /* sets the actionBar ^^^ */

        // URL IMAGE DISPLAY
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            .defaultDisplayImageOptions(defaultOptions)
            .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        // END URL IMAGE DISPLAY

        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvQuote = (TextView)findViewById(R.id.tvQuote);
        tvAuthor = (TextView)findViewById(R.id.tvAuthor);
        tvCategory = (TextView)findViewById(R.id.tvCategory);
        //tvTags = (TextView)findViewById(R.id.tvTags);   /* omitted for now */
        ivBackgroundImage = (ImageView)findViewById(R.id.ivBackgroundImage);


        //Get the bundle from CategoriesActivity
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String viewTitle = bundle.getString("viewTitle");
        String quote = bundle.getString("quote");
        String author = bundle.getString("author");
        String category = bundle.getString("category");
        String backgroundImage = bundle.getString("backgroundImage");

        ImageLoader.getInstance().displayImage(backgroundImage, ivBackgroundImage);

        tvTitle.setText(viewTitle);
        tvQuote.setText(" ' " + quote + " ' ");
        tvAuthor.setText(author.toUpperCase());
        tvCategory.setText(category.toUpperCase());





    }

    /* creates the menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_categories:
                Intent intent = new Intent(this, CategoriesActivity.class);
                this.startActivity(intent);
                break;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                this.startActivity(settingsIntent);
                break;
        }
        return true;
    }
    /* creates the menu ^^^ */
    }

