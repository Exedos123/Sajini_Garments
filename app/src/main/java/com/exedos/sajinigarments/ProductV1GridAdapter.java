package com.exedos.sajinigarments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static com.exedos.sajinigarments.MainActivity.showCart;

public class ProductV1GridAdapter extends BaseAdapter {

    List<ProductVoneModel> productV1ModelList;

    public ProductV1GridAdapter(List<ProductVoneModel> productV1ModelList) {
        this.productV1ModelList = productV1ModelList;
    }

    @Override
    public int getCount() {
        return productV1ModelList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_vone_item_layout, null);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   Intent contactIntent = new Intent(v.getContext(), MainActivity.class);
                    MainActivity.showContact = true;
                    v.getContext().startActivity(contactIntent);

                }
            });

        }else {

            view = convertView;

        }


        ImageView productImage = view.findViewById(R.id.product_v1_image);
        TextView productTitle = view.findViewById(R.id.product_v1_title);
        TextView productDesc = view.findViewById(R.id.product_v1_description);
        TextView prodSummary = view.findViewById(R.id.product_v1_summary);
        TextView productCost = view.findViewById(R.id.product_v1_price);
        TextView productStock = view.findViewById(R.id.product_v1_stock);



        Glide.with(parent.getContext()).load(productV1ModelList.get(position).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.placeholder2)).into(productImage);
        productTitle.setText(productV1ModelList.get(position).getProductTitle());
        productDesc.setText(productV1ModelList.get(position).getProductDescription());
        prodSummary.setText(productV1ModelList.get(position).getProductSummary());
        productCost.setText("Rs."+productV1ModelList.get(position).getProductPrice()+"/-");
        productStock.setText(productV1ModelList.get(position).getProductStock());



        return view;
    }

}


