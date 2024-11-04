package com.example.cleanarchitecture.domain.repository;

import com.example.cleanarchitecture.domain.model.User;

import java.util.List;

public interface UserRepository {
    interface OnUsersLoaded {
        void onSuccess(List<User> users);
        void onError(String errorMessage);
    }

    void getUsers(OnUsersLoaded callback);
}
