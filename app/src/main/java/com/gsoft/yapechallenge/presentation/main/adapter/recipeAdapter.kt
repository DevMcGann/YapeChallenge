package com.gsoft.yapechallenge.presentation.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.databinding.RecipeRowItemBinding
import javax.inject.Inject

class RecipeAdapter @Inject constructor()
    : ListAdapter<Recipe, RecipeAdapter.ViewHolder>(Comparator){

    private var listener: ((item: Recipe) -> Unit)? = null

    fun setOnItemClickListener(listener: (item: Recipe) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = RecipeRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(
            getItem(position),
            listener
        )

    class ViewHolder(
        private val itemBinding: RecipeRowItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            recipe: Recipe,
            listener: ((item: Recipe) -> Unit)?
        ) {
            itemBinding.tTitle.text = recipe.title
            Glide.with(context).load(recipe.image)
                .fitCenter().into(itemBinding.recipeImage)
            itemBinding.root.setOnClickListener { listener?.invoke(recipe) }
        }
    }

    object Comparator : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem == newItem
    }
}