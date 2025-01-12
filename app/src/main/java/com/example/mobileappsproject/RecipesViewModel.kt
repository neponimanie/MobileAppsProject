package com.example.mobileappsproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipesViewModel : ViewModel() {

    private val _recipesFlow = MutableStateFlow<List<Recipe>>(emptyList())
    val recipesFlow: StateFlow<List<Recipe>> = _recipesFlow.asStateFlow()

    private var originalRecipes = listOf<Recipe>()

    fun setRecipes(recipes: List<Recipe>) {
        originalRecipes = recipes
        _recipesFlow.value = recipes
    }

    fun searchRecipes(query: String) {
        if (query.length < 3) {
            _recipesFlow.value = originalRecipes
        } else {
            val filteredList = originalRecipes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
            if (filteredList != _recipesFlow.value) {
                _recipesFlow.value = filteredList
            }
        }
    }
}

data class Recipe(
    val title: String,
    val description: String,
    val imageRes: Int
)
