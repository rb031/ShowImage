package ir.reza.showimaes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ir.reza.showimaes.Adapters.ImageAdapter;
import ir.reza.showimaes.Adapters.OnLoadMoreListener;
import ir.reza.showimaes.Constant.Constant;
import ir.reza.showimaes.Model.Image;
import ir.reza.showimaes.Network.ProductsRequest;
import ir.reza.showimaes.Utils.AppSingleton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcShowImage;

    private ImageAdapter adapter;
    private ArrayList<Image> images = new ArrayList<>();
    private int page = 1;
    private int loadingItemIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    private void initialize() {
        findViews();
        setTypeFaces();
        setupFragment();
        setupListeners();


        getPosts();
    }


    private void findViews() {

        rcShowImage=(RecyclerView)findViewById(R.id.rcShowImage);


        rcShowImage.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ImageAdapter(this, images, rcShowImage);
        rcShowImage.setAdapter(adapter);
    }

    private void setTypeFaces() {
    }
    private void setupFragment() {

    }
    private void setupListeners() {
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getPosts();
                images.add(null);
                loadingItemIndex = images.size() - 1;
                adapter.notifyItemInserted(loadingItemIndex);
            }
        });
    }
    protected void getPosts() {


        final String url = Constant.BASE_URL + "image/get";
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put(Constant.PAGE, page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response.Listener<ArrayList<Image>> listener = new Response.Listener<ArrayList<Image>>() {
            @Override
            public void onResponse(ArrayList<Image> response) {
                if (page != 1) {
                    images.remove(loadingItemIndex);
                    adapter.notifyItemRemoved(images.size());

                }
                if (response != null) {
                    images.addAll(response);
                    adapter.notifyDataSetChanged();
                    rcShowImage.setVisibility(View.VISIBLE);
                    adapter.setLoading(false);

                } else {
                    if (page == 1) {
                        rcShowImage.setVisibility(View.GONE);

                    }

                }

                Toast.makeText(MainActivity.this,response+"",Toast.LENGTH_LONG).show();

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
            }
        };

        ProductsRequest request = new ProductsRequest(url, jsonData, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("Content-Type", "application/json; charset=utf-8");
                return hashMap;
            }
        };

        AppSingleton.getInstance(MainActivity.this).addToRequestQueue(request);

    }
}
