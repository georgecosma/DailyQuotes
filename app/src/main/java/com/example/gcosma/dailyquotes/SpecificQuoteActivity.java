package com.example.gcosma.dailyquotes;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcosma.dailyquotes.models.QuoteModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SpecificQuoteActivity extends AppCompatActivity {

    public static QuoteModel specificQuoteModel;
    public TextView tvSpecificQuote;
    public Button btnSpecificQuoteDetails;
    public String viewTitle;
    public ImageView ivBackgroundImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_quote);

                /* sets the actionBar */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarDarkColor)));
        /* sets the actionBar ^^^ */

        tvSpecificQuote = (TextView)findViewById(R.id.tvSpecificQuote);
        btnSpecificQuoteDetails = (Button)findViewById(R.id.btnSpecificQuoteDetails);
        ivBackgroundImage = (ImageView)findViewById(R.id.ivBackgroundImage);

        // START initializing specificQuoteModel item
        specificQuoteModel = new QuoteModel();
        specificQuoteModel.setQuote("DefaultQuote");
        specificQuoteModel.setBackground("DefaultBackground");
        specificQuoteModel.setTitle("DefaultTile");
        List<String> defaultTags = new List<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public String get(int index) {
                return null;
            }

            @Override
            public String set(int index, String element) {
                return null;
            }

            @Override
            public void add(int index, String element) {

            }

            @Override
            public String remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<String> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<String> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<String> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        defaultTags.add("Default1");
        defaultTags.add("Default2");
        specificQuoteModel.setTags(defaultTags);
        specificQuoteModel.setCategory("DefaultCategory");
        specificQuoteModel.setAuthor("DefaultAuthor");
        specificQuoteModel.setDate("DefaultDate");
        // END initializing specificQuoteModel item

        //Get the bundle from CategoriesActivity
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String category = bundle.getString("category");



        //selecting which category data to extract
        switch (category){
            case "inspire":
                viewTitle = "Inspiring quote of the day";
                new JSONTaskSpecificQuote().execute("http://quotes.rest/qod.json?category=inspire");
                break;
            case "management":
                viewTitle = "Management quote of the day";
                new JSONTaskSpecificQuote().execute("http://quotes.rest/qod.json?category=management");
                break;
            case "sports":
                viewTitle = "Sports quote of the day";
                new JSONTaskSpecificQuote().execute("http://quotes.rest/qod.json?category=sports");
                break;
            case "life":
                viewTitle = "Life's quote of the day";
                new JSONTaskSpecificQuote().execute("http://quotes.rest/qod.json?category=life");
                break;
            case "funny":
                viewTitle = "Funny quote of the day";
                new JSONTaskSpecificQuote().execute("http://quotes.rest/qod.json?category=funny");
                break;
            case "love":
                viewTitle = "Quote of the day for love";
                new JSONTaskSpecificQuote().execute("http://quotes.rest/qod.json?category=love");
                break;
            case "art":
                viewTitle = "Quote of the day for art";
                new JSONTaskSpecificQuote().execute("http://quotes.rest/qod.json?category=art");
                break;
            case "students":
                viewTitle = "Quote of the day for students";
                new JSONTaskSpecificQuote().execute("http://quotes.rest/qod.json?category=students");
                break;
        }

        btnSpecificQuoteDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SpecificQuoteActivity.this, QuoteDetailsActivity.class);

                String quote = specificQuoteModel.getQuote();
                String author = specificQuoteModel.getAuthor();
                String category = specificQuoteModel.getCategory();
                String backgroundImage = specificQuoteModel.getBackground();

                Bundle bundle = new Bundle(); // for sending addition data to next activity
                bundle.putString("viewTitle", viewTitle);
                bundle.putString("quote", quote);
                bundle.putString("author", author);
                bundle.putString("category", category);
                bundle.putString("backgroundImage", backgroundImage);

                intent.putExtras(bundle);
                startActivity(intent);
            }

        });


    } //OnCreate END

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

    public class JSONTaskSpecificQuote extends AsyncTask<String, String, QuoteModel> {



        @Override
        protected QuoteModel doInBackground(String... params) {
            BufferedReader reader = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONObject parentContents = parentObject.getJSONObject("contents");
                JSONArray contentQuotesArray = parentContents.getJSONArray("quotes");
                JSONObject finalQuote = contentQuotesArray.getJSONObject(0);
                JSONArray finalQuoteTags = finalQuote.getJSONArray("tags");


                specificQuoteModel.setQuote(finalQuote.getString("quote"));
                specificQuoteModel.setAuthor(finalQuote.getString("author"));
                specificQuoteModel.setCategory(finalQuote.getString("category"));
                specificQuoteModel.setBackground(finalQuote.getString("background"));

                return specificQuoteModel;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(QuoteModel result) {

            super.onPostExecute(result);
            ImageLoader.getInstance().displayImage(specificQuoteModel.getBackground(), ivBackgroundImage);

            tvSpecificQuote.setText((" ' " + specificQuoteModel.getQuote() + " ' "));


        }
    }

}// SpecificQuotesActivity class END
