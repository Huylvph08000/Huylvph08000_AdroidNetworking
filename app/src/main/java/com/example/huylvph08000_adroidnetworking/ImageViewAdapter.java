package com.example.huylvph08000_adroidnetworking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huylvph08000_adroidnetworking.model.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Image> photoArrayList;
    AdapterListener adapterListener;

    public ImageViewAdapter(Context context, ArrayList<Image> photoArrayList, AdapterListener adapterListener) {
        this.context = context;
        this.photoArrayList = photoArrayList;
        this.adapterListener = adapterListener;
    }
    public interface AdapterListener{
        void OnClick(int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Image image = photoArrayList.get(position);
        holder.tvView.setText(image.getViews());
        Picasso.with(context).load(image.getUrlL()).into(holder.imgPhoto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnClick(position);
            }
        });

    }
    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imageview);
            tvView = itemView.findViewById(R.id.textviewView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
