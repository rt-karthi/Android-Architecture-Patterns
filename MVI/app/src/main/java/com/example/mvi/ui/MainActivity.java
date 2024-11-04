package com.example.mvi.ui;

import android.os.Bundle;
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

import com.example.mvi.R;
import com.example.mvi.viewmodel.UserViewModel;
import com.example.mvi.viewmodel.UserViewState;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private UserAdapter userAdapter;
    private UserViewModel userViewModel;

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

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(userAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Observe the ViewState
        userViewModel.getViewState().observe(this, new Observer<UserViewState>() {
            @Override
            public void onChanged(UserViewState userViewState) {
                if (userViewState instanceof UserViewState.Loading) {
                    Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                } else if (userViewState instanceof UserViewState.UsersLoaded) {
                    userAdapter.updateData(((UserViewState.UsersLoaded) userViewState).getUsers());
                } else if (userViewState instanceof UserViewState.Error) {
                    Toast.makeText(MainActivity.this, "Error loading users", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Send an initial intent to load users
        userViewModel.processIntent(new UserIntent.FetchUsers());
    }
}