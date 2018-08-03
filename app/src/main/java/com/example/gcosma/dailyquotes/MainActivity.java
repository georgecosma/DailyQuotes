package com.example.gcosma.dailyquotes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcosma.dailyquotes.models.QuoteModel;

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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    public static QuoteModel quoteModel;
    private TextView tvTodayQuote;
    private Button btnQuoteDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* sets the actionBar */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarDarkColor)));

        /* sets the actionBar ^^^ */

        tvTodayQuote =  (TextView)findViewById(R.id.tvTodayQuote);
        btnQuoteDetails = (Button)findViewById(R.id.btnQuoteDetails);
        Button btnTodayQuotes = (Button)findViewById(R.id.btnTodayQuote);


        btnQuoteDetails.setVisibility(View.INVISIBLE);


        /*    Sets QuoteDetailsActivity for general usage  */
        btnQuoteDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, QuoteDetailsActivity.class);

                String viewTitle = "Quote of the day";
                String quote = quoteModel.getQuote();
                String author = quoteModel.getAuthor();
                String category = quoteModel.getCategory();
                String backgroundImage = quoteModel.getBackground();

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

        btnTodayQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute("http://quotes.rest/qod.json");

            }
        });

        // START initializing quoteModel item
            quoteModel = new QuoteModel();
            quoteModel.setQuote("DefaultQuote");
            quoteModel.setBackground("DefaultBackground");
            quoteModel.setTitle("DefaultTile");
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
            quoteModel.setTags(defaultTags);
            quoteModel.setCategory("DefaultCategory");
            quoteModel.setAuthor("DefaultAuthor");
            quoteModel.setDate("DefaultDate");
        // END initializing quoteModel item


        // NOTIFICATION
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext());
        Bitmap bitmap_image = BitmapFactory.decodeResource(this.getResources(), R.drawable.squarelogo);

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.logo)
                .setTicker("Hearty365")
                .setLargeIcon(bitmap_image)
                .setContentTitle("Daily Quotes")
                .setContentText("Find out the quote of the day!")
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");




        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());


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
                Intent categoriesIntent = new Intent(this, CategoriesActivity.class);
                this.startActivity(categoriesIntent);
                break;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                this.startActivity(settingsIntent);
                break;
        }
        return true;
    }
    /* creates the menu ^^^ */


    public class JSONTask extends AsyncTask<String, String, QuoteModel> {



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


                quoteModel.setQuote(finalQuote.getString("quote"));
                quoteModel.setAuthor(finalQuote.getString("author"));
                quoteModel.setCategory(finalQuote.getString("category"));
                quoteModel.setBackground(finalQuote.getString("background"));

               


                return quoteModel;

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
            tvTodayQuote.setText((" ' " + quoteModel.getQuote() + " ' "));
            btnQuoteDetails.setVisibility(View.VISIBLE);
        }
    }
}
