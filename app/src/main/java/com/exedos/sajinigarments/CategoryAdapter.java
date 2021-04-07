package com.exedos.sajinigarments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryItemModel> categoryItemModelList;

    private int lastPosition = -1;

    public CategoryAdapter(List<CategoryItemModel> categoryItemModelList) {
        this.categoryItemModelList = categoryItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item_layout,viewGroup,false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder viewHolder, int position) {

      //  int resource = categoryItemModelList.get(position).getCategoryImage();
        String Image = categoryItemModelList.get(position).getIcon();
        String title = categoryItemModelList.get(position).getCategoryName();

        viewHolder.setData(title);
        viewHolder.setCategoryImage(Image);

        if(lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(viewHolder.itemView.getContext(), R.anim.fade_in);
            viewHolder.itemView.setAnimation(animation);
            lastPosition = position;
        }


    }

    @Override
    public int getItemCount() {
        return categoryItemModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView categoryImage;
        private TextView categoryTitle;
        private  CardView cardView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            categoryImage = itemView.findViewById(R.id.category_image);
            categoryTitle = itemView.findViewById(R.id.category_name);
            cardView = itemView.findViewById(R.id.category_card);

        }




        private void setCategoryImage(String imageUrl) {

            if (!imageUrl.equals("null")) {
                Glide.with(itemView.getContext()).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.placeholder2)).into(categoryImage);
            }else{
                categoryImage.setImageResource(R.drawable.sg_logo);
            }
        }

        private void setData(String title){


            // categoryImage.setImageResource(resource);
            categoryTitle.setText(title);
            cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(categoryItemModelList.get(getAdapterPosition()).getBackgroundColor())));

            if(!title.equals("")) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Click on anything go to productVone class
                        Intent contactIntent = new Intent(v.getContext(), ProductsVone.class);
                        v.getContext().startActivity(contactIntent);
                        //Click on anything go to productVone class



                       /*
                        int pos = getAdapterPosition();

                        if (pos == 0) {
                            v.getContext().startActivity(new Intent(v.getContext(), Men.class));
                        } else if (pos == 1) {
                            v.getContext().startActivity(new Intent(v.getContext(), Women.class));
                        } else if (pos == 2) {
                            v.getContext().startActivity(new Intent(v.getContext(), Kids.class));
                        }


                        */

                    }
                });
            }

        }


    }
}
