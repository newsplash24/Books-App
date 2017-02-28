package com.MohamedHamed.www.booksapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import data.Product;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private TextView title, description;
    private ImageView image;
    private ArrayList<Product> mProducts;
    private ProductsAdapter mAdapter;
    private ListView mList;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.product_title);
        description = (TextView) findViewById(R.id.product_description);
        image = (ImageView) findViewById(R.id.product_image);
        mList = (ListView) findViewById(R.id.productsList);

        mProducts = new ArrayList<>();

        downloadData();
    }



    private void downloadData(){

        pDialog = new ProgressDialog(this);
        String tag_string_req2= "string_req";

        String url2= this.getString(R.string.books_url);

        pDialog.setMessage("Loading...");
        if(!pDialog.isShowing())
            pDialog.show();
        pDialog.setCanceledOnTouchOutside(false);

        StringRequest stringObjReq2= new StringRequest(Request.Method.GET,
                url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());

                        try {

                            mAdapter = new ProductsAdapter(MainActivity.this , getProductsDataFromJson(response));
                            mList.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        pDialog.dismiss();
//                        Toast.makeText(MainActivity.this, mProducts.get(0).getProduct_image(), Toast.LENGTH_SHORT).show();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());

            }
        });

        Global.getInstance().addToRequestQueue(stringObjReq2, tag_string_req2);
    }

    private  ArrayList<Product> getProductsDataFromJson(String productsJsonStr)
            throws JSONException {

        final String PRODUCTS_LIST = "books";
        final String PRODUCT_TITLE = "title";
        final String PRODUCT_DESCRIPTION = "description";
        final String PRODUCT_IMAGE = "image";


        JSONObject productsJson = new JSONObject(productsJsonStr);
        JSONArray productsArray = productsJson.getJSONArray(PRODUCTS_LIST);



        for (int i = 0; i < productsArray.length(); i++) {


            String title;
            String description;
            String imagePath;

            JSONObject movieDetails = productsArray.getJSONObject(i);

            title = movieDetails.getString(PRODUCT_TITLE);
            description = movieDetails.getString(PRODUCT_DESCRIPTION);
            imagePath = movieDetails.getString(PRODUCT_IMAGE);

            Product product = new Product();
            product.setProduct_title(title);
            product.setProduct_description(description);
            product.setProduct_image(imagePath);

            mProducts.add(product);

        }

        return mProducts;

    }
}
