package com.example.cleanarchitecture.presentation.main.state;

import com.example.cleanarchitecture.domain.model.User;

import java.util.List;

public abstract class UserViewState {
    public static class Loading extends UserViewState {}
    public static class UsersLoaded extends UserViewState {
        private final List<User> users;

        public UsersLoaded(List<User> users) {
            this.users = users;
        }

        public List<User> getUsers() {
            return users;
        }
    }
    public static class Error extends UserViewState {
        private final String errorMessage;

        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
