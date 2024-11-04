package com.example.cleanarchitecture.presentation.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.data.repository.UserRepositoryImpl;
import com.example.cleanarchitecture.domain.model.User;
import com.example.cleanarchitecture.presentation.main.adapter.UserAdapter;
import com.example.cleanarchitecture.presentation.main.state.UserViewState;
import com.example.cleanarchitecture.presentation.main.viewmodel.UserViewModel;
import com.example.cleanarchitecture.presentation.main.viewmodel.UserViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel viewModel;
    private UserAdapter userAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the UserRepository
        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        // Use UserViewModelFactory to instantiate UserViewModel
        viewModel = new ViewModelProvider(this, new UserViewModelFactory(userRepository)).get(UserViewModel.class);

        // Set up RecyclerView and Adapter
        recyclerView = findViewById(R.id.recycler_view);
        userAdapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        progressBar = findViewById(R.id.progress_bar);
        observeViewModel();
        viewModel.fetchUsers();

    }

    private void observeViewModel() {
        viewModel.getViewState().observe(this, new Observer<UserViewState>() {
            @Override
            public void onChanged(UserViewState state) {
                if (state instanceof UserViewState.Loading) {
                    progressBar.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else if (state instanceof UserViewState.UsersLoaded) {
                    progressBar.setVisibility(View.GONE);
                    List<User> users = ((UserViewState.UsersLoaded) state).getUsers();
                    userAdapter.setUsers(users);
                    recyclerView.setVisibility(View.VISIBLE);
                } else if (state instanceof UserViewState.Error) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, ((UserViewState.Error) state).getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}