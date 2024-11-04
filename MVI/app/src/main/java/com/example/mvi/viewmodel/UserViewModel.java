package com.example.mvi.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvi.model.UserResponse;
import com.example.mvi.network.ApiService;
import com.example.mvi.network.RetrofitClient;
import com.example.mvi.ui.UserIntent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<UserViewState> viewState = new MutableLiveData<>();
    private final ApiService apiService;

    public UserViewModel() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public LiveData<UserViewState> getViewState() {
        return viewState;
    }

    public void processIntent(UserIntent intent) {
        if (intent instanceof UserIntent.FetchUsers) {
            fetchUsers();
        }
    }

    private void fetchUsers() {
        viewState.setValue(new UserViewState.Loading());

        apiService.getUsers(1).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    viewState.setValue(new UserViewState.UsersLoaded(response.body().getData()));
                } else {
                    viewState.setValue(new UserViewState.Error("Failed to load users"));
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                viewState.setValue(new UserViewState.Error(t.getMessage()));
            }
        });
    }
}
