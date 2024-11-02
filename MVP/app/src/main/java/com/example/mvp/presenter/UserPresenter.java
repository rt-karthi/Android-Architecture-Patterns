package com.example.mvp.presenter;


import com.example.mvp.model.User;
import com.example.mvp.model.UserResponse;
import com.example.mvp.network.ApiService;
import com.example.mvp.network.RetrofitClient;
import com.example.mvp.view.UserView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    private UserView userView;

    public UserPresenter(UserView userView) {
        this.userView = userView;
    }

    public void fetchUsers() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        apiService.getUsers(1).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body().getData();
                    userView.showUsers(users);
                } else {
                    userView.showError("Failed to load users");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                userView.showError(t.getMessage());
            }
        });
    }
}
