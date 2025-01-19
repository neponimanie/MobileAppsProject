package com.example.mobileappsproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipesViewModel : ViewModel() {

    private val _recipesFlow = MutableStateFlow<List<Recipe>>(emptyList())
    val recipesFlow: StateFlow<List<Recipe>> = _recipesFlow.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var originalRecipes = listOf<Recipe>()

    fun setRecipes(recipes: List<Recipe>) {
        originalRecipes = recipes
        _recipesFlow.value = recipes
    }

    suspend fun searchRecipes(query: String) {
        _isLoading.value = true
        delay(2000) // Simulate loading

        if (query.isEmpty() || query.length < 3) {
            _recipesFlow.value = originalRecipes
        } else {
            _recipesFlow.value = originalRecipes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        _isLoading.value = false
    }
}

data class Recipe(
    val title: String,
    val description: String,
    val imageRes: Int
)
