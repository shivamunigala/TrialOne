package com.example.trialone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trialone.model.User;

import java.util.List;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    LayoutInflater inflater;
    private List<User> listItems;
    private Context context;

    public MyAdapter(List<User> listItems, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater
                .inflate(R.layout.userlayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User usersList = listItems.get(position);

//        holder.textViewId.setText(usersList.getId());
//        holder.textViewPassword.setText(usersList.getPassword());
        holder.textViewName.setText(usersList.getName());
        holder.textViewUsername.setText(usersList.getUsername());
        holder.textViewRole.setText(usersList.getRole());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

//        TextView textViewId;
//        TextView textViewPassword;
        TextView textViewName;
        TextView textViewUsername;
        TextView textViewRole;

        public ViewHolder(@NonNull View itemView) {
            super((itemView));

//            textViewId = itemView.findViewById(R.id.textViewId);
//            textViewPassword= itemView.findViewById(R.id.textViewPassword);
            textViewName= itemView.findViewById(R.id.textViewName);
            textViewUsername= itemView.findViewById(R.id.textViewUsername);
            textViewRole= itemView.findViewById(R.id.textViewRole);
        }
    }
}
