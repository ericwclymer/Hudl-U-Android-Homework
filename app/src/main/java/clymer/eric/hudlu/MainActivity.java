package clymer.eric.hudlu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import clymer.eric.hudlu.models.MashableNews;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnAdapterInteractionListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager linearLayoutManager;
    private RecyclerView.Adapter recyclerViewAdapter;
    private String[] inputData = new String[]{
            "Eric Clymer",
            "Eric Clymer1",
            "Eric Clymer2",
            "Eric Clymer3",
            "Eric Clymer4",
            "Eric Clymer5",
            "Eric Clymer6",
            "Eric Clymer7",
            "test"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter =
                new RecyclerViewAdapter(this, inputData);
        recyclerView.setAdapter(recyclerViewAdapter);
        fetchLatestNews();
    }

    public void fetchLatestNews() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        final Context thisContext = this;
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast fetchingNewsToast = Toast.makeText(thisContext, "fetching news from mashable", Toast.LENGTH_SHORT);
            fetchingNewsToast.show();
            RequestQueue requestQueue = Volley.newRequestQueue(thisContext);

            StringRequest request = new StringRequest(Request.Method.GET,
                    "http://mashable.com/stories.json?hot_per_page=0&new_per_page=5&rising_per_page=0",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            MashableNews news = new Gson().fromJson(response, MashableNews.class);
                            Log.d("your tag", news.newsItems.get(0).title);
                            // Asynchronous 'success' call back that runs on the main thread
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Errorlog", error.getMessage());
                            Toast errorFetchingNewsToast = Toast.makeText(thisContext, "Error fetching news from mashable", Toast.LENGTH_SHORT);
                            errorFetchingNewsToast.show();
                            // Asynchronous 'error' call back that runs on the main thread
                        }
                    });

            requestQueue.add(request);

        } else {
            Toast noconnectionToast = Toast.makeText(this, "no internet", Toast.LENGTH_SHORT);
            noconnectionToast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("HudlU", "Settings menu item clicked.");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(View view, int position) {
        Snackbar.make(view, inputData[position], Snackbar.LENGTH_SHORT).show();
    }
}
