package com.example.gcosma.dailyquotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.nfc.tech.TagTechnology;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcosma.dailyquotes.models.CategoryModel;
import com.example.gcosma.dailyquotes.models.QuoteModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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

public class CategoriesActivity extends AppCompatActivity {

    public ListView lvCategories;
    public List<CategoryModel> categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        /* sets the actionBar */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarDarkColor)));
        /* sets the actionBar ^^^ */

        TextView tvCategories = (TextView)findViewById(R.id.tvCategories);
        lvCategories = (ListView)findViewById(R.id.lvCategories);





        /* START categoriesList init
        CategoryModel catMod = new CategoryModel();
        catMod.setName("Default name");
        catMod.setBackgroundImage(" ");

        categoriesList.add(catMod);
        categoriesList.add(catMod);
        categoriesList.add(catMod);
         END categoriesList init */

        new JSONTaskCategory().execute("http://quotes.rest/qod/categories.json");

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent newActivity = new Intent(CategoriesActivity.this, SpecificQuoteActivity.class);
                        String categoryName = "inspire";
                        Bundle bundle = new Bundle(); // for sending addition data to next activity
                        bundle.putString("category", categoryName);
                        newActivity.putExtras(bundle);
                        startActivity(newActivity);
                        break;
                    case 1:
                        newActivity = new Intent(CategoriesActivity.this, SpecificQuoteActivity.class);
                        categoryName = "management";
                        bundle = new Bundle(); // for sending addition data to next activity
                        bundle.putString("category", categoryName);
                        newActivity.putExtras(bundle);
                        startActivity(newActivity);
                        break;
                    case 2:
                        newActivity = new Intent(CategoriesActivity.this, SpecificQuoteActivity.class);
                        categoryName = "sports";
                        bundle = new Bundle(); // for sending addition data to next activity
                        bundle.putString("category", categoryName);
                        newActivity.putExtras(bundle);
                        startActivity(newActivity);
                        break;
                    case 3:
                        newActivity = new Intent(CategoriesActivity.this, SpecificQuoteActivity.class);
                        categoryName = "life";
                        bundle = new Bundle(); // for sending addition data to next activity
                        bundle.putString("category", categoryName);
                        newActivity.putExtras(bundle);
                        startActivity(newActivity);
                        break;
                    case 4:
                        newActivity = new Intent(CategoriesActivity.this, SpecificQuoteActivity.class);
                        categoryName = "funny";
                        bundle = new Bundle(); // for sending addition data to next activity
                        bundle.putString("category", categoryName);
                        newActivity.putExtras(bundle);
                        startActivity(newActivity);
                        break;
                    case 5:
                        newActivity = new Intent(CategoriesActivity.this, SpecificQuoteActivity.class);
                        categoryName = "love";
                        bundle = new Bundle(); // for sending addition data to next activity
                        bundle.putString("category", categoryName);
                        newActivity.putExtras(bundle);
                        startActivity(newActivity);
                        break;
                    case 6:
                        newActivity = new Intent(CategoriesActivity.this, SpecificQuoteActivity.class);
                        categoryName = "art";
                        bundle = new Bundle(); // for sending addition data to next activity
                        bundle.putString("category", categoryName);
                        newActivity.putExtras(bundle);
                        startActivity(newActivity);
                        break;
                    case 7:
                        newActivity = new Intent(CategoriesActivity.this, SpecificQuoteActivity.class);
                        categoryName = "students";
                        bundle = new Bundle(); // for sending addition data to next activity
                        bundle.putString("category", categoryName);
                        newActivity.putExtras(bundle);
                        startActivity(newActivity);
                        break;
                }

            }
            @SuppressWarnings("unused")
            public void onClick(View v){
            };
        });

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

    public class JSONTaskCategory extends AsyncTask<String, String, List<CategoryModel>> {

        //private CategoryModel categoryModel;

        @Override
        protected List<CategoryModel> doInBackground(String... params) {
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
                JSONObject contentCategories = parentContents.getJSONObject("categories");


                categoriesList = new ArrayList<>();

                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setName(contentCategories.getString("inspire"));
                categoryModel.setBackgroundImage(" ");
                categoriesList.add(categoryModel);

                categoryModel = new CategoryModel();
                categoryModel.setName(contentCategories.getString("management"));
                categoryModel.setBackgroundImage(" ");
                categoriesList.add(categoryModel);

                categoryModel = new CategoryModel();
                categoryModel.setName(contentCategories.getString("sports"));
                categoryModel.setBackgroundImage(" ");
                categoriesList.add(categoryModel);

                categoryModel = new CategoryModel();
                categoryModel.setName(contentCategories.getString("life"));
                categoryModel.setBackgroundImage(" ");
                categoriesList.add(categoryModel);

                categoryModel = new CategoryModel();
                categoryModel.setName(contentCategories.getString("funny"));
                categoryModel.setBackgroundImage(" ");
                categoriesList.add(categoryModel);

                categoryModel = new CategoryModel();
                categoryModel.setName(contentCategories.getString("love"));
                categoryModel.setBackgroundImage(" ");
                categoriesList.add(categoryModel);

                categoryModel = new CategoryModel();
                categoryModel.setName(contentCategories.getString("art"));
                categoryModel.setBackgroundImage(" ");
                categoriesList.add(categoryModel);

                categoryModel = new CategoryModel();
                categoryModel.setName(contentCategories.getString("students"));
                categoryModel.setBackgroundImage(" ");
                categoriesList.add(categoryModel);


                return categoriesList;

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
        protected void onPostExecute(List<CategoryModel> result) {
            super.onPostExecute(result);
            QuoteCategoryAdapter adapter = new QuoteCategoryAdapter(getApplicationContext(), R.layout.row, result);
            lvCategories.setAdapter(adapter);

        }
    }

    public class QuoteCategoryAdapter extends ArrayAdapter {

        private List<CategoryModel> categoriesList;
        private int resource;
        private LayoutInflater inflater;
        public QuoteCategoryAdapter(Context context, int resource, List<CategoryModel> categories) {
            super(context, resource, categories);
            categoriesList = categories;
            this.resource = resource;
            inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            ImageView ivCategoryIcon;
            TextView tvCategoryText;

            ivCategoryIcon = (ImageView)convertView.findViewById(R.id.ivCategoryIcon);
            tvCategoryText = (TextView)convertView.findViewById(R.id.tvCategoryText);

            tvCategoryText.setText(categoriesList.get(position).getName());

            switch (position)
            {
                case 0 :
                    ivCategoryIcon.setImageResource(R.drawable.inspire);
                    break;
                case 1 :
                    ivCategoryIcon.setImageResource(R.drawable.management);
                    break;
                case 2 :
                    ivCategoryIcon.setImageResource(R.drawable.sport);
                    break;
                case 3 :
                    ivCategoryIcon.setImageResource(R.drawable.life);
                    break;
                case 4 :
                    ivCategoryIcon.setImageResource(R.drawable.funny);
                    break;
                case 5 :
                    ivCategoryIcon.setImageResource(R.drawable.love);
                    break;
                case 6 :
                    ivCategoryIcon.setImageResource(R.drawable.art);
                    break;
                case 7 :
                    ivCategoryIcon.setImageResource(R.drawable.students);
                    break;
            }


            return convertView;
        }
    }
}


