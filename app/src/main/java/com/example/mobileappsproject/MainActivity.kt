package com.example.mobileappsproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
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
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private val handler = Handler(Looper.getMainLooper())
    private val searchDelay = 300L  // Delay for debouncing (300ms)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        searchView = findViewById(R.id.searchView)
        val logoutButton = findViewById<Button>(R.id.logoutButton)

        // Set up RecyclerView
        adapter = RecyclerAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Show initial loading state for 2 seconds
        displayLoading(true)
        Handler(Looper.getMainLooper()).postDelayed({
            val recipes = getRecipes()
            viewModel.setRecipes(recipes)
            displayLoading(false)
        }, 2000)

        // Observe ViewModel and update RecyclerView
        lifecycleScope.launch {
            viewModel.recipesFlow.collect { updatedRecipes ->
                adapter.updateData(updatedRecipes)
            }
        }

        // Set up SearchView with debouncing
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    displayLoading(true)
                    handler.removeCallbacksAndMessages(null)  // Remove previous delayed task
                    handler.postDelayed({
                        lifecycleScope.launch {
                            viewModel.searchRecipes(it)
                            displayLoading(false)
                        }
                    }, searchDelay)
                }
                return true
            }
        })

        // Set up Logout Button to navigate back to FragmentB
        logoutButton.setOnClickListener {
            val fragmentBIntent = Intent(this, FragmentB::class.java)
            startActivity(fragmentBIntent)
            finish() // Optional: Finish MainActivity if you want to remove it from the stack
        }
    }

    private fun getRecipes(): List<Recipe> {
        val titles = resources.getStringArray(R.array.food)
        val descriptions = resources.getStringArray(R.array.decription)
        val images = listOf(R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4)

        return titles.indices.map { index ->
            Recipe(titles[index], descriptions[index], images[index % images.size])
        }
    }

    private fun displayLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}
