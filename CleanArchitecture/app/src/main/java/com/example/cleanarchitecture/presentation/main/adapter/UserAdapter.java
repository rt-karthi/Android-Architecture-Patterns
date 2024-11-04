package com.example.cleanarchitecture.presentation.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> userList = new ArrayList<>();

    public void setUsers(List<User> users) {
        userList.clear();
        userList.addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    // ViewHolder class for user items
    static class UserViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarImageView;
        private final TextView nameTextView;
        private final TextView emailTextView;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.image_avatar);
            nameTextView = itemView.findViewById(R.id.text_name);
            emailTextView = itemView.findViewById(R.id.text_email);
        }

        void bind(User user) {
            nameTextView.setText(user.getFirstName() + " " + user.getLastName());
            emailTextView.setText(user.getEmail());

            // Load user avatar with Glide
            Glide.with(avatarImageView.getContext())
                    .load(user.getAvatar())
//                    .placeholder(R.drawable.placeholder_avatar) // Placeholder image
//                    .error(R.drawable.error_avatar) // Error image
                    .into(avatarImageView);
        }
    }
}
