package com.exedos.sajinigarments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CustomizeGridAdapter extends BaseAdapter {
    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public CustomizeGridAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @Override
    public int getCount() {
        return horizontalProductScrollModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;



        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customize_grid_item_layout,null);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Click on anything go to productVone class
                    Intent contactIntent = new Intent(v.getContext(), ProductsVone.class);
                    v.getContext().startActivity(contactIntent);
                    //Click on anything go to productVone class

                  /*  Code for product details on clicking particular product
                    Intent productDetailsIntent = new Intent(parent.getContext(),ProductDetailsActivity.class);
                    productDetailsIntent.putExtra("PRODUCT_ID",horizontalProductScrollModelList.get(position).getProductId());
                    parent.getContext().startActivity(productDetailsIntent);

                      Code for product details on clicking particular product  */
                }
            });

            ImageView productImage = view.findViewById(R.id.product_v1_image);
            TextView productTitle = view.findViewById(R.id.product_v1_title);
            TextView productDescription = view.findViewById(R.id.product_v1_description);
            TextView productPrice = view.findViewById(R.id.product_v1_price);

            Glide.with(parent.getContext()).load(horizontalProductScrollModelList.get(position).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.placeholder2)).into(productImage);
            productTitle.setText(horizontalProductScrollModelList.get(position).getProductTitle());
            productDescription.setText(horizontalProductScrollModelList.get(position).getProductDescription());
            productPrice.setText("Rs."+horizontalProductScrollModelList.get(position).getProductPrice()+"/-");

        }else{

            view = convertView;

        }

        return view;
    }
}


