package com.exedos.sajinigarments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridProductAdapter  extends BaseAdapter {

    List<ProductFixedModel> productFixedModelList;

    public GridProductAdapter(List<ProductFixedModel> productFixedModelList) {
        this.productFixedModelList = productFixedModelList;
    }

    @Override
    public int getCount() {
        return productFixedModelList.size();
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
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_fixed_layout, null);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(parent.getContext(),ProductDetailsActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage = view.findViewById(R.id.prodImage);
            TextView productTitle = view.findViewById(R.id.prodTitle);
            TextView productDesc = view.findViewById(R.id.proddesc);
            TextView productCost = view.findViewById(R.id.prodCost);



            productImage.setImageResource(productFixedModelList.get(position).getProductImage());
            productTitle.setText(productFixedModelList.get(position).getProductTitle());
            productDesc.setText(productFixedModelList.get(position).getProductDesc());
            productCost.setText("Rs."+productFixedModelList.get(position).getProductPrice()+"/-");



        }else {

            view = convertView;

        }

        return view;

    }
}
