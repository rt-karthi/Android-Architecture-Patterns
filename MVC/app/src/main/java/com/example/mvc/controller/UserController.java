package com.example.mvc.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.mvc.model.User;
import com.example.mvc.model.UserResponse;
import com.example.mvc.network.ApiService;
import com.example.mvc.network.RetrofitClient;
import com.example.mvc.view.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserController {
    private final Context context;
    private final UserAdapter userAdapter;

    public UserController(Context context, UserAdapter userAdapter) {
        this.context = context;
        this.userAdapter = userAdapter;
    }

    public void fetchUsers() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        apiService.getUsers(1).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body().getData();
                    userAdapter.updateData(users);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
