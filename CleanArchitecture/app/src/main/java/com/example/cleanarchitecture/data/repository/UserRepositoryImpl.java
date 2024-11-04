package com.example.cleanarchitecture.data.repository;


import com.example.cleanarchitecture.data.model.UserResponse;
import com.example.cleanarchitecture.data.network.ApiService;
import com.example.cleanarchitecture.data.network.RetrofitClient;
import com.example.cleanarchitecture.domain.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    private final ApiService apiService;

    public UserRepositoryImpl() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public void getUsers(final UserRepository.OnUsersLoaded callback) {
        apiService.getUsers(1).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onError("Failed to load users");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}