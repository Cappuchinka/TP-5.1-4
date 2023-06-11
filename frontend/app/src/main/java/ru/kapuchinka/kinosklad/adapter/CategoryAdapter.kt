package ru.kapuchinka.kinosklad.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.Category

class CategoryAdapter(_categoryList: MutableList<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList: MutableList<Category> = _categoryList

    class CategoryViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName : TextView = itemView.findViewById(R.id.item_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}