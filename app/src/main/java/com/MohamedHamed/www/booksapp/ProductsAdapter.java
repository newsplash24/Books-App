package com.MohamedHamed.www.booksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import data.Product;

/**
 * A template from stackoverflow
 * Modified by Mohamed Hamed on 2/27/2017.
 */
public class ProductsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Product> myItems;

    public ProductsAdapter(Context context, ArrayList<Product> myItems) {
        this.context = context;
        this.myItems = myItems;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public Object getItem(int position) {
        return myItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0; //'0' is the default return value, you may change this if necessary
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        Product currentProduct = (Product) getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.products_item, null);
            holder = new Holder();

            holder.title = (TextView) convertView.findViewById(R.id.product_title);
            holder.description = (TextView) convertView.findViewById(R.id.product_description);
            holder.cover = (ImageView) convertView.findViewById(R.id.product_image);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.title.setText(currentProduct.getProduct_title());
        holder.description.setText(currentProduct.getProduct_description());


        Picasso.with(context).load(currentProduct.getProduct_image()).
                placeholder(R.drawable.thumb).into(holder.cover);

        return convertView;
    }

    static class Holder {
        TextView title, description;
        ImageView cover;
    }
}
