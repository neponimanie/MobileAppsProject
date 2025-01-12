package com.example.mobileappsproject

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = RecyclerAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Load data and post to ViewModel
        val recipes = getRecipes()
        viewModel.setRecipes(recipes)

        // Observe StateFlow and update RecyclerView
        lifecycleScope.launch {
            viewModel.recipesFlow.collect { updatedRecipes ->
                adapter.updateData(updatedRecipes)
            }
        }

        // Set up SearchView
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchRecipes(it) }
                return true
            }
        })
    }

    private fun getRecipes(): List<Recipe> {
        val titles = resources.getStringArray(R.array.food)
        val descriptions = resources.getStringArray(R.array.decription)
        val images = listOf(R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4)

        return titles.indices.map { index ->
            Recipe(titles[index], descriptions[index], images[index % images.size])
        }
    }
}
