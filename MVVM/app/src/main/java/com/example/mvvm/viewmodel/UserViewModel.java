package com.example.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.model.User;
import com.example.mvvm.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final LiveData<List<User>> usersLiveData;

    public UserViewModel() {
        userRepository = new UserRepository();
        usersLiveData = userRepository.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        return usersLiveData;
    }
}


//without repository class

/*
public class UserViewModel extends ViewModel {
    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    private final ApiService apiService;

    public UserViewModel() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        fetchUsers();
    }

    private void fetchUsers() {
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
    }

    public LiveData<List<User>> getUsers() {
        return usersLiveData;
    }
}
 */
