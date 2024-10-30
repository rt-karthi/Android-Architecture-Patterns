package com.example.samplerestapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new UserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        Log.d("FIrstLast","Name"+user.getFirstName());
        holder.firstnameTextView.setText(user.getFirstName());
        holder.lastnameTextView.setText(user.getLastName());
        holder.emailTextView.setText(user.getEmail());

        Glide.with(context)
                .load(user.getAvatar())
                .into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView firstnameTextView, lastnameTextView, emailTextView;
        ImageView avatarImageView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            firstnameTextView = itemView.findViewById(R.id.text_firstname);
            lastnameTextView = itemView.findViewById(R.id.text_lastname);
            emailTextView = itemView.findViewById(R.id.text_email);
            avatarImageView = itemView.findViewById(R.id.image_avatar);
        }
    }
}