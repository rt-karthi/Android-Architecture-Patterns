package com.example.cleanarchitecture.presentation.main.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.cleanarchitecture.domain.repository.UserRepository;
import com.example.cleanarchitecture.domain.usecase.GetUsersUseCase;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private final UserRepository userRepository;

    public UserViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(userRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}