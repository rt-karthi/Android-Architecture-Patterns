package com.example.mvp.view;

import com.example.mvp.model.User;

import java.util.List;

public interface UserView {
    void showUsers(List<User> users);
    void showError(String message);
}
