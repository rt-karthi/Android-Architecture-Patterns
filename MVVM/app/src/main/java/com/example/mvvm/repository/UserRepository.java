package com.example.mvvm.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.model.User;
import com.example.mvvm.model.UserResponse;
import com.example.mvvm.network.ApiService;
import com.example.mvvm.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private final ApiService apiService;

    public UserRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public LiveData<List<User>> getUsers() {
        MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();

        apiService.getUsers(1).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    usersLiveData.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                usersLiveData.setValue(null);
            }
        });

        return usersLiveData;
    }
}
