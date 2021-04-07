package com.exedos.sajinigarments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewHolder, int position) {
        String productId = horizontalProductScrollModelList.get(position).getProductId();
        String resource = horizontalProductScrollModelList.get(position).getProductImage();
        String title = horizontalProductScrollModelList.get(position).getProductTitle();
        String description = horizontalProductScrollModelList.get(position).getProductDescription();
        String price = horizontalProductScrollModelList.get(position).getProductPrice();

        viewHolder.setData(productId,resource,title,description,price);


    }

    @Override
    public int getItemCount() {
        if(horizontalProductScrollModelList.size() > 6 ){
            return 6;
        }
        else{
            return horizontalProductScrollModelList.size();
        }



    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_v1_image);
            productTitle = itemView.findViewById(R.id.product_v1_title);
            productDescription = itemView.findViewById(R.id.product_v1_description);
            productPrice = itemView.findViewById(R.id.product_v1_price);

        }
        private void setData(String productId,String resource,String title,String description, String price) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.placeholder2)).into(productImage);
            productPrice.setText("Rs."+price+"/-");
            productDescription.setText(description);
            productTitle.setText(title);

            //Click on anything go to productVone class

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent contactIntent = new Intent(v.getContext(), ProductsVone.class);
                    v.getContext().startActivity(contactIntent);
                }
            });

            //Click on anything go to productVone class


         /*  Code for product details on clicking particular product

          if(!title.equals("")) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        productDetailsIntent.putExtra("PRODUCT_ID",productId);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }

          Code for product details on clicking particular product    */

        }


    }
}
