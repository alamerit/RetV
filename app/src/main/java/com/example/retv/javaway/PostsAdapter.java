package com.example.retv.javaway;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retv.Constantes;
import com.example.retv.R;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<LinkedTreeMap> posts;
    private Double num = 1.0;

    public PostsAdapter(List<LinkedTreeMap> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    void setNum(double a) {
        this.num = a;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.name.setText((CharSequence) posts.get(position).get(Constantes.name));
        holder.value.setText(redoubl((posts.get(position).get(Constantes.value))));
        holder.nominal.setText(Constantes.nominal + " " + (posts.get(position).get(Constantes.nominal)));
        holder.icon.setText((CharSequence) posts.get(position).get(Constantes.charCode));
        holder.oldNam.setText(Constantes.prev + " " + redoubl((posts.get(position).get(Constantes.previous))));
        holder.tog.setText(redoubl(num / (Double) posts.get(position).get(Constantes.value) / (Double) posts.get(position).get(Constantes.nominal)));
    }


    private String redoubl(Object result) {
        return Double.toString((Double) result);
    }

    @Override
    public int getItemCount() {
        if (posts == null) return 0;
        return posts.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView value;
        TextView name;
        TextView nominal;
        TextView oldNam;
        TextView icon;
        RelativeLayout relativeLayout;
        TextView tog;


        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.postItem_post);
            value = itemView.findViewById(R.id.postItem_site);
            nominal = itemView.findViewById(R.id.indexName);
            oldNam = itemView.findViewById(R.id.oldNam);
            icon = itemView.findViewById(R.id.icon);
            relativeLayout = itemView.findViewById(R.id.relat);
            tog = itemView.findViewById(R.id.tog);
        }
    }
}
