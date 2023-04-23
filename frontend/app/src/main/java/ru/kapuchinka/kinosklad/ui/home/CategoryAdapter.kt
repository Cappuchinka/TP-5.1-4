package ru.kapuchinka.kinosklad.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R

class CategoryAdapter(_categoryList: MutableList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList: MutableList<CategoryModel> = _categoryList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryItems: View = LayoutInflater.from(parent.context).inflate(R.layout.item_category_layout, parent, false)
        return CategoryViewHolder(categoryItems)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryName.text = categoryList[position].categoryName

        val bundle = Bundle()
        bundle.putString("categoryName", categoryList[position].categoryName)

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_home_to_filmsFragment, bundle)
        }

    }

    class CategoryViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName : TextView = itemView.findViewById(R.id.item_category)
    }
}