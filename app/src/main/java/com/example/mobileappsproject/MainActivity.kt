package com.example.mobileappsproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load data from strings.xml
        postToList()

        // Set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(titlesList, descList, imagesList)
    }

    private fun postToList() {
        // Retrieve string arrays from strings.xml
        val foodTitles = resources.getStringArray(R.array.food)
        val foodDescriptions = resources.getStringArray(R.array.decription)

        // Add data to the lists
        for (i in foodTitles.indices) {
            addToList(
                title = foodTitles[i],
                description = foodDescriptions[i],
                image = getImageResource(i)
            )
        }
    }

    private fun addToList(title: String, description: String, image: Int) {
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

    private fun getImageResource(index: Int): Int {
        // Map images to their corresponding index
        return when (index) {
            0 -> R.drawable.food1
            1 -> R.drawable.food2
            2 -> R.drawable.food3
            3 -> R.drawable.food4
            else -> R.mipmap.ic_launcher_round // Default image
        }
    }
}
