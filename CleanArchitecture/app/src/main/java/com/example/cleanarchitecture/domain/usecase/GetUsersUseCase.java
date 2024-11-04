package com.example.cleanarchitecture.domain.usecase;

import com.example.cleanarchitecture.domain.repository.UserRepository;

public class GetUsersUseCase {
    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UserRepository.OnUsersLoaded callback) {
        userRepository.getUsers(callback);
    }
}
