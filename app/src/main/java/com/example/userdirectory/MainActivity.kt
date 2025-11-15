package com.example.userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.userdirectory.data.database.UserDatabase
import com.example.userdirectory.data.network.ApiClient
import com.example.userdirectory.data.repository.UserRepository
import com.example.userdirectory.ui.UserDirectoryApp
import com.example.userdirectory.ui.theme.UserDirectoryTheme
import com.example.userdirectory.viewmodel.UserViewModel
import com.example.userdirectory.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Build Room + Repository once
        val database = UserDatabase.getInstance(applicationContext)
        val repository = UserRepository(
            api = ApiClient.api,
            userDao = database.userDao()
        )

        setContent {
            UserDirectoryTheme {
                val viewModel: UserViewModel = viewModel(
                    factory = UserViewModelFactory(repository)
                )
                UserDirectoryApp(viewModel)
            }
        }
    }
}
