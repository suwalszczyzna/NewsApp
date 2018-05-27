package pl.com.suwala.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {
    private static final String USGS_REQUEST_URL =
            "https://content.guardianapis.com/search?";
    private ArticleAdapter adapter;
    ListView articleListView;
    TextView noArticleText;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleListView = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress_bar);
        noArticleText = findViewById(R.id.empty_view);
        adapter = new ArticleAdapter(this, new ArrayList<Article>());
        articleListView.setAdapter(adapter);
        articleListView.setEmptyView(noArticleText);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            noArticleText.setText(R.string.no_internet);
        }
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Article currentArticle = adapter.getItem(position);
                Uri articleUri = Uri.parse(currentArticle.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);
                startActivity(websiteIntent);
            }
        });
    }
    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        String TAG = "URI";
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean books = sharedPrefs.getBoolean("books",false);
        Boolean business = sharedPrefs.getBoolean("business",false);
        Boolean fashion = sharedPrefs.getBoolean("fashion",false);
        Boolean games = sharedPrefs.getBoolean("games",false);
        Boolean lifeandstyle = sharedPrefs.getBoolean("lifeandstyle",false);
        Boolean money = sharedPrefs.getBoolean("money",false);
        Boolean science = sharedPrefs.getBoolean("science",false);
        Boolean sport = sharedPrefs.getBoolean("sport",false);
        Boolean technology = sharedPrefs.getBoolean("technology",false);
        Boolean travel = sharedPrefs.getBoolean("travel",false);

        String numArticles = sharedPrefs.getString(
                getString(R.string.settings_num_articles_key),
                getString(R.string.settings_num_articles_default));

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);

        Uri.Builder uriBuilder = baseUri.buildUpon();

        String sections = "";
        if(books){
            sections += "books|";
        }
        if(business){
            sections += "business|";
        }
        if(fashion){
            sections += "fashion|";
        }
        if(games){
            sections += "games|";
        }
        if(lifeandstyle){
            sections += "lifeandstyle|";
        }
        if(money){
            sections += "money|";
        }
        if(science){
            sections += "science|";
        }
        if(sport){
            sections += "sport|";
        }
        if(technology){
            sections += "technology|";
        }
        if(travel){
            sections += "travel|";
        }

        if(sections.endsWith("|")){
            sections = sections.substring(0, sections.length()-1) + "";
        }
        uriBuilder.appendQueryParameter("section", sections);
        uriBuilder.appendQueryParameter("page-size", numArticles);
        uriBuilder.appendQueryParameter("show-fields", "thumbnail,byline");
        uriBuilder.appendQueryParameter("api-key", "test");

        Log.d(TAG, "uriBuilder: " + uriBuilder.toString());
        return new ArticleLoader(this, uriBuilder.toString());
    }



    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        noArticleText.setText(R.string.no_article);
        // Clear the adapter of previous article data
        adapter.clear();

        // If there is a valid list of {@link article}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (articles != null && !articles.isEmpty()) {
            adapter.addAll(articles);
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        adapter.clear();
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
