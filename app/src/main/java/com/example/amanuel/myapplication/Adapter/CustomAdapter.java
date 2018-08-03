package com.example.amanuel.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amanuel.myapplication.R;
import com.example.amanuel.myapplication.model.Cat;
import com.example.amanuel.myapplication.model.DataResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   private List<Cat> cats;
   private Context context;
   public CustomAdapter(List<Cat> cats, Context context){
       this.cats = cats;
       this.context = context;
   }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.custom_row,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cat cat = cats.get(position);
        holder.title.setText( cat.getTitle());
        holder.description.setText(cat.getDescription());
        holder.date.setText(cat.getTimestamp());
        Picasso.get().load(cat.getImage_url()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return cats.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        TextView description;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.myTitle);
            date = itemView.findViewById(R.id.myDate);
            description = itemView.findViewById(R.id.myDescription);
            imageView = itemView.findViewById(R.id.myImage);
        }
    }
    public void addMoreCats(List<Cat> cats){
       this.cats.addAll(cats);
       notifyDataSetChanged();
    }
}
