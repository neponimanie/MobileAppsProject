package com.example.mobileappsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private var recipes: List<Recipe>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)
        val itemDetail: TextView = itemView.findViewById(R.id.tv_description)
        val itemPicture: ImageView = itemView.findViewById(R.id.iv_image)
        private val buttonAction1: Button = itemView.findViewById(R.id.btn_action1)
        private val buttonAction2: Button = itemView.findViewById(R.id.btn_action2)

        init {
            buttonAction1.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You pressed the Share button on item: ${recipes[position].title}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            buttonAction2.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You pressed the Like button on item: ${recipes[position].title}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.itemTitle.text = recipe.title
        holder.itemDetail.text = recipe.description
        holder.itemPicture.setImageResource(recipe.imageRes)
    }

    fun updateData(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}