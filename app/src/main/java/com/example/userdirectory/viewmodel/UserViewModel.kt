package com.example.userdirectory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdirectory.data.model.User
import com.example.userdirectory.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Users from Room (Single Source of Truth)
    @OptIn(ExperimentalCoroutinesApi::class)
    val users: StateFlow<List<User>> = _searchQuery
        .flatMapLatest { query -> repository.getUsersStream(query) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    init {
        // initial refresh when ViewModel is created
        refreshUsers()
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun refreshUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                repository.refreshUsers()
            } catch (e: Exception) {
                // Offline or network error → keep showing cached data
                _errorMessage.value = "Unable to refresh. Showing cached users."
            } finally {
                _isLoading.value = false
            }
        }
    }
}
