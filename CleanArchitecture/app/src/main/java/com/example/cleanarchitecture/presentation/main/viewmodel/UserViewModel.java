package com.example.cleanarchitecture.presentation.main.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cleanarchitecture.domain.model.User;
import com.example.cleanarchitecture.domain.repository.UserRepository;
import com.example.cleanarchitecture.domain.usecase.GetUsersUseCase;
import com.example.cleanarchitecture.presentation.main.state.UserViewState;

import java.util.List;

public class UserViewModel extends ViewModel {
    private final GetUsersUseCase getUsersUseCase;
    private final MutableLiveData<UserViewState> viewState = new MutableLiveData<>();

    public UserViewModel(UserRepository userRepository) {
        this.getUsersUseCase = new GetUsersUseCase(userRepository);
    }

    public LiveData<UserViewState> getViewState() {
        return viewState;
    }

    public void fetchUsers() {
        viewState.setValue(new UserViewState.Loading());
        getUsersUseCase.execute(new UserRepository.OnUsersLoaded() {
            @Override
            public void onSuccess(List<User> users) {
                viewState.setValue(new UserViewState.UsersLoaded(users));
            }

            @Override
            public void onError(String errorMessage) {
                viewState.setValue(new UserViewState.Error(errorMessage));
            }
        });
    }
}
